package com.example.tankorbox.myplayer.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tankorbox.myplayer.Adapter.MyNaviAdapter;
import com.example.tankorbox.myplayer.Classes.OfflineMusic;
import com.example.tankorbox.myplayer.Data.MyNavi;
import com.example.tankorbox.myplayer.Data.OfflineSong;
import com.example.tankorbox.myplayer.Fragment.FragmentOffline;
import com.example.tankorbox.myplayer.Fragment.FragmentOnline;
import com.example.tankorbox.myplayer.R;
import com.example.tankorbox.myplayer.Services.PlayMusicService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentOffline.SongClickListener{
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FragmentOnline fragment;
    FragmentOffline fragment2;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    Typeface typeface;
    Typeface typeface1;
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    LinearLayout linearLayout;
    Intent serviceIntent;
    OfflineMusic offlinemusic;
    ImageButton playButton;
    List<OfflineSong> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFont();
        setToolbarAndNavigation();
        setService();
        setReferences();

        offlinemusic = new OfflineMusic();
        list = new ArrayList<>();
        list = offlinemusic.getPlayList();

        linearLayout = (LinearLayout) findViewById(R.id.transActi);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PlayingActivity.class);
                startActivity(intent);
            }
        });
        //navilist
        List<MyNavi> list = new ArrayList<>();
        String[] str = {"Home", "Chat For Fun", "Feedback", "About"};
        int[] a = new int[4];
        a[0] = R.drawable.home;
        a[1] = R.drawable.chat;
        a[2] = R.drawable.feedback;
        a[3] = R.drawable.about;
        for (int i = 0; i <= 3; i++) {
            list.add(new MyNavi(a[i], str[i]));
        }
        MyNaviAdapter myNaviAdapter = new MyNaviAdapter(list, this);
        ListView listView = (ListView) findViewById(R.id.navilist);
        listView.setAdapter(myNaviAdapter);
        fragment = new FragmentOnline();
        fragment2 = new FragmentOffline();
        viewPager = (ViewPager) findViewById(R.id.viewPager_baitap);
        setupViewPager(viewPager, fragment, fragment2);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        //navi back button
        ImageButton imageButton = (ImageButton) findViewById(R.id.backfromNavi);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    ((TextView) toolbar.findViewById(R.id.title_toolbar)).setText("Online");
                    viewPager.setCurrentItem(0, true);
                    drawerLayout.closeDrawers();
                } else if (i == 1) {
                }
            }
        });

        setupUI(findViewById(R.id.parent));

    }

    private void setReferences() {
        playButton = (ImageButton) findViewById(R.id.play);
    }


    private void setService() {
        try {
            serviceIntent = new Intent(MainActivity.this, PlayMusicService.class);
        }
        catch (Exception e) {

        }
    }

    private void setToolbarAndNavigation() {
        //toolbar and navigation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        setSupportActionBar(toolbar);
        TextView tv = (TextView) toolbar.findViewById(R.id.title_toolbar);
        tv.setTypeface(typeface);
        tv.setText("My Player");
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void setFont() {
        TextView textView = (TextView) findViewById(R.id.titlenavi);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "alina.otf");
        textView.setTypeface(typeface);
    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }

    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.cloud,
                R.drawable.local
        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager, final FragmentOnline fragment1, final FragmentOffline fragment2) {
        adapter.addFrag(fragment1, "ONE");
        adapter.addFrag(fragment2, "TWO");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    ((TextView) toolbar.findViewById(R.id.title_toolbar)).setText("Online Music");
                } else if (position == 1) {
                    ((TextView) toolbar.findViewById(R.id.title_toolbar)).setText("Offline Music");
                }
                adapter.notifyDataSetChanged();
                setupTabIcons();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }
    @Override
    public void OnSongClickListener(int i,boolean isplay) {
        if (isplay ==false) {
            serviceIntent.putExtra("songPath",list.get(i).getPath());
            playButton.setImageResource(R.drawable.pause);
            startService(serviceIntent);

        }
        else {
            playButton.setImageResource(R.drawable.play);
            stopService(serviceIntent);
        }
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
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}
