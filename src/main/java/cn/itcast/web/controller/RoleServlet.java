package cn.itcast.web.controller;

import cn.itcast.domain.Role;
import cn.itcast.service.SecurityService;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by yvettee on 2017/11/9.
 */
@WebServlet(name = "RoleServlet", urlPatterns = "/servlet/roleServlet")
public class RoleServlet extends HttpServlet {
    SecurityService service = new SecurityService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("getAll".equals(method)) {
            getAll(request, response);
        }
        if ("addUI".equals(method)) {
            addUI(request, response);
        }
        if ("add".equals(method)) {
            add(request, response);
        }
        if ("forUpdateRolePrivilegeUI".equals(method)) {
            forUpdateRolePrivilegeUI(request, response);
        }
        if ("updatePrivilege".equals(method)) {
            updatePrivilege(request, response);
        }
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List list = service.getAllRole();

        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/listRole.jsp").forward(request, response);
    }

    public void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/security/addRole.jsp").forward(request, response);

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Role role = WebUtils.request2Bean(request, Role.class);
            role.setId(UUID.randomUUID().toString());

            service.addRole(role);
            request.setAttribute("message", "添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    //为更新角色的权限授予界面
    public void forUpdateRolePrivilegeUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleId = request.getParameter("id");
        Role role = service.findRole(roleId);

        List list = service.getAllPrivilege();
        request.setAttribute("role", role);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/updateRolePrivilege.jsp").forward(request, response);
    }

    public void updatePrivilege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String roleId = request.getParameter("roleId");
            String pIds[] = request.getParameterValues("pid");

            service.updateRolePrivilege(roleId,pIds);
            request.setAttribute("message", "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "更新失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
