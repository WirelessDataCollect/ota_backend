# fota

## 1. 项目介绍
远程升级项目

## 2. CICD准备

MongoDB、MySQL等环境默认安装。下面是代码的持续集成（CI）与持续交付（CD）的准备工作。

* **安装maven**

```bash
# 下载和安装maven
wget https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar zxf apache-maven-3.6.3-bin.tar.gz
mv apache-maven-3.6.3 /usr/local/maven3/

# 在适当的位置比如文件~/.bashrc中，添加maven路径，并source更新
export M2_HOME=/usr/local/maven3
export PATH=$PATH:$M2_HOME/bin
```

* **安装Docker**

见[之前设计的自动化部署脚本——step2_installation.sh](https://github.com/Neyzoter/TestScript/blob/master/linux/auto_install4ruili/packonline/step2_installation.sh)

* **安装git**

通过git来从云端下载代码，然后进行项目的打包

```bash
# 安装git
yum install git

# 生成密钥，自行填写YOUR_EMAIL_ARR邮箱地址
# 连续按3个回车（密码默认为空），得到 id_rsa 和 id_rsa.pub 文件，在/root/.ssh 下说明生成成功
ssh-keygen -t rsa -C "YOUR_EMAIL_ARR"

# 打开 Github，登录自己的账号后
# 点击自己的头像->settings->SSH And GPG Keys->New SSH key
# 将本地 id_rsa.pub 中的内容粘贴到 Key 文本框中，随意输入一个 title(不要有中文)，点击 Add Key 即可

# clone项目
git clone git@github.com:WirelessDataCollect/ota_backend.git
# 更新项目
git pull
```

* **安装Java 1.8**

```bash
yum -y install java-1.8.0-openjdk*
```

## 3. CICD
```bash
# 更新maven依赖，一般只需要执行一次
mvn -f pom.xml dependency:copy-dependencies

# 清除target
mvn clean

# 打包项目，且不测试
mvn package -Dmaven.test.skip=true

# 将jar拷贝到Dockerfile目录下
cp ./target/*.jar ./src/main/docker/

# 进入./src/main/docker/运行Dockerfile
sudo docker build -t ota_backend:0.3.5 .

# 运行实例
# --env-file是配置文件，项目src/main/docker/文件夹下包含实例
# 端口自行配置
sudo docker run --env-file=./script/deploy.env --name ota_backend -d -p 9090:9090 -p 9092:9099 ota_backend:0.3.5
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
17. Maven下载依赖
    mvn -f pom.xml dependency:copy-dependencies
```