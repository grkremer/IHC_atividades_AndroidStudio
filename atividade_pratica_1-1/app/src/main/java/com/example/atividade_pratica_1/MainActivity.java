package com.example.atividade_pratica_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.botao);
        EditText entrada1 = (EditText) findViewById(R.id.editTextNumber);
        EditText entrada2 = (EditText) findViewById(R.id.editTextNumber2);
        TextView saida = (TextView) findViewById(R.id.resultado);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String valor_1 =  entrada1.getText().toString();
                String valor_2 =  entrada2.getText().toString();
                if(!(TextUtils.isEmpty(valor_1)) && !(TextUtils.isEmpty(valor_2))){
                    saida.setText(String.valueOf(Integer.parseInt(valor_1)+Integer.parseInt(valor_2)));
                }
                else{
                    saida.setText("Insira um número por favor");
                }
            }
        });

    }



}
