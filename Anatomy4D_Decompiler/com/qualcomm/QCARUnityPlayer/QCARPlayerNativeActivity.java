package com.qualcomm.QCARUnityPlayer;

import android.app.NativeActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.KeyEvent;
import android.view.View;
import com.qualcomm.QCARUnityPlayer.QCARPlayerSharedActivity.IUnityInitializer;
import com.unity3d.player.UnityPlayer;
import java.lang.reflect.Method;

public class QCARPlayerNativeActivity extends NativeActivity {
    private QCARPlayerSharedActivity mQCARShared;
    protected UnityPlayer mUnityPlayer;

    private class UnityInitializer implements IUnityInitializer {
        private UnityInitializer() {
        }

        public void InitializeUnity() {
            try {
                Method method = Class.forName("com.unity3d.player.UnityPlayer").getMethod("init", new Class[]{Integer.TYPE, Boolean.TYPE});
                int glesMode = QCARPlayerNativeActivity.this.mUnityPlayer.getSettings().getInt("gles_mode", 1);
                method.invoke(QCARPlayerNativeActivity.this.mUnityPlayer, new Object[]{Integer.valueOf(glesMode), Boolean.valueOf(false)});
            } catch (Exception e) {
            }
            View playerView = QCARPlayerNativeActivity.this.mUnityPlayer.getView();
            QCARPlayerNativeActivity.this.setContentView(playerView);
            playerView.requestFocus();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        this.mUnityPlayer = new UnityPlayer(this);
        super.onCreate(savedInstanceState);
        this.mQCARShared = new QCARPlayerSharedActivity();
        this.mQCARShared.onCreate(this, this.mUnityPlayer.getSettings().getInt("gles_mode", 1), new UnityInitializer());
        getWindow().takeSurface(null);
        setTheme(16973831);
        getWindow().setFormat(4);
        if (this.mUnityPlayer.getSettings().getBoolean("hide_status_bar", true)) {
            getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        }
    }

    protected void onDestroy() {
        this.mQCARShared.onDestroy();
        this.mUnityPlayer.quit();
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        this.mUnityPlayer.pause();
        this.mQCARShared.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.mQCARShared.onResume();
        this.mUnityPlayer.resume();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mUnityPlayer.configurationChanged(newConfig);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mUnityPlayer.windowFocusChanged(hasFocus);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 2) {
            return this.mUnityPlayer.onKeyMultiple(event.getKeyCode(), event.getRepeatCount(), event);
        }
        return super.dispatchKeyEvent(event);
    }
}
