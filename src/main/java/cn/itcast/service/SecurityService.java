package cn.itcast.service;

import cn.itcast.dao.PrivilegeDao;
import cn.itcast.dao.ResourceDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Privilege;
import cn.itcast.domain.Resource;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yvettee on 2017/11/9.
 */
public class SecurityService {

    private ResourceDao rdao = new ResourceDao();
    private PrivilegeDao pdao = new PrivilegeDao();
    private RoleDao roledao = new RoleDao();
    private UserDao udao = new UserDao();

    /***************************************************************************
     * 提供资源相关的服务
     **************************************************************************/
    public void addResource(Resource r) {
        rdao.add(r);
    }

    public Resource findResource(String uri) {
        return rdao.find(uri);
    }

    public Resource findResourceByID(String id) {
        return rdao.findById(id);
    }

    public List getAllResource() {
        return rdao.getAll();
    }

    // 更新控制资源的权限
    public void updateResourcePrivilege(String resourceid, String privilegeid) {
        Resource r = rdao.findById(resourceid);
        Privilege p = pdao.find(privilegeid);
        rdao.updatePrivilege(r, p);
    }




    /***************************************************************************
     * // 提供权限相关的服务
     **************************************************************************/
    public void addPrivilege(Privilege p) {
        pdao.add(p);
    }

    public Privilege findPrivilege(String id) {
        return pdao.find(id);
    }

    public List getAllPrivilege() {
        return pdao.getAll();
    }




    /***************************************************************************
     * // 提供角色相关的服务
     **************************************************************************/
    public void addRole(Role role) {
        roledao.add(role);
    }

    public Role findRole(String id) {
        return roledao.find(id);
    }

    public List getAllRole() {
        return roledao.getAll();
    }

    //更新角色拥有的权限
    public void updateRolePrivilege(String roleid, String[] privilege_ids) {

        Role role = roledao.find(roleid);
        List list = new ArrayList();
        for (int i = 0; privilege_ids != null && i < privilege_ids.length; i++) {
            Privilege p = pdao.find(privilege_ids[i]);
            list.add(p);
        }
        roledao.updateRolePrivileges(role, list);
    }



    /***************************************************************************
     * 提供用户相关的服务
     /**************************************************************************/

    public void addUser(User user) {
        udao.add(user);
    }

    public User findUser(String id) {
        return udao.find(id);
    }

    public User findUser(String username, String password) {
        return udao.find(username, password);
    }

    public List getAllUser() {
        return udao.getAll();
    }

    //更新用户拥有的角色
    public void updateUserRole(String userid, String[] roleids) {

        User user = udao.find(userid);
        List list = new ArrayList();
        for (int i = 0; roleids != null && i < roleids.length; i++) {
            Role r = roledao.find(roleids[i]);
            list.add(r);
        }
        udao.updateUserRoles(user, list);
    }

    //得到某个用户拥有的所有权限
    public List<Privilege> getUserAllPrivilege(String userid) {

        List allPrivilege = new ArrayList();

        User user = udao.find(userid);
        Set<Role> roles = user.getRoles();
        for (Role r : roles) {
            r = roledao.find(r.getId());
            Set privileges = r.getPrivileges();
            allPrivilege.addAll(privileges);
        }
        return allPrivilege;
    }
}
