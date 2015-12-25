package com.vladimirprus.littleprogram;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar t = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(t);

        Picture[] model = new Picture[]{
                new Picture("Hoverboard", R.drawable.hoverboard),
                new Picture("Shuttle", R.drawable.shuttle),
                new Picture("Death Star", R.drawable.death_star),
                new Picture("Tractor", R.drawable.tractor)
        };

        ObservableList<PictureViewModel> viewModel = new ObservableArrayList<PictureViewModel>();
        for (Picture p: model) {
            viewModel.add(PictureViewModel.make(p, getResources()));
        }

        RecyclerView r = (RecyclerView) findViewById(R.id.main_list);
        BindingListAdapter<PictureViewModel> a =
                new BindingListAdapter<PictureViewModel>(viewModel, R.layout.main_item, com.vladimirprus.littleprogram.BR.model);
        a.setOnClickListener(new ClickListener<PictureViewModel>() {
            @Override
            public void onClick(PictureViewModel model) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_PICTURE, model.model);
                startActivity(intent);
            }
        });
        r.setAdapter(a);
        r.setLayoutManager(new GridLayoutManager(this, 2));
        r.addItemDecoration(new SpacesItemDecoration(r, 16));
    }
}
