// package com.github.ulwx.aka.dbutils.springboot.datasource;
//
// import javax.sql.DataSource;
//
// public class DataSourceWapper {
//
//     private DataSource originalDataSource;
//     private DataSource dataSource;
//     private String dsName="";
//     private String poolType="";
//     private String seata="";
//
//     public String getPoolType() {
//         return poolType;
//     }
//
//     public void setPoolType(String poolType) {
//         this.poolType = poolType;
//     }
//
//     public String getSeata() {
//         return seata;
//     }
//
//     public void setSeata(String seata) {
//         this.seata = seata;
//     }
//
//     public String getDsName() {
//         return dsName;
//     }
//
//     public void setDsName(String dsName) {
//         this.dsName = dsName;
//     }
//
//     public DataSourceWapper(String dsName,DataSource dataSource) {
//         this.dsName=dsName;
//         this.dataSource = dataSource;
//     }
//
//     public DataSource getDataSource() {
//         return dataSource;
//     }
//
//     public void setDataSource(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }
//
//     public DataSource getOriginalDataSource() {
//         return originalDataSource;
//     }
//
//     public void setOriginalDataSource(DataSource originalDataSource) {
//         this.originalDataSource = originalDataSource;
//     }
//
// }
