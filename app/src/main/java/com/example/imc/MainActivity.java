package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MainActivity extends AppCompatActivity {
    private EditText editText_alt, editText_peso, editText_res;
    private Button btn_cal;
    private double us_alt, us_peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_alt = findViewById(R.id.text_altura);
        editText_peso = findViewById(R.id.text_peso);
        editText_res = findViewById(R.id.text_res);
        btn_cal = findViewById(R.id.button_cal);

        maskEdit();

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_alt.getText().toString().isEmpty() || editText_peso.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Favor preencher os campos em branco!!", Toast.LENGTH_SHORT).show();

                }else{
                    us_alt = Double.parseDouble(editText_alt.getText().toString());
                    us_peso = Double.parseDouble(editText_peso.getText().toString());
                    CalculoImc(us_alt, us_peso);
                    editText_alt.setText("");
                    editText_peso.setText("");
                }
            }
        });
    }
    private void CalculoImc(Double altura, Double peso){
        double media = 0.0;
        double res_altura = 0.0;
        res_altura = Math.pow(altura, 2);
        media = peso/res_altura;
        Log.i("CalculoImc", String.valueOf(media));

        if(media<18.5){
            editText_res.setText("Abaixo do peso!!");

        }else if(media<=24.9){
            editText_res.setText("Peso normal!!");

        }else if(media<=29.9){
            editText_res.setText("Acima do peso!!");

        }else if(media<=34.9){
            editText_res.setText("Obesidade Grau I!!");

        }
        else if(media<=39.9){
            editText_res.setText("Obesidade Grau II!!");

        }else{
            editText_res.setText("Obesidade Grau III!!");

        }
    }
    //Método para adicionar máscaras em EditText definidos
    private void maskEdit() {
        SimpleMaskFormatter mskTel = new SimpleMaskFormatter("N.NN");
        MaskTextWatcher maskT = new MaskTextWatcher(editText_alt, mskTel);
        editText_alt.addTextChangedListener(maskT);


    }
}