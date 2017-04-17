package com.unity3d.player;

import android.app.NativeActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.KeyEvent;
import android.view.View;

public class UnityPlayerNativeActivity extends NativeActivity {
    protected UnityPlayer mUnityPlayer;

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return keyEvent.getAction() == 2 ? this.mUnityPlayer.onKeyMultiple(keyEvent.getKeyCode(), keyEvent.getRepeatCount(), keyEvent) : super.dispatchKeyEvent(keyEvent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mUnityPlayer.configurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().takeSurface(null);
        setTheme(16973831);
        getWindow().setFormat(4);
        this.mUnityPlayer = new UnityPlayer(this);
        if (this.mUnityPlayer.getSettings().getBoolean("hide_status_bar", true)) {
            getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        }
        this.mUnityPlayer.init(this.mUnityPlayer.getSettings().getInt("gles_mode", 1), false);
        View view = this.mUnityPlayer.getView();
        setContentView(view);
        view.requestFocus();
    }

    protected void onDestroy() {
        this.mUnityPlayer.quit();
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        this.mUnityPlayer.pause();
    }

    protected void onResume() {
        super.onResume();
        this.mUnityPlayer.resume();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.mUnityPlayer.windowFocusChanged(z);
    }
}
