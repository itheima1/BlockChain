#node开发环境搭建

nodejs的版本必须大于8.0.0
下载地址 https://nodejs.org/en/download/


配置npm的镜像仓库

命令行指定

```
npm --registry https://registry.npmmirror.com info underscore 
```

编辑 `~/.npmrc` 加入下面内容

```
registry = https://registry.npmmirror.com
```


#智能合约编译

源代码---> solidity 编译器 --->  abi/ bytecode  --->部署到某个网络


truffle (智能合约创建,本地测试,部署) ---> rinkeby,  网上的demo大多数都是基于truffle的

truffle的问题, 1. api不稳定, 2.bug比较多 3.一些功能缺失

以太坊的很多工具集都处于不稳定的版本,开发状态.

我们从最底层,手把手实现每一个步骤.手动操作理解每一个底层工具的细节.


源代码类型|字节码类型|执行环境|调用者
:-|:-|:-|:-
.java源文件|.class字节码|jvm执行|java代码调用
.sol源文件|bytecode字节码|区块链环境执行|javascript代码调用




# solidity开发环境搭建
1. solidity编译器,编译环境
2. mocha 抹茶测试环境
3. 部署智能合约的脚本 到指定网络



```
npm init
```
一路yes下来

#开发环境的目录结构

* contracts目录
	- Inbox.sol
* test
	- Inbox.test.js
* package.json
* compile.js
* deploy.js

