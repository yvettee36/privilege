package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yvettee on 2017/11/7.
 */
public class User {
    private String id;
    private String userName;
    private String passWord;
    private String description;

    //用户属于哪个角色
    private Set<Role> roles = new HashSet<Role>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
