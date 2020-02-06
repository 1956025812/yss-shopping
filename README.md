# 立项
- 想整一套自己的springcloud架构，采取商城的项目进行模拟。后期可以不断将自己所学应用整合到该项目中。
- 目标： 搭建一套自己的商城系统。


# 后端技术架构
- springcloud + springcloud-alibaba + nacos 
- mybatis + mybaits-plus + sharding-jdbc
- mysql + redis + mongodb
- security
- rabbitmq
- xxl-job


# 前端
- vue.js
- iview-admin的UI框架


# 规划时间
- 项目架构搭建
    - springcloud-alibaba引入[OK]	
    - nacos相关引入[OK]
    - 用户服务的CRUD[OK]
    - 商品服务使用FEIGN调用用户服务[OK]
    - 网关[TODO网关的配置文件写在项目中没问题，但是放到nacos上就无法做到路由转发TODO]
    - springboot-admin监控台[OK]
    - common模块做统一异常处理和AOP日志[OK]
    - 封装redis工具类[OK]
    - 整合调度工程yss-shopping-schedule成功，注意：要下载服务端，我的git上的xxl-job项目，这个抽出来共用[OK]
    - jenkins打包部署
    - docker打包部署
     
- USER用户工程
    - 用户、角色、菜单、用户角色、角色菜单表设计[OK]
    - 用户表的CRUD[OK]
    - 整合springvolidate，并自定义常用参数校验注解[OK]
    - 整合redis[OK]

- 整合第三方插件
    - sharding-jdbc

 

