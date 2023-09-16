# Reactive REST API With Spring, Kotlin and Coroutines

## Build & Run native image

### Requirements
- GraalVM installed and set as environment variable `JAVA_HOME` (sdkman easiest variant)
- native-image installed (use `gu`) and reachable

### Steps
- Build using `./gradlew :nativeCompile`
- Run using `./build/native/nativeCompile/spring-boot-kotlin-reactive-example`

## Spring recommended way

```shell
# Use Cloud Native Buildpacks

./gradlew bootBuildImage --imageName=pawga777/r2-dictionary:1.0.0  


```

```
https://www.baeldung.com/spring-rest-openapi-documentation
https://www.javainuse.com/spring/boot_swagger3
https://habr.com/ru/post/541592/
Open API: http://localhost:8080/swagger-ui/index.html#/

http://localhost:8080/actuator
http://localhost:8080/actuator/health

```

# Run the image
<b>1 )</b> Run the project though this command shown below (docker)
```
docker run -it --rm -p 8080:8080 pawga777/r2-dictionary:1.0.0
```

<b>2 )</b> Run the project though this command shown below (docker-compose)
```
    docker-compose up -d
```

### Known Issues
- Native tests not working due to mock dependencies



## Further readings
* [Exposing a Helpful Info Endpoint with Spring Boot Actuator](https://reflectoring.io/spring-boot-info-endpoint/)
* [Gradle user manual](https://docs.gradle.org/)
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
* [Ways to set environment variables in Compose](https://docs.docker.com/compose/environment-variables/set-environment-variables/)  
* [Codersee's article](https://codersee.com/reactive-rest-api-with-spring-kotlin-and-coroutines/)

