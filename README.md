# fota

## 项目介绍
远程升级项目

## 项目打包
以下过程均可通过IDEA图形界面实现，

```bash
# 清除target
mvn clean

# 打包项目
mvn package

# 将jar拷贝到Dockerfile目录下
cp ./target/*.jar ./src/main/docker/

# 进入./src/main/docker/运行Dockerfile
docker build -t ota_backend:0.3.5 .

```

其他maven语句拓展，

```
1. 创建Maven的普通java项目： 
   mvn archetype:create 
   -DgroupId=packageName 
   -DartifactId=projectName  
2. 创建Maven的Web项目：   
    mvn archetype:create 
    -DgroupId=packageName    
    -DartifactId=webappName 
    -DarchetypeArtifactId=maven-archetype-webapp    
3. 编译源代码： mvn compile 
4. 编译测试代码：mvn test-compile    
5. 运行测试：mvn test   
6. 产生site：mvn site   
7. 打包：mvn package   
8. 在本地Repository中安装jar：mvn install 
9. 清除产生的项目：mvn clean   
10. 生成eclipse项目：mvn eclipse:eclipse  
11. 生成idea项目：mvn idea:idea  
12. 组合使用goal命令，如只打包不测试：mvn -Dtest package   
13. 编译测试的内容：mvn test-compile  
14. 只打jar包: mvn jar:jar  
15. 只测试而不编译，也不测试编译：mvn test -skipping compile -skipping test-compile 
      ( -skipping 的灵活运用，当然也可以用于其他组合命令)  
16. 清除eclipse的一些系统设置:mvn eclipse:clean  
```