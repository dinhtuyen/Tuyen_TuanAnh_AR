package com.qualcomm.vuforia;

import java.nio.ByteBuffer;

class VuforiaJNI {
    public static final native int Area_getType(long j, Area area);

    public static final native long CameraCalibration_getDistortionParameters(long j, CameraCalibration cameraCalibration);

    public static final native long CameraCalibration_getFocalLength(long j, CameraCalibration cameraCalibration);

    public static final native long CameraCalibration_getPrincipalPoint(long j, CameraCalibration cameraCalibration);

    public static final native long CameraCalibration_getSize(long j, CameraCalibration cameraCalibration);

    public static final native boolean CameraDevice_deinit(long j, CameraDevice cameraDevice);

    public static final native long CameraDevice_getCameraCalibration(long j, CameraDevice cameraDevice);

    public static final native long CameraDevice_getInstance();

    public static final native int CameraDevice_getNumVideoModes(long j, CameraDevice cameraDevice);

    public static final native long CameraDevice_getVideoMode(long j, CameraDevice cameraDevice, int i);

    public static final native boolean CameraDevice_init__SWIG_0(long j, CameraDevice cameraDevice, int i);

    public static final native boolean CameraDevice_init__SWIG_1(long j, CameraDevice cameraDevice);

    public static final native boolean CameraDevice_selectVideoMode(long j, CameraDevice cameraDevice, int i);

    public static final native boolean CameraDevice_setFlashTorchMode(long j, CameraDevice cameraDevice, boolean z);

    public static final native boolean CameraDevice_setFocusMode(long j, CameraDevice cameraDevice, int i);

    public static final native boolean CameraDevice_start(long j, CameraDevice cameraDevice);

    public static final native boolean CameraDevice_stop(long j, CameraDevice cameraDevice);

    public static final native long CylinderTargetResult_SWIGUpcast(long j);

    public static final native long CylinderTargetResult_getClassType();

    public static final native long CylinderTargetResult_getTrackable(long j, CylinderTargetResult cylinderTargetResult);

    public static final native long CylinderTarget_SWIGUpcast(long j);

    public static final native float CylinderTarget_getBottomDiameter(long j, CylinderTarget cylinderTarget);

    public static final native long CylinderTarget_getClassType();

    public static final native float CylinderTarget_getSideLength(long j, CylinderTarget cylinderTarget);

    public static final native float CylinderTarget_getTopDiameter(long j, CylinderTarget cylinderTarget);

    public static final native String CylinderTarget_getUniqueTargetId(long j, CylinderTarget cylinderTarget);

    public static final native boolean CylinderTarget_setBottomDiameter(long j, CylinderTarget cylinderTarget, float f);

    public static final native boolean CylinderTarget_setSideLength(long j, CylinderTarget cylinderTarget, float f);

    public static final native boolean CylinderTarget_setTopDiameter(long j, CylinderTarget cylinderTarget, float f);

    public static final native long DataSet_createMultiTarget(long j, DataSet dataSet, String str);

    public static final native long DataSet_createTrackable(long j, DataSet dataSet, long j2, TrackableSource trackableSource);

    public static final native boolean DataSet_destroy(long j, DataSet dataSet, long j2, Trackable trackable);

    public static final native boolean DataSet_exists(String str, int i);

    public static final native int DataSet_getNumTrackables(long j, DataSet dataSet);

    public static final native long DataSet_getTrackable(long j, DataSet dataSet, int i);

    public static final native boolean DataSet_hasReachedTrackableLimit(long j, DataSet dataSet);

    public static final native boolean DataSet_isActive(long j, DataSet dataSet);

    public static final native boolean DataSet_load(long j, DataSet dataSet, String str, int i);

    public static final native long Frame_getImage(long j, Frame frame, int i);

    public static final native int Frame_getIndex(long j, Frame frame);

    public static final native int Frame_getNumImages(long j, Frame frame);

    public static final native double Frame_getTimeStamp(long j, Frame frame);

    public static final native boolean ImageTargetBuilder_build(long j, ImageTargetBuilder imageTargetBuilder, String str, float f);

    public static final native int ImageTargetBuilder_getFrameQuality(long j, ImageTargetBuilder imageTargetBuilder);

