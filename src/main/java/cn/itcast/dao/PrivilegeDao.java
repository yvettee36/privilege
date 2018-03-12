package cn.itcast.dao;

import cn.itcast.domain.Privilege;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.*;

/**
 * Created by yvettee on 2017/11/7.
 */
public class PrivilegeDao {
    public void add(Privilege privilege) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into privilege(id,name,description) values(?,?,?)";
            Object params[] = {privilege.getId(),privilege.getName(),privilege.getDescription()};
            runner.update(sql, params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Privilege find(String id){
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from privilege where id=?";
            return (Privilege) runner.query(sql, id, new BeanHandler(Privilege.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List getAll(){
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from privilege";
            return (List) runner.query(sql, new BeanListHandler(Privilege.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
