# SpringbootDemo
基于Springboot+mybatis+redis+Thymeleaf的电商后端系统Demo，商家可对商品、库存进行管理，客户可购买商品。包含基本的增删改查功能。
## 2021.5.21更新
### 增
新增商品、新增订单、新增库存等。
### 删
逻辑删除，数据表中增加一个是否删除的字段。
### 改
规定有些字段不允许被修改。
### 查
1. 单条记录查询；
2. 多条记录查询，要求分页。
3. 多条记录查询可选择查询条件：根据金额范围查询、根据id批量查询、根据title批量查询、根据支付状态查询。
### 引入redis
1. 使用缓存，保存对象。
## 2021.6.1更新
1. 使用Lombok；
2. 使用validate做参数校验；
3. 使用Swagger生成接口文档。
## 2021.6.2更新
### 实现生产者消费者模型
新增一张商品表，优化库存表，新增商品相关接口逻辑。使用 mysql事务的特性，采用悲观锁避免商品的超卖问题。
## 2021.6.3更新
优化生者消费者模型：商家生产一个商品需要对商品表和库存表进行同步，消费者消费同样需要对两个表进行同步。主要用了Spring的Transactional
注解，在业务层保证事务性。
优化代码。
## 2021.6.4更新
新增订单详情表，订单主表和订单详情表是一对多的关系。完成用户根据商品id下单时订单主表、 订单详情表、商品表和库存表相应改变，将上述
动作封装成事务。
mysql的innodb引擎对于update操作有排他锁，在数据库层面保证了线程安全。
## 2021.6.7更新
引入Thymeleaf模板引擎，编写部分前端代码。
## 2021.6.8更新
重构优惠计算方式，采用策略模式增强扩展性。完善消费者消费的逻辑，消费者能够购买多个不同
种类的商品，订单表、订单项目表、库存表、商品表同时更新。完成编写部分消费者购买商品的前端代码。
## 2021.6.10更新
完善策略模式，完成消费者购买商品前端相关代码及逻辑。
## 2021.6.15更新
引入Nacos，配置中心和服务的注册和发现示例代码写在StoreRestController中。
## 2021.6.16更新
引入Mongo DB。