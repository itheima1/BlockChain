##视频下载地址

链接：https://pan.baidu.com/s/1Pz9lOMtao0SotLz5zog4Pw 密码：lhcp

# note: demo代码要编号

## 导出模块

一个js文件就是一个模块，模块内部的所有变量，对象，方法对外界都不可见。如果想暴漏出去让别人用，就需要导出模块。语法如下：

```javascript
module.exports = {
    a :a,
    foo
}
```



## crypto模块

NodeJs的crypto模块提供了哈希，加密相关的功能支持。

#### 哈希算法：MD5，SHA1，SHA256，Hmac

> 哈希算法用来对数据进行签名，确定数据的唯一性，以及是否被篡改。由于其过程不可逆，也常常用来对用户密码进行加密。

```javascript
// 计算字符串的hash
let data = '123456'
// const hash = crypto.createHash('md5')
// const hash = crypto.createHash('sha1')
// const hash = crypto.createHash('sha256')
const hash = crypto.createHash('sha512')
hash.update(data)
console.log(hash.digest('hex'));
// 计算文件的hash
const reader = fs.createReadStream('xxx.txt')
reader.on('data', (chunk) =>{
    hash.update(chunk)
})
reader.on('end', ()=>{
    console.log(hash.digest('hex'));
})
```

Hmac是基于key和hash的认证算法。它在上面哈希算法的基础上，再传入一个key。只要key变化，即使输入同样的数据也会得到不同的结果。可以将Hmac理解为随机数增强的哈希算法。

```javascript
const key = 'hehe'
const hmac = crypto.createHmac('md5', key)
hmac.update(data)
console.log(hmac.digest('hex'));
```
#### 对称加密算法：AES

AES是一种常用的对称加密算法，加解密都用同一个密钥。

AES加密：

```javascript
let data = '123456'
let password = 'baby'
const cipher  = crypto.createCipher('aes192', password)
let encrypted = cipher.update(data, 'utf-8', 'hex')
encrypted += cipher.final('hex')
console.log(encrypted);
```

AES解密：

```javascript
const decipher = crypto.createDecipher('aes192', password)
let encrytedData = 'd1b992fe45e85e43f3b73e0716874bc8'
let decrypted = decipher.update(encrytedData, 'hex', 'utf-8')
decrypted += decipher.final('utf-8')
console.log(decrypted);
```



## event模块

大多数 Node.js 核心 API 都采用惯用的异步事件驱动架构，其中某些类型的对象（触发器）会周期性地触发命名事件来调用函数对象（监听器）。例如，`fs.ReadStream`会在文件被打开时触发事件；`stream` 会在数据可读时触发事件。

用法如下：

```javascript
const EventEmitter = require('events')
class MyEmitter extends EventEmitter{}

const myEmitter = new MyEmitter()
// 注册xxx事件
myEmitter.on('aaa', (a)=>{
    console.log('aaa事件被触发，参数：'+a);
})
// 1秒钟之后触发aaa事件，并传递参数
setTimeout(()=>{
    myEmitter.emit('aaa', 'aaaaaa')
},1000)
```

当你想设计一个模块，它具有在某个条件下执行某个操作的功能，那么event模块就派上用场了。例如：你想设计一个用户注册模块，当用户注册成功之后给用户发送一个email。



## 【案例】美女图片爬虫项目

**功能需求：爬取http://www.27270.com/ent/meinvtupian/网站页面，处理GBK乱码的问题；从中提取出图片数据，并下载美女图片。**

