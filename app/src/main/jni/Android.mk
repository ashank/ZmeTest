LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
APP_PLATFORM =android-14
LOCAL_MODULE :=zmeImage
LOCAL_SRC_FILES :=JniDemo.c
include $(BUILD_SHARED_LIBRARY)