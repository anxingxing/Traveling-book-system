package com.example.travelbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class Login1Activity extends AppCompatActivity {
    private Button btn_login;
    EditText account;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1_layout);
        btn_login = (Button)findViewById(R.id.btn_login);
        account =  (EditText)findViewById(R.id.account_input);
        password =  (EditText)findViewById(R.id.password_input);
        setListener();
    }
    /**
     * 设置监听
     */
    private void setListener() {
        // 按钮点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                        String accounts = account.getText().toString();
                        String passwords = password.getText().toString();
                        if(accounts.length()==0){
                            Looper.prepare();
                            Toast toast = Toast.makeText(Login1Activity.this, "账号不能为空！", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                        else {
                            if (passwords.length() == 0) {
                                Looper.prepare();
                                Toast toast = Toast.makeText(Login1Activity.this, "密码不能为空！", Toast.LENGTH_SHORT);
                                toast.show();
                                Looper.loop();
                            }
                            else {
                                HashMap<String,Object> map = DBsql.getInfoByAccount(accounts,passwords);  //将账号传入数据库
                                if (map != null) {
                                    Intent intent = new Intent(Login1Activity.this,Main2Activity.class);
                                    startActivity(intent);

                            } else {
                                    Looper.prepare();
                                    Toast toast = Toast.makeText(Login1Activity.this, "账号或密码错误", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Looper.loop();
                                }
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
