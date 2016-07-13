package com.dkstudio.icorrect.practice.speaking;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import android.widget.ImageView;
import android.widget.TextView;
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

    @Bind(R.id.imgAvatar)
    public ImageView imgAvatar;

    @Bind(R.id.tvName)
    public TextView tvName;

    @Bind(R.id.tvInfo)
    public TextView tvInfo;

    @Bind(R.id.btnSignin)
    public Button btnSignin;

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
        mSelectedId = savedInstanceState == null ? R.id.nav_item_home : savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);
        goHomeScreen();
    }

    //ducnm start
    //google signin start
    //GoogleApiClient mGoogleApiClient;

  /*  void initSignin() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this *//* FragmentActivity *//*, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    }
                } *//* OnConnectionFailedListener *//*)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public09

            void onClick(View v)
            {
                signIn();
            }
        });

    }
*/
    //ducnm end
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
        Fragment fragment = null;
        Class fragmentClass = null;

        switch (mSelectedId)
        {

            case R.id.nav_item_home:
                fragmentClass = SpeakingLevelFragment.class;
                toolbar.setTitle(getString(R.string.speaking_practice));

                break;

            case R.id.nav_item_comunity_sharing:
                fragmentClass = AboutFragment.class;
                toolbar.setTitle(getString(R.string.comunity_sharing));

                break;
            case R.id.nav_item_my_order:
                toolbar.setTitle(getString(R.string.my_order));

                break;
            case R.id.nav__item_my_practice:
                toolbar.setTitle(getString(R.string.my_practice));

                break;
            case R.id.nav_item_free_coin:
                toolbar.setTitle(getString(R.string.my_practice));

                break;



            case R.id.nav_item_sigout:

                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();
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
