## 智慧停车系统

- test_alipay 跑通沙箱测试

- iot.server 对接硬件 实现主动发送报文和接收报文 https://blog.csdn.net/Crazy_Cw/article/details/126613967
    - 存在问题：硬件设备是否要加入数据库中

可参考： https://gitee.com/iteaj/iot
https://blog.csdn.net/zhangleiyes123/article/details/103871450

- test_mp 使用 MyBatisPlus 的自定义TableNameHandler 实现分表查询
    - 问题：每次都要传入 currentId 进行mod运算 有点费劲，后续看看其他的写法，以及使用 ThreadLocal存在上下文切换问题
    - 使用分布式ID? TODO: 了解分布式事务