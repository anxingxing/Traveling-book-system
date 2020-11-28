package com.example.travelbookingsystem;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class reservation extends AppCompatActivity {
    private TextView tv_data4;
    private Button btn_reservation;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x11:
                    String s = (String) msg.obj;
                    tv_data4.setText(s);
                    break;
                case 0x12:
                    String ss = (String) msg.obj;
                    tv_data4.setText(ss);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        btn_reservation = (Button) findViewById(R.id.btn_reservation1);
        tv_data4 = (TextView)findViewById(R.id.tv_data4);
        setListener();
    }
    private void setListener()
    {
        // 按钮点击事件
        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并insert
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String sql = "select * from reservation";
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
