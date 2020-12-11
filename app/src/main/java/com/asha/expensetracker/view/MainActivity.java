package com.asha.expensetracker.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.asha.expensetracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    FloatingActionButton btn_floating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_floating = findViewById(R.id.add_amount_floating);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void add_amount_float(View view) {

        btn_floating.setEnabled(false);
        pb = new ProgressBar(getApplicationContext());
        pb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        pb.setIndeterminate(true);
        pb.setVisibility(View.VISIBLE);

        Intent intent = new Intent(MainActivity.this, AddAmount.class);
        startActivity(intent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        btn_floating.setEnabled(true);
    }
}