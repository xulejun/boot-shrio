1.Shiro自定义配置：ShiroConfig.java
                ——拦截器拦截所有请求
                ——设置安全管理器
                ——给过滤器设置安全管理器
                ——配置公共资源和验证资源
                ——给自定义Realm配置：
                    ——MD5加密算法，HashedCredentialsMatcher.setHashAlgorithmName("MD5")
                    ——散列次数setHashIterations(1024)
                    ——通过自定义Realm开启缓存管理setCacheManager

2.自定义Realm域：CustomerRealm.java
                ——用户认证
                    ——根据形参AuthenticationToken获取身份信息Principal
                    ——通过Principal查询数据库，返回用户信息（用户名，密码，加密盐）
                    ——在UserController.java中进行信息校验
                ——用户授权
                    ——SimpleAuthorizationInfo
                    ——根据身份信息获取角色和权限
                    ——遍历该身份所拥有的的角色和所有权限

3.将用户认证和授权的信息放入Redis缓存：RedisCache.java
                ——实现Cache接口，重写put、get、clear、remove

4.自定义Redis缓存管理器：RedisCacheManager.java
                ——具体实现在RedisCache.java

5.处理身份验证：UserController.java
                ——声明Subject主体，调用login（）方法进行校验

6.生成Salt盐：SaltUtil.java
                ——随机字符串，用StringBuilder存储

7.MD5+随机Salt盐整合：UserServiceImpl.java
                ——new Md5Hash(user.getPassword(), salt, 1024); // 1024为散列次数

8.生成登录验证码：ImageUtil.java

