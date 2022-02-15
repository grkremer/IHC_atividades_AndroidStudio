package com.example.atividade_pratica_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        TextView mensagem = (TextView) findViewById(R.id.textView);

        if (extras != null) {
            String value = extras.getString("mensagem");
            mensagem.setText(value);
        }
    }

}