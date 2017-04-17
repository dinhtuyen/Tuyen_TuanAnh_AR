package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* renamed from: com.unity3d.player.m */
public final class C0098m extends Dialog implements TextWatcher, OnClickListener {
    private Context f158a;
    private UnityPlayer f159b;

    /* renamed from: com.unity3d.player.m.1 */
    class C00951 implements OnFocusChangeListener {
        final /* synthetic */ C0098m f155a;

        C00951(C0098m c0098m) {
            this.f155a = c0098m;
        }

        public final void onFocusChange(View view, boolean z) {
            if (z) {
                this.f155a.getWindow().setSoftInputMode(5);
            }
        }
    }

    /* renamed from: com.unity3d.player.m.2 */
    class C00962 extends EditText {
        final /* synthetic */ C0098m f156a;

        C00962(C0098m c0098m, Context context) {
            this.f156a = c0098m;
            super(context);
        }

        public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                this.f156a.m132a(this.f156a.m128a(), true);
            }
            return i == 84 ? true : super.onKeyPreIme(i, keyEvent);
        }

        public final void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z) {
                ((InputMethodManager) this.f156a.f158a.getSystemService("input_method")).showSoftInput(this, 0);
            }
        }
    }

    /* renamed from: com.unity3d.player.m.3 */
    class C00973 implements OnEditorActionListener {
        final /* synthetic */ C0098m f157a;

        C00973(C0098m c0098m) {
            this.f157a = c0098m;
        }

        public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6) {
                this.f157a.m132a(this.f157a.m128a(), false);
            }
            return false;
        }
    }

    public C0098m(Context context, UnityPlayer unityPlayer, String str, int i, boolean z, boolean z2, boolean z3, String str2) {
        super(context);
        this.f158a = null;
        this.f159b = null;
        this.f158a = context;
        this.f159b = unityPlayer;
        getWindow().setGravity(80);
        getWindow().requestFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(createSoftInputView());
        getWindow().clearFlags(2);
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        m130a(editText, str, i, z, z2, z3, str2);
        button.setOnClickListener(this);
        editText.setOnFocusChangeListener(new C00951(this));
    }

    private static int m127a(int i, boolean z, boolean z2, boolean z3) {
        int i2 = 0;
        int i3 = (z2 ? AccessibilityNodeInfoCompat.ACTION_SET_SELECTION : 0) | (z ? AccessibilityNodeInfoCompat.ACTION_PASTE : 0);
        if (z3) {
            i2 = AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
        }
        i2 |= i3;
        return (i < 0 || i > 7) ? i2 : i2 | new int[]{1, 16385, 12290, 17, 2, 3, 97, 33}[i];
    }

    private String m128a() {
        EditText editText = (EditText) findViewById(1057292289);
        return editText == null ? null : editText.getText().toString().trim();
    }

    private void m130a(EditText editText, String str, int i, boolean z, boolean z2, boolean z3, String str2) {
        editText.setImeOptions(6);
        editText.setText(str);
        editText.setHint(str2);
        editText.setInputType(C0098m.m127a(i, z, z2, z3));
        editText.addTextChangedListener(this);
        int inputType = editText.getInputType();
        editText.setKeyListener(TextKeyListener.getInstance());
        editText.setRawInputType(inputType);
        editText.setClickable(true);
        if (!z2) {
            editText.selectAll();
        }
    }

    private void m132a(String str, boolean z) {
        Selection.removeSelection(((EditText) findViewById(1057292289)).getEditableText());
        this.f159b.reportSoftInputStr(str, 1, z);
    }

    public final void m134a(String str) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
    }

    public final void afterTextChanged(Editable editable) {
        this.f159b.reportSoftInputStr(editable.toString(), 0, false);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected final View createSoftInputView() {
        View relativeLayout = new RelativeLayout(this.f158a);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        View c00962 = new C00962(this, this.f158a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        c00962.setLayoutParams(layoutParams);
        c00962.setId(1057292289);
        relativeLayout.addView(c00962);
        c00962 = new Button(this.f158a);
        c00962.setText(this.f158a.getResources().getIdentifier("ok", "string", "android"));
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(11);
        c00962.setLayoutParams(layoutParams);
        c00962.setId(1057292290);
        relativeLayout.addView(c00962);
        ((EditText) relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new C00973(this));
        return relativeLayout;
    }

    public final void onBackPressed() {
        m132a(m128a(), true);
    }

    public final void onClick(View view) {
        m132a(m128a(), false);
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
