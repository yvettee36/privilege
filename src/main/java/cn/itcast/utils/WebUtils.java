package cn.itcast.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yvettee on 2017/11/9.
 */
public class WebUtils {
    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
            T bean = beanClass.newInstance();
            Map map = request.getParameterMap();
            BeanUtils.populate(bean,map);//把map数据往bean里填充
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
