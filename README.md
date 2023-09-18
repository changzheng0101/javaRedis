# javaRedis

用java实现Redis，开箱即用。

## 如何运行

确保有java环境之后直接运行Main函数即可

## struct

| 类别                        | 实现方式                      |
| ------------------------- | ------------------------- |
| SDS simple dynamic string | 用java默认的string实现          |
| linked list               | 用java的 LinkedList实现（双向链表） |
| dict                      | 代码中实现                     |
| skiplist                  | 代码中实现                     |
| intset                    | 使用ArrayList实现             |
| zipList                   | 使用ArrayList实现             |

## 已经实现命令

简单String命令

- set
- get
- del
- exists
- flushall

哈希表相关

- hset 只支持3个参数
- hget 
- hgetall hashName  
- hdel hashName key
- hexists hashName key

列表List

- lpush/rpush arrayName value
- lpop/rpop arrayName value

集合Set

- sadd setName value
- smembers setName 查看集合中的元素
- srem setName value

## 开源说明

本项目作者：weixiao

本项目链接：https://github.com/changzheng0101/javaRedis

此项目是一个开源项目。此项目使用 EPL v2.0 开源许可。开源许可是具有法律效力的合同，请自觉遵守开源许可，尊重他人劳动。

根据许可，你可以对该项目进行传播、分发、修改以及二次发布，包括个人和商业用途，但我方不鼓励一切商业用途。

您必须给出源码来源，**包括作者，项目链接**（见上）等，必须使用相同的协议开源。

若此项目的源码作为项目的一部分与你私有的源码一起发布时，你可以使用其它协议，**但要声明 EPL 部分的内容并声明此部分继续遵循 EPL 协议**。

## 贡献须知

详情请见 [贡献指南](./CONTRIBUTION.md)。