package cn.itcast.web.controller;

import cn.itcast.domain.Resource;
import cn.itcast.domain.User;
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
@WebServlet(name = "UserServlet", urlPatterns = "/servlet/userServlet")
public class UserServlet extends HttpServlet {
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
        if ("forUpdateUserRoleUI".equals(method)) {
            forUpdateUserRoleUI(request, response);
        }
        if ("updateRole".equals(method)) {
            updateRole(request, response);
        }
        if ("login".equals(method)) {
            login(request, response);
        }
        if ("logout".equals(method)) {
            logout(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/index.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        User user = service.findUser(userName, passWord);
        if (user == null) {
            request.setAttribute("message", "用户名或密码错误");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/index.jsp");
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List list = service.getAllUser();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/listUser.jsp").forward(request, response);
    }

    public void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/security/addUser.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = WebUtils.request2Bean(request, User.class);
            user.setId(UUID.randomUUID().toString());

            service.addUser(user);
            request.setAttribute("message", "添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    //为更新用户角色提供UI界面
    public void forUpdateUserRoleUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        User user = service.findUser(userId);

        List list = service.getAllRole();
        request.setAttribute("user", user);
        request.setAttribute("list", list);

        request.getRequestDispatcher("/security/updateUserRole.jsp").forward(request, response);
    }

    //更新资源的权限
    public void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = request.getParameter("userId");
            String rIds[] = request.getParameterValues("rId");

            service.updateUserRole(userId, rIds);
            request.setAttribute("message", "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "更新失败！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
