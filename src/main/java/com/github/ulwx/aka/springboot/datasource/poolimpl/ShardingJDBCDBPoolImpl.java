package com.github.ulwx.aka.springboot.datasource.poolimpl;

import com.github.ulwx.aka.springboot.datasource.DataSourceWapper;
import com.github.ulwx.aka.springboot.datasource.PoolConfig;
import com.github.ulwx.aka.springboot.datasource.config.shardingjdbc.ShardingJdbcConfig;
import com.github.ulwx.aka.springboot.datasource.support.reflect.ReflectionUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

public class ShardingJDBCDBPoolImpl extends DBPool {

    public static ShardingJDBCDBPoolImpl instance=new ShardingJDBCDBPoolImpl();
    final static String className="org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory";

    @Override
    public String getType() {
        return PoolType.ShardingJDBC;
    }
    static PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver =
            new PathMatchingResourcePatternResolver();
    @Override
    public DataSource configNewDataSource(DBPoolAttr dbPoolAttr){
        ShardingJdbcConfig config = (ShardingJdbcConfig)dbPoolAttr.getAttributes();
        String configLocation=config.getConfigPath();
        try {
             Resource[] resources=pathMatchingResourcePatternResolver
                     .getResources(configLocation);

            checkResource(resources, configLocation);
            Resource resource = resources[0];

            byte[] bs = toByteArray(resource.getInputStream());

            DataSource dataSource = (DataSource) ReflectionUtil.invoke(
                    Class.forName(className),
                    "createDataSource",
                    byte[].class, bs);
            return dataSource;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    @Override
    public void configProperties(DataSource p, PoolConfig poolConfig, Map<String, Method> getMethods) {

    }
    @Override
    public DataSourceWapper getNewDataSource(DBPoolAttr dbPoolAttr) throws Exception {

        DataSource p=configNewDataSource(dbPoolAttr);
        //后面和seata集成

        DataSourceWapper dataSourceWapper=null;
        DataSource finalDataSource=p;
        dataSourceWapper = new DataSourceWapper(dbPoolAttr.getDsName(), finalDataSource);
        dataSourceWapper.setPoolType(this.getType());
        dataSourceWapper.setSeata(dbPoolAttr.getSeata());

        init(dataSourceWapper);

        return dataSourceWapper;
    }
    public static byte[] toByteArray(InputStream input) throws IOException {

       try( ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
           int nRead;
           byte[] data = new byte[16384];
           while ((nRead = input.read(data, 0, data.length)) != -1) {
               buffer.write(data, 0, nRead);
           }
           return buffer.toByteArray();
       }
    }

   public static void checkResource(Resource[] resources, String location)  throws Exception{
        if (resources == null || resources.length == 0) {
            throw new RuntimeException("错误！没有找到" + location + "配置文件!");
        } else if(resources.length == 1){
            if(!resources[0].exists()){
                throw new Exception("错误！" + location + "配置文件不存在!");
            }
        }else if (resources.length >1) {
            String str = printResources(resources);
            throw new Exception("错误！根据" + location + "找到多个文件![" + str + "]");
        }
    }
    public static String printResources(Resource[] resources) throws Exception{
        String str="";
        for (Resource resource : resources) {
            if (str.isEmpty()) {
                str = resource.getURL().toString();
            } else {
                str = str + " ; " + resource.getURL().toString();
            }
        }
        return str;
    }
    @Override
    public void init(DataSourceWapper dataSource)throws  Exception{
    }


    @Override
    public void close(DataSourceWapper dataSource) throws Exception {
        ReflectionUtil.invoke(dataSource.getOriginalDataSource(), "close");

    }
}
