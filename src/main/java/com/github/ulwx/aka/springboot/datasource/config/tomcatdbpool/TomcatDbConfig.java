package com.github.ulwx.aka.springboot.datasource.config.tomcatdbpool;

import com.github.ulwx.aka.springboot.datasource.PoolConfig;
import com.github.ulwx.aka.springboot.datasource.poolimpl.PoolType;

public class TomcatDbConfig extends PoolConfig {

    private Boolean enable;
    private  String url ;
    private  String driverClassName ;
    private  Boolean defaultAutoCommit ;
    private  Boolean defaultReadOnly ;
    private  String defaultCatalog ;
    private  String connectionProperties;
    private  Integer initialSize ;
    private  Integer maxActive ;
    private  Integer maxIdle ;
    private  Integer minIdle ;
    private  Integer maxWait ;
    private  String validationQuery;
    private  Integer validationQueryTimeout ;
    private  String validatorClassName;
    private  Boolean testOnBorrow ;
    private  Boolean testOnReturn ;
    private  Boolean testWhileIdle ;
    private  Integer timeBetweenEvictionRunsMillis ;
    private  Integer numTestsPerEvictionRun;
    private  Integer minEvictableIdleTimeMillis ;
    private  Boolean accessToUnderlyingConnectionAllowed ;
    private  Boolean removeAbandoned ;
    private  Integer removeAbandonedTimeout ;
    private  Boolean logAbandoned ;
    private  String password;
    private  String username;
    private  Long validationIntegererval ;
    private  Boolean jmxEnabled ;
    private  String initSQL;
    private  Boolean testOnConnect ;
    private  String jdbcIntegererceptors;
    private  Boolean fairQueue ;
    private  Boolean useEquals ;
    private  Integer abandonWhenPercentageFull ;
    private  Long maxAge ;
    private  Integer suspectTimeout ;
    private  Object dataSource ;
    private  String dataSourceJNDI ;
    private  Boolean alternateUsernameAllowed ;
    private  Boolean commitOnReturn ;
    private  Boolean rollbackOnReturn ;
    private  Boolean useDisposableConnectionFacade ;
    private  Boolean logValidationErrors ;
    private  Boolean propagateIntegererruptState ;
    private  Boolean ignoreExceptionOnPreLoad ;
    private  Boolean useStatementFacade ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Boolean getDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    public void setDefaultAutoCommit(Boolean defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }

    public Boolean getDefaultReadOnly() {
        return defaultReadOnly;
    }

    public void setDefaultReadOnly(Boolean defaultReadOnly) {
        this.defaultReadOnly = defaultReadOnly;
    }

    public String getDefaultCatalog() {
        return defaultCatalog;
    }

    public void setDefaultCatalog(String defaultCatalog) {
        this.defaultCatalog = defaultCatalog;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Integer getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(Integer validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public String getValidatorClassName() {
        return validatorClassName;
    }

    public void setValidatorClassName(String validatorClassName) {
        this.validatorClassName = validatorClassName;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public Boolean getAccessToUnderlyingConnectionAllowed() {
        return accessToUnderlyingConnectionAllowed;
    }

    public void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed) {
        this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
    }

    public Boolean getRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned(Boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public Integer getRemoveAbandonedTimeout() {
        return removeAbandonedTimeout;
    }

    public void setRemoveAbandonedTimeout(Integer removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public Boolean getLogAbandoned() {
        return logAbandoned;
    }

    public void setLogAbandoned(Boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getValidationIntegererval() {
        return validationIntegererval;
    }

    public void setValidationIntegererval(Long validationIntegererval) {
        this.validationIntegererval = validationIntegererval;
    }

    public Boolean getJmxEnabled() {
        return jmxEnabled;
    }

    public void setJmxEnabled(Boolean jmxEnabled) {
        this.jmxEnabled = jmxEnabled;
    }

    public String getInitSQL() {
        return initSQL;
    }

    public void setInitSQL(String initSQL) {
        this.initSQL = initSQL;
    }

    public Boolean getTestOnConnect() {
        return testOnConnect;
    }

    public void setTestOnConnect(Boolean testOnConnect) {
        this.testOnConnect = testOnConnect;
    }

    public String getJdbcIntegererceptors() {
        return jdbcIntegererceptors;
    }

    public void setJdbcIntegererceptors(String jdbcIntegererceptors) {
        this.jdbcIntegererceptors = jdbcIntegererceptors;
    }

    public Boolean getFairQueue() {
        return fairQueue;
    }

    public void setFairQueue(Boolean fairQueue) {
        this.fairQueue = fairQueue;
    }

    public Boolean getUseEquals() {
        return useEquals;
    }

    public void setUseEquals(Boolean useEquals) {
        this.useEquals = useEquals;
    }

    public Integer getAbandonWhenPercentageFull() {
        return abandonWhenPercentageFull;
    }

    public void setAbandonWhenPercentageFull(Integer abandonWhenPercentageFull) {
        this.abandonWhenPercentageFull = abandonWhenPercentageFull;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getSuspectTimeout() {
        return suspectTimeout;
    }

    public void setSuspectTimeout(Integer suspectTimeout) {
        this.suspectTimeout = suspectTimeout;
    }

    public Object getDataSource() {
        return dataSource;
    }

    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSourceJNDI() {
        return dataSourceJNDI;
    }

    public void setDataSourceJNDI(String dataSourceJNDI) {
        this.dataSourceJNDI = dataSourceJNDI;
    }

    public Boolean getAlternateUsernameAllowed() {
        return alternateUsernameAllowed;
    }

    public void setAlternateUsernameAllowed(Boolean alternateUsernameAllowed) {
        this.alternateUsernameAllowed = alternateUsernameAllowed;
    }

    public Boolean getCommitOnReturn() {
        return commitOnReturn;
    }

    public void setCommitOnReturn(Boolean commitOnReturn) {
        this.commitOnReturn = commitOnReturn;
    }

    public Boolean getRollbackOnReturn() {
        return rollbackOnReturn;
    }

    public void setRollbackOnReturn(Boolean rollbackOnReturn) {
        this.rollbackOnReturn = rollbackOnReturn;
    }

    public Boolean getUseDisposableConnectionFacade() {
        return useDisposableConnectionFacade;
    }

    public void setUseDisposableConnectionFacade(Boolean useDisposableConnectionFacade) {
        this.useDisposableConnectionFacade = useDisposableConnectionFacade;
    }

    public Boolean getLogValidationErrors() {
        return logValidationErrors;
    }

    public void setLogValidationErrors(Boolean logValidationErrors) {
        this.logValidationErrors = logValidationErrors;
    }

    public Boolean getPropagateIntegererruptState() {
        return propagateIntegererruptState;
    }

    public void setPropagateIntegererruptState(Boolean propagateIntegererruptState) {
        this.propagateIntegererruptState = propagateIntegererruptState;
    }

    public Boolean getIgnoreExceptionOnPreLoad() {
        return ignoreExceptionOnPreLoad;
    }

    public void setIgnoreExceptionOnPreLoad(Boolean ignoreExceptionOnPreLoad) {
        this.ignoreExceptionOnPreLoad = ignoreExceptionOnPreLoad;
    }

    public Boolean getUseStatementFacade() {
        return useStatementFacade;
    }

    public void setUseStatementFacade(Boolean useStatementFacade) {
        this.useStatementFacade = useStatementFacade;
    }

    @Override
    public String getPoolType() {
        return PoolType.TOMCAT_DB_POOL;
    }
}
