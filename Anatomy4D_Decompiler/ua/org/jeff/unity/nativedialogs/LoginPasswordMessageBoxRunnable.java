package ua.org.jeff.unity.nativedialogs;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;

public class LoginPasswordMessageBoxRunnable implements Runnable {
    static final Integer LOGIN_FIELD_ID;
    static final Integer PASSWORD_FIELD_ID;
    ArrayList<String> _buttons;
    String _callbackObject;
    boolean _cancelable;
    String _caption;
    int _id;
    String _login;
    String _message;
    String _password;

    public LoginPasswordMessageBoxRunnable(String caption, String message, String login, String password, ArrayList<String> buttons, String callbackObject, int id, boolean cancelable) {
        this._caption = caption;
        this._message = message;
        this._login = login;
        this._password = password;
        this._buttons = buttons;
        this._callbackObject = callbackObject;
        this._id = id;
        this._cancelable = cancelable;
    }

    static {
        LOGIN_FIELD_ID = Integer.valueOf(55);
        PASSWORD_FIELD_ID = Integer.valueOf(56);
    }

    public void run() {
        LinearLayout layout = new LinearLayout(UnityPlayer.currentActivity);
        layout.setOrientation(1);
        EditText login = new EditText(UnityPlayer.currentActivity);
        login.setHint("Login");
        login.setId(LOGIN_FIELD_ID.intValue());
        login.setText(this._login);
        EditText password = new EditText(UnityPlayer.currentActivity);
        password.setHint("Password");
        password.setTransformationMethod(new PasswordTransformationMethod());
        password.setId(PASSWORD_FIELD_ID.intValue());
        password.setText(this._password);
        layout.addView(login);
        layout.addView(password);
        AlertDialog alertDialog = new Builder(UnityPlayer.currentActivity).create();
        if (this._caption.length() != 0) {
            alertDialog.setTitle(this._caption);
        }
        if (this._message.length() != 0) {
            alertDialog.setMessage(this._message);
        }
        alertDialog.setView(layout);
        if (this._buttons.size() > 0) {
            String button = (String) this._buttons.get(0);
            alertDialog.setButton(button, new LoginPasswordMessageBoxButtonClickHandler(button, this._callbackObject, layout, this._id));
        }
        if (this._buttons.size() > 1) {
            button = (String) this._buttons.get(1);
            alertDialog.setButton2(button, new LoginPasswordMessageBoxButtonClickHandler(button, this._callbackObject, layout, this._id));
        }
        if (this._buttons.size() > 2) {
            button = (String) this._buttons.get(2);
            alertDialog.setButton3(button, new LoginPasswordMessageBoxButtonClickHandler(button, this._callbackObject, layout, this._id));
        }
        alertDialog.setCanceledOnTouchOutside(this._cancelable);
        alertDialog.setCancelable(this._cancelable);
        alertDialog.show();
    }
}
