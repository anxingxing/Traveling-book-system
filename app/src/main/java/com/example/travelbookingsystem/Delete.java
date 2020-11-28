package com.example.travelbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Delete extends AppCompatActivity {

    private Button delete_flight;
    private Button delete_bus;
    private Button delete_hotel;

    private Button insert_flight;
    private Button insert_bus;
    private  Button insert_hotel;

    private Button update_flight;
    private Button update_bus;
    private  Button update_hotel;
    private  Button qcust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_layout);
        delete_flight = (Button) findViewById(R.id.delete_flight);
        delete_bus = (Button) findViewById(R.id.delete_bus);
        delete_hotel = (Button) findViewById(R.id.delete_hotel);
        insert_flight = (Button) findViewById(R.id.insert_flight);
        insert_bus = (Button) findViewById(R.id.insert_bus);
        insert_hotel = (Button) findViewById(R.id.insert_hotel);
        update_flight = (Button) findViewById(R.id.update_flight);
        update_bus = (Button) findViewById(R.id.update_bus);
        qcust = (Button) findViewById(R.id.qcust);
        setListener();
    }

    private void setListener() {
        // 按钮点击事件

        delete_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {             //删除大巴
                Intent intent = new Intent(Delete.this, delete_flight.class);
                startActivity(intent);
            }
        });

        delete_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {              //删除航班
                Intent intent = new Intent(Delete.this, delete_bus.class);
                startActivity(intent);
            }
        });

        delete_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                 //删除宾馆
                Intent intent = new Intent(Delete.this, delete_hotel.class);
                startActivity(intent);
            }
        });


        insert_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {             //增加航班
                Intent intent = new Intent(Delete.this, insert_flight.class);
                startActivity(intent);
            }
        });

        update_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {              //更改航班
                Intent intent = new Intent(Delete.this, update_flight.class);
                startActivity(intent);
            }
        });

        qcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                 //查询所有宾馆
                Intent intent = new Intent(Delete.this, look_customer.class);
                startActivity(intent);
            }
        });

    }
}
