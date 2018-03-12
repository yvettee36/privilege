package cn.itcast.domain;

/**
 * Created by yvettee on 2017/11/7.
 */
//权限类
public class Privilege {
    private String id;
    private String name;//添加分类
    private String description;

    //自定义的对象只要涉及到比较，就要重写equals和hashCode方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Privilege privilege = (Privilege) o;

        if (!id.equals(privilege.id)) return false;
        if (!name.equals(privilege.name)) return false;
        return description.equals(privilege.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
