package ua.org.jeff.unity.nativedialogs;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.widget.EditText;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;

public class PromptMessageBoxRunnable implements Runnable {
    ArrayList<String> _buttons;
    String _callbackObject;
    boolean _cancelable;
    String _caption;
    int _id;
    String _message;
    String _prompt;

    public PromptMessageBoxRunnable(String caption, String message, String prompt, ArrayList<String> buttons, String callbackObject, int id, boolean cancelable) {
        this._caption = caption;
        this._message = message;
        this._prompt = prompt;
        this._buttons = buttons;
        this._callbackObject = callbackObject;
        this._id = id;
        this._cancelable = cancelable;
    }

    public void run() {
        EditText prompt = new EditText(UnityPlayer.currentActivity);
        prompt.setText(this._prompt);
        AlertDialog alertDialog = new Builder(UnityPlayer.currentActivity).create();
        if (this._caption.length() != 0) {
            alertDialog.setTitle(this._caption);
        }
        if (this._message.length() != 0) {
            alertDialog.setMessage(this._message);
        }
        alertDialog.setView(prompt);
        if (this._buttons.size() > 0) {
            String button = (String) this._buttons.get(0);
            alertDialog.setButton(button, new PromptMessageBoxButtonClickHandler(button, this._callbackObject, prompt, this._id));
        }
        if (this._buttons.size() > 1) {
            button = (String) this._buttons.get(1);
            alertDialog.setButton2(button, new PromptMessageBoxButtonClickHandler(button, this._callbackObject, prompt, this._id));
        }
        if (this._buttons.size() > 2) {
            button = (String) this._buttons.get(2);
            alertDialog.setButton3(button, new PromptMessageBoxButtonClickHandler(button, this._callbackObject, prompt, this._id));
        }
        alertDialog.setCanceledOnTouchOutside(this._cancelable);
        alertDialog.setCancelable(this._cancelable);
        alertDialog.show();
    }
}
