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

public class delete_flight extends AppCompatActivity {
    private Button btn_dlookf;
    private Button btn_deletef;
    private TextView tv_show;
    EditText dstartcity;
    EditText dendcity;
    EditText dflightnum;

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
        setContentView(R.layout.activity_delete_flight);
        btn_dlookf = (Button)findViewById(R.id.btn_dlookf);
        btn_deletef = (Button)findViewById(R.id.btn_deletef);
        dstartcity =  (EditText)findViewById(R.id.dstartcity);
        dendcity =  (EditText)findViewById(R.id.dendcity);
        dflightnum = (EditText)findViewById(R.id.dflightnum);
        tv_show = (TextView)findViewById(R.id.tv_showd);
        setListener();
    }

    private void setListener() {
        // 按钮点击事件
        btn_dlookf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String start = dstartcity.getText().toString();
                        String end = dendcity.getText().toString();
                        // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                        if (start.length() == 0) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(delete_flight.this, "出发城市不能为空！", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        } else {
                            if (end.length() == 0) {
                                Looper.prepare();
                                Toast toast = Toast.makeText(delete_flight.this, "目的城市不能为空！", Toast.LENGTH_SHORT);
                                toast.show();
                                Looper.loop();
                            } else {
                                HashMap<String, Object> map = DBsql.getInfoByAccount(start,end);
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
                    }
                }).start();
            }
        });

        btn_deletef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并insert
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用数据库工具类DBsql
                        String fnum = dflightnum.getText().toString();
                        DBsql.delete(fnum,1);
                    }
                }).start();
                Intent intent = new Intent(delete_flight.this,Delete.class);
                startActivity(intent);
            }
        });
    }
}
