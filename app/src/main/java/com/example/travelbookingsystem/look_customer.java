package com.example.travelbookingsystem;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class look_customer extends AppCompatActivity {
    private Button btn_lookc;
    private TextView tv_look;
    EditText cname;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x11:
                    String s = (String) msg.obj;
                    tv_look.setText(s);
                    break;
                case 0x12:
                    String ss = (String) msg.obj;
                    tv_look.setText(ss);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_customer);
        btn_lookc = (Button)findViewById(R.id.btn_lookc);
        cname = (EditText)findViewById(R.id.cname);
        tv_look = (TextView)findViewById(R.id.tv_look);
        setListener();
    }

    private void setListener() {
        // 按钮点击事件
        btn_lookc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name = cname.getText().toString();
                        String sql = "select * from reservation where custname ='" + name
                                + "'";
                        // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                        String result=DBsql.query(sql);
                        Message message = handler.obtainMessage();
                        if (result != null) {
                            String s = "";
                            s = s + result;
                            message.what = 0x12;
                            message.obj = s;
                        } else {
                            message.what = 0x11;
                            message.obj = "查询结果为空";
                        }
                        // 发消息通知主线程更新UI
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}

