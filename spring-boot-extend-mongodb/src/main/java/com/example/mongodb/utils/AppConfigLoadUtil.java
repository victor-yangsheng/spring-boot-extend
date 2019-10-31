package com.example.mongodb.utils;

import com.example.common.constant.EnvironmentManager;
import com.example.mongodb.properties.MongoDbProperties;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * @version 1.0
 * @ClassName AppConfigLoadUtil
 * @Description 应用配置加载工具类
 * @Author mingj
 * @Date 2019/4/14 18:04
 **/
public class AppConfigLoadUtil {

    /**
    *@Description 加载mongodb单数据源配置信息
    *@Param [apolloConfig, env]
    *@Author mingj
    *@Date 2019/4/14 18:19
    *@Return com.zto.titans.mongodb.properties.MongodbProperties
    **/
    public static MongoDbProperties loadMongoDbConfig(Environment env) {
        String name = getProperty(env, EnvironmentManager.MONGODB_CONFIG_NAME);
        String authenticationDatabase = getProperty(env, EnvironmentManager.MONGODB_CONFIG_REPOSITORIESAUTHENTICATIONDATABASE);
        String mongodbUri = getProperty(env,EnvironmentManager.MONGODB_CONFIG_MONGODB_URI);
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(authenticationDatabase) || StringUtils.isEmpty(mongodbUri)) {
            return null;
        }
        MongoDbProperties mongoDBProperties = new MongoDbProperties();
        mongoDBProperties.setName(name);
        mongoDBProperties.setRepositoriesAuthenticationDatabase(authenticationDatabase);
        mongoDBProperties.setMongodbUri(mongodbUri);

        String repositoriesEnabled = getProperty(env,EnvironmentManager.MONGODB_CONFIG_REPOSITORIESENABLED);
        if (!StringUtils.isEmpty(repositoriesEnabled)) {
            mongoDBProperties.setRepositoriesEnabled(Boolean.valueOf(repositoriesEnabled));
        } else {
            mongoDBProperties.setRepositoriesEnabled(EnvironmentManager.MONGODB_DEFAULT_CONFIG_REPOSITORIESENABLED);
        }

        String repositoriesLocations = getProperty(env,EnvironmentManager.MONGODB_CONFIG_REPOSITORIESLOCATIONS);
        if (!StringUtils.isEmpty(repositoriesLocations)) {
            mongoDBProperties.setRepositoriesLocations(repositoriesLocations);
        }

        String connectionPreHost = getProperty(env,EnvironmentManager.MONGODB_CONFIG_CONNECTIONSPERHOST);
        if (!StringUtils.isEmpty(connectionPreHost)) {
            mongoDBProperties.setConnectionsPerHost(Integer.parseInt(connectionPreHost));
        } else {
            mongoDBProperties.setConnectionsPerHost(EnvironmentManager.MONGODB_DEFAULT_CONFIG_CONNECTIONSPERHOST);
        }

        String connectionTime = getProperty(env,EnvironmentManager.MONGODB_CONFIG_CONNECTTIMEOUT);
        if (!StringUtils.isEmpty(connectionTime)) {
            mongoDBProperties.setConnectTimeout(Integer.parseInt(connectionTime));
        } else {
            mongoDBProperties.setConnectTimeout(EnvironmentManager.MONGODB_DEFAULT_CONFIG_CONNECTTIMEOUT);
        }

        String maxConnectionIdleTime = getProperty(env,EnvironmentManager.MONGODB_CONFIG_MAXCONNECTIONIDLETIME);
        if (!StringUtils.isEmpty(maxConnectionIdleTime)) {
            mongoDBProperties.setMaxConnectionIdleTime(Integer.parseInt(maxConnectionIdleTime));
        } else {
            mongoDBProperties.setMaxConnectionIdleTime(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MAXCONNECTIONIDLETIME);
        }

        String maxConnectionLifeTime = getProperty(env,EnvironmentManager.MONGODB_CONFIG_MAXCONNECTIONLIFETIME);
        if (!StringUtils.isEmpty(maxConnectionLifeTime)) {
            mongoDBProperties.setMaxConnectionLifeTime(Integer.parseInt(maxConnectionLifeTime));
        } else {
            mongoDBProperties.setMaxConnectionLifeTime(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MAXCONNECTIONLIFETIME);
        }

        String maxnWaitTime = getProperty(env,EnvironmentManager.MONGODB_CONFIG_MAXWAITTIME);
        if (!StringUtils.isEmpty(maxnWaitTime)) {
            mongoDBProperties.setMaxWaitTime(Integer.parseInt(maxnWaitTime));
        } else {
            mongoDBProperties.setMaxWaitTime(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MAXWAITTIME);
        }

