package com.example.pro.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pro on 2017/5/14.
 */

public class Fourfragment extends Fragment {
    public User user1,userf;
    private ImageButton imageButton;
    private ImageView imageView;
    private View headerview;
    private NavigationView navigationView;
    private Button btn1;
    private String username,password;
    private int sex,age,character;
    private threefragmentUserAndPassword four;
    //private User user1;
    public Fourfragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //user1 = new User("李大松","123456","ldslds","这个人很懒，什么都没有写","我是李大松，来自上海交大，今年大二、、、",20,"上海",1);
        user1 = four.getCurrentUser();
        userf = four.getFriendUser();

        //imageButton = (ImageButton) getActivity().findViewById(R.id.imageView5);
        navigationView = (NavigationView) getActivity().findViewById(R.id.navigation);
        headerview = navigationView.getHeaderView(0);
        //imageButton = (ImageButton) headerview.findViewById(R.id.imageView5);
        imageView = (ImageView) headerview.findViewById(R.id.imageView5);
        imageView.setImageResource(R.drawable.cafedeadend);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Userdetail.class);//activity
                Bundle bundle = new Bundle();

                //bundle.putInt("ItemImage", placess[position].image);
                bundle.putString("user_name",user1.name);
                bundle.putString("signature",user1.signature);
                bundle.putString("introduction",user1.introduction);
                bundle.putString("place",user1.place);
                bundle.putInt("sex",user1.sex);
                bundle.putInt("age",user1.age);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        TextView tv1 = (TextView) headerview.findViewById(R.id.nav_tv1);
        TextView tv2 = (TextView) headerview.findViewById(R.id.nav_tv2);
        tv1.setText(user1.name);
        tv2.setText(user1.introduction);
        //imageButton.setOnClickListener();
        btn1 = (Button) getActivity().findViewById(R.id.btn_fragment4_exit);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        NavigationView view2 = (NavigationView) getActivity().findViewById(R.id.navigation);
        view2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                int id = menuItem.getItemId();
                if (id == R.id.nav_camera) {
                    Intent intent = new Intent(getActivity(), Userdetail.class);//activity
                    Bundle bundle = new Bundle();

                    //bundle.putInt("ItemImage", placess[position].image);
                    bundle.putString("user_name",user1.name);
                    bundle.putString("signature",user1.signature);
                    bundle.putString("introduction",user1.introduction);
                    bundle.putString("place",user1.place);
                    bundle.putInt("sex",user1.sex);
                    bundle.putInt("age",user1.age);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (id == R.id.nav_gallery) {
                    //fragmentManager.beginTransaction().replace(R.id.content_frame,new FirstFragment()).commit();
                    Intent intent = new Intent(getActivity(), Userdetail.class);//activity
                    Bundle bundle = new Bundle();

                    //bundle.putInt("ItemImage", placess[position].image);
                    bundle.putString("user_name",userf.name);
                    bundle.putString("signature",userf.signature);
                    bundle.putString("introduction",userf.introduction);
                    bundle.putString("place",userf.place);
                    bundle.putInt("sex",userf.sex);
                    bundle.putInt("age",userf.age);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (id == R.id.nav_manage) {

                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }
                return true;
            }
        });
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(getActivity(), Userdetail.class);//activity
            Bundle bundle = new Bundle();

            //bundle.putInt("ItemImage", placess[position].image);
            bundle.putString("user_name",user1.name);
            bundle.putString("signature",user1.signature);
            bundle.putString("introduction",user1.introduction);
            bundle.putString("place",user1.place);
            bundle.putInt("sex",user1.sex);
            bundle.putInt("age",user1.age);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new FirstFragment()).commit();
            Intent intent = new Intent(getActivity(), Userdetail.class);//activity
            Bundle bundle = new Bundle();

            //bundle.putInt("ItemImage", placess[position].image);
            bundle.putString("user_name",userf.name);
            bundle.putString("signature",userf.signature);
            bundle.putString("introduction",userf.introduction);
            bundle.putString("place",userf.place);
            bundle.putInt("sex",userf.sex);
            bundle.putInt("age",userf.age);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        return true;
    }*/
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            four = (threefragmentUserAndPassword) activity;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("接口实例化异常");
        }
    }


}
