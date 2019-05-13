FROM java:8
MAINTAINER ruili
ADD *.jar app.jar
EXPOSE 9098

#ENTRYPOINT ["java","-Xmx3072m","-Duser.timezone=GMT+08","-jar","/app.jar"]
ENTRYPOINT ["java","-Xmx800m","-Duser.timezone=GMT+08","-jar","/app.jar"]
