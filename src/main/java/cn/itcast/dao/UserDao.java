package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.*;

/**
 * Created by yvettee on 2017/11/8.
 */
public class UserDao {
    public void add(User user) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into user(id,username,password,description) values(?,?,?,?)";
            Object params[] = {user.getId(), user.getUserName(), user.getPassWord(), user.getDescription()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public User find(String id) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user where id=?";
            User user = (User) runner.query(sql, id, new BeanHandler(User.class));
            if (user == null) {
                return null;
            }
            //找出用户的角色
            sql = "select * from user_role ur,role r where ur.user_id=? and r.id=ur.role_id";
            List list = (List) runner.query(sql, id, new BeanListHandler(Role.class));
            user.getRoles().addAll(list);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public User find(String username, String password) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user where username=? and password=?";
            Object params[] = {username, password};
            User user = (User) runner.query(sql, params, new BeanHandler(User.class));
            if (user == null) {
                return null;
            }
            //找出用户的角色
            sql = "select * from user_role ur,role r where ur.user_id=? and r.id=ur.role_id";
            List list = (List) runner.query(sql, user.getId(), new BeanListHandler(Role.class));
            user.getRoles().addAll(list);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserRoles(User user, List<Role> roles) {

        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            //先删除用户所有的角色
            String sql = "delete from user_role where user_id=?";
            runner.update(sql, user.getId());

            //再为用户赋予新的角色
            for (Role role : roles) {
                sql = "insert into user_role(user_id,role_id) values(?,?)";
                Object params[] = {user.getId(), role.getId()};
                runner.update(sql, params);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List getAll() {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user";
            List<User> list = (List) runner.query(sql, new BeanListHandler(User.class));
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