    public static final native long ImageTargetBuilder_getTrackableSource(long j, ImageTargetBuilder imageTargetBuilder);

    public static final native void ImageTargetBuilder_startScan(long j, ImageTargetBuilder imageTargetBuilder);

    public static final native void ImageTargetBuilder_stopScan(long j, ImageTargetBuilder imageTargetBuilder);

    public static final native long ImageTargetResult_SWIGUpcast(long j);

    public static final native long ImageTargetResult_getClassType();

    public static final native int ImageTargetResult_getNumVirtualButtons(long j, ImageTargetResult imageTargetResult);

    public static final native long ImageTargetResult_getTrackable(long j, ImageTargetResult imageTargetResult);

    public static final native long ImageTargetResult_getVirtualButtonResult__SWIG_0(long j, ImageTargetResult imageTargetResult, int i);

    public static final native long ImageTargetResult_getVirtualButtonResult__SWIG_1(long j, ImageTargetResult imageTargetResult, String str);

    public static final native long ImageTarget_SWIGUpcast(long j);

    public static final native long ImageTarget_createVirtualButton(long j, ImageTarget imageTarget, String str, long j2, Area area);

    public static final native boolean ImageTarget_destroyVirtualButton(long j, ImageTarget imageTarget, long j2, VirtualButton virtualButton);

    public static final native long ImageTarget_getClassType();

    public static final native String ImageTarget_getMetaData(long j, ImageTarget imageTarget);

    public static final native int ImageTarget_getNumVirtualButtons(long j, ImageTarget imageTarget);

    public static final native long ImageTarget_getSize(long j, ImageTarget imageTarget);

    public static final native String ImageTarget_getUniqueTargetId(long j, ImageTarget imageTarget);

    public static final native long ImageTarget_getVirtualButton__SWIG_0(long j, ImageTarget imageTarget, int i);

    public static final native long ImageTarget_getVirtualButton__SWIG_1(long j, ImageTarget imageTarget, String str);

    public static final native boolean ImageTarget_setSize(long j, ImageTarget imageTarget, long j2, Vec2F vec2F);

    public static final native long ImageTracker_SWIGUpcast(long j);

    public static final native boolean ImageTracker_activateDataSet(long j, ImageTracker imageTracker, long j2, DataSet dataSet);

    public static final native long ImageTracker_createDataSet(long j, ImageTracker imageTracker);

    public static final native boolean ImageTracker_deactivateDataSet(long j, ImageTracker imageTracker, long j2, DataSet dataSet);

    public static final native boolean ImageTracker_destroyDataSet(long j, ImageTracker imageTracker, long j2, DataSet dataSet);

    public static final native int ImageTracker_getActiveDataSetCount(long j, ImageTracker imageTracker);

    public static final native long ImageTracker_getActiveDataSet__SWIG_0(long j, ImageTracker imageTracker, int i);

    public static final native long ImageTracker_getActiveDataSet__SWIG_1(long j, ImageTracker imageTracker);

    public static final native long ImageTracker_getClassType();

    public static final native long ImageTracker_getImageTargetBuilder(long j, ImageTracker imageTracker);

    public static final native long ImageTracker_getTargetFinder(long j, ImageTracker imageTracker);

    public static final native boolean ImageTracker_persistExtendedTracking(long j, ImageTracker imageTracker, boolean z);

    public static final native boolean ImageTracker_resetExtendedTracking(long j, ImageTracker imageTracker);

    public static final native int Image_getBufferHeight(long j, Image image);

    public static final native int Image_getBufferWidth(long j, Image image);

    public static final native int Image_getFormat(long j, Image image);

    public static final native int Image_getHeight(long j, Image image);

    public static final native ByteBuffer Image_getPixels(long j, Image image);

    public static final native int Image_getStride(long j, Image image);

    public static final native int Image_getWidth(long j, Image image);

    public static final native long MarkerResult_SWIGUpcast(long j);

    public static final native long MarkerResult_getClassType();

    public static final native long MarkerResult_getTrackable(long j, MarkerResult markerResult);

    public static final native long MarkerTracker_SWIGUpcast(long j);

    public static final native long MarkerTracker_createFrameMarker(long j, MarkerTracker markerTracker, int i, String str, long j2, Vec2F vec2F);

