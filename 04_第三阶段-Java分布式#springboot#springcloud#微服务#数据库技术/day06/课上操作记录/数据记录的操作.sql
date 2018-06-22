# ===============================数据的添加===========================
INSERT INTO student (id , NAME , age ) VALUES (NULL ,"zhangsan2" ,10 );

INSERT INTO student ( NAME , age  ) VALUES ("lisi" ,20 );

INSERT INTO student VALUES(NULL,"wangwu",17);


# ===============================数据的删除===========================

DELETE FROM student;

# where 增加条件关键字
DELETE FROM student WHERE id=7;

DELETE FROM student WHERE NAME = 'lisi';

# ===============================数据的更新===========================
# update 表名 set 字段名=值,字段名=值 [where 条件];

# 表中的所有记录年纪都会变成37
UPDATE student SET age = 37 ;


UPDATE student SET age = 55 WHERE id = 9;



# ===============================数据的更新===========================

# 1.  查询所有的字段数据
SELECT * FROM student;

#2. 只查询具体的列（字段）
SELECT NAME FROM student;

SELECT NAME , age FROM student;

#3. 列和表可以起别名  as可以起别名， 也可以省略掉不写
SELECT NAME AS 名字1 , age AS 年龄 FROM student;


#4. 去重复，查询学生里面有多少种年龄
SELECT DISTINCT NAME FROM student;





# 1. 查询学生的所有数据

SELECT * FROM student;

#2. 只看姓名
SELECT NAME  FROM student;

#3. 起别名
SELECT NAME AS 姓名  FROM student;

























