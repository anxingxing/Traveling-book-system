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

public class book_bus extends AppCompatActivity {
    private Button btn_lookb;
    private Button btn_bookb;
    private TextView tv_show1;
    EditText location;
    EditText bnum;
    EditText bname;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x11:
                    String s = (String) msg.obj;
                    tv_show1.setText(s);
                    break;
                case 0x12:
                    String ss = (String) msg.obj;
                    tv_show1.setText(ss);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus);
        btn_lookb = (Button)findViewById(R.id.btn_lookb);
        btn_bookb = (Button)findViewById(R.id.btn_bookb);
        location =  (EditText)findViewById(R.id.location);
        bnum = (EditText)findViewById(R.id.bnum);
        tv_show1 = (TextView)findViewById(R.id.tv_show1);
        bname = (EditText)findViewById(R.id.bname);
        setListener();
    }

    private void setListener() {
        // 按钮点击事件
        btn_lookb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String loc = location.getText().toString();
                        String num = bnum.getText().toString();
                        int i = Integer.parseInt(num);
                        // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                        if (loc.length() == 0) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(book_bus.this, "所在城市不能为空！", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                        else {
                                HashMap<String, Object> map = DBsql.getInfoByAccount2(loc,i);
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

        btn_bookb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并insert
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用数据库工具类DBsql
                        String num = bnum.getText().toString();
                        int i = Integer.parseInt(num);
                        String custname = bname.getText().toString();
                        String loc = location.getText().toString();
                        int resvtype=2;
                        String hint = "Take a bus in "+ loc;
                        DBsql.insert(custname,resvtype,hint);
                        DBsql.update(2,i,loc);
                        Looper.prepare();
                        Toast toast = Toast.makeText(book_bus.this, "预定成功！", Toast.LENGTH_SHORT);
                        toast.show();
                        Looper.loop();
                    }
                }).start();

                Intent intent = new Intent(book_bus.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
