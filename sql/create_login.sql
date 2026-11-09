-- 为任务管理系统创建专用 SQL 登录账号（教学用）
-- 仅授权 TaskDb 一个库的读写，权限最小化
-- 执行前：把 <SET_YOUR_OWN_PASSWORD> 换成你自己的密码，并与 db.properties 保持一致
-- 注意：本脚本提交到 GitHub，永远不要在这里写真实密码
SET NOCOUNT ON;
IF SUSER_ID('taskapp') IS NULL
    CREATE LOGIN taskapp WITH PASSWORD = '<SET_YOUR_OWN_PASSWORD>';
USE TaskDb;
IF USER_ID('taskapp') IS NULL
    CREATE USER taskapp FOR LOGIN taskapp;
ALTER ROLE db_datareader ADD MEMBER taskapp;
ALTER ROLE db_datawriter ADD MEMBER taskapp;
SELECT 'login_and_user_ready' AS result;
