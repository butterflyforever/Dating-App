package com.example.pro.test1;

import java.util.ArrayList;

/**
 * Created by liyang on 2017/6/16.
 */
public class peopletest {
    /*private static ArrayList<people> people_list = new ArrayList<people>(){{
        add(new people(28,	"Abigail"	,"123",	"1239233666",	"Nice to meet you!",	"Hello, everyone!",	21,	"shanghai",	0,	"1239233691",	5,	"13362169179"));
        add(new people(29,	"Ada",	"123",	"1239233667",	"Nice to meet you!",	"Hello, everyone!",	21,	"shanghai",	0,	"1239233692",	4,	"13362169180"));
        add(new people(30,	"Adela",	"123",	"1239233668",	"Nice to meet you!",	"Hello, everyone!",	22,"shanghai",	0,	"1239233693",	6,	"13362169181"));
        add(new people(31,	"Adelaide",	"123",	"1239233669",	"Nice to meet you!",	"Hello, everyone!",	23,	"zhejiang",	0,	"1239233694",	4,	"13362169182"));
        add(new people(32,	"Barbara",	"123",	"1239233670",	"Nice to meet you!",	"Hello, everyone!",	23,	"zhejiang",	0,	"1239233695",	5,	"13362169183"));
        add(new people(57,	"Burton",	"123",	"1239233695",	"Nice to meet you!",	"Hello, everyone!",	24,	"zhejiang",	1,	"1239233670",	5,	"13362169208"));
        add(new people(58,	"Byron",	"123",	"1239233696",	"Nice to meet you!",	"Hello, everyone!",	25,	"shanghai",	1,	"1239233671",	5,	"13362169209"));
        add(new people(61,	"Dwight",	"123",	"1239233699",	"Nice to meet you!",	"Hello, everyone!",	28,	"zhejiang",	1,	"1239233674",	7,	"13362169212"));
        add(new people(62,	"Dylan",	"123",	"1239233700",	"Nice to meet you!",	"Hello, everyone!",	29,	"zhejiang",	1,	"1239233675",	8,	"13362169213"));
        add(new people(63,	"Earl",	"123",	"1239233701",	"Nice to meet you!",	"Hello, everyone!",	30,	"zhejiang",	1,	"1239233676",	8,	"13362169214"));
    }};*/

    private static people[] peoples;
    private int Sex,Xingge,Age;
    private String Place;
    public peopletest(ArrayList<people> people_list,int sex,int xingge,int age,String place)
    {
        int num = people_list.size();
        System.out.println(num);
        peoples = new people[num];
        for(int i = 0; i<num; i++){
            peoples[i] = people_list.get(i);
        }
        this.Sex = sex; this.Xingge = xingge;
        this.Age = age; this.Place = place;
    }



    public people[] sss() {
        /*int num = people_list.size();
        
        peoples = new people[num];
        for(int i = 0; i<num; i++){
            peoples[i] = people_list.get(i);
        }*/
        //int sex = 1;
        peopleSort res  = new peopleSort(peoples, Sex);
        //int i_age = 22, i_xingge = 6;
        //String i_place = "zhejiang";
        people[] alllist = res.recommendSort(Age, Xingge, Place);

        /*System.out.println("All:");
        for(people x: alllist)
        {
            System.out.println(x.name);
        }
        */
        return alllist;
    }
}
