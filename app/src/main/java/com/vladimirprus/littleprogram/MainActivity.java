package com.vladimirprus.littleprogram;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DataBindingUtil.setContentView(this, R.layout) instanceof ? (() DataBindingUtil.setContentView(this, R.layout)) : null;
        com.vladimirprus.littleprogram.MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKSdk.login(MainActivity.this, "email");
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean vkResult = VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Toast.makeText(MainActivity.this, "Authorized", Toast.LENGTH_LONG).show();

                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Token", res.accessToken);
                clipboard.setPrimaryClip(clip);
            }
            @Override
            public void onError(VKError error) {
            }
        });

        if (!vkResult) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
