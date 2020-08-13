# 立项
- 想整一套自己的springcloud架构，采取商城的项目进行模拟。后期可以不断将自己所学应用整合到该项目中。
- 目标： 搭建一套自己的商城系统。


# 后端技术架构
- springcloud + springcloud-alibaba + nacos 
- mybatis + mybaits-plus + sharding-jdbc
- mysql + redis + mongodb + druid
- security + jwt
- rocketmq
- xxl-job
- elasticsearch
- OSS
- EFK


# 前端
- vue.js
- iview-admin的UI框架
- 网站 + 小程序 


# 部署
- jenkins打包部署
- docker打包部署
- k8s


# 服务拆分     
- 公共子模块

    - REDIS子模块
    
    - ROCKETMQ子模块
    
    - MONGODB子模块

- 基础服务

    - CDP基础数据服务
    
    - PAY支付服务
    
    - WX微信服务
    
    - SMS短信服务
    
    - CALL呼叫服务
    
    - JOB调度服务
    
    - OSS阿里云存储服务

- 业务服务

    - USER用户服务
        - 用户、角色、菜单、用户角色、角色菜单表设计[OK]
        - 整合springvolidate，并自定义常用参数校验注解[OK]
        - 用户、角色、权限的CRUD[OK]
        - 整合jwt+security
    
    - GOODS商品服务
    
    - ORDER订单服务
    
    - STORAGE库存服务
    
    - LOGISTICS物流服务

    - CMS内容管理服务
    

 # 规划
 
 - V1.0【脚手架】
     - 项目架构搭建
     - springcloud整合springcloud-alibaba[OK]	
     - 整合nacos[OK]
     - 用户服务的CRUD[OK]
     - 整合springboot-admin监控台[OK]
     - 整合网关[TODO网关的配置文件写在项目中没问题，但是放到nacos上就无法做到路由转发TODO]
     - 商品服务使用FEIGN调用用户服务[OK]
     - common子模块做统一异常处理和AOP日志[OK]
     - 用户模块用户角色菜单CRUD[OK]
     - 整合JWT+SECURITY  并整好到网关工程[80%]
     - linux部署，并打一个TAG
 
     - 整合redis子模块，并封装redis工具类
     - 整合xxl-job
 
     

