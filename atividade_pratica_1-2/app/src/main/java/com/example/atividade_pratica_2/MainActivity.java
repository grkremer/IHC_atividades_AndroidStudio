package com.example.atividade_pratica_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.botao_enviar);
        EditText texto = (EditText) findViewById(R.id.editTextTextPersonName);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value= texto.getText().toString();
                if (!(value.isEmpty())){
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    i.putExtra("mensagem",value);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"A mensagem está vazia",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}