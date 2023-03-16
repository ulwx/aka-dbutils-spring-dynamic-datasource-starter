package com.github.ulwx.aka.springboot.datasource.config.shardingjdbc;

import com.github.ulwx.aka.springboot.datasource.PoolConfig;
import com.github.ulwx.aka.springboot.datasource.poolimpl.PoolType;

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
        return PoolType.ShardingJDBC;
    }
}
