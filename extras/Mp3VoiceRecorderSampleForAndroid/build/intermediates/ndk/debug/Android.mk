LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := mp3lame
LOCAL_SRC_FILES := \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/Note.txt \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/com_uraroji_garage_android_lame_SimpleLame.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/Android.mk \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/newmdct.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/VbrTag.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/presets.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/quantize_pvt.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/reservoir.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/tables.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/encoder.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/mpglib_interface.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/gain_analysis.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/lame.rc \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/version.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/fft.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/vbrquantize.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/takehiro.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/bitstream.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/quantize.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/id3tag.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/psymodel.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/lame.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/set_get.c \
	/home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni/lame-3.98.4_libmp3lame/util.c \

LOCAL_C_INCLUDES += /home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/main/jni
LOCAL_C_INCLUDES += /home/atabouraya/robusta/Projects/Recorder/extras/Mp3VoiceRecorderSampleForAndroid/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
