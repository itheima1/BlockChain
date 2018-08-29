##视频下载地址
链接：https://pan.baidu.com/s/1jtSAOK1IdjIvZcvM-C31WQ 密码：furz

##  ES6常用新语法

### 前言

是时候学点新的JS了！

为了在学习NodeJs之前，能及时用上语言的新特性，我们打算从一开始先学习一下JavaScript语言的最基本最常用新语法。本课程的内容，是已经假设你有过一些JavaScript的使用经验的，并不是纯粹的零基础。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpkqg54dq5j20sg06p3zp.jpg)



### ES6新语法

> 什么是ES6? 
>
> 由于JavaScript是上个世纪90年代，由**Brendan Eich**在用了10天左右的时间发明的；虽然语言的设计者很牛逼，但是也扛不住"时间紧，任务重"。因此，JavaScript在早期有很多的设计缺陷；而它的管理组织为了修复这些缺陷，会定期的给JS添加一些新的语法特性。JavaScript前后更新了很多个版本，我们要学的是ES6这个版本。
>
> ES6是JS管理组织在2015年发布的一个版本，这个版本和之前的版本大不一样，包含了大量实用的，拥有现代化编程语言特色的内容，比如：**Promise, async/await, class继承**等。因此，我们可以认为这是一个革命性的版本。



### 定义变量

- 使用`const`来定义一个常量，常量也就是不能被修改，不能被重新赋值的变量。
- 使用`let`来定义一个变量，而不要再使用`var`了，因为`var`有很多坑；可以认为`let`就是修复了bug的`var`。比如，var允许重复声明变量而且不报错；var的作用域让人感觉疑惑。
- 最佳实践：优先用`const`，如果变量需要被修改才用`let`；要理解目前很多早期写的项目中仍然是用`var`。




### 解构赋值

> ES6 允许我们按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构（Destructuring）

- 数组的解构赋值

  ```javascript
  const arr = [1, 2, 3] //我们得到了一个数组
  let [a, b, c] = arr //可以这样同时定义变量和赋值
  console.log(a, b, c); // 1 2 3	
  ```

- 对象的解构赋值（常用）

  ```javascript
  const obj = { name: '俊哥',address:'深圳', age: '100'} //我们得到了一个对象
  let {name, age} = obj //可以这样定义变量并赋值
  console.log(name, age); //俊哥 100
  ```

- 函数参数的解构赋值（常用）

  ```javascript
  const person = { name: '小明', age: 11}
  function printPerson({name, age}) { // 函数参数可以解构一个对象
      console.log(`姓名：${name} 年龄：${age}`);
  }
  printPerson(person) // 姓名：小明 年龄：11
  ```

  ​

### 函数扩展

> ES6 对函数增加了很多实用的扩展功能。

- 参数默认值，从ES6开始，我们可以为一个函数的参数设置默认值

  ```javascript
  function foo(name, address = '深圳') {
      console.log(name, address);
  }
  foo("小明") // address将使用默认值
  foo("小王", '上海') // address被赋值为'上海'
  ```

- 箭头函数，将`function`换成`=>`定义的函数，就是箭头函数

  ```javascript
  function add(x, y) {
      return x + y
  }
  // 这个箭头函数等同于上面的add函数
  (x, y) => x +y;
  // 如果函数体有多行，则需要用大括号包裹
  (x, y) => {
      if(x >0){
          return x + y
      }else {
          return x - y
      }
  }
  ```



### Class继承

>由于js一开始被设计为函数式语言，万物皆函数。所有对象都是从函数原型继承而来，通过继承某个函数的原型来实现对象的继承。但是这种写法会让新学者产生疑惑，并且和传统的OOP语言差别很大。ES6 封装了class语法来大大简化了对象的继承。

