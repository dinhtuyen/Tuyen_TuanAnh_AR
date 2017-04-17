package ua.org.jeff.unity.nativedialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.unity3d.player.UnityPlayer;

class MessageBoxButtonClickHandler implements OnClickListener {
    String _button;
    String _callbackObject;
    int _id;

    MessageBoxButtonClickHandler(String button, String callbackObject, int id) {
        this._button = button;
        this._callbackObject = callbackObject;
        this._id = id;
    }

    public void onClick(DialogInterface dialog, int which) {
        UnityPlayer.UnitySendMessage(this._callbackObject, "MessageBoxButtonClicked", String.format("MessageBox\n%d\n%s", new Object[]{Integer.valueOf(this._id), this._button}));
    }
}
