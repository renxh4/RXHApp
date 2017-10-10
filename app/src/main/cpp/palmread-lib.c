//
// Created by xiaohui on 2017/10/10.
//

#include "palmread-lib.h"
#include "jni.h" #include <stdlib.h>
#include <string.h>

JNIEXPORT jstring JNICALL
Java_com_example_jh_rxhapp_utils_NDKHelper_GetStringFromC(JNIEnv *env, jclass type,
                                                                jstring str_) {
    const char *a = (*env)->GetStringUTFChars(env, str_, 0);
    // TODO
    char * b = " from c" ;
    char *result = malloc(strlen(a)+strlen(b)+1);
    strcpy(result, a);
    strcat(result, b);

    (*env)->ReleaseStringUTFChars(env, str_, a);

    return (*env)->NewStringUTF(env, result);
}