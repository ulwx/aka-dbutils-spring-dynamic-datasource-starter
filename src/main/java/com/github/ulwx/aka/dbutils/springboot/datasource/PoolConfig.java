package com.github.ulwx.aka.dbutils.springboot.datasource;

public abstract class PoolConfig {
    private Boolean enable=null;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    public abstract String getPoolType();
}
