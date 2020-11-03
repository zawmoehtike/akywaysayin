package com.johnsmith.zawmoehtike.akywaysayin.utils.mmdetect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RadioButton;

import java.util.concurrent.Executor;

import me.myatminsoe.mdetect.MDetect;

@SuppressLint("AppCompatCustomView")
public class MMRadioButton extends RadioButton {
    public MMRadioButton(Context context) {
        super(context);
        setMMText(getText().toString(), null);
    }

    public MMRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMMText(getText().toString(), null);
    }

    public MMRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMMText(getText().toString(), null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MMRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMMText(final String unicodeString, Executor executor) {
        if(executor == null) {
            setText(MDetect.INSTANCE.getText(unicodeString));
        } else {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    final String convertedString = MDetect.INSTANCE.getText(unicodeString);
                    post(new Runnable() {
                        @Override
                        public void run() {
                            setText(convertedString);
                        }
                    });
                }
            });
        }
    }
}