        String minConnectionsProHost = getProperty(env,EnvironmentManager.MONGODB_CONFIG_MINCONNECTIONSPERHOST);
        if (!StringUtils.isEmpty(minConnectionsProHost)) {
            mongoDBProperties.setMinConnectionsPerHost(Integer.parseInt(minConnectionsProHost));
        } else {
            mongoDBProperties.setMinConnectionsPerHost(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MINCONNECTIONSPERHOST);
        }

        String serverSlectionTimeout = getProperty(env,EnvironmentManager.MONGODB_CONFIG_SERVERSELECTIONTIMEOUT);
        if (!StringUtils.isEmpty(serverSlectionTimeout)) {
            mongoDBProperties.setServerSelectionTimeout(Integer.parseInt(serverSlectionTimeout));
        } else {
            mongoDBProperties.setServerSelectionTimeout(EnvironmentManager.MONGODB_DEFAULT_CONFIG_SERVERSELECTIONTIMEOUT);
        }

        String socketKeepAlive = getProperty(env,EnvironmentManager.MONGODB_CONFIG_SOCKETKEEPALIVE);
        if (!StringUtils.isEmpty(socketKeepAlive)) {
            mongoDBProperties.setSocketKeepAlive(Boolean.valueOf(socketKeepAlive));
        } else {
            mongoDBProperties.setSocketKeepAlive(EnvironmentManager.MONGODB_DEFAULT_CONFIG_SOCKETKEEPALIVE);
        }

        String socketTimeout = getProperty(env,EnvironmentManager.MONGODB_CONFIG_SOCKETTIMEOUT);
        if (!StringUtils.isEmpty(socketTimeout)) {
            mongoDBProperties.setSocketTimeout(Integer.parseInt(socketTimeout));
        } else {
            mongoDBProperties.setSocketTimeout(EnvironmentManager.MONGODB_DEFAULT_CONFIG_SOCKETTIMEOUT);
        }

