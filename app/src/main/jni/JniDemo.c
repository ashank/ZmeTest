#include <com_funhotel_hmvp_test_jni_JniDemo.h>

JNIEXPORT jstring JNICALL Java_com_funhotel_hmvp_test_jni_JniDemo_getString

  (JNIEnv * env, jclass cla){
    return (*env)->NewStringUTF(env, "hello  JNI");
  }

  JNIEXPORT jint JNICALL Java_com_funhotel_hmvp_test_jni_JniDemo_calendar

   (JNIEnv * enc, jobject cla, jint a, jint b){

    return a+b;
   }

