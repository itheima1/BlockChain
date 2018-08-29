## 高并发架构

在业务的最初期，由于业务和用户的体量比较小，可能采用单机就足够了。随着业务的增长，用户量和并发请求量都会不断上升。当增长到一定的瓶颈的时候，系统能否抗住压力，就需要采取一些方案了。这就是著名的C10K，甚至C100K，C1000K的问题。

一般我们会从2个层面去解决这些问题：硬件层面和软件架构层面。

#### 硬件层面

硬件层面，我们可以进行纵向扩展和横向扩展。

纵向扩展就是增加硬件的性能和配置。这个很好理解，比如采用配置更高的服务器设备和更大带宽的网络，数据库采用Oracle。纵向扩展实施起来简单，缺点是费钱；而且硬件的性能都有上限，最终还是要靠横向扩展。

横向扩展就是购买多台机器，通过负载均衡来提供服务。比如：1台不行就10台，10台不行就100台。数据库也是，数据量太大就分库分表。

#### 软件架构层面

软件层面可以细分为很多方面，比如缓存，消息队列，数据库优化，微服务架构。

对于大多数系统，数据库都是“压倒骆驼的最后一根稻草”，是最容易引起瓶颈的问题所在。我们从架构层面尽量减少到达数据库的访问。

缓存其实就是将高频访问的数据进行缓存，获取的时候先从缓存中取，然后返回给客户端；这样就大大减少了对数据库的访问次数。缓存是成本最低，见效最高的一种方案，也是今天重点学习的内容。

消息队列可以用来应对秒杀场景的高并发，我们将所有的请求看做消息存储到消息队列，然后立即返回给客户端。然后在按照顺序从队列中取消息，一条一条的处理；可以避免系统在海量并发下造成崩溃。

数据库优化其实就SQL技巧和索引的优化了；也可以采用适合应用场景的数据库。比如如果没有事务要求，可以采用性能更高的NoSQL数据库（比如MongoDB）；如果有大量的搜索需求，可以采用ElasticSearch。

微服务架构是将单体架构进行拆分，可以更好的优化具有性能瓶颈的服务，提升单个服务的性能。



## Redis使用

#### 介绍

Redis是目前最流行的缓存数据库，它将数据存到内存中，并支持持久化；所以读取速度非常快，普通机器也能轻松达到10w+/s；并支持丰富的数据类型，如key/value，list，map，set等。

官网：https://redis.io/

中文网站：http://www.redis.net.cn/

#### redis-cli使用

redis-cli是Redis提供的命令行客户端，可以方便执行Redis命令。简单演示命令行的操作：

- get/set
- del xxx
- rpush/lrange

#### node_redis客户端

Redis有各种语言的客户端实现，就像我们使用MySQL驱动连接MySQL数据库一样，我们需要使用NodeJs的Redis客户端去操作Redis数据库。在NodeJs中最为好用的是node_redis实现。

node_redis：https://github.com/NodeRedis/node_redis

具体做法是：先将热点数据存储到Redis中，业务模块取数据的时候优先从缓存中获取。

代码演示热数据的准备和从缓存中取数据：

```js
'use strict'
require('./db')
let redis = require('redis');
let util = require('util');
let client = redis.createClient('redis://127.0.0.1:6379');
let getAsync = util.promisify(client.get).bind(client)
let lrangeAsync = util.promisify(client.lrange).bind(client)
let existsAsync = util.promisify(client.exists).bind(client)

client.on('error', err=>{
    console.log('redis connect fail: ' + err.toString());
});

let Product = require('./model/product');
// 将商品的数据取出来，放入redis中
async function prepareHotData() {
    let list = await Product.find();

    let key = "product";
    let data = list.reverse();
    data.forEach( d=>{
        client.lpush(key, JSON.stringify(d))
    })
}
// 数据的准备可以在项目启动时进行，或者访问频次少的时间段
// prepareHotData()

async function getProductsByPage(page = 1) {
    let hasProduct = await existsAsync('product')
    if(hasProduct===1){
        let skip = (page-1)*5;
        let limit = skip+5 - 1;
        let res = await lrangeAsync('product', skip, limit)
        console.log(res);
    }else {
        // 从数据库中获取
    }

}

getProductsByPage(1);
```