    public static final native boolean MarkerTracker_destroyMarker(long j, MarkerTracker markerTracker, long j2, Marker marker);

    public static final native long MarkerTracker_getClassType();

    public static final native long MarkerTracker_getMarker(long j, MarkerTracker markerTracker, int i);

    public static final native int MarkerTracker_getNumMarkers(long j, MarkerTracker markerTracker);

    public static final native long Marker_SWIGUpcast(long j);

    public static final native long Marker_getClassType();

    public static final native int Marker_getMarkerId(long j, Marker marker);

    public static final native int Marker_getMarkerType(long j, Marker marker);

    public static final native long Marker_getSize(long j, Marker marker);

    public static final native boolean Marker_setSize(long j, Marker marker, long j2, Vec2F vec2F);

    public static final native float[] Matrix34F_data_get(long j, Matrix34F matrix34F);

    public static final native void Matrix34F_data_set(long j, Matrix34F matrix34F, float[] fArr);

    public static final native float[] Matrix44F_data_get(long j, Matrix44F matrix44F);

    public static final native void Matrix44F_data_set(long j, Matrix44F matrix44F, float[] fArr);

    public static final native long MultiTargetResult_SWIGUpcast(long j);

    public static final native long MultiTargetResult_getClassType();

    public static final native int MultiTargetResult_getNumPartResults(long j, MultiTargetResult multiTargetResult);

    public static final native long MultiTargetResult_getPartResult__SWIG_0(long j, MultiTargetResult multiTargetResult, int i);

    public static final native long MultiTargetResult_getPartResult__SWIG_1(long j, MultiTargetResult multiTargetResult, String str);

    public static final native long MultiTargetResult_getTrackable(long j, MultiTargetResult multiTargetResult);

    public static final native long MultiTarget_SWIGUpcast(long j);

    public static final native int MultiTarget_addPart(long j, MultiTarget multiTarget, long j2, Trackable trackable);

    public static final native long MultiTarget_getClassType();

    public static final native int MultiTarget_getNumParts(long j, MultiTarget multiTarget);

    public static final native boolean MultiTarget_getPartOffset(long j, MultiTarget multiTarget, int i, long j2, Matrix34F matrix34F);

    public static final native long MultiTarget_getPart__SWIG_0(long j, MultiTarget multiTarget, int i);

    public static final native long MultiTarget_getPart__SWIG_1(long j, MultiTarget multiTarget, String str);

    public static final native boolean MultiTarget_removePart(long j, MultiTarget multiTarget, int i);

    public static final native boolean MultiTarget_setPartOffset(long j, MultiTarget multiTarget, int i, long j2, Matrix34F matrix34F);

    public static final native long Obb2D_getCenter(long j, Obb2D obb2D);

    public static final native long Obb2D_getHalfExtents(long j, Obb2D obb2D);

    public static final native float Obb2D_getRotation(long j, Obb2D obb2D);

    public static final native long RectangleInt_SWIGUpcast(long j);

    public static final native int RectangleInt_getAreaSize(long j, RectangleInt rectangleInt);

    public static final native int RectangleInt_getHeight(long j, RectangleInt rectangleInt);

    public static final native int RectangleInt_getLeftTopX(long j, RectangleInt rectangleInt);

    public static final native int RectangleInt_getLeftTopY(long j, RectangleInt rectangleInt);

    public static final native int RectangleInt_getRightBottomX(long j, RectangleInt rectangleInt);

    public static final native int RectangleInt_getRightBottomY(long j, RectangleInt rectangleInt);

    public static final native int RectangleInt_getType(long j, RectangleInt rectangleInt);

    public static final native int RectangleInt_getWidth(long j, RectangleInt rectangleInt);

    public static final native long Rectangle_SWIGUpcast(long j);

    public static final native float Rectangle_getAreaSize(long j, Rectangle rectangle);

    public static final native float Rectangle_getHeight(long j, Rectangle rectangle);

    public static final native float Rectangle_getLeftTopX(long j, Rectangle rectangle);

    public static final native float Rectangle_getLeftTopY(long j, Rectangle rectangle);

    public static final native float Rectangle_getRightBottomX(long j, Rectangle rectangle);

