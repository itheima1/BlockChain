##视频下载地址
链接：https://pan.baidu.com/s/1_K88eS9I1lnXVwWsXI83TQ 密码：beex


## 前言

由于NodeJs本身的异步非阻塞特性和对http的天然支持，所以使用NodeJs编写高性能，可伸缩的Web服务器非常简单。开发完整的Web服务器还需要路由，错误处理，请求拦截，请求和响应的解析，模板引擎等功能，所以直接使用NodeJs的http模块开发起来还是挺痛苦的。

目前有很多的Web框架都是基于http模块封装而成，最流行的当属Express框架。

学习资源：

- [express github主页](https://github.com/expressjs/express)
- [express官方网站](https://expressjs.com/)
- [中文网站](http://www.expressjs.com.cn/)



## 快速开始

```bash
npm install express --save
```

快速开启服务器：

```js
const express = require('express');
const app = express();

app.get('/', function(req, res){
  res.send('hello world');
});

app.listen(3000);
```



## 静态文件

express提供了托管静态文件的功能，比如html，图片，CSS，JavaScript等文件。

```js
app.use(express.static('public'));
```

支持指定挂载路径：

```js
app.use('/static', express.static('public'));
```

一般框架提供的静态文件托管功能，只是在开发环境使用。在生产环境，推荐交给反向代理服务器来管理，比如Nginx；最佳的做法是花点钱交给CDN提供商来服务。



## 路由

路由就是根据用户请求的url路径，交由对应的响应方法处理。

#### 请求方法

```js
app.get('/', function (req, res) {
  res.send('GET request to the homepage');
});

// POST method route
app.post('/', function (req, res) {
  res.send('POST request to the homepage');
});
```

#### 路径匹配

```js
// 匹配根路径
app.get('/', function (req, res) {
  res.send('root');
});

// 匹配 /user 路径的请求
app.get('/user', function (req, res) {
  res.send('user');
});

// 匹配 /xxx 路径的请求
app.get('/xxx', function (req, res) {
  res.send('xxx');
});
```

支持简单的字符串匹配：

```js
// 匹配 acd 和 abcd
app.get('/ab?cd', function(req, res) {
  res.send('ab?cd');
});

// 匹配 abcd、abbcd、abbbcd等
app.get('/ab+cd', function(req, res) {
  res.send('ab+cd');
});

// 匹配 abcd、abxcd、abRABDOMcd、ab123cd等
app.get('/ab*cd', function(req, res) {
  res.send('ab*cd');
}); 
```

也支持完全的正则方式，但是不能用字符串了。

```js
// 匹配以abc开头的所有字符
app.get(/abc[0-9a-zA-Z]*/, function(req, res) {
  res.send('ab?cd');
});
```



#### 模块化路由

模块化路由是指我们可以将一组具有业务相关性的路由封装为一个router对象，然后挂载到指定路径。

```js
const express = require('express');
const router = express.Router();

// 该路由使用的中间件
router.use(function timeLog(req, res, next) {
  console.log('Time: ', Date.now());
  next();
});
// 定义模块的根路由
router.get('/', function(req, res) {
  res.send('auth模块根路由');
});
// 定义登录
router.get('/login', function(req, res) {
  res.send('login');
});

module.exports = router;
```

将router挂载到指定路径：

```js
const auth = require('./auth');
...
app.use('/auth', auth);
```



## 中间件

中间件就是整个请求——>响应流程中的一系列处理函数。路由也是属于中间件，是挂载了路径的中间件。

使用中间件有两点注意：

1. next() 方法和 res.send() 必须调用一个，否则请求就会挂起，客户端等待响应。
2. 中间件的顺序很重要，它是按照你注册的顺序执行的。

#### 应用程序级别中间件

```js
const app = express();

// 没有挂载路径的中间件，应用的每个请求都会执行该中间件
app.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 挂载至 /user/:id 的中间件，任何指向 /user/:id 的请求都会执行它
app.use('/user/:id', function (req, res, next) {
  console.log('Request Type:', req.method);
  next();
});
```

​	

#### 路由级别中间件

路由级中间件和应用级中间件一样，只是它绑定的对象为 `express.Router()`。

```js
const router = express.Router();

// 没有挂载路径的中间件，通过该路由的每个请求都会执行该中间件
router.use(function (req, res, next) {
  console.log('Time:', Date.now());
  next();
});

// 一个中间件栈，显示任何指向 /user/:id 的 HTTP 请求的信息
router.use('/user/:id', function(req, res, next) {
  console.log('Request URL:', req.originalUrl);
  next();
}, function (req, res, next) {
  console.log('Request Type:', req.method);
  next();
});

// 一个中间件栈，处理指向 /user/:id 的 GET 请求
router.get('/user/:id', function (req, res, next) {
  // 如果 user id 为 0, 跳到下一个路由
  if (req.params.id == 0) next('route');
  // 负责将控制权交给栈中下一个中间件
  else next(); //
}, function (req, res, next) {
  // 渲染常规页面
  res.render('regular');
});

module.exports = router;
```

挂载router

```js
const app = express();
app.use('/', router);
```



## **错误处理**

程序总会遇到错误，比如：传参错误，我们不小心调用了`undifined`，或者我们自己也可能抛出异常。当发生这些错误的时候我们需要给用户一个友好的回应，这就是错误处理。

定义错误处理中间件和定义其他中间件一样，除了需要 4 个参数，而不是 3 个，其格式如下 `(err, req, res, next)`。例如：

```js
app.use(function(err, req, res, next) {
  console.error(err.stack);
  res.status(500).send('出错了!');
});
```

错误中间件返回的响应是随意的，可以响应一个 HTML 错误页面、一句简单的话、一个 JSON 字符串，或者其他任何您想要的东西。

在其他 `app.use()` 和路由调用后，最后定义错误处理中间件，比如：

```js
app.use(bodyParser());
app.use(router;
app.use(function(err, req, res, next) {
  // ...
});
```



## 模板引擎

Html页面本身是静态的，不含数据的。模板引擎的作用就是将从数据库读出来的，处理好的数据填充到html页面，然后返回给用户。

express支持很多的模板引擎，每个模板引擎都有自己的语法。这里以jade为例。

需要安装相应的模板引擎 npm 软件包。

```bash
npm install jade --save
```

设置模板的目录，和当前使用的模板引擎

```js
app.set('views', './views') //设置模板存放的目录
app.set('view engine', 'jade'); //设置使用的引擎
```

在 `views` 目录下生成名为 `index.jade` 的 Jade 模板文件，内容如下：

```jade
html
  head
    title=title
  body
    h1=message
```

然后创建一个路由渲染 `index.jade` 文件。

```js
app.get('/', function (req, res) {
  res.render('index', { title: 'Hey', message: 'Hello there!'});
});
```

 `index.jade` 文件会被引擎渲染为html文件，然后发送给用户。**jade**引擎的更多使用请参考它的官方文档，这里只做简单演示，原因如下：

**现代Web开发的模式是逐渐趋向于前后端分离，前端的人负责写Html页面，后台的人写API服务，前端数据通过AJAX得到。这样的好处是开发效率高，沟通成本降低。加上Vue和React等具有高性能和高效率的前端渲染引擎的出现，后端的模板引擎的几乎没有用武之地了。但不排除某些公司的技术人员仍然顽固地选择后端渲染。**



## 进程管理

在生成环境我们不会使用`node app.js`的方式来开启程序，因为我们有这样的一些需求：

- 如果进程崩溃了，需要重启
- 监控当前进程的CPU使用率和内存占用
- 控制集群

进程管理器就能提供上面的功能，让我们的NodeJs进程永不退出。最流行的进程管理器有3个：

- [StrongLoop Process Manager](http://www.expressjs.com.cn/advanced/pm.html#sl)
- [PM2](http://www.expressjs.com.cn/advanced/pm.html#pm2)
- [Forever](http://www.expressjs.com.cn/advanced/pm.html#forever)

其中PM2除了提供上面的功能外，还可以监视程序的日志，错误提醒，并且内置了负载均衡器，界面还友好。所以我们选择使用PM2。

首先，安装。

```bash
npm install pm2 -g
```

启动程序：

```bash
pm2 start app.js
```

其他常用命令：

```bash
pm2 list
pm2 stop app
pm2 reload app
pm2 start xxx.js -i 4
```



## 学习资源

第三方中间件：http://www.expressjs.com.cn/resources/middleware.html

API查询：http://www.expressjs.com.cn/4x/api.html





## TODO 后台项目 

#### 项目介绍

完成对TODO事项的增删改查功能，数据库使用mongodb。

#### 安装依赖

创建项目，新建`package.json`文件。

```sh
npm i express -S
npm i mongoose -S
npm i morgan -S  // 打印log的类库
npm i express-async-errors -S  // 用来捕获promise错误
```

由于`express 4x`为了精简框架，移除了很多内置的中间件，比如`body-parser`。所以为了能够解析body参数，需要额外安装这个模块。

```sh
npm i body-parser -S
```



#### 项目结构搭建

整个项目结构，仍然是基于MVC架构来分层。

- service包：存放所有的业务逻辑类，相当于service层


- router包：存放所有的路由，相当于controller层
- util包：存放所有的帮助类
- model包：存放所有的mongodb模型类，相当于dao层
- `config.js`：配置文件
- `db.js`：数据库入口文件
- `app.js`：app入口文件


#### 编写项目逻辑

依次编写：

1. `app.js` 和 `config.js`

   ```js
   'use strict'
   // 连接数据库
   require('./db')

   const config = require('./config')
   const bodyParser = require('body-parser');
   const morgan = require('morgan');
   const express = require('express')
   // 引入express异步异常捕获模块
   require('express-async-errors');
   const app = express();

   // 注册log中间件
   app.use(morgan('combined'));

   // 注册body解析中间件
   // parse application/x-www-form-urlencoded
   app.use(bodyParser.urlencoded({ extended: false }));
   // parse application/json
   app.use(bodyParser.json());

   // 注册路由
   app.use('/todos', require('./router/todo'));

   // 注册错误处理中间件
   app.use(function (err, req, res, next) {
       console.log(err);
       res.send({
           code: -1,
           msg: err.toString()
       })
   });

   app.listen(config.PORT);
   ```

   ```js
   'use strict'
   const config = {
       PORT: 3000,
       DB: 'TODO',
   }

   module.exports = config
   ```

2. router

   ```js
   'use strict'

   const router = require('express').Router()
   const todoController = require('../controller/todo')

   module.exports = router

   router.get('/', async (req, res)=>{
       let todos = await todoController.getAllTodo();
       res.send({
           code: 0,
           data: todos
       })
   });

   router.post('/', async (req, res)=>{
       await todoController.addTodo(req.body)
       res.send({code: 0})
   });

   router.put('/:id', async (req, res)=>{
       await todoController.updateTodo(req.params.id, req.body)
       res.send({code: 0})
   });

   // 删除某个todo
   router.delete('/:id', async (req, res)=>{
       await todoController.deleteTodo(req.params.id)
       res.send({code: 0})
   });
   ```

   ​

3. `db.js` 和 model

   ```js
   'use strict'

   const config = require('./config')
   const mongoose = require('mongoose')

   mongoose.connect(`mongodb://127.0.0.1/${config.DB}`)

   const db = mongoose.connection

   db.on('error', err=>{
       console.log(err);
   });
   db.on('open', ()=>{
       console.log('db connect successful!');
   });
   ```

   ```js
   'use strict'
   // To-do: 模型类, 内容，是否完成，创建日期
   const mongoose = require('mongoose')

   const schema = new mongoose.Schema({
       content: String,
       isDone: {
           type:Boolean,
           default: false
       },
       created: {
           type: Date,
           default: Date.now()
       }
   });

   module.exports = mongoose.model('todo', schema);
   ```

   ​

4. controller 

   ```js
   'use strict'

   const Todo = require('../model/todo')

   // 取出数据库中的所有todo，然后返回
   async function getAllTodo() {
       return await Todo.find({})
   }

   async function updateTodo(id, update) {
       await isIdExist(id)

       // { n: 1, nModified: 1, ok: 1 }
       let res = await Todo.updateOne({_id:id}, update)
       if(!res || res.n<1){
           throw `${update}更新失败`
       }
   }

   async function addTodo(todo) {
       // 先查询名字是否存在
       let t = await Todo.findOne({content: todo.content})
       if(t){
           //说明已经存在，则抛出异常，由异常处理中间件负责处理
           throw `${todo.content} 已经存在`
       }
       await Todo.create(todo)
   }

   async function isIdExist(id) {
       // 先判断传入的id是否存在
       let t = await Todo.findOne({_id: id})
       if(!t){
           throw `${id}不存在`
       }
   }

   async function deleteTodo(id) {
       await isIdExist(id)

       // { n: 1, ok: 1 }
       let res = await Todo.deleteOne({_id:id})
       console.log(res);
       if(!res || res.n < 1){
           throw `${id}删除失败`
       }
   }

   module.exports = {
       getAllTodo, updateTodo, addTodo, deleteTodo
   }
   ```

   ​

#### API 测试

使用postman进行测试每个接口。



