package com.example.pro.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;

import android.text.TextUtils;


import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;


/**
 * Created by pro on 2017/6/10.
 */

public class signup extends AppCompatActivity {
    private static final String REMOTE_IP = "localhost:33104";//这里是映射地址，可以随意写，不是服务器地址
    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/mydatabase";
    private static final String USER = "date";
    private static final String PASSWORD = "yuehui";
    private User user1;
    private EditText edit1,edit2;
    private Connection conn;
    private Button btn1,btn2;
    private int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        btn1 = (Button) findViewById(R.id.btn_signup);
        btn2 = (Button) findViewById(R.id.btn_sign_to_login);
        //onConn();
        onConnSsh();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onConn();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onUpdate();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onId();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit1 = (EditText) findViewById(R.id.edit_signup_username);
                edit2 = (EditText) findViewById(R.id.edit_signup_password);

                //环信注册
                register();

                onInsert();
                Intent intent = new Intent(signup.this,login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();
            }
        });

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

    public void onInsert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                user1 = new User(edit1.getText().toString(),edit2.getText().toString(),edit1.getText().toString(),"this gay is too busy towritesomething","hello the world",20,"Shanghai",1);
                user1.telephone = "54749110";
                user1.character = 1;
                user1.friendname = "1239233608";
                //Util.go();
                //conn = Util.openConnection(URL, USER, PASSWORD);
                //String sql = "INSERT INTO table_user_info (Id,Password,Account) VALUES (1,\"yyh\",\"yaoyuhang\")";
                //String sql = "INSERT INTO table_user_info (Id,Password,Account) VALUES (1,\"yyh\",\"yaoyuhang\")";
                //String sql="INSERT INTO table_user_info (Id,Password,Account,Signature,Introduction,Age,Place,Sex,Friendname,Character,Telephone) " +
                  //      "VALUES ("+String.valueOf(id)+",\""+user1.password+"\",\""+user1.account+"\",\""+user1.signature+"\",\""+user1.introduction+"\","+ String.valueOf(user1.age)+",\""+user1.place+"\",\""+ String.valueOf(user1.sex)+"\",\""+user1.friendname+"\","+ String.valueOf(user1.character)+",\""+user1.telephone+"\");";
                //String sql = "INSERT INTO table_user_info (Id,Password,Account) VALUES (1,\"yyh\",\"yaoyuhang\")";
                Log.i("logoogfo",String.valueOf(id));
                //String sql="INSERT INTO table_user_info (Id,Password,Account,Signature,Introduction,Age,Place,Sex,Friendname,Character,Telephone) VALUES ("+String.valueOf(id)+",\""+user1.password+"\",\""+user1.account+"\",\""+user1.signature+"\",\""+user1.introduction+"\","+String.valueOf(user1.age)+",\""+user1.place+"\","+ String.valueOf(user1.sex)+",\""+user1.friendname+"\","+ String.valueOf(user1.character)+",\""+user1.telephone+ "\")";
                String sql="INSERT INTO table_user_info (Id,Password,Account,Xingge,Introduction,Age,Place,Sex,Friendname,Telephone) VALUES ("+String.valueOf(id)+",\""+user1.password+"\",\""+user1.account+"\","+ String.valueOf(user1.character)+ ",\""+user1.introduction+"\","+String.valueOf(user1.age)+",\""+user1.place+"\","+ String.valueOf(user1.sex)+",\""+user1.friendname+"\",\""+user1.telephone+ "\")";
                Util.execSQL(conn, sql);
                Log.i("onInsert", "onInsert");
            }
        }).start();
    }



    public void onDelete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "delete from table_user_info where Id=1;";
                Util.execSQL(conn, sql);
                Log.i("onDelete", "onDelete");
            }
        }).start();
    }

    public void onUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String username1 = "lidasong";
                int age = 150;
                String sql = "update table_user_info set Age ="+ String.valueOf(age) +" where Account= \""+username1+"\";";
                //String sql = "update table_user_info set Age = 150 where Account= \""+username1+"\";";
                Util.execSQL(conn, sql);
                Log.i("onUpdate", "onUpdate");
            }
        }).start();
    }

    public void onQuery() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Util.query(conn, "select * from table_user_info;");
                Log.i("onQuery", "onQuery");
            }
        }).start();
    }
    public void onpassword() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String S=Util.getpassword(conn, "select password from table_user_info where Account=\"yaoyuhang\";");
                //Toast.makeText(signup.this,"onpasswordonpassword",Toast.LENGTH_SHORT).show();
                /*TextView TextView_1 = (TextView) findViewById(R.id.TextView_1);
                TextView_1.setText(S);*/
            }
        }).start();
    }
    public void onId() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String S=Util.getId(conn, "select Id from table_user_info order by Id desc limit 1;");
                Log.i("onID",S);
                id = Integer.parseInt(S) + 1;
                //Toast.makeText(signup.this,"onpasswordonpassword",Toast.LENGTH_SHORT).show();
                /*TextView TextView_1 = (TextView) findViewById(R.id.TextView_1);
                TextView_1.setText(S);*/
            }
        }).start();
    }

    /**
     * 环信注册
     *
     */
    public void register() {
        final String username = edit1.getText().toString().trim();
        final String pwd = edit2.getText().toString().trim();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        // 调用sdk注册方法
                        EMClient.getInstance().createAccount(username, pwd);
                    } catch (final HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
