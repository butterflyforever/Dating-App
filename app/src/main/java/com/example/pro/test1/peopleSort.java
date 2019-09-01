package com.example.pro.test1;

/**
 * Created by liyang on 2017/6/16.
 */
public class peopleSort {
    private int total,one = 0,two = 0,three = 0;
    public people[] allpeople;

    public peopleSort( people[] peoples , int sex)
    {
        int tmp = peoples.length;

        for(int i = 0; i < tmp; ++i)
        {
            if((peoples[i].xingge == 1 || peoples[i].xingge == 2 || peoples[i].xingge == 3) && peoples[i].sex != sex) one++;
            else if((peoples[i].xingge == 4 || peoples[i].xingge == 5 || peoples[i].xingge == 6) && peoples[i].sex != sex) two++;
            else if((peoples[i].xingge == 7 || peoples[i].xingge == 8 || peoples[i].xingge == 9) && peoples[i].sex != sex) three++;
        }

        total = one + two + three;
        allpeople = new people[total];

        int cont = 0;
        for(int i = 0; i <tmp; i++)
        {
                if(peoples[i].sex != sex)
                {
                    allpeople[cont] = peoples[i];
                    cont++;
                }
        }
    }


    public static int partition(people []array,int lo,int hi, int i_age, int i_xingge, String i_place){
        //固定的切分方式
        people key = array[lo];
        while(lo<hi){
            while(Math.abs(array[hi].age - i_age) > Math.abs(key.age - i_age) && hi>lo ||
                    Math.abs(array[hi].age - i_age) == Math.abs(key.age - i_age) && Math.abs(array[hi].xingge - i_xingge) > Math.abs(key.xingge - i_xingge) && hi>lo ||
                    Math.abs(array[hi].age - i_age) == Math.abs(key.age - i_age) && Math.abs(array[hi].xingge - i_xingge) == Math.abs(key.xingge - i_xingge) && array[hi].place != i_place && hi>lo){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(Math.abs(array[lo].age - i_age) < Math.abs(key.age - i_age) && hi>lo ||
                    Math.abs(array[lo].age - i_age) == Math.abs(key.age - i_age) && Math.abs(array[lo].xingge - i_xingge)< Math.abs(key.xingge - i_xingge) && hi>lo ||
                    Math.abs(array[lo].age - i_age) == Math.abs(key.age - i_age) && Math.abs(array[lo].xingge - i_xingge) == Math.abs(key.xingge - i_xingge) && array[lo].place == i_place && hi>lo){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }


    public static void sort(people[] array,int lo ,int hi, int i_age, int i_xingge, String i_place){
        if(lo>=hi){
            return ;
        }

        int index=partition(array,lo,hi, i_age,i_xingge, i_place);
        sort(array,lo,index-1, i_age, i_xingge, i_place);
        sort(array,index+1,hi,i_age, i_xingge, i_place);
    }

    public people[] recommendSort(int i_age, int i_xingge, String i_place)
    {
        int num;
        int xingge_flag;
        if( i_xingge == 1 || i_xingge == 2 || i_xingge == 3) {num = one; xingge_flag = 0;}
        else if( i_xingge == 4 || i_xingge == 5 || i_xingge == 6) {num = two; xingge_flag = 1;}
        else {num = three;xingge_flag = 2;}

        people[] recommendlist = new people[num];

        int cont = 0;
        for(int i = 0; i<total; i++)
        {
            if((allpeople[i].xingge - 1)/3 == xingge_flag ) {
                recommendlist[cont] = allpeople[i];
                cont++;
            }
        }

        sort(recommendlist,0,num-1,i_age, i_xingge, i_place);
        return recommendlist;
    }






}
