import java.sql.*;

/**
 * 连接自检工具：确认 db.properties、JDBC 驱动、数据库三者都通。
 * 在项目根目录执行（Windows 用分号分隔 classpath）：
 *   javac -encoding UTF-8 -cp lib/mssql-jdbc-12.10.1.jre11.jar -d out src/*.java sql/TestDb.java
 *   java -cp "out;lib/mssql-jdbc-12.10.1.jre11.jar" TestDb
 */
public class TestDb {
    public static void main(String[] args) throws Exception {
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, title, course, completed FROM dbo.tasks ORDER BY id")) {
            System.out.println("连接成功：" + conn.getMetaData().getDatabaseProductName());
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2)
                        + " | " + rs.getString(3) + " | " + rs.getBoolean(4));
            }
        }
    }
}
