package com.tutorial.okadaakihito.androidtutorial.m2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.tutorial.okadaakihito.androidtutorial.R;

public class HeavyListviewActivity extends ActionBarActivity implements HeavyListviewFragment.OnFragmentInteractionListener{

    private String urlString = "http://beauty.geocities.jp/kira_eos/etc/may/tome2.jpg";

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
}