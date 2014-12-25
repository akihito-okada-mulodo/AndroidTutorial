package com.tutorial.okadaakihito.androidtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;

public class SplashActivity extends ActionBarActivity {

	private static final String TAG = SplashActivity.class.getSimpleName();

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.activity_splash);
		getSupportActionBar().hide();

        new Handler().postDelayed( delayStartActivity, 1500);
	}

    private final Runnable delayStartActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.putExtra("KEYWORD", "keywordStr");
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    };

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return false;
	}
}