```javascript
class Person {
    constructor(name, age){
        this.name = name
        this.age = age
    }
    // 注意：没有function关键字
    sayHello(){
        console.log(`大家好，我叫${this.name}`);
    }
}
class Man extends Person{
    constructor(name, age){
        super(name, age)
    }
    //重写父类的方法
    sayHello(){
        console.log('我重写了父类的方法！');
    }
}
let p = new Person("小明", 33) //创建对象
p.sayHello() // 调用对象p的方法，打印 大家好，我叫小明
let m = new Man("小五", 33)
m.sayHello() // 我重写了父类的方法！
```



### 总结

ES6 的新语法有很多，有人将它总结为了一本书。当然，ES6提出的只是标准，各大浏览器和node基本实现了90%以上的新特性，极其个别还没有实现。我们目前讲的是最基本的一些语法，由于你们还未了解同步和异步的概念；Promise和async/await的内容将会在后面的课程中讲解。



### 学习资源

**ES6 入门教程**：http://es6.ruanyifeng.com/

**各大浏览器的支持程度**：http://kangax.github.io/compat-table/es6/





_____





## Node的发展历史和异步IO机制

### 故事的开端

我给大家讲个故事。

很久很久以前，浏览器只能展示文本和图片，并不能像现在这样有动画，弹窗等绚丽的特效。为了提升浏览器的交互性，Javascript就被设计出来；而且很快统一了所有浏览器，成为了前端脚本开发的唯一标准。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fppzy45q91j20e60cbju7.jpg)



### 浏览器之战

随着互联网的不断普及和Web的迅速发展，几家巨头公司开始了浏览器之战。微软推出了IE系列浏览器，Mozilla推出了Firefox浏览器，苹果推出了Safari浏览器，谷歌推出了Chrome浏览器。其中，微软的IE6由于推出的早，并和Windows系统绑定，在早期成为了浏览器市场的霸主。没有竞争就没有发展。微软认为IE6已经非常完善，几乎没有可改进之处，就解散了IE6的开发团队。而Google却认为支持现代Web应用的新一代浏览器才刚刚起步，尤其是浏览器负责运行JavaScript的引擎性能还可提升10倍，于是自己偷偷开发了一个高性能的Javascript解析引擎，取名V8，并且开源。在浏览器大战中，微软由于解散了最有经验、战斗力最强的浏览器团队，被Chrome远远的抛在身后。。。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpkpnv3l03j20ht0cjq3f.jpg)



### Node的诞生

浏览器大战和Node有何关系？

话说有个叫**Ryan Dahl**的歪果仁，他的工作是用C/C++写高性能Web服务。对于高性能，异步IO、事件驱动是基本原则，但是用C/C++写就太痛苦了。于是这位仁兄开始设想用高级语言开发Web服务。他评估了很多种高级语言，发现很多语言虽然同时提供了同步IO和异步IO，但是开发人员一旦用了同步IO，他们就再也懒得写异步IO了，所以，最终，**Ryan**瞄向了JS。因为JavaScript是单线程执行，根本不能进行同步IO操作，只能使用异步IO。

另一方面，因为V8是开源的高性能JavaScript引擎。Google投资去优化V8，而他只需拿来改造一下。

于是在2009年，Ryan正式推出了基于JavaScript语言和V8引擎的开源Web服务器项目，命名为Node.js。虽然名字很土，但是，Node第一次把JavaScript带入到后端服务器开发，加上世界上已经有无数的JavaScript开发人员，所以Node一下子就火了起来。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpkq2ac6mrj20go0570te.jpg)



### 浏览器端JS和Node端JS的区别

相同点就是都使用了Javascript这门语言来开发。

浏览器端的JS，受制于浏览器提供的接口。比如浏览器提供一个弹对话框的Api，那么JS就能弹出对话框。浏览器为了安全考虑，对文件操作，网络操作，操作系统交互等功能有严格的限制，所以在浏览器端的JS功能无法强大，就像是压在五行山下的孙猴子。

