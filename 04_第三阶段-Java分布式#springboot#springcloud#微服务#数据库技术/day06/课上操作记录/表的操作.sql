# =========================表的添加操作=========================
CREATE TABLE product (id INT , NAME VARCHAR(25) , price DOUBLE);


# =========================表的查询操作=========================

# 显示所有表
SHOW TABLES;

# 查看表的结构 -- 表里面有什么字段， 能存什么值
DESC product;

# 显示建表语句
SHOW CREATE TABLE product;
NAME

# =========================表的删除操作=========================

# 删除表 - 连表都没有了，什么都没有了
DROP TABLE stu;

# 删除表中的数据，表还存在。 不释放内存，如果主键自增，那么新增加记录，会继续接上以前的编号
DELETE FROM teacher;

# 删除表中数据，释放内存，如果主键是自增，那么会从1开始
TRUNCATE TABLE teacher;


# =========================表的修改操作=========================

# 修改表名 - 一般很少用。
RENAME TABLE teacher TO student;

# 给表添加一个字段
ALTER TABLE student ADD phone VARCHAR(11);

# 修改字段的名字 phone ---> mobile
ALTER TABLE student CHANGE phone mobile VARCHAR(11);

# 删除某个字段
ALTER TABLE student DROP mobile;

# 给某个字段添加约束
DESC student;
ALTER TABLE student MODIFY address VARCHAR(25) NOT NULL;


