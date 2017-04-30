package com.python.cat.drawermenu;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.drawerlayout.DrawerMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DrawerMenu dm = (DrawerMenu) findViewById(R.id.dm_layout);
        findViewById(R.id.main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.changeState();
            }
        });
        vp = (ViewPager) findViewById(R.id.main_view_pager);
        View v1 = LayoutInflater.from(this).inflate(R.layout.vp_layout_01, null);
        View v2 = LayoutInflater.from(this).inflate(R.layout.vp_layout_02, null);
        View v3 = LayoutInflater.from(this).inflate(R.layout.vp_layout_03, null);
        View v4 = LayoutInflater.from(this).inflate(R.layout.vp_layout_04, null);
        final ArrayList<View> viewList = new ArrayList<>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);
        viewList.add(v4);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        });
    }

    public void leftMenuClick(View view) {
        String text = ((TextView) view).getText().toString();
        LogUtils.e("item = " + view.getId() + " , text = " + text);
        Toast.makeText(getApplicationContext(),
                text,
                Toast.LENGTH_SHORT)
                .show();
    }
}
