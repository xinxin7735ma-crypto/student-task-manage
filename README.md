# student-task-manage

一个自学用的「学习任务管理器」。项目里有两套**独立**实现：控制台 Java 版（数据存 SQL Server）和纯前端网页版（数据存浏览器 localStorage）。

> 这是个人练习项目，不是课程作业。

## 功能

**Java 控制台版**
- 查看任务列表
- 添加任务（任务名称 + 课程分类）
- 修改任务
- 删除任务
- 标记任务完成
- 每次操作实时写入数据库，重启程序数据不丢失

**网页版**
- 侧边栏筛选：任务总览 / 今日任务 / 已完成
- 添加、删除、勾选任务，顶部统计卡片实时更新
- 任务带创建日期与课程分类，数据存 `localStorage`

## 技术栈

- Java（JDK 26）
- JDBC + Microsoft SQL Server 2025 Express
- HTML / CSS / 原生 JavaScript

## 目录结构

```
src/
  Main.java          控制台菜单与交互
  Task.java          任务数据模型（id / title / course / completed）
  TaskManager.java   业务逻辑：四个 CRUD 方法全部走数据库
  DBUtil.java        数据库连接工具，从 db.properties 读配置
sql/
  create_login.sql   建专用数据库账号（模板，不含真实密码）
  TestDb.java        连接自检工具
web/
  index.html         网页版（单文件）
docs/
  Java自学路线图.md   自学计划
lib/
  mssql-jdbc-*.jar   SQL Server JDBC 驱动
db.properties.example  配置模板（复制成 db.properties 后填写）
```

## 快速开始

**1. 建库建表**（在 SSMS 里连到本机 SQL Server，执行）

```sql
IF DB_ID('TaskDb') IS NULL
    CREATE DATABASE TaskDb;
GO
USE TaskDb;
GO
IF OBJECT_ID('dbo.tasks', 'U') IS NULL
CREATE TABLE dbo.tasks (
    id         INT IDENTITY(1,1) PRIMARY KEY,
    title      NVARCHAR(100) NOT NULL,
    course     NVARCHAR(50)  NOT NULL,
    completed  BIT           NOT NULL DEFAULT 0,
    created_at DATETIME2     NOT NULL DEFAULT SYSDATETIME()
);
GO
```

**2. 建专用账号**：把 `sql/create_login.sql` 里的 `<SET_YOUR_OWN_PASSWORD>` 换成自己的密码后执行。账号只拥有 `TaskDb` 的读写权限。

**3. 配置连接信息**：复制 `db.properties.example` 为 `db.properties`，填入 URL、账号、密码。

```properties
db.url=jdbc:sqlserver://127.0.0.1:50888;databaseName=TaskDb;encrypt=true;trustServerCertificate=true
db.user=taskapp
db.password=改成你自己的密码
```

> JDBC 只走 TCP/IP，所以 SQL Server 需要开启 TCP 并指定端口；本项目的开发环境用的是本机回环 `127.0.0.1:50888`，不对外网开放。

**4. 在 IDEA 里运行**：`File → Project Structure → Libraries` 把 `lib/mssql-jdbc-*.jar` 加进模块依赖，然后运行 `Main`。

命令行方式（项目根目录下，Windows 用 `;` 分隔 classpath）：

```bash
javac -encoding UTF-8 -cp "lib/mssql-jdbc-12.10.1.jre11.jar" -d out src/*.java sql/TestDb.java
java -Dstdout.encoding=UTF-8 -cp "out;lib/mssql-jdbc-12.10.1.jre11.jar" Main
java -Dstdout.encoding=UTF-8 -cp "out;lib/mssql-jdbc-12.10.1.jre11.jar" TestDb   # 连接自检
```

网页版直接用浏览器打开 `web/index.html` 即可，不需要服务器。

## 安全说明

- `db.properties` 已加入 `.gitignore`，**密码绝不进 Git**；仓库里只有不含密码的模板
- 所有 SQL 都用 `PreparedStatement` + `?` 占位符，用户输入永远只是数据，避免 SQL 注入
- 数据库账号只授权单个库的读写遵循最小权限原则
- 文件读写显式指定 UTF-8，避免换环境后中文乱码

## 已知限制

- 两套实现的数据源不互通：Java 版存数据库，网页版存浏览器
- 网页版用 `prompt()` 输入，交互比较朴素
- 任务标记完成后暂时不能取消勾选
- 连接信息按本机环境配置，换机器需要重新填 `db.properties`

## 开发进度

- [x] 创建 Task 任务类
- [x] 实现任务列表
- [x] 实现新增任务
- [x] 实现查看任务
- [x] 实现标记完成
- [x] 实现删除任务
- [x] 实现修改任务
- [x] 实现本地文件保存（早期版本，已退役）
- [x] 实现本地文件加载（早期版本，已退役）
- [x] 开发可视化网页界面
- [x] 用 JDBC + SQL Server 替换 txt 存档
- [x] 四个 CRUD 方法全部改为数据库持久化
- [x] 连接配置外置，密码不进仓库
- [x] 修复网页版「今日任务」空白、课程信息丢失
- [ ] 用户登录 / 多用户
- [ ] 两套实现共用同一个数据源
