# spring-boot-extend
## 背景
个人开发在项目中因为比较多的使用了多个数据库数据源，以往的做法是在spring-boot的基础上通过新建配置类来实现多数据源的操作，而且在事务操作上不是很友好，并且如果多个项目都使用到的话，每个项目都得写一套，很繁琐，因此自己做了一个可以快速集成多数据源的工具。后面又集成了apollo配置中心，还可以动态部署配置参数，非常简便快捷，同时集成了Dubbo，也可以实现分布式服务。

## spring-boot-extend是什么?
一个能快速继承多数据源的mysql数据库/多数据源mongidb数据库/apollo配置中心/zookeeper注册中心/dubbo服务的工具

## spring-boot-extend有哪些功能？

* 快速`集成Mysql数据源`
    *  直接依赖相关组件，在启动类中添加注解，并且在项目的配置文件/apollo配置中心中配置相关数据源属性即可使用
    *  支持多数据源的事务
* 快速`集成MongoDB数据源`
    *  直接依赖相关组件，在启动类中添加注解，并且在项目的配置文件/apollo配置中心中配置相关数据源属性即可使用
* 集成`apollo配置中心`
    *  详情请查看apollo配置中心文档[apollo](https://github.com/ctripcorp/apollo)
* 引入`Dubbo`功能
    *  使用`Zookeeper`作为注册中心
    *  Dubbo官方文档[Dubbo](http://dubbo.apache.org/)

## 有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流
* Email: mingjay0601@163.com
* QQ: 704714211
* 微博: [@如烟啊我是_](https://weibo.com/2849671144/profile?rightmod=1&wvr=6&mod=personinfo&is_all=1)
* 知乎: [@盛夏](https://www.zhihu.com/people/sheng-xia-63-39-64/activities)

## 感激
感谢以下的项目，在个人学习过程中，给我起到了很大的帮助

* [Dubbo](http://dubbo.apache.org/zh-cn/) 
* [Zookeeper](https://zookeeper.apache.org/)
* [apollo](https://github.com/ctripcorp/apollo)
* [Mybatis](https://github.com/mybatis/mybatis-3)
* [Mybatis-Spring](https://github.com/mybatis/spring)
* [Spring](https://github.com/spring-projects/spring-framework)
* [YunaiV](https://github.com/YunaiV/Blog)

## 关于作者
94年萌新一枚，目前还是在持续学习阶段，欢迎大佬们多多指教。

## 未来计划
* 修改apollo注册中心为zookeeper
* 引入hystrix组件
* 动态数据源变更
* 动态日志变更  **(Done)**
* DUBBO的SPI扩展 **(已扩展实现Filter过滤器)**
* RocketMQ模块 **(TO DO)**
