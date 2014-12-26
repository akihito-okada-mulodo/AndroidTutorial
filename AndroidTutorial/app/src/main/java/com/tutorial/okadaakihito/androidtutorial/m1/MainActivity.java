package com.tutorial.okadaakihito.androidtutorial.m1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tutorial.okadaakihito.androidtutorial.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MainFragment extends Fragment {

        public MainFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SecondFragment extends Fragment {

        public SecondFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_second, container, false);
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ThirdFragment extends Fragment {

        public ThirdFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_third, container, false);
            return rootView;
        }
    }

    @Override
    public void onBackPressed() {
        int backStackCnt = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("", String.valueOf(backStackCnt));
        if (backStackCnt != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    public void selectedButton(View view) {
        int id = view.getId();
        Fragment newFragment = null;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch(id) {
            case R.id.secondActivity:
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("KEYWORD", "keywordStr");
                startActivity(intent);
                break;
            case R.id.secondFragment:
                newFragment = new SecondFragment();
                transaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.thirdFragment:
                newFragment = new ThirdFragment();
                transaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.close:
                getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
