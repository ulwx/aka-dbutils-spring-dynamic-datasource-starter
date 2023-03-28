package com.github.ulwx.aka.dbutils.springboot.datasource.config.shardingjdbc;

import com.github.ulwx.aka.dbutils.spring.multids.DSPoolType;
import com.github.ulwx.aka.dbutils.springboot.datasource.PoolConfig;

public class ShardingJdbcConfig extends PoolConfig {

    private String configPath;

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    @Override
    public String getPoolType() {
        return DSPoolType.ShardingJDBC;
    }
}
