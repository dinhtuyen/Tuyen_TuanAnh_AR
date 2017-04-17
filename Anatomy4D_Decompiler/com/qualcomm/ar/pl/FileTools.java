package com.qualcomm.ar.pl;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.BufferedInputStream;
import java.io.File;

public class FileTools {
    private static final int AR_FILE_POS_CURRENT = 1;
    private static final int AR_FILE_POS_END = 2;
    private static final int AR_FILE_POS_START = 0;
    private static final int FILE_PATHTYPEINDEX_ABSOLUTE = -1;
    private static final int FILE_PATHTYPEINDEX_ANDROID_ASSETS = 0;
    private static final int FILE_PATHTYPEINDEX_ANDROID_DATALOCAL = 4;
    private static final int FILE_PATHTYPEINDEX_ANDROID_MEDIASTORAGE = 3;
    private static final int FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPCACHE = 2;
    private static final int FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPSTORAGE = 1;
    private static final String MODULENAME = "FileTools";

    public static class AssetFileInfo {
        long filePos;
        BufferedInputStream fileStream;
        String relativePath;
    }

    public static String getAbsolutePath(int pathTypeIndex, String relativePath) {
        String basePath;
        switch (pathTypeIndex) {
            case FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPSTORAGE /*1*/:
                File fileDir = SystemTools.getActivityFromNative().getFilesDir();
                if (fileDir != null) {
                    basePath = fileDir.getAbsolutePath();
                    break;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPCACHE /*2*/:
                File cacheDir = SystemTools.getActivityFromNative().getCacheDir();
                if (cacheDir != null) {
                    basePath = cacheDir.getAbsolutePath();
                    break;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case FILE_PATHTYPEINDEX_ANDROID_MEDIASTORAGE /*3*/:
                File externalStorageDir = Environment.getExternalStorageDirectory();
                if (externalStorageDir != null) {
                    basePath = externalStorageDir.getAbsolutePath();
                    break;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            default:
                SystemTools.setSystemErrorCode(6);
                return null;
        }
        if (!(basePath == null || relativePath == null)) {
            if (basePath.length() > 0 && basePath.charAt(basePath.length() + FILE_PATHTYPEINDEX_ABSOLUTE) != '/') {
                basePath = basePath + "/";
            }
            basePath = basePath + relativePath;
        }
        return basePath;
    }

    public static boolean mediastorage_isAvailable() {
        String state = Environment.getExternalStorageState();
        return "mounted".equals(state) || "mounted_ro".equals(state);
    }

    public static boolean mediastorage_checkPermission() {
        try {
            Activity activity = SystemTools.getActivityFromNative();
            if (activity.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", activity.getPackageName()) == 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean asset_exists(String relativePath) {
        int listLength;
        AssetManager am = SystemTools.getActivityFromNative().getAssets();
        File relativePathFile = new File(relativePath);
        String pathOnly = relativePathFile.getParent();
        String fileNameOnly = relativePathFile.getName();
        if (pathOnly == null) {
            try {
                pathOnly = "";
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                return false;
            }
        }
        String[] listOfFiles = am.list(pathOnly);
        if (listOfFiles != null) {
            listLength = listOfFiles.length;
        } else {
            listLength = FILE_PATHTYPEINDEX_ANDROID_ASSETS;
        }
        for (int i = FILE_PATHTYPEINDEX_ANDROID_ASSETS; i < listLength; i += FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPSTORAGE) {
            if (listOfFiles[i].compareTo(fileNameOnly) == 0) {
                return true;
            }
        }
        return false;
    }

    public static Object asset_open(String relativePath) {
        AssetFileInfo assetFileInfo = new AssetFileInfo();
        assetFileInfo.fileStream = null;
        assetFileInfo.relativePath = relativePath;
        assetFileInfo.filePos = 0;
        try {
            assetFileInfo.fileStream = new BufferedInputStream(SystemTools.getActivityFromNative().getAssets().open(relativePath, FILE_PATHTYPEINDEX_ANDROID_MEDIASTORAGE), AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
            return assetFileInfo;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
    }

    public static boolean asset_close(Object fileObj) {
        try {
            ((AssetFileInfo) fileObj).fileStream.close();
            return true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public static byte[] asset_read(Object fileObj, int bytesToRead) {
        try {
            int bufferLengthToRead;
            AssetFileInfo assetFileInfo = (AssetFileInfo) fileObj;
            int availableBufferLength = assetFileInfo.fileStream.available();
            if (availableBufferLength < bytesToRead) {
                bufferLengthToRead = availableBufferLength;
            } else {
                bufferLengthToRead = bytesToRead;
            }
            byte[] buffer = new byte[bufferLengthToRead];
            assetFileInfo.filePos += (long) assetFileInfo.fileStream.read(buffer, FILE_PATHTYPEINDEX_ANDROID_ASSETS, bufferLengthToRead);
            return buffer;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            return null;
        }
    }

    public static boolean asset_isEOF(Object fileObj) {
        int numBytesAvailable = FILE_PATHTYPEINDEX_ANDROID_ASSETS;
        try {
            numBytesAvailable = ((AssetFileInfo) fileObj).fileStream.available();
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
        }
        if (numBytesAvailable == 0) {
            return true;
        }
        return false;
    }

    public static boolean asset_setPosition(Object fileObj, long offset, int origin) {
        AssetFileInfo assetFileInfo = (AssetFileInfo) fileObj;
        if (offset < 0) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
        switch (origin) {
            case FILE_PATHTYPEINDEX_ANDROID_ASSETS /*0*/:
                try {
                    assetFileInfo.fileStream.close();
                    try {
                        assetFileInfo.fileStream = new BufferedInputStream(SystemTools.getActivityFromNative().getAssets().open(assetFileInfo.relativePath, FILE_PATHTYPEINDEX_ANDROID_MEDIASTORAGE), AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
                        assetFileInfo.filePos = 0;
                        break;
                    } catch (Exception e) {
                        SystemTools.setSystemErrorCode(6);
                        return false;
                    }
                } catch (Exception e2) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            case FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPSTORAGE /*1*/:
                break;
            case FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPCACHE /*2*/:
                try {
                    for (int bytesAvailable = assetFileInfo.fileStream.available(); bytesAvailable != 0; bytesAvailable = assetFileInfo.fileStream.available()) {
                        assetFileInfo.filePos += assetFileInfo.fileStream.skip((long) bytesAvailable);
                    }
                    break;
                } catch (Exception e3) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            default:
                SystemTools.setSystemErrorCode(FILE_PATHTYPEINDEX_ANDROID_MEDIASTORAGE);
                return false;
        }
        if (offset != 0) {
            long skippedTotal = 0;
            do {
                try {
                    long skipped = assetFileInfo.fileStream.skip(offset - skippedTotal);
                    skippedTotal += skipped;
                    assetFileInfo.filePos += skipped;
                    if (skippedTotal < offset) {
                    }
                } catch (Exception e4) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                }
            } while (assetFileInfo.fileStream.available() != 0);
        }
        return true;
    }

    public static long asset_getPosition(Object fileObj) {
        return ((AssetFileInfo) fileObj).filePos;
    }
}
