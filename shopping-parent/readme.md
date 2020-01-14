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
    - common模块做统一异常处理和AOP日志
    - jenkins打包部署
    - docker打包部署
     
- 用户中心权限模块


- 整合第三方插件
    - xxl-job
    - sharding-jdbc

 

