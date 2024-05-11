#include <iostream>
#include "com_example_demo_helper_Divide.h"

JNIEXPORT void JNICALL Java_com_example_demo_helper_Divide_ndiv
  (JNIEnv *, jobject, jint) {
    div(5, 0);
  }