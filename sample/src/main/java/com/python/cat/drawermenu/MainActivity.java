package com.python.cat.drawermenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.python.cat.drawerlayout.DrawerMenu;

public class MainActivity extends AppCompatActivity {

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
