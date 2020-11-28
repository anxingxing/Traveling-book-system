package com.example.travelbookingsystem;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login2Activity extends AppCompatActivity {
    private Button btn_enroll;
    private EditText name;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2_layout);
        btn_enroll = (Button) findViewById(R.id.btn_enroll);
        name = (EditText) findViewById(R.id.account_input);
        password = (EditText) findViewById(R.id.password_input);
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        // 按钮点击事件
        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并insert
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                        String accounts = name.getText().toString();
                        String passwords = password.getText().toString();
                        if (accounts.length() == 0) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(Login2Activity.this, "账号不能为空！", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        } else {
                            if (passwords.length() == 0) {
                                Looper.prepare();
                                Toast toast = Toast.makeText(Login2Activity.this, "密码不能为空！", Toast.LENGTH_SHORT);
                                toast.show();
                                Looper.loop();
                            }
                            else {
                                DBsql db = new DBsql();
                                boolean result = db.insert(accounts, passwords);
                                if (!result) {
                                    Looper.prepare();
                                    Toast toast = Toast.makeText(Login2Activity.this, "注册成功！", Toast.LENGTH_SHORT);
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

