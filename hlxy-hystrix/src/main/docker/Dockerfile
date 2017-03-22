FROM registry.cn-hangzhou.aliyuncs.com/peachyy/java8base
VOLUME /tmp
ADD hlxy-hystrix-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 3555
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]