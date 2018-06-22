#================================= 条件查询=============================================

# 查询张三的数据
SELECT * FROM student WHERE id = 11;

# 查询年龄大于35的
SELECT * FROM student WHERE age > 35;

# 查询名字含有张的学生  -- 模糊查询  like 要配合 % 来使用，%在前，表示匹配前面的文字， % 在后，表示匹配后面的文字
# 张%  ---> 张三  张思说啥   %张  ---- 开张   %张%----张三丰
SELECT * FROM student WHERE NAME LIKE '%张%'

# 查询年龄是具体某几个数值中的学生
SELECT * FROM student WHERE age IN(37,30);

# 查询年龄是从30  -- 40 之间的学生
SELECT * FROM student WHERE age BETWEEN 35 AND 40 ;


# or  |  and 关键字 和java的理解是样。  or 就是表示要不满足前面的条件，要不满足后面的条件。 and 前后的条件都必须要满足

# 名字是张三 ，年龄是30
SELECT * FROM student WHERE NAME = '张三' AND age = 30;


# 注意几个关键字  like   between x  and  x

#================================= 排序查询  order by=================================
# 排序默认使用的是升序  ， 降序是DESC   升序是 ASC
SELECT * FROM student ORDER BY age ASC;


#================================= 聚合查询 =================================
# 聚合查询其实就是 查询总数 、平均值、最大值 、 最小值 、总和

SELECT COUNT(*) FROM student;

SELECT AVG(age) FROM student;

SELECT MAX(age) FROM student;

SELECT MIN(age) FROM student;

SELECT SUM(age) FROM student;



#================================= 聚合查询 =================================
# 分组查询

ALTER TABLE student ADD class INT;

# 按班级分组，然后计算每个班级有多少个学生
SELECT class , COUNT(*) FROM student GROUP BY class;

# 按班级分组，然后显示平均年龄大于40的数据  having
# where 是设置操作条件， having 必须要配合group by 使用，它的作用是对分完组了之后，再对组的数据进行过滤。
# having 必须配合group by使用。  必须在group by 的后面
SELECT class , AVG(age) FROM student GROUP BY class HAVING AVG(age) > 40;


#============== 分页查询 适用于数据量很大，一开始只查询几条记录，然后后续再查询剩余的记录=====================
SELECT * FROM student;

SELECT * FROM student LIMIT a , b;  #a : 跳过前面的多少条记录 ， b : 返回多少条记录。

SELECT * FROM student LIMIT 2;   # 如果没有任何数据要跳过，那么可以直接写成  limit 2;

SELECT * FROM student LIMIT 0 , 2;    # 这是第一页的数据，不用跳过任何数据，然后获取2条记录 


SELECT * FROM student LIMIT 2 , 2;  # 这是第二页的数据，要跳过前面的2条记录， 返回2条。

SELECT * FROM student LIMIT 4 , 2;  # 这是第三页的数据 ，要跳过前面的4条记录， 返回2条。








#每页返回3条。

#1  --- 3
SELECT * FROM student LIMIT 0 , 3;


# 第一个3，表示忽略3条数据
SELECT * FROM student LIMIT 3 , 3;





















