# javaperf-demo
Try to understand capablities of JFR or other debugging tools.

You may need `ulimit -c unlimited` before launching the java program.

## How to run this demo application
```
$ ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-XX:StartFlightRecording=dumponexit=true"
```

## Endpoints
### High CPU
```
curl localhost:8080/userLoad
```

### Slow endpoint
```
curl localhost:8080/sleep?seconds=100
```

### Slow dependency
```
curl localhost:8080/testHttp
```

### Compete locking
```
curl localhost:8080/blocking
```

### JVM Crash
See also `https://github.com/YusukeTobo/javaperf-demo/blob/main/src/main/cpp/README.md`
```
curl localhost:8080/crash
```
