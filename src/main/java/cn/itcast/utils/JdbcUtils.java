package cn.itcast.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

/**
 * Created by yvettee on 2017/10/19.
 */
public class JdbcUtils {
    private static DataSource ds = null;

    static {
        ds = new ComboPooledDataSource();
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
