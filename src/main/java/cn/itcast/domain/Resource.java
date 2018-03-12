package cn.itcast.domain;

/**
 * Created by yvettee on 2017/11/7.
 */
public class Resource {
    private String id;
    private String uri;//代表哪个资源  /servletDemo1
    private String description;

    //资源被哪个权限控制
    private Privilege privilege;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