    public static final native float Rectangle_getRightBottomY(long j, Rectangle rectangle);

    public static final native int Rectangle_getType(long j, Rectangle rectangle);

    public static final native float Rectangle_getWidth(long j, Rectangle rectangle);

    public static final native long Renderer_begin__SWIG_0(long j, Renderer renderer);

    public static final native void Renderer_begin__SWIG_1(long j, Renderer renderer, long j2, State state);

    public static final native boolean Renderer_bindVideoBackground(long j, Renderer renderer, int i);

    public static final native boolean Renderer_drawVideoBackground(long j, Renderer renderer);

    public static final native void Renderer_end(long j, Renderer renderer);

    public static final native long Renderer_getInstance();

    public static final native long Renderer_getVideoBackgroundConfig(long j, Renderer renderer);

    public static final native long Renderer_getVideoBackgroundTextureInfo(long j, Renderer renderer);

    public static final native void Renderer_setARProjection(long j, Renderer renderer, float f, float f2);

    public static final native void Renderer_setVideoBackgroundConfig(long j, Renderer renderer, long j2, VideoBackgroundConfig videoBackgroundConfig);

    public static final native boolean Renderer_setVideoBackgroundTextureID(long j, Renderer renderer, int i);

    public static final native long State_getFrame(long j, State state);

    public static final native int State_getNumTrackableResults(long j, State state);

    public static final native int State_getNumTrackables(long j, State state);

    public static final native long State_getTrackable(long j, State state, int i);

    public static final native long State_getTrackableResult(long j, State state, int i);

    public static final native void TargetFinder_clearTrackables(long j, TargetFinder targetFinder);

    public static final native boolean TargetFinder_deinit(long j, TargetFinder targetFinder);

    public static final native long TargetFinder_enableTracking(long j, TargetFinder targetFinder, long j2, TargetSearchResult targetSearchResult);

    public static final native long TargetFinder_getImageTarget(long j, TargetFinder targetFinder, int i);

    public static final native int TargetFinder_getInitState(long j, TargetFinder targetFinder);

    public static final native int TargetFinder_getNumImageTargets(long j, TargetFinder targetFinder);

    public static final native long TargetFinder_getResult(long j, TargetFinder targetFinder, int i);

    public static final native int TargetFinder_getResultCount(long j, TargetFinder targetFinder);

    public static final native boolean TargetFinder_isRequesting(long j, TargetFinder targetFinder);

    public static final native void TargetFinder_setUIPointColor(long j, TargetFinder targetFinder, float f, float f2, float f3);

    public static final native void TargetFinder_setUIScanlineColor(long j, TargetFinder targetFinder, float f, float f2, float f3);

    public static final native boolean TargetFinder_startInit(long j, TargetFinder targetFinder, String str, String str2);

    public static final native boolean TargetFinder_startRecognition(long j, TargetFinder targetFinder);

    public static final native boolean TargetFinder_stop(long j, TargetFinder targetFinder);

    public static final native int TargetFinder_updateSearchResults(long j, TargetFinder targetFinder);

    public static final native void TargetFinder_waitUntilInitFinished(long j, TargetFinder targetFinder);

    public static final native String TargetSearchResult_getMetaData(long j, TargetSearchResult targetSearchResult);

    public static final native String TargetSearchResult_getTargetName(long j, TargetSearchResult targetSearchResult);

    public static final native float TargetSearchResult_getTargetSize(long j, TargetSearchResult targetSearchResult);

    public static final native short TargetSearchResult_getTrackingRating(long j, TargetSearchResult targetSearchResult);

    public static final native String TargetSearchResult_getUniqueTargetId(long j, TargetSearchResult targetSearchResult);

    public static final native long TextTracker_SWIGUpcast(long j);

    public static final native long TextTracker_getClassType();

    public static final native void TextTracker_getRegionOfInterest(long j, TextTracker textTracker, long j2, RectangleInt rectangleInt, long j3, RectangleInt rectangleInt2, int[] iArr);

    public static final native long TextTracker_getWordList(long j, TextTracker textTracker);

    public static final native boolean TextTracker_setRegionOfInterest(long j, TextTracker textTracker, long j2, RectangleInt rectangleInt, long j3, RectangleInt rectangleInt2, int i);