![](https://upload-images.jianshu.io/upload_images/4499731-18552fb0a2d217b0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


1. 编写js

2. 部署js

3. package.json配置

4. .test.js测试代码

5. .sol的智能合约

# 插件安装

[https://plugins.jetbrains.com/plugin/9475-intellij-solidity](https://plugins.jetbrains.com/plugin/9475-intellij-solidity)


#solidity编译

```
npm install --save solc
```

https://www.npmjs.com/package/solc


读文件

```
const path = require('path');
const fs = require('fs');
const solc = require('solc')

const inboxPath = path.resolve(__dirname,'contracts','Inbox.sol');
const source = fs.readFileSync(inboxPath,'utf-8');
solc.compile(source,1)

```

把编译后的结果打印到控制台

对照java的字节码 和 机器码


# 编译脚本优化

```
node compile.js
```



看看编译出来的json对象

:Inbox
	- bytecode(机器码)
	- interface(ABI)
```
module.exports = solc.compile(source,1).contracts.[':Inbox']
```
#题外话,智能合约的安全问题
http://baijiahao.baidu.com/s?id=1599034807067855966&wfr=spider&for=pc
http://www.bitcoin86.com/szb/eth/20518.html

##软分叉,硬分叉
* 软分叉:如果区块链的共识规则改变后，这种改变是向前兼容的，旧节点可以兼容新节点产生的区块，即为软分叉。
* 硬分叉:如果区块链软件的共识规则被改变，并且这种规则改变无法向前兼容，旧节点无法认可新节点产生的区块，即为硬分叉

区块链的价值在于共识, 所有人达成一致,这种一致性带来的价值是无法衡量的, 为什么只有比特币有价值？现在事实给了你们一些答案，你可以复制比特币的代码，创造无数个比特币，但是比特币背后的生态（开发者、矿工、交易所、商家、用户）你无法复制。就像如果把淘宝、微信的源码给你，你能再造一个淘宝和微信吗？

##以太坊硬分叉
https://baijiahao.baidu.com/s?id=1574463853064616&wfr=spider&for=pc


#以太坊的智能合约测试
![](https://upload-images.jianshu.io/upload_images/4499731-8df03e268c743ed8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


每一行代码都价值千金, 一行代码就可能搞死一个家公司
```
npm install --save mocha
```

使用ganache环境进行测试, 

ganache是testrpc的升级版, (一个local test network)
```
npm install --save ganache-cli
```
bytecode--->部署到ganache-cli
ABI--->使用web3.js进行调用
```
npm install --save web3
```
区块链开发就学两个新东西:
1. web3.js
2. 智能合约

剩下来的内容都是传统技术解决的

区块链项目是解决传统业务中心化,不信任,数据不公开可能被篡改的痛点.


#web3.js的安装

npm install --save web3

1. 创建test文件夹
2. 创建Inbox.test.js
3. 引入断言库
```
const  asset = require('assert');
const ganache = require('ganache-cli');
const Web3 = require('web3');
```
注意Web3 是构造函数, 首字母大写

```
若安装报错, 注意,部分windows电脑可能要安装的工具

npm install --global --production windows-build-tools 

终极大招,安装visual studio
```


#web3.js的版本

Web3的版本, v0.x.x  v1.x.x

网上的教材基本上都是v0.x.x 只能用回调来完成异步代码, 写起来是回调地狱

v1.x.x 是支持async/await. 新版本. 但是基本上没有教程.

我们上课采用v1.x.x的web3

#Web3的工作模式
Web3 大写的Web3是构造函数,  
通过构造函数 生成web3的实例.

web3的实例需要装入provider电话卡(联通卡,移动卡,电信卡) 
才可以去以太坊网络进行交互

```
const web3 = new Web3(ganache.provider());//测试电话卡
```
![](https://upload-images.jianshu.io/upload_images/4499731-f192955fdae2316b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

小括号里面的参数,接不同的电话卡, 不同的电话卡接入不同的网络
后面我们会修改这个参数, 连接到rankybe网络等.


#mocha测试框架的使用

函数|作用
:-|:-
it| 跑一个测试或者断言
describe| it函数分组
beforeEach | 执行一些初始化代码

```
class Dog{
    say(){
        return 'wangwang';
    }
    happy({
        return 'wuwu';
    })
}

describe('dog',()=>{
   it('test say',()=>{
       const  dog = new Dog();
       assert.equal(dog.say(),'wangwang');
   }) ;
});
```

package.json添加 scripts
```
"test":"mocha"
```

```
npm run test
```



1. 抽取new Dog()的过程 到beforeEach()
2. 每个测试函数it 需要使用到dog, 声明类的全局变量 let


#mocha代码测试流程

1. mocha start
2. 部署智能合约  beforeEach
3. 调用智能合约 it
4. 进行断言 it

![image.png](https://upload-images.jianshu.io/upload_images/4499731-7bab79533dd496b1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



#web3的常见api

### web3.version
### web3.currentProvider

### web3.utils.toHex
```
var str = "abcABC";
var obj = {abc: 'ABC'};
var bignumber = new BigNumber('12345678901234567890');

var hstr = web3.utils.toHex(str);  //新版api web3 1.x.x
var hobj = web3.toHex(obj);  //旧版api  0.2.x 0.6.x
var hbg = web3.toHex(bignumber);
```

### web3.utils.toAscii
```
var str = web3.utils.toAscii("0x657468657265756d000000000000000000000000000000000000000000000000");
console.log(str); // "ethereum"
```
### web3.utils.fromAscii
```
var str = web3.utils.fromAscii('ethereum');
console.log(str); // "0x657468657265756d"

var str2 = web3.utils.fromAscii('ethereum', 32);
console.log(str2); // "0x657468657265756d000000000000000000000000000000000000000000000000"

```
### web3.utils.fromWei
```
var value = web3.fromWei('21000000000000', 'finney');
console.log(value); // "0.021"
```
### web3.utils.toWei
```
var value = web3.toWei('1', 'ether');
console.log(value); // "1000000000000000000"
```

### web3.eth.getBalance
```
var balance = web3.eth.getBalance("0xa23D7B053aBce4500b2cD452ce6d680CA8b114ff");
console.log(balance); // instanceof BigNumber
console.log(balance.toString(10)); // '1000000000000'
console.log(balance.toNumber()); // 1000000000000
```
#从ganache测试框架获取测试账户

![](https://upload-images.jianshu.io/upload_images/4499731-d32e23cd8e23610e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


```
beforeEach()=>{
    web3.eth.getAccounts().then(account =>{
        console.log(account)
    });
}
```

```
descirbe('Inbox',()=>{
  it('test',()=>{
      
  });  
});
```
#es6的async和await

```
let accounts;
accounts = await web3.eth.getAccounts();
```

函数体用async修饰

#使用web3部署智能合约

```
const {interface, bytecode} = require('../compile');

inbox = await new web3.eth.Contract(JSON.parse(interface)).deploy({
    data:bytecode, arguments:['hi']
}.send({from:accounts[0],gas:'1000000'}));
```

打印inbox





#回顾部署智能合约的流程

* 设置web3 模块部署的智能合约有什么接口可以供交互 contract(interface)
* 设置web3 模块要部署的智能合约真正的字节码  deploy(bytecode)
* send方法 指引web3去发送transaction,部署智能合约 send(trans)


web3 不仅可以部署智能合约  abi ,bytecode
也可以调用部署好的智能合约 abi, address

注意异步的代码

#对部署的智能合约进行测试

1. 测试智能合约是不是已经拿到了address
```
	assert.ok(inbox.options.address)
```
2. 测试验证message设置
```
	call的调用和invoke
	inbox.methods.message().call();
	
  inbox.methods.setMessage('hello').send({
       from: accounts[0]  
	});
	返回值是hash
```


#部署智能合约到真实网络
![](https://upload-images.jianshu.io/upload_images/4499731-f192955fdae2316b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/4499731-93f592530e1073cd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/4499731-5592f03bcb12d453.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
修改电话卡provider
```
介绍infura api

注册infura api


安装 新的module
https://www.npmjs.com/package/truffle-hdwallet-provider

npm install --save truffle-hdwallet-provider


```
var HDWalletProvider = require("truffle-hdwallet-provider");
var mnemonic = "opinion destroy betray ..."; // 12 word mnemonic
var provider = new HDWalletProvider(mnemonic, "http://infura/xxx");
const web3 = new Web3(provider);
```

```
const deploy = async()=>{
    const accounts = await web3.eth.getAccounts();
    console.log(accounts[0]);
    new web3.eth.Contract(xxxx;).deploy(xxx).send(xxx);
}
```

#etherscan使用介绍

介绍www.etherscan.io


输入智能合约的地址.


# 智能合约开发总结

1. solc 编译智能合约
2. mocha测试框架 beforeEach describe it
3. ganache 创建账户
4. provider 测试provider hd-wallet provider
5. web3 交互


# 使用web3转账

```
let accounts;
const deploy = async () => {
    accounts = await  web3.eth.getAccounts();
    console.log(accounts);

    const translation = await web3.eth.sendTransaction({from: accounts[0], to: accounts[1], value: 3000000})
    console.log(translation);

    console.log(await  web3.eth.getBalance(accounts[0]));
    console.log(await web3.eth.getBalance(accounts[1]));

};
deploy();
```



# 开发以太坊水龙头


```
var express = require('express');
var Web3 = require('web3');
var HDWalletProvider = require("truffle-hdwallet-provider");
var mnemonic = "skill fragile view aspect tragic depend flock river uncover century pioneer xxxx"; // 12 word mnemonic
var provider = new HDWalletProvider(mnemonic, "https://rinkeby.infura.io/xxxxx");
const web3 = new Web3(provider);


var app = express();

app.get('/:address', async (req,res) => {
    console.log('begin');
    try {
        var accounts = await web3.eth.getAccounts();
        var str = 'info.'
        let data = Buffer.from(str).toString('hex');
        data = '0x'+data;
        var id = await web3.eth.sendTransaction({from: accounts[0],
            data:data,
            to: req.params.address, value: 100000000000000});
        res.send('黑马程序员送您0.0001个以太币,转账id为: ' + id.transactionHash)
    }catch (error){
        res.send('转账失败,请检查地址合法性'+error);
    }
    console.log('end');
});
app.listen(3000);

```