NodeJs完全没有了浏览器端的限制，让Js拥有了文件操作，网络操作，进程操作等功能，和Java，Python，Php等语言已经没有什么区别了。而且由于底层使用性能超高的V8引擎来解析执行，和天然的异步IO机制，让我们编写高性能的Web服务器变得轻而易举。Node端的JS就像是被唐僧解救出来的齐天大圣一样，法力无边。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpld3t16yuj20sz08tjvd.jpg)



### 理解NodeJS的事件驱动和异步IO

NodeJS在用户代码层，只启动一个线程来运行用户的代码。每当遇到耗时的IO操作，比如文件读写，网络请求，则将耗时操作丢给底层的事件循环去执行，而自己则不会等待，继续执行下面的代码。当底层的事件循环执行完耗时IO时，会执行我们的回调函数来作为通知。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpkl36ujqnj20t00dj0u1.jpg)

**同步就是你去银行排队办业务，排队的时候啥也不能干(阻塞)；异步就是你去银行用取号机取了一个号，此时你可以自由的做其他事情，到你的时候会用大喇叭对你进行事件通知。而银行系统相当于底层的事件循环，不断的处理耗时的业务(IO)。**

但是NodeJs只有一个线程用来执行用户代码，如果耗时的是CPU计算操作，比如for循环100000000次，那么在循环的过程中，下面的代码将会无法执行，阻塞了唯一的一个线程。所以，Node适合大并发的IO处理，不适合CPU密集型的计算操作。Web开发大部分都是耗时IO操作，所以Node非常适合进行Web开发。如果真的遇到了CPU密集的计算，比如从1亿个用户中计算出哪些人和你兴趣相投的这个功能，就非常耗CPU，那这个功能就交由C++，C，Go，Java这些语言实现。像淘宝，京东这种大型网站绝对不是一种语言就可以实现的。

语言只是工具，让每一种语言做它最擅长的事，才能构建出稳定，强大的系统。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpkqetpsduj20go0cidgh.jpg)





### NodeJs能做什么？

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpkrco3i9qj20xu0enwhq.jpg)





_____







## NodeJs常用模块

### 前言

在浏览器端写JS，其实就是使用浏览器给我们提供的功能和方法来写代码。

在Node端写JS，就是用Node封装好的一系列功能模块来写代码。NodeJS封装了网络，文件，安全加密，压缩等等很多功能模块，我们只需要学会常用的一些，然后在需要的时候去查询文档即可。

### NodeJs下载与安装

下载地址：<http://nodejs.cn/download/>

安装完毕，在命令行输入：`node -v`查看node的版本，如果能成功输出，证明安装没有问题。

### npm介绍

npm是Nodejs自带的包管理器，当你安装Node的时候就自动安装了npm。通俗的讲，当我们想使用一个功能的时候，而Node本身没有提供，那么我们就可以从npm上去搜索并下载这个模块。每个开发语言都有自己的包管理器，比如，java有maven，python有pip。而npm是目前世界上生态最丰富，可用模块最多的一个社区，没有之一。基本上，你所能想到的功能都不用自己手写了，它已经在npm上等着你下载使用了。

npm的海量模块，使得我们开发复杂的NodeJs的程序变得更为简单。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpkqhvkhfvj20f703gt9a.jpg)

学习2个知识点：

- 怎么生成`package.json`
- 怎么从npm安装包，并保存到`package.json`文件中？



### 全局变量

> 全局变量是指我们在任何js文件的任何地方都可以使用的变量。

- `__dirname`：当前文件的目录
- `__filename`：当前文件的绝对路径
- `console`：控制台对象，可以输出信息
- `process`：进程对象，可以获取进程的相关信息，环境变量等
- `setTimeout/clearTimeout`：延时执行
- `setInterval/clearInterval`：定时器



### path模块

> path模块供了一些工具函数，用于处理文件与目录的路径

- `path.basename`：返回一个路径的最后一部分
- `path.dirname`：返回一个路径的目录名
- `path.extname`：返回一个路径的扩展名
- `path.join`：用于拼接给定的路径片段
- `path.normalize`：将一个路径正常化



### fs模块

> 文件操作相关的模块