    public static final native long Tool_convertPose2GLMatrix(long j, Matrix34F matrix34F);

    public static final native long Tool_getProjectionGL(long j, CameraCalibration cameraCalibration, float f, float f2);

    public static final native long Tool_multiplyGL(long j, Matrix44F matrix44F, long j2, Matrix44F matrix44F2);

    public static final native long Tool_multiply__SWIG_0(long j, Matrix34F matrix34F, long j2, Matrix34F matrix34F2);

    public static final native long Tool_multiply__SWIG_1(long j, Matrix44F matrix44F, long j2, Matrix44F matrix44F2);

    public static final native long Tool_multiply__SWIG_2(long j, Vec4F vec4F, long j2, Matrix44F matrix44F);

    public static final native long Tool_projectPoint(long j, CameraCalibration cameraCalibration, long j2, Matrix34F matrix34F, long j3, Vec3F vec3F);

    public static final native void Tool_setRotation(long j, Matrix34F matrix34F, long j2, Vec3F vec3F, float f);

    public static final native void Tool_setTranslation(long j, Matrix34F matrix34F, long j2, Vec3F vec3F);

    public static final native long TrackableResult_getClassType();

    public static final native long TrackableResult_getPose(long j, TrackableResult trackableResult);

    public static final native int TrackableResult_getStatus(long j, TrackableResult trackableResult);

    public static final native long TrackableResult_getTrackable(long j, TrackableResult trackableResult);

    public static final native long TrackableResult_getType(long j, TrackableResult trackableResult);

    public static final native boolean TrackableResult_isOfType(long j, TrackableResult trackableResult, long j2, Type type);

    public static final native long Trackable_getClassType();

    public static final native int Trackable_getId(long j, Trackable trackable);

    public static final native String Trackable_getName(long j, Trackable trackable);

    public static final native long Trackable_getType(long j, Trackable trackable);

    public static final native boolean Trackable_isOfType(long j, Trackable trackable, long j2, Type type);

    public static final native boolean Trackable_startExtendedTracking(long j, Trackable trackable);

    public static final native boolean Trackable_stopExtendedTracking(long j, Trackable trackable);

    public static final native boolean TrackerManager_deinitTracker(long j, TrackerManager trackerManager, long j2, Type type);

    public static final native long TrackerManager_getInstance();

    public static final native long TrackerManager_getTracker(long j, TrackerManager trackerManager, long j2, Type type);

    public static final native long TrackerManager_initTracker(long j, TrackerManager trackerManager, long j2, Type type);

    public static final native long Tracker_getClassType();

    public static final native long Tracker_getType(long j, Tracker tracker);

    public static final native boolean Tracker_isOfType(long j, Tracker tracker, long j2, Type type);

    public static final native boolean Tracker_start(long j, Tracker tracker);

    public static final native void Tracker_stop(long j, Tracker tracker);

    public static final native boolean Type_isOfType(long j, Type type, long j2, Type type2);

    public static final native void UpdateCallback_QCAR_onUpdate(long j, UpdateCallback updateCallback, long j2, State state);

    public static final native void UpdateCallback_change_ownership(UpdateCallback updateCallback, long j, boolean z);

    public static final native void UpdateCallback_director_connect(UpdateCallback updateCallback, long j, boolean z, boolean z2);

    public static final native float[] Vec2F_data_get(long j, Vec2F vec2F);

    public static final native void Vec2F_data_set(long j, Vec2F vec2F, float[] fArr);

    public static final native int[] Vec2I_data_get(long j, Vec2I vec2I);

    public static final native void Vec2I_data_set(long j, Vec2I vec2I, int[] iArr);

    public static final native float[] Vec3F_data_get(long j, Vec3F vec3F);

    public static final native void Vec3F_data_set(long j, Vec3F vec3F, float[] fArr);

    public static final native int[] Vec3I_data_get(long j, Vec3I vec3I);

    public static final native void Vec3I_data_set(long j, Vec3I vec3I, int[] iArr);

    public static final native float[] Vec4F_data_get(long j, Vec4F vec4F);

    public static final native void Vec4F_data_set(long j, Vec4F vec4F, float[] fArr);

