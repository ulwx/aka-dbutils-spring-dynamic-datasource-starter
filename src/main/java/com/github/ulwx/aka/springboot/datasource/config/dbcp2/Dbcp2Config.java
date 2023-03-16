
package com.github.ulwx.aka.springboot.datasource.config.dbcp2;

import com.github.ulwx.aka.springboot.datasource.PoolConfig;
import com.github.ulwx.aka.springboot.datasource.poolimpl.PoolType;

import java.util.List;
import java.util.Set;


public class Dbcp2Config extends PoolConfig {

    private Boolean defaultAutoCommit;
    private Boolean defaultReadOnly;
    private Integer defaultTransactionIsolation;
    private Integer defaultQueryTimeoutSeconds;
    private String defaultCatalog;
    private String defaultSchema;

    private Boolean cacheState;
    private Boolean lifo;

    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    private Integer initialSize;
    private Long maxWaitMillis;

    private Boolean poolPreparedStatements;
    private Boolean clearStatementPoolOnReturn;
    private Integer maxOpenPreparedStatements;
    private Boolean testOnCreate;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;

    private Long timeBetweenEvictionRunsMillis;
    private Integer numTestsPerEvictionRun;
    private Long minEvictableIdleTimeMillis;
    private Long softMinEvictableIdleTimeMillis;
    private String evictionPolicyClassName;
    private Boolean testWhileIdle;

    private String validationQuery;
    private Integer validationQueryTimeoutSeconds;
    private String connectionFactoryClassName;

    private List<String> connectionInitSqls;
    private Boolean accessToUnderlyingConnectionAllowed;
    private Long maxConnLifetimeMillis;
    private Boolean logExpiredConnections;
    private String jmxName;
    private Boolean autoCommitOnReturn;
    private Boolean rollbackOnReturn;
    private Set<String> disconnectionSqlCodes;
    private Boolean fastFailValidation;
    private String connectionProperties;

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

    public Integer getDefaultTransactionIsolation() {
        return defaultTransactionIsolation;
    }

    public void setDefaultTransactionIsolation(Integer defaultTransactionIsolation) {
        this.defaultTransactionIsolation = defaultTransactionIsolation;
    }

    public Integer getDefaultQueryTimeoutSeconds() {
        return defaultQueryTimeoutSeconds;
    }

    public void setDefaultQueryTimeoutSeconds(Integer defaultQueryTimeoutSeconds) {
        this.defaultQueryTimeoutSeconds = defaultQueryTimeoutSeconds;
    }

    public String getDefaultCatalog() {
        return defaultCatalog;
    }

    public void setDefaultCatalog(String defaultCatalog) {
        this.defaultCatalog = defaultCatalog;
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }

    public void setDefaultSchema(String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    public Boolean getCacheState() {
        return cacheState;
    }

    public void setCacheState(Boolean cacheState) {
        this.cacheState = cacheState;
    }

    public Boolean getLifo() {
        return lifo;
    }

    public void setLifo(Boolean lifo) {
        this.lifo = lifo;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
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

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Boolean getClearStatementPoolOnReturn() {
        return clearStatementPoolOnReturn;
    }

    public void setClearStatementPoolOnReturn(Boolean clearStatementPoolOnReturn) {
        this.clearStatementPoolOnReturn = clearStatementPoolOnReturn;
    }

    public Integer getMaxOpenPreparedStatements() {
        return maxOpenPreparedStatements;
    }

    public void setMaxOpenPreparedStatements(Integer maxOpenPreparedStatements) {
        this.maxOpenPreparedStatements = maxOpenPreparedStatements;
    }

    public Boolean getTestOnCreate() {
        return testOnCreate;
    }

    public void setTestOnCreate(Boolean testOnCreate) {
        this.testOnCreate = testOnCreate;
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

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public Long getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    public void setSoftMinEvictableIdleTimeMillis(Long softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public String getEvictionPolicyClassName() {
        return evictionPolicyClassName;
    }

    public void setEvictionPolicyClassName(String evictionPolicyClassName) {
        this.evictionPolicyClassName = evictionPolicyClassName;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }


    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Integer getValidationQueryTimeoutSeconds() {
        return validationQueryTimeoutSeconds;
    }

    public void setValidationQueryTimeoutSeconds(Integer validationQueryTimeoutSeconds) {
        this.validationQueryTimeoutSeconds = validationQueryTimeoutSeconds;
    }

    public String getConnectionFactoryClassName() {
        return connectionFactoryClassName;
    }

    public void setConnectionFactoryClassName(String connectionFactoryClassName) {
        this.connectionFactoryClassName = connectionFactoryClassName;
    }

    public List<String> getConnectionInitSqls() {
        return connectionInitSqls;
    }

    public void setConnectionInitSqls(List<String> connectionInitSqls) {
        this.connectionInitSqls = connectionInitSqls;
    }

    public Boolean getAccessToUnderlyingConnectionAllowed() {
        return accessToUnderlyingConnectionAllowed;
    }

    public void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed) {
        this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
    }

    public Long getMaxConnLifetimeMillis() {
        return maxConnLifetimeMillis;
    }

    public void setMaxConnLifetimeMillis(Long maxConnLifetimeMillis) {
        this.maxConnLifetimeMillis = maxConnLifetimeMillis;
    }

    public Boolean getLogExpiredConnections() {
        return logExpiredConnections;
    }

    public void setLogExpiredConnections(Boolean logExpiredConnections) {
        this.logExpiredConnections = logExpiredConnections;
    }

    public String getJmxName() {
        return jmxName;
    }

    public void setJmxName(String jmxName) {
        this.jmxName = jmxName;
    }

    public Boolean getAutoCommitOnReturn() {
        return autoCommitOnReturn;
    }

    public void setAutoCommitOnReturn(Boolean autoCommitOnReturn) {
        this.autoCommitOnReturn = autoCommitOnReturn;
    }

    public Boolean getRollbackOnReturn() {
        return rollbackOnReturn;
    }

    public void setRollbackOnReturn(Boolean rollbackOnReturn) {
        this.rollbackOnReturn = rollbackOnReturn;
    }

    public Set<String> getDisconnectionSqlCodes() {
        return disconnectionSqlCodes;
    }

    public void setDisconnectionSqlCodes(Set<String> disconnectionSqlCodes) {
        this.disconnectionSqlCodes = disconnectionSqlCodes;
    }

    public Boolean getFastFailValidation() {
        return fastFailValidation;
    }

    public void setFastFailValidation(Boolean fastFailValidation) {
        this.fastFailValidation = fastFailValidation;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    @Override
    public String getPoolType() {
        return PoolType.DBCP2;
    }
}