- `fs.stat/fs.statSync`：访问文件的元数据，比如文件大小，文件的修改时间


- `fs.readFile/fs.readFileSync`：异步/同步读取文件

- `fs.writeFile/fs.writeFileSync`：异步/同步写入文件

- `fs.readdir/fs.readdirSync`：读取文件夹内容

- `fs.unlink/fs.unlinkSync`：删除文件

- `fs.rmdir/fs.rmdirSync`：只能删除空文件夹，思考：如何删除非空文件夹？

  > 使用`fs-extra` 第三方模块来删除。

- `fs.watchFile`：监视文件的变化




### stream操作大文件

> 传统的`fs.readFile`在读取小文件时很方便，因为它是一次把文件全部读取到内存中；假如我们要读取一个3G大小的电影文件，那么内存不就爆了么？node提供了流对象来读取大文件。
>
> 流的方式其实就是把所有的数据分成一个个的小数据块（chunk），一次读取一个chunk，分很多次就能读取特别大的文件，写入也是同理。这种读取方式就像水龙头里的水流一样，一点一点的流出来，而不是一下子涌出来，所以称为流。

```js
const fs = require('fs')
const path = require('path')

// fs.readFile('bigfile', (err, data)=>{
//     if(err){
//         throw err;
//     }
//     console.log(data.length);
// })

// 需求复制一份MobyLinuxVM.vhdx文件
const reader = fs.createReadStream('MobyLinuxVM.vhdx')
const writer = fs.createWriteStream('MobyLinuxVM-2.vhdx')
// let total = 0
// reader.on('data', (chunk)=>{
//     total += chunk.length
//     writer.write(chunk)
// })
// reader.on('end',()=>{
//     console.log('总大小：'+total/(1024*1024*1024));
// })
reader.pipe(writer);
```

**任务：用以下知识点完成大文件的拷贝。**

- `fs.createReadStream/fs.createWriteStream`
- `reader.pipe(writer)`

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fplirzoulnj20h80bn0v2.jpg)



### Promise和asnyc/await

我们知道，如果我们以同步的方式编写耗时的代码，那么就会阻塞JS的单线程，造成CPU一直等待IO完成才去执行后面的代码；而CPU的执行速度是远远大于硬盘IO速度的，这样等待只会造成资源的浪费。异步IO就是为了解决这个问题的，异步能尽可能不让CPU闲着，它不会在那等着IO完成；而是传递给底层的事件循环一个函数，自己去执行下面的代码。等磁盘IO完成后，函数就会被执行来作为通知。

虽然异步和回调的编程方式能充分利用CPU，但是当代码逻辑变的越来越复杂后，新的问题出现了。请尝试用异步的方式编写以下逻辑代码：

> 先判断一个文件是文件还是目录，如果是目录就读取这个目录下的文件，找出结尾是txt的文件，然后获取它的文件大小。

恭喜你，当你完成上面的任务时，你已经进入了终极关卡：**Callback hell回调地域!**

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpq0c9tlkzj207l05iwei.jpg)



为了解决**Callback hell**的问题，`Promise`和`async/await`诞生。

- `promise`的作用是对异步回调代码包装一下，把原来的一个回调函数拆成2个回调函数，这样的好处是可读性更好。语法如下：

  语法注意：**Promise内部的resolve和reject方法只能调用一次，调用了这个就不能再调用了那个；如果调用，则无效。**

  ```javascript
  // 创建promise对象
  let promise = new Promise((resolve, reject)=>{
      // 在异步操作成功的情况选调用resolve，失败的时候调用reject
      fs.readFile('xxx.txt',(err, data)=>{
          if(err){
              reject(err)
          }else {
              resolve(data.toString())
          }
      })
  });
  // 使用promise
  promise.then((text)=>{
      //then方法是当Promise内部调用了resolve的时候执行
  }).catch((err)=>{
      //catch方法是当Promise内部调用了reject的时候执行
      console.log(err);
  })
  ```

  ​