    public static final native int[] Vec4I_data_get(long j, Vec4I vec4I);

    public static final native void Vec4I_data_set(long j, Vec4I vec4I, int[] iArr);

    public static final native boolean VideoBackgroundConfig_Enabled_get(long j, VideoBackgroundConfig videoBackgroundConfig);

    public static final native void VideoBackgroundConfig_Enabled_set(long j, VideoBackgroundConfig videoBackgroundConfig, boolean z);

    public static final native long VideoBackgroundConfig_Position_get(long j, VideoBackgroundConfig videoBackgroundConfig);

    public static final native void VideoBackgroundConfig_Position_set(long j, VideoBackgroundConfig videoBackgroundConfig, long j2, Vec2I vec2I);

    public static final native int VideoBackgroundConfig_Reflection_get(long j, VideoBackgroundConfig videoBackgroundConfig);

    public static final native void VideoBackgroundConfig_Reflection_set(long j, VideoBackgroundConfig videoBackgroundConfig, int i);

    public static final native long VideoBackgroundConfig_Size_get(long j, VideoBackgroundConfig videoBackgroundConfig);

    public static final native void VideoBackgroundConfig_Size_set(long j, VideoBackgroundConfig videoBackgroundConfig, long j2, Vec2I vec2I);

    public static final native boolean VideoBackgroundConfig_Synchronous_get(long j, VideoBackgroundConfig videoBackgroundConfig);

    public static final native void VideoBackgroundConfig_Synchronous_set(long j, VideoBackgroundConfig videoBackgroundConfig, boolean z);

    public static final native long VideoBackgroundTextureInfo_ImageSize_get(long j, VideoBackgroundTextureInfo videoBackgroundTextureInfo);

    public static final native void VideoBackgroundTextureInfo_ImageSize_set(long j, VideoBackgroundTextureInfo videoBackgroundTextureInfo, long j2, Vec2I vec2I);

    public static final native int VideoBackgroundTextureInfo_PixelFormat_get(long j, VideoBackgroundTextureInfo videoBackgroundTextureInfo);

    public static final native void VideoBackgroundTextureInfo_PixelFormat_set(long j, VideoBackgroundTextureInfo videoBackgroundTextureInfo, int i);

    public static final native long VideoBackgroundTextureInfo_TextureSize_get(long j, VideoBackgroundTextureInfo videoBackgroundTextureInfo);

    public static final native void VideoBackgroundTextureInfo_TextureSize_set(long j, VideoBackgroundTextureInfo videoBackgroundTextureInfo, long j2, Vec2I vec2I);

    public static final native float VideoMode_Framerate_get(long j, VideoMode videoMode);

    public static final native void VideoMode_Framerate_set(long j, VideoMode videoMode, float f);

    public static final native int VideoMode_Height_get(long j, VideoMode videoMode);

    public static final native void VideoMode_Height_set(long j, VideoMode videoMode, int i);

    public static final native int VideoMode_Width_get(long j, VideoMode videoMode);

    public static final native void VideoMode_Width_set(long j, VideoMode videoMode, int i);

    public static final native long VirtualButtonResult_getVirtualButton(long j, VirtualButtonResult virtualButtonResult);

    public static final native boolean VirtualButtonResult_isPressed(long j, VirtualButtonResult virtualButtonResult);

    public static final native long VirtualButton_getArea(long j, VirtualButton virtualButton);

    public static final native int VirtualButton_getID(long j, VirtualButton virtualButton);

    public static final native String VirtualButton_getName(long j, VirtualButton virtualButton);

    public static final native boolean VirtualButton_isEnabled(long j, VirtualButton virtualButton);

    public static final native boolean VirtualButton_setArea(long j, VirtualButton virtualButton, long j2, Area area);

    public static final native boolean VirtualButton_setEnabled(long j, VirtualButton virtualButton, boolean z);

    public static final native boolean VirtualButton_setSensitivity(long j, VirtualButton virtualButton, int i);

    public static final native long WordList_SWIGUpcast(long j);

    public static final native boolean WordList_addWordToFilterListU(long j, WordList wordList, short[] sArr);

    public static final native boolean WordList_addWordU(long j, WordList wordList, short[] sArr);

    public static final native int WordList_addWordsFromFile(long j, WordList wordList, String str, int i);

