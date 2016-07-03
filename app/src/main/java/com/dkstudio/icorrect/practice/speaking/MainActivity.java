package com.dkstudio.icorrect.practice.speaking;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.dkstudio.icorrect.R;
import com.dkstudio.icorrect.practice.speaking.ui.fragment.AboutFragment;
import com.dkstudio.icorrect.practice.speaking.ui.fragment.SpeakingLevelFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Bind(R.id.main_drawer)
    public NavigationView mDrawer;

    @Bind(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;

    public ActionBarDrawerToggle drawerToggle;
    public int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        mDrawer.setNavigationItemSelectedListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId = savedInstanceState == null ? R.id.navigation_item_home : savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);
        goHomeScreen();
    }

    private void setToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }
    }

    private void itemSelection(int mSelectedId)
    {


        switch (mSelectedId)
        {

            case R.id.navigation_item_home:
                goHomeScreen();
                break;

            case R.id.navigation_item_about:
                goAboutScreen();
                break;

            case R.id.navigation_sub_item_rate:
                goToApp();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.navigation_sub_item_other_app:
                goToStore();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

        }

    }

    private void goAboutScreen()
    {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_content, AboutFragment.instance("About Screen"))
                .commit();
        toolbar.setTitle(getString(R.string.about_me));
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void goHomeScreen()
    {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_content, SpeakingLevelFragment.instance())
                .commit();
        toolbar.setTitle(getString(R.string.speaking_practice));
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem)
    {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID", mSelectedId);
    }

    public void goToApp()
    {
        try
        {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
        }
        catch (Exception e)
        {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }

    }

    public void goToStore()
    {
        try
        {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:SoftForLife")));
        }
        catch (ActivityNotFoundException e)
        {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/search?q=pub:SoftForLife")));
        }
    }
}
