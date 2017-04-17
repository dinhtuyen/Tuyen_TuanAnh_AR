package ua.org.jeff.unity.nativedialogs;

import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;

public class AndroidPlugin {
    int _counter;
    ProgressDialogRunnable _progressDialog;

    public AndroidPlugin() {
        this._progressDialog = null;
    }

    public int messageBox(String caption, String message, ArrayList<String> buttons, ArrayList<String> arrayList, String objectName, boolean cancelable) {
        this._counter++;
        UnityPlayer.currentActivity.runOnUiThread(new MessageBoxRunnable(caption, message, buttons, objectName, this._counter, cancelable));
        return this._counter;
    }

    public int loginPasswordMessageBox(String caption, String message, ArrayList<String> buttons, ArrayList<String> values, String objectName, boolean cancelable) {
        this._counter++;
        UnityPlayer.currentActivity.runOnUiThread(new LoginPasswordMessageBoxRunnable(caption, message, (String) values.get(0), (String) values.get(1), buttons, objectName, this._counter, cancelable));
        return this._counter;
    }

    public int promptMessageBox(String caption, String message, ArrayList<String> buttons, ArrayList<String> values, String objectName, boolean cancelable) {
        this._counter++;
        UnityPlayer.currentActivity.runOnUiThread(new PromptMessageBoxRunnable(caption, message, (String) values.get(0), buttons, objectName, this._counter, cancelable));
        return this._counter;
    }

    public int securePromptMessageBox(String caption, String message, ArrayList<String> buttons, ArrayList<String> values, String objectName, boolean cancelable) {
        this._counter++;
        UnityPlayer.currentActivity.runOnUiThread(new SecurePromptMessageBoxRunnable(caption, message, (String) values.get(0), buttons, objectName, this._counter, cancelable));
        return this._counter;
    }

    public void progressDialog(String title, String message, boolean cancelable) {
        if (this._progressDialog != null) {
            hideProgressDialog();
        }
        this._progressDialog = new ProgressDialogRunnable(title, message, cancelable);
        UnityPlayer.currentActivity.runOnUiThread(this._progressDialog);
    }

    public void hideProgressDialog() {
        if (this._progressDialog != null) {
            this._progressDialog.hide();
            this._progressDialog = null;
        }
    }
}
