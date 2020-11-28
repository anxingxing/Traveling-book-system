package com.example.travelbookingsystem;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signinbutton=(Button)findViewById(R.id.signin);
        signinbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Login1Activity.class);
                startActivity(intent);
            }
        });

        Button singupbutton=(Button)findViewById(R.id.singup);
        singupbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Login2Activity.class);
                startActivity(intent);
            }
        });
    }
}


