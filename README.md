# SpringbootDemo
基于Springboot的mini电商后端系统Demo，商家可对商品、库存进行管理，客户可购买商品。包含基本的增删改查功能。
## 2021.5.21更新
### 增
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