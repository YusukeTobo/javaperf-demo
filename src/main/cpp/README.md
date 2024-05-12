# How to setup the native module.
## Build .so file by javac and g++
```
javac -h src/main/cpp src/main/java/com/example/demo/helper/Divide.java
cd src/main/cpp
g++ --shared -fPIC -I"$JAVA_HOME"/include -I"$JAVA_HOME"/include/linux  -o /tmp/libdiv.so divide.cpp
```

## Specify what directory has the .so file, then launch Java as you like
```
export LD_LIBRARY_PATH=/tmp/
```