> 提示：html的解析需要用到`cheerio`模块，使用**iconv-lite**模块处理gbk编码问题，[iconv-lite Github](https://github.com/ashtuchkin/iconv-lite)

前置知识点学习：

- 学习使用`cheerio`模块进行数据的提取。[cheerio官方文档](https://cheerio.js.org/)

  > 参考官网的api，分别练习从一段给定的dom中，提取标签的text，属性值，以及如何取多个标签的text。

  ​



实现爬虫功能，需要实现抓取页面，提取数据，下载图片，这些子功能。而这些功能的调用都是当某个步骤完成了，就执行下一个步骤，有顺序的。

**所以，请尝试使用event模块来重构整个项目的架构**。

____



## MongoDB



#### 介绍

MongoDB是一个由C++编写的高性能的文档型数据库，为web应用提供可扩展数据库解决方案。它和MySQL的区别如下：

|     项目     |                      MongoDB                       |                            MySQL                             |
| :----------: | :------------------------------------------------: | :----------------------------------------------------------: |
|   存储单元   | 按collection存储，一个collection中包含很多document |            按table存储，一个table中包含很多的记录            |
| 数据格式要求 |    非常灵活，document可以存储任意json格式的数据    |        row中的每一列的数据类型都是限定死的，不够灵活         |
| 数据字段扩展 |               对数据字段的扩展零消耗               | 数据扩展字段有很大的消耗。比如：上万条数据再增加一列就要消耗几十秒。 |
|   事务支持   |          在4.0之后版本支持，目前已经支持           |                             支持                             |
|   读写性能   |        一般情况下，读写性能都要略高于MySQL         |                           性能平稳                           |

具体区别如下：

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpyb1t9110j20qe08swf5.jpg)



#### 下载与安装

下载地址：https://www.mongodb.com/download-center?jmp=nav#community，然后傻瓜式安装。

#### 启动

- 为了方便执行命令，先将安装目录下的`bin`添加到环境变量中。

- 启动server端：`mongod --dbpath F:\xxx`

- 启动client端：`mongo`

- **为了方便，推荐将mongodb设置为Windows的开机启动服务**，做法如下：

  1. 在mongodb的根目录下创建mongo.config文件，内容如下：

     ```
     dbpath=F:\mongodb\data
     logpath=F:\mongodb\log\mongo.log
     ```

  2. 用管理员启动cmd，执行：

     ```bash
     mongod --config f:\mongodb\mongo.config --install --serviceName mongodb
     ```
     服务安装完毕，不要忘记启动服务。

  3. 启动和停止服务

     ```bash
     net start/stop mongodb
     ```

     ​

#### **mongo shell**的增删改查

简单演示。



____



## ORM - **mongoose**

无论是mysql还是mongodb，传统的与数据库交互的方式都是按照他们提供的API来写代码。它们提供的API往往不是很容易理解，而且难以记忆，如果传错了参数，写错一个符号都要查文档。就像上面我们学习的数据库API一样。

ORM框架允许我们面向对象操作，不需要记忆任何的数据库API，只需要操作对象即可，由框架底层去调用数据库API，这样就大大提高了程序员的开发效率。不过既然多了一层封装，肯定要损失一点点的性能，可以忽略不计。

在NodeJS中，mongodb最好的orm框架就是**mongoose**。

#### 安装mongoose

```bash
npm install mongoose --save
```

如果你本机没有安装nodejs驱动，会自动安装nodejs驱动，因为mongoose依赖nodejs驱动了。

文档参考：[mongoose官方文档](http://mongoosejs.com/docs/index.html)

#### 连接数据库

```javascript
const mongoose = require('mongoose')
//连接数据库
mongoose.connect("mongodb://127.0.0.1:27017/test");
const db = mongoose.connection;

db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
    console.log('连接mongodb成功！');
});
```

#### 模型定义

mongoose使用schema来描述数据的格式，字段，规则，有了schema之后可以生成model来操作数据。

```js
const mongoose = require('mongoose')
const schema = mongoose.Schema({
    name: {
      type: String,
      required: true
    },
    age: Number,
    fav: [String],
});

module.exports = mongoose.model('user', schema)
```

#### 增删改查

直接查看文档中Model相关API。

```js
await User.create()
await User.findOne()
await User.updateOne()
await User.deleteOne()
//增加条件操作符: 所有支持的操作符：https://docs.mongodb.com/manual/reference/operator/query/
await User.findOne({
    age: {$gt:11},
    tags:{$in:[1,2,3]}
 })
```

#### 高级查询

```js
//分页查询
await Book.find().skip(10).limit(10)

//排序和字段映射
await Book.find().sort("field").select("field1 field2")

// 填充查询
let res = await Book.find({price: {$gt: 50}}).populate('author', 'name')
console.log(res);
```

#### 