    public static final native boolean WordList_clearFilterList(long j, WordList wordList);

    public static final native boolean WordList_containsWordU(long j, WordList wordList, short[] sArr);

    public static final native int WordList_getFilterListWordCount(long j, WordList wordList);

    public static final native short[] WordList_getFilterListWordU(long j, WordList wordList, int i);

    public static final native int WordList_getFilterMode(long j, WordList wordList);

    public static final native boolean WordList_loadFilterList(long j, WordList wordList, String str, int i);

    public static final native boolean WordList_loadWordList(long j, WordList wordList, String str, int i);

    public static final native boolean WordList_removeWordFromFilterListU(long j, WordList wordList, short[] sArr);

    public static final native boolean WordList_removeWordU(long j, WordList wordList, short[] sArr);

    public static final native boolean WordList_setFilterMode(long j, WordList wordList, int i);

    public static final native boolean WordList_unloadAllLists(long j, WordList wordList);

    public static final native long WordResult_SWIGUpcast(long j);

    public static final native long WordResult_getClassType();

    public static final native long WordResult_getObb(long j, WordResult wordResult);

    public static final native long WordResult_getTrackable(long j, WordResult wordResult);

    public static final native long Word_SWIGUpcast(long j);

    public static final native long Word_getClassType();

    public static final native int Word_getLength(long j, Word word);

    public static final native long Word_getLetterBoundingBox(long j, Word word, int i);

    public static final native long Word_getMask(long j, Word word);

    public static final native int Word_getNumCodeUnits(long j, Word word);

    public static final native long Word_getSize(long j, Word word);

    public static final native short[] Word_getStringU(long j, Word word);

    public static final native void deinit();

    public static final native void delete_Area(long j);

    public static final native void delete_CameraDevice(long j);

    public static final native void delete_CylinderTarget(long j);

    public static final native void delete_CylinderTargetResult(long j);

    public static final native void delete_DataSet(long j);

    public static final native void delete_Frame(long j);

    public static final native void delete_ImageTarget(long j);

    public static final native void delete_ImageTargetResult(long j);

    public static final native void delete_ImageTracker(long j);

    public static final native void delete_Marker(long j);

    public static final native void delete_MarkerResult(long j);

    public static final native void delete_MarkerTracker(long j);

    public static final native void delete_Matrix34F(long j);

    public static final native void delete_Matrix44F(long j);

    public static final native void delete_MultiTarget(long j);

    public static final native void delete_MultiTargetResult(long j);

    public static final native void delete_Obb2D(long j);

    public static final native void delete_Rectangle(long j);

    public static final native void delete_RectangleInt(long j);

    public static final native void delete_Renderer(long j);

    public static final native void delete_State(long j);

    public static final native void delete_TargetFinder(long j);

    public static final native void delete_TargetSearchResult(long j);

    public static final native void delete_TextTracker(long j);

    public static final native void delete_Tool(long j);

    public static final native void delete_Trackable(long j);

    public static final native void delete_TrackableResult(long j);

    public static final native void delete_TrackableSource(long j);

    public static final native void delete_Tracker(long j);

    public static final native void delete_TrackerManager(long j);

    public static final native void delete_Type(long j);

    public static final native void delete_UpdateCallback(long j);

    public static final native void delete_Vec2F(long j);

    public static final native void delete_Vec2I(long j);

    public static final native void delete_Vec3F(long j);

    public static final native void delete_Vec3I(long j);

    public static final native void delete_Vec4F(long j);

    public static final native void delete_Vec4I(long j);

    public static final native void delete_VideoBackgroundConfig(long j);

    public static final native void delete_VideoBackgroundTextureInfo(long j);

    public static final native void delete_VideoMode(long j);

    public static final native void delete_Word(long j);

    public static final native void delete_WordList(long j);

    public static final native void delete_WordResult(long j);

    public static final native int getBitsPerPixel(int i);

    public static final native int getBufferSize(int i, int i2, int i3);

    public static final native long new_Frame__SWIG_0();

    public static final native long new_Frame__SWIG_1(long j, Frame frame);

    public static final native long new_Matrix34F__SWIG_0();

    public static final native long new_Matrix34F__SWIG_1(long j, Matrix34F matrix34F);

