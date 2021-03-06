package org.tlc.whereat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.tlc.whereat.fragments.SettingsFragment;
import org.tlc.whereat.modules.pubsub.receivers.SettingsActivityReceivers;
import org.tlc.whereat.modules.ui.MenuHandler;


public class SettingsActivity extends AppCompatActivity {

    public static String TAG = SettingsActivity.class.getCanonicalName();

    protected MenuHandler mMenu;
    protected SettingsActivityReceivers mReceivers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMenu = new MenuHandler(this);
        mReceivers = new SettingsActivityReceivers(this);
        showFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mReceivers.register();
    }

    @Override
    public void onPause(){
        super.onPause();
        mReceivers.unregister();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mMenu.create(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mMenu.select(item, super::onOptionsItemSelected);
    }

    protected void showFragment(){
        getFragmentManager()
            .beginTransaction()
            .replace(
                android.R.id.content,
                new SettingsFragment(),
                "settings_fragment"
            ).commit();
    }

}

