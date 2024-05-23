# javaperf-demo
Try to understand capablities of JFR or other debugging tools.

You may need `ulimit -c unlimited` before launching the java program.

## How to run this demo application
```
$ ./mvnw spring-boot:run
```

## Endpoints
### High CPU
```
curl localhost:8080/userLoad
```
