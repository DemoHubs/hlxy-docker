# hlxy-docker
一个基于docker springcloud的应用

部署流程
1、pull源码

``
 https://github.com/DemoHubs/hlxy-docker.git
``
2、切换到hlxy-docker目录
``
cd hlxy-docker
``
3、构建maven 这里会构建image所以需要先拉取一下基础的java8镜像registry.cn-hangzhou.aliyuncs.com/peachyy/java8base
``
  mvn clean install
``
4、使用docker-compose 创建并运行容器
``
 docker-compose up -d
``

spring admin url: http://ip:3444/

spring Eureka url:http://ip:3000