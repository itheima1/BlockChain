## pREST API的设计

### 前言

客户端通过请求URL，传递参数，去获取指定的数据，这就是API(ApplicationProgramInterface)。

API是前端和客户端操作后端数据的一种方式，一种接口。当一个Web应用以API的方式对外提供功能和接口时，整个应用的架构模式是这样的：

![](http://ww1.sinaimg.cn/large/71b38f2cgy1frggootlu4j20qw09ojrq.jpg)

但是，URL怎么约定，参数以什么编码方式传，响应数据的格式是什么样的，以及响应码怎么约定，API版本升级怎么设计，这些问题是设计这些API的后端人员不得不考虑的问题。

2000年，Roy Fielding博士在论文中提出[REST](http://zh.wikipedia.org/wiki/REST)（Representational State Transfer）风格的软件API架构模式。该模式对上面的问题提出了针对性的解决方案，迅速被大家接受，逐渐流行起来。

REST是一种API的设计模式，它将数据库的数据看做是一个个的资源。客户端的请求就是对这些资源的操作，这些操作无外乎增删改查四种。这种模式简单易读，易于维护。

这种设计模式提倡我们遵循以下原则。

### 数据格式
REST提倡数据格式应该使用轻量级，易于阅读的json格式。这要求我们除了`GET`请求方式外，其他请求方式的请求体都应该是json，响应体也是json。
所以，请求头和响应头的`Content-Type`都应该为`application/json`。

### 请求方式
REST将请求看做是对某个资源的增删改查操作，正好对应了Http请求的`GET`,`POST`, `PUT`, `DELETE` 4种请求方法。
对应关系如下：
- 获取资源：`GET`方式
- 创建资源：`POST`方式
- 更新资源：`PUT方`式
- 删除资源：`DELETE`方式

### URL设计
REST提倡声明式的URL设计。URL设计需要考虑层次化，可扩展，易于维护。每一个URL都表示对某个资源的操作，假设我们要操作服务器的博客数据，可以这样设计：
- 通用的资源操作方式：
  ```bash
  // 获取所有博客
  GET /api/blogs  
  // 获取指定博客
  GET /api/blogs/1234
  // 创建博客
  POST /api/blogs
  // 更新指定博客
  PUT /api/blogs/1234
  // 删除指定博客
  DELETE /api/blogs/1234
  ```
- 资源的过滤操作，使用查询参数来限定条件：
  ```js
  // 分页获部分博客
  GET /api/blogs?page=1&size=15&sort=time  
  ```
- 按层次组织资源：
  ```js
  // 获取某个博客下面的所有评论
  GET /api/blogs/143/comments
  ```
  层次化的设计有可能会让URL太长，不便于阅读，比如：
  ```js
  // 获取某个博客下面的某个评论的某个回复
  GET /api/blogs/123/comments/12343/replys/2231
  ```
  所以，我们也可以使用扁平化的方式设计所有的资源：
  ```js
  // 获取某个博客的所有评论，使用查询参数来限定条件
  GET /api/comments?blogId=123
  // 获取某个评论的所有回复
  GET /api/replys?commentId=123
  ```
- API的版本升级。
  我们的API会不断的更新迭代，比如：获取所有订单的URL参数多加了一个时间戳。如果我们直接更改原来接口的逻辑，会导致旧版本的客户端获取失败。因此，通过添加一个路径`/v1`来让我们的API更加维护性。
  ```js
  // 获取所有订单, 第一版
  GET /api/v1/orders
  // 获取所有订单, 第二版
  GET /api/v2/orders
  ```

### 响应设计
响应设计分为数据结构设计和响应码设计。
- 响应数据结构设计

  客户端对资源的操作有可能成功，也有可能失败。以前我一直使用使用这样的数据结构：
  ```json
  {
      code: 1/0, // 会有很多标识码,11, 12, 13 ...
      msg: "获取成功/获取失败",
      data: [...]/{}
  }
  ```
  整体的结构一定不能变，数据的变化体现在`data`字段。这样非常方便客户端的解析或者序列化（静态语言）。

- 响应码设计

  响应码分为http响应码和逻辑响应码。http响应码很简单，如果成功就200，服务器出错就500。而逻辑响应码则是需要自己来定义，比如：1表示用户不存在，2表示密码错误。




## 前后端分离

在以前的时代，用户请求一个网页，服务端通过JSP技术，或者其他模板引擎技术动态渲染后，返回给用户。它们看起来像这样：

![](http://ww1.sinaimg.cn/large/71b38f2cgy1frs693a8qcj20l90b00t7.jpg)

这样做的坏处是，后端和前端融合在一起，哪怕前端代码不是由后端人员来写，仍然需要2方人员进行必要的交流和沟通，降低了开发效率；而且前端和后端共同部署，灵活性差；而且后台处理静态资源的性能较差，也不应该去处理静态资源压缩，缓存等问题，应该交给代理服务器来处理，比如Nginx。

前后端分离最极致作法是：前端和后端各自独立编写，独立部署，通过API接口进行交互。他们看起来像这样：

![](http://ww1.sinaimg.cn/large/71b38f2cgy1frs6ljjaghj20nw0bywf8.jpg)



## 项目实战：电商管理后台

目标，实现一个有账户模块，权限模块，商品和分类模块和订单模块。

项目采用前后端分离的开发方式，本身只实现API功能，并没有提供界面。对应的管理界面，等到Vue课程的时候去实现。



### 项目结构搭建和实施流程

在之前TODO项目的基础上，增加一个`middleware`包和test包，前者用来存放中间件的包，因为权限管理需要用中间件来实现；后者是测试相关包。

1. 每个模块的实现顺序为：model层 --> service层 --> router层。
2. 单元测试：service层写脚本测试；router层使用postman测试。

### 配置文件的环境切换

开发环境和生产环境的配置一般是不一样的，比如端口配置，数据库配置。一般我们是通过环境变量`NODE_ENV`来区分。为了能够动态切换配置，就需要根据当前`NODE_ENV`的值来加载不同的配置对象。

做法就是：

1. 建立config目录，创建`dev.js`和`prod.js`分别存放对应的配置信息
2. 编写`index.js`，实现动态切换配置的逻辑。

目录结构：

![](http://ww1.sinaimg.cn/large/71b38f2cgy1frt6wfvd5xj20xe05wjro.jpg)



### 编写入口文件

添加依赖：

```bash
npm i body-parser express express-async-errors mongoose morgan
```

编写`app.js`和`db.js`文件。

#### app.js

```js
//引入dib
require('./db')

const config = require('./config');
const morgan = require('morgan')
const bodyParser = require('body-parser');
const express = require('express')
// 引入异常捕获处理
require('express-async-errors');

const app = express();

//注册中间件
// log中间件
app.use(morgan('combined'));

//注册自定义的中间件

// 注册body-parser中间件
// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));
// parse application/json
app.use(bodyParser.json());

// 注册路由
app.use("/users", require('./router/account'));

// 注册异常处理中间件
app.use((err, req, res, next)=>{
    res.fail(err.toString())
});

//启动
app.listen(config.PORT);

```

#### db.js

```js
const config = require('./config');
const mongoose  = require('mongoose');
mongoose.connect(`mongodb://127.0.0.1/${config.DB}`);

const db = mongoose.connection;

db.on('error', (err)=>{
    console.log(err);
});

db.on("open", ()=>{
    console.log("mongodb connect successfully!");
});
```



### 账户模块

先编写model，再service，最后router；最后对service和router进行测试。

#### REST中间件

为了方便进行REST结果的返回，我们编写一个`rest_md.js`中间件，作用是给每个res对象安装2个方法，注意该中间件注册的顺序尽量放在前面。代码如下：

```js
module.exports = function (req, res, next) {
  res.success = (data = null) =>{
    res.send({
        code: 0,
        msg: "操作成功",
        data: data
    })
  };
  res.fail = (msg)=>{
    res.send({
        code: -1,
        msg: msg
    })
  };

  next();
};
```

#### 账户model

```js
const mongoose = require('mongoose')
const schema = new mongoose.Schema({
    username: {
        type: String,
        required: [true, "用户名不能缺少"]
    },
    password: {
        type: String,
        required: [true, "密码不能缺少"]
    },
    age: {
        type: Number,
        min: [0, "年龄不能小于0"],
        max: [120, "年龄不能大于120"]
    },
    role: {
        type: Number,
        default: 0 //角色,0: 普通用户, 10是商家， 100是管理员
    },
    created:{
        type: Date,
        default: Date.now()
    }
});

module.exports = mongoose.model('user', schema);
```

#### 账户service

```js
const User = require('../model/user');
const config = require('../config')
const crypto = require('lxj-crypto')

/**
 * 分页获取用户
 * @param page
 * @returns {Promise<*>}
 */
async function getUsersByPage(page = 0) {
    return await User.find({}).limit(config.PageCount).skip(page * config.PageCount).sort("-created").select("-__v")
}

/**
 * 根据用户名获取某个用户
 * @param username
 * @returns {Promise<*>}
 */
async function getUserByUsername(username) {
    return await User.findOne({username: username}).select("-__v")
}


async function checkCanInsertUser(user) {
    //检查密码是否为空
    if(!user.password || user.password.length===0){
        throw Error("密码不能为空")
    }
    //检查用户是否已经存在
    let res = await getUserByUsername(user.username);
    if(res){
        throw Error("用户名已经存在")
    }
}

/**
 * 添加商家用户
 * @param user
 * @returns {Promise<void>}
 */
async function addMerchantUser(user) {
    await checkCanInsertUser(user)

    user.role = 10;
    user.created = Date.now();

    //对密码进行加密,加密的方式：使用username作为随机数对password进行哈希
    user.password = crypto.md5Hmac(user.password, user.username)

    await User.create(user)
}


/**
 * 添加普通用户
 * @param user
 * @returns {Promise<void>}
 */
async function addUser(user) {
    await checkCanInsertUser(user);

    user.role = 0;
    user.created = Date.now();

    //对密码进行加密,加密的方式：使用username作为随机数对password进行哈希
    user.password = crypto.md5Hmac(user.password, user.username)
    await User.create(user)
}

/**
 * 更新用户信息
 * @param id
 * @param update
 * @returns {Promise<void>}
 */
async function updateUser(id, update) {
    let res = await User.updateOne({_id: id}, {age: update.age});
    if(!res || res.n===0){
        throw Error("更新用户失败")
    }
}

async function deleteUser(id) {
    let res = await User.deleteOne({_id:id});
    if(!res || res.n===0){
        throw Error("删除用户失败")
    }
}

/**
 * 登录的方法
 * @param user
 * @returns token
 */
async function login(user) {
    // 1. 对密码进行加密
    user.password = crypto.md5Hmac(user.password, user.username)
    // 2. 进行查询
    let res = await User.findOne({username:user.username, password: user.password});
    if(!res){
        throw Error("用户名或者密码错误")
    }

    // 说明用户名和密码验证成功，需要生产token返回给客户端，以后客户端的header中带上这个token
    // token 生产方法：用aes进行对指定的data加密
    let tokenData = {
        username: user.username,
        expire: Date.now() + config.TokenDuration
    };
    let token = crypto.aesEncrypt(JSON.stringify(tokenData), config.TokenKey);
    return token
}

module.exports = {
    getUsersByPage, getUserByUsername,
    addUser, addMerchantUser,
    updateUser,
    deleteUser,
    login
};
```



#### 账户router

```js
let router = require("express").Router();
let accountService = require('../service/accout')

router.get("/", async (req, res)=>{
    let page = req.query.page || 0
    let users = await accountService.getUsersByPage(page);
    res.success(users);
});

router.get("/:username", async (req, res)=>{
    let user = await accountService.getUserByUsername(req.params.username);
    res.success(user);
});

// 登录
router.post("/login", async (req, res)=>{
    let token = await accountService.login(req.body);
    res.success({token});
});

// 注册
router.post("/", async (req, res)=>{
    await accountService.register(req.body)
    res.success()
});

router.delete("/:id", async (req, res)=>{
    await accountService.deleteUser(req.params.id)
    res.success()
});

module.exports = router;
```





### 商品分类模块

#### 分类model

```js
const mongoose = require('mongoose')
const schema = new mongoose.Schema({
    name: {
        type: String,
        required: [true, "分类名称不能少"],
        unique: true
    },
    created:{
        type:Date,
        default: Date.now()
    }
});

module.exports = mongoose.model('category', schema);
```



#### 分类service

```js
const Category = require('../model/category')
const config = require('../config')
async function getCategorysByPage(page=0) {
    let categorys = await Category.find({}).limit(config.PageCount).skip(config.PageCount*page).sort("-created").select("-__v")
    return categorys
}

async function addCategory(category) {
    category.created = Date.now()
    await Category.create(category)
}

async function deleteCategory(id) {
    let res = await Category.deleteOne({_id: id})
    if(!res || res.n===0){
        throw Error("删除分类失败")
    }
}

async function updateCategory(id, update) {
    let res = await Category.updateOne({_id: id}, update)
    if(!res || res.n===0){
        throw Error("更新分类失败")
    }
}

module.exports = {
    getCategorysByPage, addCategory, deleteCategory, updateCategory
}
```



#### 分类router

```js
let router = require('express').Router()
let categoryService = require('../service/category')

router.get("/", async (req,res, next)=>{
    let page = req.query.page || 0
    let categorys = await categoryService.getCategorysByPage(page)
    res.success(categorys)
});

router.post("/", async (req,res, next)=>{
    await categoryService.addCategory(req.body)
    res.success()
})

router.delete("/:id", async (req,res, next)=>{
    await categoryService.deleteCategory(req.params.id)
    res.success()
})

router.put("/:id", async (req,res, next)=>{
    await categoryService.updateCategory(req.params.id, req.body)
    res.success()
})

module.exports = router
```



### 商品模块

#### 商品model

```js
const mongoose = require('mongoose')
const schema = new mongoose.Schema({
    name: {
        type: String,
        required:[true, "商品名字不能少"],
        unique: true
    },
    price: {
        type: String,
        required:[true, "商品价格不能少"]
    },
    stock: {
        type: Number,
        default: 0,
    },
    category:{
        type: mongoose.Schema.Types.ObjectId,
        required: [true, "分类id不能为空"]
    },
    description:{
        type: String,
    },
    isOnSale:{   //是否上架
        type: Boolean,
        default: true
    },
    created:{
        type: Date,
        default: Date.now()
    }
});

module.exports = mongoose.model('product', schema)
```

#### 商品service

```js
const Product = require('../model/product')
const config = require('../config')

async function getProductsByPage(page=0) {
    if(page<0){
        throw Error("page不能小于0")
    }

    let products = await Product.find({}).limit(config.PageCount).skip(config.PageCount*page).sort("-created").select("-__v");
    return products
}

async function getProductById(id) {
    let res = await Product.findOne({_id: id});
    return res
}

async function updateProductById(id, update) {
    let res = await Product.updateOne({_id: id}, update);
    if(!res || res.n ===0){
        throw Error("更新失败")
    }
}

async function deleteProduct(id) {
    let res = await Product.deleteOne({_id: id})
    if(!res || res.n ===0){
        throw Error("删除失败")
    }
}

async function addProduct(product) {
    product.created = Date.now()
    let res = await Product.create(product)
}
module.exports = {
    getProductsByPage, getProductById, updateProductById, deleteProduct, addProduct
}
```

#### 商品router

```js
let router = require('express').Router()
let productService = require('../service/product')

router.get("/", async (req, res, next)=>{
    let page = req.query.page 
    let products = await productService.getProductsByPage(page);
    res.success(products)
});

router.post("/", async (req,res,next)=>{
   await productService.addProduct(req.body)
    res.success()
});

router.put("/:id", async (req,res,next)=>{
    await productService.updateProductById(req.params.id, req.body)
    res.success()
});

router.delete("/:id", async (req,res,next)=>{
    await productService.deleteProduct(req.params.id)
    res.success()
});

module.exports = router;
```





### 订单模块

#### 订单model

```js
const mongoose = require('mongoose')
const schema = new mongoose.Schema({
    productId: {
        type: mongoose.Schema.Types.ObjectId,
        required: [true, "商品id不能为空"]
    },
    productName:{
        type: String,
        required:[true, "商品名字不能缺少"]
    },
    productPrice: {
        type: String,
        required: [true, "商品价格不能缺少"]
    },
    count: {
        type: Number,
        required: [true, "商品数量不能为空"],
        min:[1, "商品数量不能小于0"]
    },
    total:{
        type: String
    },
    status: {
      type: String, // 订单状态: unpay success cancel
      default:"unpay"
    },
    created: {
        type:Date,
        default: Date.now(),
    },
    payTime: {
        type: Date
    },
    cancelTime: Date
});

module.exports = mongoose.model('order', schema);
```



#### 订单service

```js
'use strict'

const Order = require('../model/order')
const config = require('../config');
const productService = require('./product')
const Big = require('big.js');

async function getOrdersByPage(page = 0) {
    return await Order.find().limit(config.PageCount).skip(page*config.PageCount).sort("-created").select("-__v")
}

async function getOrderById(id) {
    return await Order.findOne({_id: id})
}

async function setOrderSuccess(id) {
    let order = await getOrderById(id)
    order.status = "success"
    order.payTime = Date.now()
    let res = await Order.updateOne({_id: id}, order);
    if(!res || res.n===0){
        throw Error("更新失败")
    }
}

async function setOrderCancel(id) {
    let order = await getOrderById(id)
    order.status = "cancel"
    order.cancelTime = Date.now()
    let res = await Order.updateOne({_id: id}, order);
    if(!res || res.n===0){
        throw Error("更新失败")
    }
}

async function addOrder(order) {
    //1. 根据商品id查询出商品
    let product = await productService.getProductById(order.productId);
    if(!product){
        throw Error("未找到商品")
    }
    //判断库存够不够
    if(product.stock < order.count){
        throw Error("商品库存不够")
    }

    order.productName = product.name;
    order.productPrice = product.price;
    order.total = Big(order.productPrice).times(order.count);
    order.created = Date.now();
    await Order.create(order)

    //2. 减去库存
    let update = {
        stock: product.stock - order.count
    };
    await productService.updateProduct(order.productId, update)
}

module.exports = {
    getOrdersByPage, getOrderById,
    setOrderSuccess, setOrderCancel,
    addOrder
};
```



#### 订单router

```js
const router = require('express').Router()
const orderService = require('../service/order')

router.get("/", async (req, res, next) => {
    let page = req.query.page || 0
    let orders = await orderService.getOrdersByPage(page)
    res.success(orders)
});

router.get("/:id", async (req,res,next)=>{
   res.success(await orderService.getOrderDetail(req.params.id))
});

router.post("/", async (req,res,next)=>{
   await orderService.addOrder(req.body)
    res.success()
});

module.exports = router;
```




