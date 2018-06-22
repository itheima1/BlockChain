# =============================主键约束 primary key=============================

#1. 建表的时候，顺便指定某一个字段是主键，一般就是id
CREATE TABLE teacher(id INT PRIMARY KEY  , NAME VARCHAR(25));

CREATE TABLE teacher (id INT PRIMARY KEY AUTO_INCREMENT , NAME VARCHAR(25));



#2. 先创建表， 然后再指定某一个字段是主键
CREATE TABLE teacher(id INT  , NAME VARCHAR(25));

ALTER TABLE teacher MODIFY id INT PRIMARY KEY ;

ALTER TABLE teacher MODIFY id INT AUTO_INCREMENT;


#=============================唯一约束  unique=============================
ALTER TABLE product MODIFY NAME VARCHAR(25) UNIQUE;


#===========================非空约束：not null==============================
ALTER TABLE product MODIFY id INT NOT NULL; 