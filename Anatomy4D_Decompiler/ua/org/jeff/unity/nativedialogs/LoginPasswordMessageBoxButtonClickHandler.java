package ua.org.jeff.unity.nativedialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.EditText;
import com.unity3d.player.UnityPlayer;

class LoginPasswordMessageBoxButtonClickHandler implements OnClickListener {
    String _button;
    String _callbackObject;
    int _id;
    View _loginForm;

    public LoginPasswordMessageBoxButtonClickHandler(String button, String callbackObject, View loginForm, int id) {
        this._button = button;
        this._callbackObject = callbackObject;
        this._loginForm = loginForm;
        this._id = id;
    }

    public void onClick(DialogInterface dialog, int which) {
        String login = ((EditText) this._loginForm.findViewById(LoginPasswordMessageBoxRunnable.LOGIN_FIELD_ID.intValue())).getText().toString();
        String password = ((EditText) this._loginForm.findViewById(LoginPasswordMessageBoxRunnable.PASSWORD_FIELD_ID.intValue())).getText().toString();
        UnityPlayer.UnitySendMessage(this._callbackObject, "MessageBoxButtonClicked", String.format("LoginPasswordMessageBox\n%d\n%s\n%s\n%s", new Object[]{Integer.valueOf(this._id), login, password, this._button}));
    }
}