- `async/await`的作用是直接**将Promise异步代码变为同步的写法，注意，代码仍然是异步的**。这项革新，具有革命性的意义。

  语法要求：

  - `await`只能用在`async`修饰的方法中，但是有`async`不要求一定有`await`。
  - `await`后面只能跟`async`方法和`promise`。

  假设拥有了一个promise对象，现在使用async/await可以这样写：

  ```javascript
  async function asyncDemo() {
      try {
          // 当promise的then方法执行的时候
          let text = await promise
          // 当你用promise包装了所有的异步回调代码后，就可以一直await，真正意义实现了以同步的方式写异步代码
          console.log('异步道明执行');
      }catch (e){
          // 捕获到promise的catch方法的异常
          console.log(e);
      }
  }
  asyncDemo()
  console.log('我是同步代码');
  ```

**小任务**

使用promise和async/await来重写上面的逻辑代码，来感受一下强大的力量吧！。



**异步代码的终极写法：**

1. 先使用`promise`包装异步回调代码，可使用node提供的`util.promisify`方法；
2. 使用`async/await`编写异步代码。

![](http://ww1.sinaimg.cn/large/71b38f2cgy1fpsmbhlyykj20n304ut8u.jpg)



### http 模块

> 封装了http server 和 client的功能，就是说可以充当server处理请求，也可以发出请求。

- `http.createServer`：创建server对象
- `http.get`：执行http get请求


```js
const http = require('http')

const server = http.createServer((req, res)=>{
   // console.log(`url: ${req.url}  method: ${req.method}`)

    // res.writeHead(200, {'Content-Type':'text/plain;charset=utf-8'})
    // res.end('收到了请求')
    router(req, res)
});
```

```js
// 执行get请求
// http.get("http://www.baidu.com", (res)=>{
//     // console.log(res);
//     res.setEncoding('utf-8')
//
//     let data = ''
//     res.on('data', (chunk)=>{
//         data += chunk
//     })
//     res.on('end', ()=>{
//         console.log(data);
//     })
// })
```



### 【案例】文件浏览服务器

功能需求：启动一个服务，当用户访问服务时，给用户展示指定目录下的所有文件；如果子文件是目录，则能继续点进去浏览。

```js
const http = require('http')
const fs = require('fs')
const path = require('path')
const util = require('util')

const server = http.createServer((req, res)=>{
    console.log(req.url);
    // 过滤favicon.ico的请求
    if(req.url === '/favicon.ico'){
        res.end('');
        return
    }
    showDir(req, res)
});
server.listen(4000)

/**
 * 展示出指定目录下 的文件列表
 * @param req
 * @param res
 */
async function showDir(req, res) {
    let target = 'html'
    if(req.url !== '/'){
        target = req.url
    }

    const preaddir = util.promisify(fs.readdir)
    let files = await preaddir(path.join(__dirname, target))

    // html -> html/aaa -> html/aaa/ccc

    let lis = '';
    for (let i = 0; i < files.length; i++) {
        let file = files[i];
        let stat = await util.promisify(fs.stat)(path.join(__dirname, target, file))
        if(stat.isDirectory()){
            let p = target + '/' + file
            lis += `<li><a href="${p}">${file}</a></li>`
        }else {
            lis += `<li>${file}</li>`
        }

    }

    res.writeHead(200, {'Content-type': 'text/html;charset=utf-8'})
    res.end(makeHtml(lis))
}

function makeHtml(lis) {
    return `
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件浏览器</title>
	<style>
		*{padding:0;margin:0}
		ul{
		padding: 15px;
		background-color:#eee;
		}
		ul>li{
		    list-style: none;
		    padding: 10px;
		    background-color:#eee;
		    transition: all 1s;
		   
		}
		li:hover{
		    background-color:#aaa;
		}
		
		li:not(:first-child){
		    border-top: 1px solid #ccc;
		}
		
		
	</style>
</head>
<body>
<ul>${lis}</ul>
</body>
</html>
    `
}
```



