package com.example.pro.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by pro on 2017/5/14.
 */

public class Threefragment extends Fragment {
    public threefragmentUserAndPassword three;
    private Button btn;
    private String username,password;
    private int sex,age,character;
    private people[] people_to_show;
    private int[] girl_pic = new int[] {R.drawable.girl1,R.drawable.girl2,R.drawable.girl3,R.drawable.girl4,R.drawable.girl5,R.drawable.girl6,
            R.drawable.girl7,R.drawable.girl8,R.drawable.girl9,R.drawable.girl10};
    private int[] boy_pic = new int[] {R.drawable.boy1,R.drawable.boy2,R.drawable.boy3,R.drawable.boy4,R.drawable.boy5,R.drawable.boy6,
            R.drawable.boy7,R.drawable.boy8,R.drawable.boy9,R.drawable.boy10};
    private Button btn_dontlike,btn_like;
    private int pic_pos,people_pos;
    private boolean flag;
    private ImageView image_show;
    private Boolean isdenglu = false;
    public Threefragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = (Button) getActivity().findViewById(R.id.button5);
        image_show = (ImageView) getActivity().findViewById(R.id.imageView6);
        username = three.getUser();
        password = three.getPassword();
        sex = three.getSex();
        age = three.getAge();
        character = three.getcharacter();
        people_to_show = three.getWantpeople();
        Log.i("username",username);
        Log.i("password",password);
        Log.i("people_to_show  ", String.valueOf(people_to_show.length));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getActivity().getPackageManager();
                Intent intent = new Intent();
                intent = packageManager.getLaunchIntentForPackage("com.hyphenate.litedemo");
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.setFlags(0);
                startActivity(intent);
                isdenglu = true;
            }
        });
        if (sex == 0) flag = true;
        else flag = false;
        pic_pos = 0; people_pos = 0;
        btn_dontlike = (Button) getActivity().findViewById(R.id.btn_dontlike);
        btn_like = (Button) getActivity().findViewById(R.id.btn_like);
        btn_dontlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    pic_pos ++;
                    people_pos ++;
                    if (pic_pos>=people_to_show.length || pic_pos>9) pic_pos = 0;
                    if (people_pos>=people_to_show.length) people_pos = 0;
                    image_show.setImageResource(boy_pic[pic_pos]);
                } else {
                    pic_pos ++;
                    people_pos ++;
                    if (pic_pos>=people_to_show.length || pic_pos>9) pic_pos = 0;
                    if (people_pos>=people_to_show.length) people_pos = 0;
                    image_show.setImageResource(girl_pic[pic_pos]);
                }
            }
        });
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isdenglu) {
                    Toast.makeText(getContext(),"请先登录聊天",Toast.LENGTH_LONG).show();
                    return;
                }
                PackageManager packageManager = getActivity().getPackageManager();
                Intent intent = new Intent();
                intent = packageManager.getLaunchIntentForPackage("com.hyphenate.litedemo");
                intent.putExtra("friendname",people_to_show[people_pos].account);

                intent.setFlags(1);
                startActivity(intent);
            }
        });
        image_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Userdetail.class);//activity
                Bundle bundle = new Bundle();

                //bundle.putInt("ItemImage", placess[position].image);
                bundle.putString("user_name",people_to_show[people_pos].name);
                bundle.putString("signature",people_to_show[people_pos].signature);
                bundle.putString("introduction",people_to_show[people_pos].introduction);
                bundle.putString("place",people_to_show[people_pos].place);
                bundle.putInt("sex",people_to_show[people_pos].sex);
                bundle.putInt("age",people_to_show[people_pos].age);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            three = (threefragmentUserAndPassword) activity;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("接口实例化异常");
        }
    }
}
