package cn.itcast.web.filter;

import cn.itcast.domain.Privilege;
import cn.itcast.domain.Resource;
import cn.itcast.domain.User;
import cn.itcast.service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yvettee on 2017/11/13.
 */
//权限许可
public class SecurityFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.检查用户是否登录
        User user = (User) request.getSession().getAttribute("user");

        //2.没登录，登录去
        if (user == null) {
            request.setAttribute("message", "请先登录");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //3.得到用户想访问的资源
        String uri = request.getRequestURI();
        //4.得到访问该资源需要的权限
        SecurityService service = new SecurityService();
        Resource resource = service.findResource(uri);
        if (resource == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Privilege required_privilege = resource.getPrivilege();//得到访问资源需要的权限
        //5.判断用户是否有相应权限
        List<Privilege> privileges = service.getUserAllPrivilege(user.getId());//得到用户所有的权限

        if (!privileges.contains(required_privilege)) {
            //6.没有权限，则暗示用户权限不足，联系管理员
            request.setAttribute("message", "对不起，您没有权限，请联系管理员。");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        //如果有，则放行
        filterChain.doFilter(request, response);
    }

    public void destroy() {

    }
}
