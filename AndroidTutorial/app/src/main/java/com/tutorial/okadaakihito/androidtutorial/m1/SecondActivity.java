package com.tutorial.okadaakihito.androidtutorial.m1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.tutorial.okadaakihito.androidtutorial.R;

/**
 * Created by okadaakihito on 2014/12/15.
 */
public class SecondActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        finish();
                        break;
                }
                break;
        }
    }

    public void selectedButton(View view) {
        int id = view.getId();

        switch(id) {
            case R.id.push:
                Intent intent = new Intent(this, ThirdActivity.class);
                intent.putExtra("KEYWORD", "keywordStr");
                startActivityForResult(intent, 0);
                overridePendingTransition(0, 0);
                break;
        }
    }

}
