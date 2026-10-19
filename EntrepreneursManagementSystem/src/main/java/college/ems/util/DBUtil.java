package college.ems.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** One shared place for JDBC configuration. Environment variables override db.properties. */
public final class DBUtil {
    private static final Properties CONFIG = new Properties();
    static {
        try (InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in != null) CONFIG.load(in);
        } catch (Exception e) { throw new ExceptionInInitializerError("Could not read db.properties: " + e.getMessage()); }
    }
    private DBUtil() { }
    public static Connection getConnection() throws SQLException {
        String url = setting("EMS_DB_URL", "db.url");
        String user = setting("EMS_DB_USERNAME", "db.username");
        String password = setting("EMS_DB_PASSWORD", "db.password");
        if (url == null || user == null || password == null || "CHANGE_ME".equals(password))
            throw new SQLException("Configure EMS_DB_URL, EMS_DB_USERNAME and EMS_DB_PASSWORD (or src/main/resources/db.properties). See README.");
        return DriverManager.getConnection(url, user, password);
    }
    private static String setting(String env, String key) {
        String value = System.getenv(env);
        if (value == null || value.isBlank()) value = System.getProperty(env);
        if (value == null || value.isBlank()) value = CONFIG.getProperty(key);
        return value == null ? null : value.trim();
    }
}
