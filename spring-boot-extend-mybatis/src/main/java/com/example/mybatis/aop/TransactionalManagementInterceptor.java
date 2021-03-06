package com.example.mybatis.aop;

import com.example.common.exception.BaseException;
import com.example.mybatis.annodation.TranscationalManagement;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @ClassName TransactionalManagementInterceptor
 * @Description 事务拦截器
 * @Author mingj
 * @Date 2019/10/27 12:59
 **/
@Aspect
public class TransactionalManagementInterceptor implements ApplicationContextAware {

    private Logger log = LoggerFactory.getLogger(TransactionalManagementInterceptor.class);
    private ApplicationContext applicationContext;

    @Around("@annotation(transactional)")
    public Object interceptor(ProceedingJoinPoint jp, TranscationalManagement transactional) throws Throwable {
        Object proceed = null;
        //获取事务配置
        String[] value = transactional.value();
        //判断是否配置事务管理器名称
        if (value.length==0) {
            //当前无事务管理器的名称
            DataSourceTransactionManager dataSourceTransactionManager = null;
            try {
                //从容器中获取TransactionManager 对象
                dataSourceTransactionManager = applicationContext.getBean(DataSourceTransactionManager.class);
            } catch (NoUniqueBeanDefinitionException e) {
                throw new BaseException("SYS001", "数据源事务管理器配置错误", false);
            }
            //创建事务定义
            TransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
            //开启事务
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            try {
                proceed = jp.proceed(jp.getArgs());
                //事务提交
                dataSourceTransactionManager.commit(transactionStatus);
            } catch (Exception e) {
                //事务回滚
                try {
                    dataSourceTransactionManager.rollback(transactionStatus);
                } catch (Exception ex){
                    throw e;
                }
                throw e;

            }

        } else {
            List<DataSourceTransactionManager> dataSourceTransactionManagerList = new ArrayList<>(value.length);
            for (String configName : value) {
                DataSourceTransactionManager dataSourceTransactionManagerBean = null;
                try {
                    //获取事务管理器
                    dataSourceTransactionManagerBean = (DataSourceTransactionManager) applicationContext.getBean(configName + "TransactionManager");
                } catch (NoSuchBeanDefinitionException e) {
                    throw new BaseException("SYS001", "数据源事务管理器配置错误", false);
                }
                dataSourceTransactionManagerList.add(dataSourceTransactionManagerBean);
            }
            //打开多个数据源事务
            List<TransactionStatus> statuses = new ArrayList<>();
            for (int i = 0; i < dataSourceTransactionManagerList.size(); i++) {
                DataSourceTransactionManager dataSourceTransactionManager = dataSourceTransactionManagerList.get(i);
                TransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
                TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
                statuses.add(transaction);
            }
            try {
                proceed = jp.proceed(jp.getArgs());
                for (int i = dataSourceTransactionManagerList.size() - 1; i > -1; i--) {
                    //顺序提交事务,网络异常可能会造成数据不一致
                    dataSourceTransactionManagerList.get(i).commit(statuses.get(i));
                }

            } catch (Exception e) {
                for (int i = dataSourceTransactionManagerList.size() - 1; i > -1; i--) {
                    //按照提交事务的反顺序回滚事务
                    try {
                        dataSourceTransactionManagerList.get(i).rollback(statuses.get(i));
                    }catch (Exception ex){
                        log.error("反顺序回滚事务异常",e);
                    }

                }
                throw e;
            }
        }
        return proceed;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
