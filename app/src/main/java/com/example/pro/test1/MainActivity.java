package com.example.pro.test1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;
import com.hjm.bottomtabbar.BottomTabBar;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.left;
import static android.R.attr.right;
import static android.R.attr.singleUser;
import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ implements threefragmentUserAndPassword {
    private static final String REMOTE_IP = "localhost:33104";//这里是映射地址，可以随意写，不是服务器地址
    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/mydatabase";
    private static final String USER = "date";
    private static final String PASSWORD = "yuehui";
    private User user1,userf;
    private Connection conn;
    private final String TAG = "MAPLocation";
    private Boolean isToggle; //private LinearLayout content;
    private EditText addrtext;
    private Button btn, btn1;
    private ImageView imageView;
    private SearchView searchView;
    private DrawerLayout mDrawerLayout;
    private FrameLayout flcontent;
    private ListView lv;
    private List<String> datas;
    public Place[] all_list, zhejiang_list, shanghai_list;
    private String title;
    private String[] city = {"1", "2", "3", "4", "5"};
    private BottomTabBar bottomTabBar;
    private static final int ITEMPAGER = 4;
    private ViewPager mVp;
    private JPTabBar mTabBar;
    private Toolbar mtoolbar;
    private long exitTime = 0;
    private int loginup = 2;
    private String account;
    private ArrayList<people> L;
    //--99809564---123--
    //--99946450086 ---123---
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_v2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        initView();
        account = bundle.getString("account");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mTabBar.setContainer(mVp);
        Toast.makeText(this,account,Toast.LENGTH_LONG).show();
        onConnSsh();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onConn();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onQuery(account);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onQuery_all();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("user1's friend name",user1.friendname);
        onQueryfrind(user1.friendname);

        /*else if (loginup == 1) {
            setContentView(R.layout.main_v2);
            initView();

            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            mVp.setAdapter(adapter);
            mTabBar.setContainer(mVp);
        }*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTabBar = (JPTabBar) findViewById(R.id.tabbar);
        /*mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView) findViewById(R.id.image_circle);
        imageView.setImageResource(R.drawable.nocircle);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/
    }
    //设置标题
    @Titles
    private static final String[] mTitles={"推荐","发现","聊天","我"};

    //设置选中图标
    @SeleIcons
    private static final int[] mSelectIcons={R.drawable.recommend1,R.drawable.find1,R.drawable.chat1,R.drawable.me1};

    //设置未选中图标
    @NorIcons
    private static final int[] mNormalIcon={R.drawable.recommend2,R.drawable.find2,R.drawable.chat2,R.drawable.me2};

    @Override
    public String getUser() {
        return user1.account;
        //return null;
    }

    @Override
    public String getPassword() {
        //return null;
        return user1.password;
    }

    @Override
    public int getSex() {
        return user1.sex;
    }

    @Override
    public int getAge() {
        return user1.age;
    }

    @Override
    public people[] getWantpeople() {
        peopletest peopletest1 = new peopletest(L,user1.sex,user1.character,user1.age,user1.place);
        people[] all_list = peopletest1.sss();
        return all_list;
        //return null;
    }

    @Override
    public int getcharacter() {
        return user1.character;
    }

    @Override
    public User getCurrentUser() {
        return user1;
    }

    @Override
    public User getFriendUser() {
        return userf;
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /*Bundle bundle=new Bundle();
            bundle.putInt("position",position+1);
            HomeFragment fragment = new HomeFragment();
            fragment.setArguments(bundle);
            return fragment;*/
            switch (position+1) {
                case 1:
                    oneFragment one1 = new oneFragment();
                    return one1;
                    //break;
                case 2:
                    twofragment two = new twofragment();
                    return two;
                    //break;
                case 3:
                    Threefragment three = new Threefragment();
                    return three;
                    //break;
                case 4:
                    Fourfragment four = new Fourfragment();
                    return four;
                    //break;
                default:
                    break;
            }
            oneFragment one = new oneFragment();
            return one;
        }

        @Override
        public int getCount() {
            return ITEMPAGER;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
            } finally {
                conn = null;
            }
        }
    }

    public void onConnSsh() {

        new Thread() {
            public void run() {
                Log.e("============", "预备连接服务器");
                Util.go();
            }
        }.start();
    }

    public void onConn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                conn = Util.openConnection(URL, USER, PASSWORD);
                Log.i("onConn", "onConn");
            }
        }).start();
    }


    /*public void onDelete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "delete from table_user_info where Id=1;";
                Util.execSQL(conn, sql);
                Log.i("onDelete", "onDelete");
            }
        }).start();
    }*/

    /*public void onUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "update table_user_info set Account='baba' where Id=1;";

                Util.execSQL(conn, sql);
                Log.i("onUpdate", "onUpdate");
            }
        }).start();
    }*/

    public void onQueryall() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Util.query(conn, "select * from table_user_info;");
                Log.i("onQuery", "onQuery");
            }
        }).start();
    }  //----查询所有内容----


    /*public void onpassword(final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ss = "\""+name+"\"";
                Log.i("onpass --- ",ss);
                //Toast.makeText(login.this,ss,Toast.LENGTH_SHORT).show();
                String S=Util.getpassword(conn, "select password from table_user_info where Account="+ss+";");
                //String S=Util.getpassword(conn, "select password from table_user_info where Account=\"yaoyuhang\";");
                password_return = S;
                Log.i("onpassword --- ",password_return);
            }
        }).start();
    }*/
    public void onQuery(final String account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //ArrayList<people> L;
                String ss = "\""+account+"\"";
                //User user1;
                user1 = Util.getuserInfo(conn, "select * from table_user_info where Account="+ss+";",account);
                Log.i("onQuery--all", user1.account);
                Log.i("onQuery--all", user1.introduction);
                Log.i("onQuery--all", user1.telephone );
                Log.i("onQuery--all", user1.friendname );
                //Log.i("onQuery--all", user1.account);
            }
        }).start();
    }
    public void onQueryfrind(final String account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //ArrayList<people> L;
                String ss = "\""+account+"\"";
                //User user1;
                userf = Util.getuserInfo(conn, "select * from table_user_info where Account="+ss+";",account);
                Log.i("onQuery--friend--all", userf.account);
                Log.i("onQuery--friend--all", userf.introduction);
                Log.i("onQuery--friend--all", userf.telephone );
                Log.i("onQuery--friend--all", userf.friendname );
                //Log.i("onQuery--all", user1.account);
            }
        }).start();
    }
    public void onQuery_all() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //ArrayList<people> L;
                L=Util.queryall(conn, "select * from table_user_info");
                Log.i("onQuery -- all people- ", String.valueOf(L.size()));
            }
        }).start();
    }
}





