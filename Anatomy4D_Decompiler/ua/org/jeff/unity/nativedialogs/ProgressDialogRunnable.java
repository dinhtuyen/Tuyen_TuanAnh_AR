package ua.org.jeff.unity.nativedialogs;

import android.app.ProgressDialog;
import com.unity3d.player.UnityPlayer;

class ProgressDialogRunnable implements Runnable {
    boolean _cancelableOnTouchOutside;
    ProgressDialog _dialog;
    String _message;
    String _title;

    public ProgressDialogRunnable(String title, String message, boolean cancelableOnTouchOutside) {
        this._title = title;
        this._message = message;
        this._cancelableOnTouchOutside = cancelableOnTouchOutside;
    }

    public void run() {
        this._dialog = ProgressDialog.show(UnityPlayer.currentActivity, this._title, this._message);
        this._dialog.setCanceledOnTouchOutside(this._cancelableOnTouchOutside);
    }

    public void hide() {
        if (this._dialog != null) {
            this._dialog.dismiss();
            this._dialog = null;
        }
    }
}
