# 智能合约众筹实战
###淘宝众筹,京东众筹
https://izhongchou.taobao.com/index.htm
###分析商业模式

![](https://upload-images.jianshu.io/upload_images/4499731-e667d25c639fa9b3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 解决京东众筹的痛点
![](https://upload-images.jianshu.io/upload_images/4499731-6b0d4645002b48a5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

https://izhongchou.taobao.com/dreamdetail.htm?spm=a215p.1596646.2.14.107f75e3s6qjBj&&id=20078649

1. 期望的金钱流动
  ![](https://upload-images.jianshu.io/upload_images/4499731-43c1a551408b12aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2. 真实的金钱流动
  ![](https://upload-images.jianshu.io/upload_images/4499731-a558c314ba8d8a7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



控制金钱流动的方向, 速度.

众筹的金钱 进入智能合约中

![](https://upload-images.jianshu.io/upload_images/4499731-e49a16a2ff5c9ff1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


每一笔开销,都要发起付款申请
![](https://upload-images.jianshu.io/upload_images/4499731-d944dd17aee366ed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

投资人support付款申请. 
![](https://upload-images.jianshu.io/upload_images/4499731-4557ae97a5bdcf3e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 智能合约的其他扩展设计
选举投票决定项目是否继续进行

任何众筹项目都面临团队不负责任或者项目仅仅是一个骗局的风险，任何中心化投票系统都面临安全问题、贿赂选票和其他博弈上的缺陷。在 区块链系统中，这些风险都得到了最小化。

![](https://upload-images.jianshu.io/upload_images/4499731-941259ff580c2490.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

https://baijiahao.baidu.com/s?id=1606757323732765805&wfr=spider

# 成员变量设计

| 名称         | 数据类型  | 说明                                           |
| :----------- | :-------- | :--------------------------------------------- |
| manager      | address   | 众筹发起人地址(众筹发起人)                     |
| projectName  | string    | 项目名称                                       |
| supportMoney | uint      | 众筹参与人需要付的钱                           |
| endTime      | uint      | 默认众筹结束的时间,为众筹发起后的一个月        |
| goalMoney    | uint      | 目标募集的资金(endTime后,达不到目标则众筹失败) |
| players      | address[] | 众筹参与人的数组                               |
| requests     | Request[] | 付款请求申请的数组                             |


###函数设计
| 函数名称        | 函数说明                                     |
| :-------------- | :------------------------------------------- |
| Funding         | 构造函数                                     |
| support         | 我要支持(需要付钱)                           |
| createRequest   | 付款申请函数,由众筹发起人调用                |
| approveRequest  | 付款批准函数, 由众筹参与人调用               |
| finalizeRequest | 众筹发起人调用, 可以调用完成付款             |
| moneyBack       | 退钱函数, 由众筹发起人调用(众筹未成功时调用) |




# 众筹智能合约的构造函数



```
pragma solidity ^0.4.17;

contract Funding{
    address public manager;
    string public projectName;
    uint public supportMoney;
    uint public endTime;	
    uint public goalMoney;
    address[] public players;
     
    function Funding(string _projectName,uint _supportMoney,uint _goalMoney) public{
        projectName = _projectName;
        supportMoney = _supportMoney;
        goalMoney = _goalMoney;
        endTime = now + 4 weeks;
        manager = msg.sender;
    } 
}
```

# 众筹支持逻辑

```
	address[] public players;
	
	function support() public payable{
        require(msg.value == supportMoney );
        players.push(msg.sender);
	}

```
#测试一下support函数 
查看一下当前智能合约余额
查看当前众筹参与人员players信息


# 付款的Request结构体


| name        | type    | 说明                                   |
| :---------- | :------ | :------------------------------------- |
| description | string  | 描述这笔付款请求是干啥的               |
| money       | uint    | 花多少钱, 钱要少于balance              |
| shopAddress | address | 钱汇给谁. 真正的收钱方                 |
| complete    | bool    | true代表当前付款请求已经处理完毕       |
| ???         | ???     | 投票机制(大家先思考, 由众筹参与者决定) |

```
struct Request{
        string description;
        uint money;
        address shopAddress;
        bool complete;
	}
```

# createRequest
只有经理可以createRequest


```
    function createRequest(string _description, uint _money, address _shopAddress) public{
        Request request = Request({
            description:_description,
            money:_money,
            shopAddress:_shopAddress,
            complete:false
        });
        requests.push(request);
    }
```

# memory和storage

![](https://upload-images.jianshu.io/upload_images/4499731-22a92f7161445d05.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* storage
* memory

##两种概念
1. 智能合约如何存储数据, 是在memory还是storage
2. solidity变量如何存储数据, 是在memory还是在storage



##智能合约的数据存储
1. storage : 成员变量. 可以跨函数调用. 有点类似于硬盘
2. memory: 临时数据存储, 类似于电脑的内存 函数的参数可以理解为memory类型
  ![](https://upload-images.jianshu.io/upload_images/4499731-a531de19a32268b5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##solidity变量的数据存储
值传递和引用传递
memory值传递
storage引用传递
```
 pragma solidity ^0.4.24;
contract Test {
          uint[] public array;
          function test() public{
              array.push(1);
              array.push(2);        
              uint[] memory  b = array;
              b[0] = 33;  
              changeArray(array);
          }

          function changeArray(uint[] array) public{
              array[0] = 1;
        }
}
```

# 投票系统需求
![](https://upload-images.jianshu.io/upload_images/4499731-dff6748b17e52fa5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 防止一个人重复投票
  ![](https://upload-images.jianshu.io/upload_images/4499731-0fd9d608178317ef.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 投票的人可能有很多(成千上万人)



### 投票系统设计稿 业务逻辑

* 检查某个人是否已经在众筹参与人列表里面
> 没参与众筹没给钱,投个屁的票啊
* 检查某个人是不是已经投过票了
> 一人一票不得违反


#投票逻辑的致命问题

有一些小砖（每块1英寸）和大砖（每块5英寸）,我们想砌出来指定长度的墙，
如果用我们选择的砖块的数量能够拼接成功，则返回true；否则返回false，
例如：makeBricks(3, 1, 8) → true


## 测试用例 (每行显示一条测试用例，格式为(参数1,参数2) -> 期望结果)

```
[test.suits]
(3, 1, 8) -> true
(3, 1, 9) -> false	
(3, 2, 10) -> true		
(3, 2, 8) -> true	
(3, 2, 9) -> false	
(6, 1, 11) -> true		
(6, 0, 11) -> false	
(1, 4, 11) -> true	
(0, 3, 10) -> true	
(1, 4, 12) -> false	
(3, 1, 7) -> true	
(1, 1, 7) -> false	
(2, 1, 7) -> true	
(7, 1, 11) -> true		
(7, 1, 8) -> true	
(7, 1, 13) -> false	
(43, 1, 46) -> true	
(40, 1, 46) -> false	
(40, 2, 47) -> true	
(40, 2, 50) -> true	
(40, 2, 52) -> false		
(22, 2, 33) -> false	
(0, 2, 10) -> true		
(1000000, 1000, 1000100) -> true	
(2, 1000000, 100003) -> false	
(20, 0, 19) -> true	
(20, 0, 21) -> false	
(20, 4, 51) -> false	
(20, 4, 39) -> true
```
代码运行的效率 vs 手续费
时间和金钱


![](https://upload-images.jianshu.io/upload_images/4499731-a4940aff9132c2b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![](https://upload-images.jianshu.io/upload_images/4499731-8a6c981a1b95bb2a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#mapping重构代码
```
pragma solidity ^0.4.17;

contract Funding{
    address public manager;
    string public projectName;
    uint public  supportMoney;
    uint public endTime;	
    uint public goalMoney;
    address[] public players;
    mapping(address => bool) playersMap;
    Request[] public requests;
    
    struct Request{
        string description;
        uint money;
        address shopAddress;
        bool complete;
        mapping(address=>bool) approvedPlayers;
        uint count;
    }
    
    function createRequest(string _description, uint _money, address _shopAddress) public{
        require(msg.sender == manager);
        Request memory request = Request({
            description:_description,
            money:_money,
            shopAddress:_shopAddress,
            complete:false,
            count:0
        });
        requests.push(request);
        playersMap[msg.sender] = true;
    }
     
    function approveRequest(uint id) public{
          require(playersMap[msg.sender]);
          require(!requests[id].approvedPlayers[msg.sender]);
          requests[id].count++;
          requests[id].approvedPlayers[msg.sender] = true;
    }
     
    function finalizeRequest(uint id) public{
        require(msg.sender == manager);
        require(requests[id].count*2>players.length);
        require(requests[id].money<=this.balance);
        require(!requests[id].complete );
        requests[id].shopAddress.transfer(requests[id].money);
        requests[id].complete = true;
    } 
     
    function Funding(string _projectName,uint _supportMoney,uint _goalMoney) public{
        projectName = _projectName;
        supportMoney = _supportMoney;
        goalMoney = _goalMoney;
        endTime = now + 4 weeks;
        manager = msg.sender;
    } 
    
    function support() public payable{
        require(msg.value == supportMoney );
        players.push(msg.sender);
	}
	
	function getAccountBalance() public view returns(uint){
	    return this.balance;
	}
}
```
#智能合约的部署
1. 代理模式, 服务器代理部署(钱服务器花)
2. 用户直接模式, 用户直接部署.(钱用户花)

```
contract FundingFactory{
    
    address[] public fundingAddress; 
    function createFunding(string _projectName,uint _supportMoney,uint _goalMoney) public{
        address funding = new Funding(_projectName,_supportMoney,_goalMoney,msg.sender);
        fundingAddress.push(funding);
    }
    
}
```


#附件
```
第一版
pragma solidity ^0.4.17;

contract Funding{
    
     //众筹发起人地址(众筹发起人)
     address public manager;
     //项目名称
     string public projectName;
     //众筹参与人需要付的钱
     uint public supportMoney;
     // 众筹结束的时间  
     uint public endTime;
     // 目标募集的资金(endTime后,达不到目标则众筹失败)
     uint public goalMoney;
     // 众筹参与人的数组
     address[] public players;
     //付款请求申请的数组(由众筹发起人申请)
     Request[] public requets;
     
     // 付款请求的结构体  
     struct Request{
          string description; // 为什么要付款 
          uint money; // 花多少钱 
          address shopAddress; //  卖家的钱包 地址  
          bool complete;  //  付款是否已经完成 
          address[] votedAddress; // 哪些已经投过票的人 
          uint  voteCount; // 投票的总的总数 
     }
     
     function createRequest( string _description, uint _money, address _shopAddress) public  onlyManagerCanCall{
         
         Request memory request = Request({
             description:_description,
             money:_money,
             shopAddress:_shopAddress,
             complete:false,
             votedAddress: new address[](0),
             voteCount : 0
         });
         requets.push(request);
     }
     
     //  众筹参与人员批准某一笔付款 ( index数组的下标 ) 
     function approveRequest(uint index) public {
         Request storage request = requets[index];
         bool supporter = false;
         //1.  检查某个人是否已经在众筹参与人列表里面
          for(uint i = 0;i<players.length;i++){
              if( players[i] == msg.sender){
                  supporter = true;
              }
          }
          require(supporter);
         //2 .检查某个人是不是已经投过票了
         bool voted = false;
         for(uint j = 0; j<request.votedAddress.length;j++){
              if( request.votedAddress[j] == msg.sender){
                  voted = true;
              }
         }
          require(!voted);
          request. voteCount ++;
          request.votedAddress.push(msg.sender);
     }
     
     
     //  构造函数  
     function Funding(string _projectName,uint _supportMoney,uint _goalMoney) public{
         manager = msg.sender;
         projectName = _projectName;
         supportMoney = _supportMoney;
         goalMoney = _goalMoney;
         endTime = now + 4 weeks;
     }
     //  参与人支持众筹 
     function support() public payable{
         require(msg.value == 79);
         players.push(msg.sender);
     }
     // 返回参与人的数量 
     function getPlayersCount() public view returns(uint){
         return players.length;
     }
     
     function getPlayers() public view returns(address[]){
         return players;
     }
     
     function getTotalBalance() public view returns (uint){
         return this.balance;
     }
     
     function getRemainDays() public view returns(uint){
         return (endTime - now)/24/60/60;
     }
     
     modifier onlyManagerCanCall(){
         require(msg.sender == manager);
         _;
     }
     
}

```


##全套代码
```
pragma solidity ^0.4.17;

contract FundingFactory{
    
    //存储所有已经部署的智能合约的地址 
    address[] public fundings;
    
    function deploy(string _projectName,uint _supportMoney,uint _goalMoney) public{
        address funding = new Funding(_projectName,_supportMoney,_goalMoney, msg.sender);
        fundings.push(funding);
    }
}

contract Funding{
    
     //众筹发起人地址(众筹发起人)
     address public manager;
     //项目名称
     string public projectName;
     //众筹参与人需要付的钱
     uint public supportMoney;
     // 众筹结束的时间  
     uint public endTime;
     // 目标募集的资金(endTime后,达不到目标则众筹失败)
     uint public goalMoney;
     // 众筹参与人的数组
     address[] public players;
     mapping(address=>bool)  playersMap;
     
     
     //付款请求申请的数组(由众筹发起人申请)
     Request[] public requets;
     
     // 付款请求的结构体  
     struct Request{
          string description; // 为什么要付款 
          uint money; // 花多少钱 
          address shopAddress; //  卖家的钱包 地址  
          bool complete;  //  付款是否已经完成 
          mapping(address=>bool) votedmap; // 哪些已经投过票的人 
          uint  voteCount; // 投票的总的总数 
     }
     
     function createRequest( string _description, uint _money, address _shopAddress) public  onlyManagerCanCall{
         
         Request memory request = Request({
             description:_description,
             money:_money,
             shopAddress:_shopAddress,
             complete:false,
             voteCount : 0
         });
         requets.push(request);
     }
     
     //  众筹参与人员批准某一笔付款 ( index数组的下标 ) 
     function approveRequest(uint index) public {
         Request storage request = requets[index];
         //1.  检查某个人是否已经在众筹参与人列表里面
          require(playersMap[msg.sender]);
         //2 .检查某个人是不是已经投过票了
          require(!requets[index].votedmap[msg.sender]);
          request. voteCount ++;
          requets[index].votedmap[msg.sender] = true;
     }
     
     //众筹发起人调用, 可以调用完成付款 index  下标 
     function finalizeRequest(uint index) public  onlyManagerCanCall {
          Request storage request = requets[index];
          // 付款 必须是 未 处理的 
          require(!request.complete);
          //  至少一半以上的参与者 同意付款 
          require(request.voteCount * 2 >players.length );
          // 打钱  转账 
          require(this.balance>=request.money);
          request.shopAddress.transfer(request.money);
          request.complete = true;
     }
     
     
     //  构造函数  
     function Funding(string _projectName,uint _supportMoney,uint _goalMoney, address _address) public{
         manager = _address;
         projectName = _projectName;
         supportMoney = _supportMoney;
         goalMoney = _goalMoney;
         endTime = now + 4 weeks;
     }
     //  参与人支持众筹 
     function support() public payable{
         require(msg.value == supportMoney);
         players.push(msg.sender);
         //设置mapping集合
         playersMap[msg.sender] = true;
     }
     // 返回参与人的数量 
     function getPlayersCount() public view returns(uint){
         return players.length;
     }
     
     function getPlayers() public view returns(address[]){
         return players;
     }
     
     function getTotalBalance() public view returns (uint){
         return this.balance;
     }
     
     function getRemainDays() public view returns(uint){
         return (endTime - now)/24/60/60;
     }
     
     modifier onlyManagerCanCall(){
         require(msg.sender == manager);
         _;
     }
     
}
```