        String threadsAllowedToBlockForConnectionMultiplier = getProperty(env,EnvironmentManager.MONGODB_CONFIG_THREADSALLOWEDTOBLOCKFORCONNECTIONMULTIPLIER);
        if (!StringUtils.isEmpty(threadsAllowedToBlockForConnectionMultiplier)) {
            mongoDBProperties.setThreadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(threadsAllowedToBlockForConnectionMultiplier));
        } else {
            mongoDBProperties.setThreadsAllowedToBlockForConnectionMultiplier(EnvironmentManager.MONGODB_DEFAULT_CONFIG_THREADSALLOWEDTOBLOCKFORCONNECTIONMULTIPLIER);
        }
        return mongoDBProperties;

    }

    /**
     *@Description 加载mongodb多数据源配置信息
     *@Param [env]
     *@Author mingj
     *@Date 2019/4/14 18:19
     *@Return com.zto.titans.mongodb.properties.MongodbProperties
     **/
    public static MongoDbProperties loadMongoDbConfig(Environment env, int index) {
        String name = getProperty(env, EnvironmentManager.MONGODB_CONFIGS_NAME, index);
        String authenticationDatabase = getProperty(env, EnvironmentManager.MONGODB_CONFIGS_REPOSITORIESAUTHENTICATIONDATABASE, index);
        String mongodbUri = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_MONGODB_URI, index);
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(authenticationDatabase) || StringUtils.isEmpty(mongodbUri)) {
            return null;
        }
        MongoDbProperties mongoDBProperties = new MongoDbProperties();
        mongoDBProperties.setName(name);
        mongoDBProperties.setRepositoriesAuthenticationDatabase(authenticationDatabase);
        mongoDBProperties.setMongodbUri(mongodbUri);

        String repositoriesEnabled = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_REPOSITORIESENABLED, index);
        if (!StringUtils.isEmpty(repositoriesEnabled)) {
            mongoDBProperties.setRepositoriesEnabled(Boolean.valueOf(repositoriesEnabled));
        } else {
            mongoDBProperties.setRepositoriesEnabled(EnvironmentManager.MONGODB_DEFAULT_CONFIG_REPOSITORIESENABLED);
        }

        String repositoriesLocations = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_REPOSITORIESLOCATIONS, index);
        if (!StringUtils.isEmpty(repositoriesLocations)) {
            mongoDBProperties.setRepositoriesLocations(repositoriesLocations);
        }

        String connectionPreHost = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_CONNECTIONSPERHOST, index);
        if (!StringUtils.isEmpty(connectionPreHost)) {
            mongoDBProperties.setConnectionsPerHost(Integer.parseInt(connectionPreHost));
        } else {
            mongoDBProperties.setConnectionsPerHost(EnvironmentManager.MONGODB_DEFAULT_CONFIG_CONNECTIONSPERHOST);
        }

        String connectionTime = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_CONNECTTIMEOUT, index);
        if (!StringUtils.isEmpty(connectionTime)) {
            mongoDBProperties.setConnectTimeout(Integer.parseInt(connectionTime));
        } else {
            mongoDBProperties.setConnectTimeout(EnvironmentManager.MONGODB_DEFAULT_CONFIG_CONNECTTIMEOUT);
        }

        String maxConnectionIdleTime = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_MAXCONNECTIONIDLETIME, index);
        if (!StringUtils.isEmpty(maxConnectionIdleTime)) {
            mongoDBProperties.setMaxConnectionIdleTime(Integer.parseInt(maxConnectionIdleTime));
        } else {
            mongoDBProperties.setMaxConnectionIdleTime(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MAXCONNECTIONIDLETIME);
        }

        String maxConnectionLifeTime = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_MAXCONNECTIONLIFETIME, index);
        if (!StringUtils.isEmpty(maxConnectionLifeTime)) {
            mongoDBProperties.setMaxConnectionLifeTime(Integer.parseInt(maxConnectionLifeTime));
        } else {
            mongoDBProperties.setMaxConnectionLifeTime(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MAXCONNECTIONLIFETIME);
        }

        String maxnWaitTime = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_MAXWAITTIME, index);
        if (!StringUtils.isEmpty(maxnWaitTime)) {
            mongoDBProperties.setMaxWaitTime(Integer.parseInt(maxnWaitTime));
        } else {
            mongoDBProperties.setMaxWaitTime(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MAXWAITTIME);
        }

        String minConnectionsProHost = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_MINCONNECTIONSPERHOST, index);
        if (!StringUtils.isEmpty(minConnectionsProHost)) {
            mongoDBProperties.setMinConnectionsPerHost(Integer.parseInt(minConnectionsProHost));
        } else {
            mongoDBProperties.setMinConnectionsPerHost(EnvironmentManager.MONGODB_DEFAULT_CONFIG_MINCONNECTIONSPERHOST);
        }

        String serverSlectionTimeout = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_SERVERSELECTIONTIMEOUT, index);
        if (!StringUtils.isEmpty(serverSlectionTimeout)) {
            mongoDBProperties.setServerSelectionTimeout(Integer.parseInt(serverSlectionTimeout));
        } else {
            mongoDBProperties.setServerSelectionTimeout(EnvironmentManager.MONGODB_DEFAULT_CONFIG_SERVERSELECTIONTIMEOUT);
        }

        String socketKeepAlive = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_SOCKETKEEPALIVE, index);
        if (!StringUtils.isEmpty(socketKeepAlive)) {
            mongoDBProperties.setSocketKeepAlive(Boolean.valueOf(socketKeepAlive));
        } else {
            mongoDBProperties.setSocketKeepAlive(EnvironmentManager.MONGODB_DEFAULT_CONFIG_SOCKETKEEPALIVE);
        }

        String socketTimeout = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_SOCKETTIMEOUT, index);
        if (!StringUtils.isEmpty(socketTimeout)) {
            mongoDBProperties.setSocketTimeout(Integer.parseInt(socketTimeout));
        } else {
            mongoDBProperties.setSocketTimeout(EnvironmentManager.MONGODB_DEFAULT_CONFIG_SOCKETTIMEOUT);
        }

        String threadsAllowedToBlockForConnectionMultiplier = getProperty(env,EnvironmentManager.MONGODB_CONFIGS_THREADSALLOWEDTOBLOCKFORCONNECTIONMULTIPLIER, index);
        if (!StringUtils.isEmpty(threadsAllowedToBlockForConnectionMultiplier)) {
            mongoDBProperties.setThreadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(threadsAllowedToBlockForConnectionMultiplier));
        } else {
            mongoDBProperties.setThreadsAllowedToBlockForConnectionMultiplier(EnvironmentManager.MONGODB_DEFAULT_CONFIG_THREADSALLOWEDTOBLOCKFORCONNECTIONMULTIPLIER);
        }
        return mongoDBProperties;

    }

    /**
    *@Description 先从apollo读取配置,如果没有再从Environment中获取配置
    *@Param [apolloConfig, env, key]
    *@Author mingj
    *@Date 2019/4/14 18:08
    *@Return java.lang.String
    **/
    private static String getProperty(Environment env, String key) {
        return env.getProperty(key);
    }

    /**
    *@Description 带有%$占位符的属性读取 先从apollo读取配置,如果没有再从Environment中获取配置
    *@Param [apolloConfig, env, keyPlaceholder, index]
    *@Author mingj
    *@Date 2019/4/14 18:08
    *@Return java.lang.String
    **/
    private static String getProperty(Environment env, String keyPlaceholder, int index) {
        String key = String.format(keyPlaceholder, index);
        return getProperty(env, key);
    }
}
