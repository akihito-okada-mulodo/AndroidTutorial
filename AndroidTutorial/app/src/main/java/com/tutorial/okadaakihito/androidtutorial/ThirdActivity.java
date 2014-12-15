package com.tutorial.okadaakihito.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by okadaakihito on 2014/12/15.
 */
public class ThirdActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void selectedButton(View view) {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEYWORD", "keywordStr");
        data.putExtras(bundle);
        setResult(RESULT_OK, data);
        finish();
        overridePendingTransition( android.R.anim.slide_in_left, android.R.anim.slide_out_right );
    }
}