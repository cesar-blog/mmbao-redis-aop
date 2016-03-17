# mmbao-redis-aop

利用AOP切面，注解的形式，改造原有缓存方案。.

其优点是低侵入性、低耦合性。只需配置简单的spring xml配置文件，以及注解，就能够实现缓存目的。

并且，针对Redis三种方案，分别是单机/分片/集群，分别写了demo.


本demo数据库使用的是Oracle，持久层是Mybatis，dao层的文件是用Mybatis工具生成。

在使用之前，你配置你的Oracle数据源，再配置Redis数据源，再配置自己的dao层文件即可。

Redis共支持5种形式的数据格式，本demo只实现string,set两种格式。序列化方案选用的是json。

如需更多的数据格式支持，只要自行添加即可。