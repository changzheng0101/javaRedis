# javaRedis

> 私有仓库中，，，待开放

用java实现Redis

## struct

SDS simple dynamic string: 用java默认的string实现  
linked list 用java的 LinkedList实现（双向链表）
dict  
skiplist  
intset 使用ArrayList实现  
zipList 使用ArrayList实现

## 已经实现命令

- set
- get
- del
- exists
- flushall

哈希表相关

- hset 只支持3个参数
- hget 