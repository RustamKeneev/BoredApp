package com.example.boredapp;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.boredapp.adapters.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    public  static void start(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ImageView [] indicators;
    private int [] colorList;
    private ArgbEvaluator argbEvaluator;

    private Button buttonSkip;
    private Button buttonFinish;
    private ImageButton imageButtonNext;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        buttonSkip = findViewById(R.id.footer_control_button_skip);
        buttonFinish = findViewById(R.id.footer_control_button_finish);
        imageButtonNext = findViewById(R.id.footer_control_button_next);

        indicators = new ImageView[]{
                findViewById(R.id.footer_control_indicator_1),
                findViewById(R.id.footer_control_indicator_2),
                findViewById(R.id.footer_control_indicator_3)};

        colorList = new int[]{
                Color.parseColor("#FFC107"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#8BC34A")};

        argbEvaluator = new ArgbEvaluator();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setBackgroundColor(colorList[0]);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int colorUpdate = (Integer) argbEvaluator.evaluate(positionOffset,
                        colorList[position], position == colorList.length-1 ? colorList[position] : colorList[position+1]);
                mViewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                // update indicator
                page = position;
                updateIndicator(position);
                mViewPager.setBackgroundColor(colorList[position]);


                buttonSkip.setVisibility(position == 2? View.INVISIBLE: View.VISIBLE);
                imageButtonNext.setVisibility(position == 2? View.GONE : View.VISIBLE);
                buttonFinish.setVisibility(position == 2 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        imageButtonNext.setOnClickListener(this);
    }

    private void updateIndicator(int position){
        for (int i = 0; i < indicators.length; i++) {
            if(i == position){
                indicators[i].setImageResource(R.drawable.indicator_selected);
            }else
                indicators[i].setImageResource(R.drawable.indicator_unselected);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.footer_control_button_next:
                page++;
                mViewPager.setCurrentItem(page, true);
                break;
        }

    }
}


