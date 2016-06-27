package mybitchinapps.cortana.daemonslayer.discover_delhi.activity;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import mybitchinapps.cortana.daemonslayer.discover_delhi.R;
import mybitchinapps.cortana.daemonslayer.discover_delhi.fragments.Fragment_A;
import mybitchinapps.cortana.daemonslayer.discover_delhi.fragments.Fragment_B;
import mybitchinapps.cortana.daemonslayer.discover_delhi.fragments.Fragment_C;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private Toolbar toolbar;
	private NavigationView navigationView;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
    private ViewPager viewPager;
    private TabLayout tabLayout;
	private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
        	setSupportActionBar(toolbar);
        }

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 
        	R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        selectedID = savedInstanceState ==null ? R.id.inbox : savedInstanceState.getInt("SELECTED_ID");;
        itemSelection(selectedID);
    }

    private void itemSelection(int selectedID) {
    	switch(selectedID) {
            case R.id.inbox:
                Toast.makeText(getApplicationContext(),"Inbox Selected",Toast.LENGTH_SHORT).show();
                
                break;

            // For rest of the options we just show a toast on click

            case R.id.starred:
                Toast.makeText(getApplicationContext(),"Stared Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sent_mail:
                Toast.makeText(getApplicationContext(),"Send Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.drafts:
                Toast.makeText(getApplicationContext(),"Drafts Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.allmail:
                Toast.makeText(getApplicationContext(),"All Mail Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.trash:
                Toast.makeText(getApplicationContext(),"Trash Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.spam:
                Toast.makeText(getApplicationContext(),"Spam Selected",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
       ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
       adapter.addFragment(new Fragment_A(), "Places");
       adapter.addFragment(new Fragment_B(), "Food");
       adapter.addFragment(new Fragment_C(), "Cool Stuff");
       viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        selectedID = menuItem.getItemId();
        itemSelection(selectedID);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID", selectedID);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    
}
