package ua.org.jeff.unity.nativedialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import com.unity3d.player.UnityPlayer;

public class PromptMessageBoxButtonClickHandler implements OnClickListener {
    String _button;
    String _callbackObject;
    EditText _editText;
    int _id;

    public PromptMessageBoxButtonClickHandler(String button, String callbackObject, EditText editText, int id) {
        this._button = button;
        this._callbackObject = callbackObject;
        this._editText = editText;
        this._id = id;
    }

    public void onClick(DialogInterface dialog, int which) {
        UnityPlayer.UnitySendMessage(this._callbackObject, "MessageBoxButtonClicked", String.format("PromptMessageBox\n%d\n%s\n%s", new Object[]{Integer.valueOf(this._id), this._editText.getText().toString(), this._button}));
    }
}
