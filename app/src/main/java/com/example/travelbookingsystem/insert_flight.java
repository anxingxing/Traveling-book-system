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

public class insert_flight extends AppCompatActivity {
    private Button btn_insertf;
    private TextView tv_show;
    EditText startcity;
    EditText endcity;
    EditText numfi;
    EditText flightnum;
    EditText pricei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_flight);
        btn_insertf = (Button)findViewById(R.id.btn_insertf);
        startcity =  (EditText)findViewById(R.id.startcityi);
        endcity =  (EditText)findViewById(R.id.endcityi);
        numfi = (EditText)findViewById(R.id.numfi);
        flightnum = (EditText)findViewById(R.id.flightnumi);
        tv_show = (TextView)findViewById(R.id.tv_showi);
        pricei = (EditText) findViewById (R.id.pricei);
        setListener();
    }

    private void setListener() {
        // 按钮点击事件
        btn_insertf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并insert
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用数据库工具类DBsql
                        String start = startcity.getText().toString();
                        String end = startcity.getText().toString();
                        String fnum = flightnum.getText().toString();
                        String pnum = numfi.getText().toString();
                        String p = pricei.getText().toString();
                        int price = Integer.parseInt(p);
                        int i = Integer.parseInt(pnum);
                        int j =i;
                        DBsql.insertflight(fnum,price,i,j,start,end);
                    }
                }).start();

                Intent intent = new Intent(insert_flight.this,Delete.class);
                startActivity(intent);
            }
        });
    }
}
