package cn.itcast.dao;

import cn.itcast.domain.Privilege;
import cn.itcast.domain.Resource;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by yvettee on 2017/11/7.
 */
public class ResourceDao {
    //添加资源进数据库
    public void add(Resource resource) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into resource(id,uri,description) values(?,?,?)";
            Object params[] = {resource.getId(), resource.getUri(), resource.getDescription()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //根据URI查找
    public Resource find(String uri) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from resource where uri=?";
            Resource resource = runner.query(sql, uri, new BeanHandler<Resource>(Resource.class));

            if (resource == null) {
                return null;
            }
            //得到控制资源的权限
            sql = "select p.* from resource r,privilege p where uri=? and p.id=r.privilege_id";

            Privilege privilege = runner.query(sql, uri, new BeanHandler<Privilege>(Privilege.class));
            resource.setPrivilege(privilege);

            return resource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //根据id查找
    public Resource findById(String id) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from resource where id=?";
            Resource resource = runner.query(sql, id, new BeanHandler<Resource>(Resource.class));

            //得到控制资源的权限
            sql = "select p.* from resource r,privilege p where r.id=? and p.id=r.privilege_id";

            Privilege privilege = runner.query(sql, id, new BeanHandler<Privilege>(Privilege.class));
            resource.setPrivilege(privilege);

            return resource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查看所有资源，得到所有的
    public List getAll() {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from resource";
            List<Resource> list = runner.query(sql, new BeanListHandler<Resource>(Resource.class));

            //在页面里显示List集合的所有资源时，要知道被哪个权限所控制
            for (Resource resource : list) {
                sql = "select p.* from resource r,privilege p where r.id=? and p.id=r.privilege_id";

                Privilege privilege = runner.query(sql, resource.getId(), new BeanHandler<Privilege>(Privilege.class));
                resource.setPrivilege(privilege);
            }

            return list;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //更新资源权限
    public void updatePrivilege(Resource resource, Privilege privilege) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "update resource set privilege_id=? where id=?";
            Object params[] = {privilege.getId(), resource.getId()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
