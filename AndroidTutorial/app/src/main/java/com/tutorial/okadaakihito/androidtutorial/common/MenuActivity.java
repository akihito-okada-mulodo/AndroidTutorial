package com.tutorial.okadaakihito.androidtutorial.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.tutorial.okadaakihito.androidtutorial.R;
import com.tutorial.okadaakihito.androidtutorial.m1.MainActivity;
import com.tutorial.okadaakihito.androidtutorial.m2.HeavyListviewActivity;


public class MenuActivity extends ActionBarActivity implements MenuFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MenuFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        Context context = getApplicationContext();
        Intent intent = null;
        switch (id){
            case "1":
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                break;
            case "2":
                intent = new Intent(context, HeavyListviewActivity.class);
                startActivity(intent);
                break;
        }

    }
}
