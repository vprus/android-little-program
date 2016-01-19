package com.vladimirprus.littleprogram;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;

import java.io.IOException;

public class GetAccessTokenTask  extends AsyncTask<Void, Void, String> {

    private static String TAG = GetAccessTokenTask.class.getName();

    Activity mActivity;
    String mEmail;
    String mScope;

    GetAccessTokenTask(Activity activity, String name, String scope) {
        this.mActivity = activity;
        this.mEmail = name;
        this.mScope = scope;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
        } catch (GoogleAuthException fatalException) {
            Log.e(TAG, fatalException.getMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s != null) {
            ClipboardManager clipboard = (ClipboardManager)
                    mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Token", s);
            clipboard.setPrimaryClip(clip);
         }
        Toast.makeText(mActivity, "Task result: " + s, Toast.LENGTH_LONG).show();
    }
}

