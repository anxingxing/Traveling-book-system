package com.example.travelbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private Button btn_delete;

    private Button book_flight;
    private Button book_bus;
    private Button book_hotel;

    private Button btn_allbus;
    private Button btn_allfights;
    private  Button btn_allhotels;
    private  Button btn_reservation;
    private  Button btn_customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_delete = (Button) findViewById(R.id.delete);
        btn_allbus = (Button) findViewById(R.id.btn_allbus);
        btn_allfights = (Button) findViewById(R.id.btn_allfights);
        btn_allhotels = (Button) findViewById(R.id.btn_allhotels);
        btn_reservation = (Button) findViewById(R.id.btn_reservation);
        btn_customers = (Button) findViewById(R.id.btn_customers);
        book_flight = (Button) findViewById(R.id.book_flight);
        book_bus = (Button) findViewById(R.id.book_bus);
        book_hotel = (Button) findViewById(R.id.book_hotel);
        setListener();
    }

    private void setListener() {
        // 按钮点击事件

        btn_allbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {             //查询所有大巴
                Intent intent = new Intent(Main2Activity.this,allbus.class);
                startActivity(intent);
            }
        });

        btn_allfights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {              //查询所有航班
                Intent intent = new Intent(Main2Activity.this,allfights.class);
                startActivity(intent);
            }
        });

        btn_allhotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                 //查询所有宾馆
                Intent intent = new Intent(Main2Activity.this,allhotels.class);
                startActivity(intent);
            }
        });



        book_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                      //预定航班
                Intent intent = new Intent(Main2Activity.this,book_flight.class);
                startActivity(intent);
            }
        });

        book_bus.setOnClickListener(new View.OnClickListener() {     //预定大巴
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,book_bus.class);
                startActivity(intent);
            }
        });

        book_hotel.setOnClickListener(new View.OnClickListener() {     //预定宾馆
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,book_hotel.class);
                startActivity(intent);
            }
        });


        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                 //查询所有预定记录 --管理员模式
                Intent intent = new Intent(Main2Activity.this,reservation.class);
                startActivity(intent);
            }
        });

        btn_customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                    //查询所有注册客户 --管理员模式
                Intent intent = new Intent(Main2Activity.this,customers.class);
                startActivity(intent);
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            //删除数据--管理员模式
                // 创建一个线程来连接数据库并insert
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 调用数据库工具类DBsql
                        Intent intent = new Intent(Main2Activity.this,Delete.class);
                        startActivity(intent);
                    }
                }).start();
            }
        });

    }
}
