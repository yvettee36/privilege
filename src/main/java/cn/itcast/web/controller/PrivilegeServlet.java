package cn.itcast.web.controller;

import cn.itcast.domain.Privilege;
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
@WebServlet(name = "PrivilegeServlet", urlPatterns = "/servlet/privilegeServlet")
public class PrivilegeServlet extends HttpServlet {
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
        if ("addP".equals(method)) {
            System.out.println("ghj");
            addP(request, response);
        }
        if ("getAll".equals(method)) {

        }

    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List list = service.getAllPrivilege();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/listPrivilege.jsp").forward(request, response);
    }

    //为添加权限提供界面
    public void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/security/addPrivilege.jsp").forward(request, response);
    }

    public void addP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("shdsgbnxm,");
        try {
            Privilege privilege = WebUtils.request2Bean(request, Privilege.class);
            privilege.setId(UUID.randomUUID().toString());
            service.addPrivilege(privilege);
            request.setAttribute("message", "添加成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败！！！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