    public static final native long new_Matrix44F__SWIG_0();

    public static final native long new_Matrix44F__SWIG_1(long j, Matrix44F matrix44F);

    public static final native long new_Obb2D__SWIG_0();

    public static final native long new_Obb2D__SWIG_1(long j, Obb2D obb2D);

    public static final native long new_Obb2D__SWIG_2(long j, Vec2F vec2F, long j2, Vec2F vec2F2, float f);

    public static final native long new_RectangleInt__SWIG_0();

    public static final native long new_RectangleInt__SWIG_1(long j, RectangleInt rectangleInt);

    public static final native long new_RectangleInt__SWIG_2(int i, int i2, int i3, int i4);

    public static final native long new_Rectangle__SWIG_0();

    public static final native long new_Rectangle__SWIG_1(long j, Rectangle rectangle);

    public static final native long new_Rectangle__SWIG_2(float f, float f2, float f3, float f4);

    public static final native long new_State__SWIG_0();

    public static final native long new_State__SWIG_1(long j, State state);

    public static final native long new_Tool();

    public static final native long new_TrackableSource();

    public static final native long new_Type__SWIG_0();

    public static final native long new_Type__SWIG_1(short s);

    public static final native long new_UpdateCallback();

    public static final native long new_Vec2F__SWIG_0();

    public static final native long new_Vec2F__SWIG_1(float[] fArr);

    public static final native long new_Vec2F__SWIG_2(float f, float f2);

    public static final native long new_Vec2F__SWIG_3(long j, Vec2F vec2F);

    public static final native long new_Vec2I__SWIG_0();

    public static final native long new_Vec2I__SWIG_1(int[] iArr);

    public static final native long new_Vec2I__SWIG_2(int i, int i2);

    public static final native long new_Vec2I__SWIG_3(long j, Vec2I vec2I);

    public static final native long new_Vec3F__SWIG_0();

    public static final native long new_Vec3F__SWIG_1(float[] fArr);

    public static final native long new_Vec3F__SWIG_2(float f, float f2, float f3);

    public static final native long new_Vec3F__SWIG_3(long j, Vec3F vec3F);

    public static final native long new_Vec3I__SWIG_0();

    public static final native long new_Vec3I__SWIG_1(int[] iArr);

    public static final native long new_Vec4F__SWIG_0();

    public static final native long new_Vec4F__SWIG_1(float[] fArr);

    public static final native long new_Vec4F__SWIG_2(float f, float f2, float f3, float f4);

    public static final native long new_Vec4F__SWIG_3(long j, Vec4F vec4F);

    public static final native long new_Vec4I__SWIG_0();

    public static final native long new_Vec4I__SWIG_1(int[] iArr);

    public static final native long new_VideoBackgroundConfig();

    public static final native long new_VideoBackgroundTextureInfo();

    public static final native long new_VideoMode__SWIG_0();

    public static final native long new_VideoMode__SWIG_1(long j, VideoMode videoMode);

    public static final native void onPause();

    public static final native void onResume();

    public static final native void onSurfaceChanged(int i, int i2);

    public static final native void onSurfaceCreated();

    public static final native void registerCallback(long j, UpdateCallback updateCallback);

    public static final native boolean requiresAlpha();

    public static final native boolean setFrameFormat(int i, boolean z);

    public static final native boolean setHint(long j, int i);

    private static final native void swig_module_init();

    VuforiaJNI() {
    }

    private static boolean loadLibrary(String nLibName) {
        try {
            System.loadLibrary(nLibName);
            System.out.println("Native library lib" + nLibName + ".so loaded");
            return true;
        } catch (UnsatisfiedLinkError e) {
            System.err.println("The library lib" + nLibName + ".so could not be loaded");
            return false;
        } catch (SecurityException e2) {
            System.err.println("The library lib" + nLibName + ".so was not allowed to be loaded");
            return false;
        }
    }

    static {
        if (!loadLibrary("Vuforia")) {
            System.exit(1);
        }
        swig_module_init();
    }

    public static void SwigDirector_UpdateCallback_QCAR_onUpdate(UpdateCallback self, long state) {
        self.QCAR_onUpdate(new State(state, false));
    }
}
