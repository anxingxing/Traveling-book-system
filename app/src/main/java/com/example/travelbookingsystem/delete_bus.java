package com.example.travelbookingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class delete_bus extends AppCompatActivity {
    private Button btn_dlookb;
    private Button btn_deleteb;
    private TextView tv_show;
    EditText dlocation;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x11:
                    String s = (String) msg.obj;
                    tv_show.setText(s);
                    break;
                case 0x12:
                    String ss = (String) msg.obj;
                    tv_show.setText(ss);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bus);
        btn_dlookb = (Button)findViewById(R.id.btn_dlookb);
        btn_deleteb = (Button)findViewById(R.id.btn_deleteb);
        dlocation=  (EditText)findViewById(R.id.dlocation);
        dlocation = (EditText)findViewById(R.id.dlocation);
        tv_show = (TextView)findViewById(R.id.tv_showd1);
        setListener();
    }

    private void setListener() {
        // 按钮点击事件
        btn_dlookb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String loc = dlocation.getText().toString();
                        // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                        if (loc.length() == 0) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(delete_bus.this, "所在城市不能为空！", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                        else {
                            HashMap<String, Object> map = DBsql.getInfoByAccount3(loc,2);
                            Message message = handler.obtainMessage();
                            if (map != null) {
                                String s = "";
                                s = s + map;
                                message.what = 0x12;
                                message.obj = s;
                            } else {
                                message.what = 0x11;
                                message.obj = "查询结果为空";
                            }
                            // 发消息通知主线程更新UI
                            handler.sendMessage(message);
                        }
                    }
                }).start();
            }
        });

        btn_deleteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并insert
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用数据库工具类DBsql
                        String location = dlocation.getText().toString();
                        DBsql.delete(location,2);
                    }
                }).start();

                Intent intent = new Intent(delete_bus.this,Delete.class);
                startActivity(intent);
            }
        });
    }
}
