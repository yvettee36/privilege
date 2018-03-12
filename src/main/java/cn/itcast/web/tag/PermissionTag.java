package cn.itcast.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.itcast.domain.Privilege;
import cn.itcast.domain.User;
import cn.itcast.service.SecurityService;

public class PermissionTag extends SimpleTagSupport {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {

        //判断用户拥有权限值中，是否包含value
        PageContext pagecontext = (PageContext) this.getJspContext();
        HttpSession session = pagecontext.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            SecurityService service = new SecurityService();
            List<Privilege> privileges = service.getUserAllPrivilege(user.getId());
            boolean b = false;
            for (Privilege p : privileges) {
                if (p.getName().equals(value)) {
                    b = true;
                    break;
                }
            }
            if (b) {
                this.getJspBody().invoke(null);
            }
        }
    }
}
