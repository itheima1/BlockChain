

## 商品分类模块

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
    let categorys = await Category.find().limit(config.PageCount).skip(config.PageCount*page).sort("-created").select("-__v")
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



## 商品模块

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





## 订单模块

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
        min:[1, "商品数量不能小于1"]
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
    let res = await Order.create(order)

    //2. 减去库存
    let update = {
        stock: product.stock - order.count
    };
    await productService.updateProduct(order.productId, update)
    return res
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
   let order = await orderService.addOrder(req.body)
    res.success(order)
});

module.exports = router;
```





## 权限管理模块

主要分2个部分，一个是登录token认证，一个是权限管理。

1. token认证，就是指有的接口需要token才能访问，有的不需要。比如：登录和注册接口不需要token，但是商品的增删改查必须要token。
2. 权限管理是指，有的接口指定角色的用户才能调用，不是该角色的人应该直接报错。比如：用户信息获取和删除用户只能管理员角色操作，商家用户则没有这个权限。

这2个部分显然需要在每次请求前进行处理，所以应该用中间件实现。

我们编写2个中间件，`token_md.js`和`permission_md.js`。

#### token中间件

```js
let crypto = require('lxj-crypto');
let config = require('../config')
let accountService = require('../service/accout')
let isExcludeUrl = require('../util/token_util');

/**
 * 判断req是否是排除检查token的请求
 * @param req
 */
function isExcludeCheckReq(req) {
    // 不需要token的url
    let urls = [
        /.*\/user\/login/,
        /.*\/user\/register/
    ];
    let result = false;
    for (let i = 0; i < urls.length; i++) {
        let urlReg = urls[i];
        if(urlReg.test(req.url)){
            result = true;
            break;
        }
    }

    return result
}

module.exports = async function (req, res, next) {
    if (!isExcludeCheckReq(req.url)) {

        //检查有没有token
        let token = req.get('token') || null;
        if (!token) {
            throw Error("缺少token")
        }

        let tokenData = null;
        try {
            //解码token, 检查token是否过期
            tokenData = JSON.parse(crypto.aesDecrypt(token, config.TokenKey))
            // console.log(tokenData.toString() + " now: " + Date.now());
        } catch (e) {
            // 说明解码失败，需要抛出一个明确的异常
            throw Error("token不合法")
        }
        // 由于tokenData中包含过期时间和username
        //1. 检查是否过期
        if (tokenData.expire < Date.now()) {
            throw Error("token已过期")
        }
        //2. 检查username是否存在，以防止非法用户
        let user = await accountService.getUserByUsername(tokenData.username);
        if (!user) {
            throw Error("token不合法")
        }
        // 说明username合法，将user信息赋予req上下文对象，这样后续的每个中间件和处理函数都能直接从req中取出user使用
        req.user = user;
    }
    // 最后不要忘了放行
    next()
};
```

#### permission中间件

```js
'use strict'
// 负责检查某个接口是否对当前用户的role能否调用
// 具体如下：
// 1. 管理员所有接口都可以调用
// 2. 商家用户能全部调用：商品相关，订单相关，商品分类相关，
//    账户相关：只能调用登录和注册

// 将角色和它对应的权限组成一个映射，然后根据当前用户的role去判断是否符合它对应的权限即可

const role_permissions = [
    // 商家角色，和它对应的权限正则
    {
        role: 0,
        permission: [
            /.*\/product.*/,
            /.*\/order.*/,
            /.*\/category.*/
        ]
    },
    // 管理员角色，和它对应的权限正则
    {
        role: 100,
        permission: [
            /.*/
        ]
    }
];

module.exports = function (req, res, next) {
    // 如果是验证过的用户才去判断
    // console.log("user："+JSON.stringify(req.user));
    if(req.user){
        let isGo = false;
        role_permissions.forEach(el => {
            if(el.role === req.user.role){
                el.permission.forEach(p=>{
                    if(p.test(req.url)){
                        isGo = true;
                    }
                });
            }
        });

        if (!isGo) {
            throw Error("当前用户权限不够")
        }
    }

    next()
};
```



## PM2集群搭建

由于NodeJs是单线程，无法利用多核CPU的优势。想要利用多核CPU，就要进行多进程。NodeJs的cluster模块提供了多进程的支持，PM2又进一步增强了该模块的功能。

PM2能实现单机内的多进程集群，充分利用多核CPU的性能，提高网站的性能。如果是多机器的集群，需要使用nginx来搭建。

编写PM2配置文件：

```yaml
apps:
  - script   : app.js
    name: xxx
    instances: max
    exec_mode: cluster
    watch  : true
    env:
      NODE_ENV: production
```

然后指定这个配置文件来运行:

```bash
pm2 start xxxx.yml
```



## 线上部署

### 云主机选择

- 注册阿里云主机，最低配置
- 可以多人合买

### 部署工作流

以ubuntu环境为例：

- 使用xshell客户端进行远程登录


- 安装nodejs和mongodb
- Server端安装Git，用来同步代码
- 工作流：本地代码更新->提交到远程git仓库->登录远程服务器更新代码，PM2会自动重启app程序