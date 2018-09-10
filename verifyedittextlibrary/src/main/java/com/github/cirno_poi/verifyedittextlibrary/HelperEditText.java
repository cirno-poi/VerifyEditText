package com.github.cirno_poi.verifyedittextlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/**
 * 解决AOSP键盘无法监听无内容状态下删除键的问题
 *
 * @author cirno-poi
 */
public class HelperEditText extends android.support.v7.widget.AppCompatEditText {

    private OnKeyListener keyListener;

    public HelperEditText(Context context) {
        super(context);
    }

    public HelperEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HelperEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //覆盖输入框和键盘的关联接口
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new MyInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }


    private class MyInputConnection extends InputConnectionWrapper {

        public MyInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        //覆盖事件传递
        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if (keyListener != null) {
                keyListener.onKey(HelperEditText.this, event.getKeyCode(), event);
            }
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            //在删除时，输入框无内容，或者删除以后输入框无内容
            if (beforeLength == 1 || afterLength == 0 || beforeLength == 0) {
                // backspace
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }
            return super.deleteSurroundingText(beforeLength, afterLength);
        }

    }

    //设置监听回调
    public void setDeleteEventListener(OnKeyListener listener) {
        keyListener = listener;
    }

}
