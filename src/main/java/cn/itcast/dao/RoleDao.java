package cn.itcast.dao;

import cn.itcast.domain.Privilege;
import cn.itcast.domain.Role;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.*;

/**
 * Created by yvettee on 2017/11/8.
 */
public class RoleDao {

    //添加角色
    public void add(Role role) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into role(id,name,description) values(?,?,?)";
            Object params[] = {role.getId(), role.getName(), role.getDescription()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //根据ID查找角色
    public Role find(String id) {
        try {
            //查找角色的基本信息
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from role where id=?";
            Role role = (Role) runner.query(sql, id, new BeanHandler(Role.class));

            //找出角色所拥有的权限
            sql = "select * from role_privilege rp,privilege p where rp.role_id=? and p.id=rp.privilege_id";
            List list = (List) runner.query(sql, id, new BeanListHandler(Privilege.class));

            role.getPrivileges().addAll(list);
            return role;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List getAll() {
        try {
            //查找角色的基本信息
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from role";
            List<Role> list = (List) runner.query(sql, new BeanListHandler(Role.class));

            for (Role r : list) {
                //找出角色所有的权限
                sql = "select * from role_privilege rp,privilege p where rp.role_id=? and p.id=rp.privilege_id";
                List list1 = (List) runner.query(sql, r.getId(), new BeanListHandler(Privilege.class));
                r.getPrivileges().addAll(list1);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //更新角色的权限
    public void updateRolePrivileges(Role role, List<Privilege> privileges) {
        try {
            //删除角色拥有的权限
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "delete from role_privilege where  role_id=?";
            runner.update(sql, role.getId());

            //为角色赋予新的权限
            for (Privilege p : privileges) {
                sql = "insert into role_privilege(role_id,privilege_id) values(?,?)";
                Object params[] = {role.getId(), p.getId()};
                runner.update(sql, params);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
