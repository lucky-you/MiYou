package com.zhowin.miyou.rongIM.dialog;

import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;

import androidx.fragment.app.FragmentActivity;

import com.zhowin.miyou.R;


/**
 * 从底部弹出的dialog
 */
public class BottomDialogFactory implements SealMicDialogFactory {

    @Override
    public Dialog buildDialog(FragmentActivity context) {
        Dialog bottomDialog = new Dialog(context, R.style.DialogFragmentStyle);
        Window dialogWindow = bottomDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        return bottomDialog;
    }
}
