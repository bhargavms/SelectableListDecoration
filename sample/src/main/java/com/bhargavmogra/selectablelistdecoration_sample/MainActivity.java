package com.bhargavmogra.selectablelistdecoration_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bhargavmogra.selectablelistdecoration.SelectedItemDecoration;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String[] texts = {
            "luctus ac ullamcorper ", " nascetur ridiculus mus", "Pellentesque dignissim",
            "Aliquam fringilla", "ac posuere eget"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        ListAdapter adapter = new ListAdapter(Arrays.asList(texts));
        assert list != null;
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        list.addItemDecoration(new SelectedItemDecoration(this, R.color.colorAccent, R.dimen.stroke_width));
    }
}
