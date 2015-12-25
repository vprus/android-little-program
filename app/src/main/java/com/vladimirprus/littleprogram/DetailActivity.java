package com.vladimirprus.littleprogram;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vladimirprus.littleprogram.databinding.DetailActivityBinding;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_PICTURE = "picture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetailActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.detail_activity);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picture picture = (Picture) getIntent().getSerializableExtra(EXTRA_PICTURE);
        PictureViewModel viewModel = PictureViewModel.make(picture, getResources());

        binding.setModel(viewModel);
    }
}
