import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类：只负责"给一条连接"，不负责业务。
 * 连接信息放在项目根目录的 db.properties，该文件不进 Git（见 .gitignore）。
 */
public class DBUtil {
    private static final Properties CONFIG = new Properties();

    // 静态代码块：类第一次被用到时，读一次配置文件就够了
    static {
        // IDEA 和命令行运行时，工作目录默认都是项目根目录
        // 用 UTF-8 的 Reader 读，和之前修 FileWriter 编码坑是同一个道理
        try (InputStream in = new FileInputStream("db.properties");
             Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            CONFIG.load(reader);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                    "找不到 db.properties：请复制 db.properties.example 并填入自己的连接信息（" + e.getMessage() + "）");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                CONFIG.getProperty("db.url"),
                CONFIG.getProperty("db.user"),
                CONFIG.getProperty("db.password"));
    }
}
