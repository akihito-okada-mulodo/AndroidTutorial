package com.tutorial.okadaakihito.androidtutorial.m2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.tutorial.okadaakihito.androidtutorial.R;

public class HeavyListviewActivity extends ActionBarActivity implements HeavyListviewFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HeavyListviewFragment())
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onDestroy() {
        ImageCache.clearCache();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
