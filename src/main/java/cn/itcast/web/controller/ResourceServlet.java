package cn.itcast.web.controller;

import cn.itcast.domain.Resource;
import cn.itcast.service.SecurityService;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yvettee on 2017/11/9.
 */
@WebServlet(name = "ResourceServlet", urlPatterns = "/servlet/resourceServlet")
public class ResourceServlet extends HttpServlet {
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
        if ("forUpdatePrivilegeUI".equals(method)) {
            forUpdatePrivilegeUI(request, response);
        }
        if ("updatePrivilege".equals(method)) {
            updatePrivilege(request, response);
        }
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List list = service.getAllResource();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/listResource.jsp").forward(request, response);
    }

    public void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/security/addResource.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Resource resource = WebUtils.request2Bean(request, Resource.class);
            resource.setId(UUID.randomUUID().toString());

            service.addResource(resource);
            request.setAttribute("message", "添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    //为更新资源权限提供UI界面
    public void forUpdatePrivilegeUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resourceId = request.getParameter("id");
        Resource resource = service.findResourceByID(resourceId);

        //得到系统所有权限
        List list = service.getAllPrivilege();

        request.setAttribute("resource", resource);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/updateResourcePrivilege.jsp").forward(request, response);
    }

    //更新资源的权限
    public void updatePrivilege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String resourceId = request.getParameter("rid");
            String privilegeId = request.getParameter("pid");

            service.updateResourcePrivilege(resourceId, privilegeId);
            request.setAttribute("message", "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "更新失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
