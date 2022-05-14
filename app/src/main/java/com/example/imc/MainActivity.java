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
    private EditText editText_altura, editText_peso, editText_resultado;
    private Button button_calcular;
    private double usuario_altura, usuario_peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_altura = findViewById(R.id.text_altura);
        editText_peso = findViewById(R.id.text_peso);
        editText_resultado = findViewById(R.id.text_resultado);
        button_calcular = findViewById(R.id.button_calculo);

        maskEdit();

        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_altura.getText().toString().isEmpty() || editText_peso.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Favor preencher os campos em branco!!", Toast.LENGTH_SHORT).show();

                }else{
                    usuario_altura = Double.parseDouble(editText_altura.getText().toString());
                    usuario_peso = Double.parseDouble(editText_peso.getText().toString());
                    CalculoImc(usuario_altura, usuario_peso);
                    editText_altura.setText("");
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

        if(media<18.5){
            editText_resultado.setText("Abaixo do peso!!");

        }else if(media<=24.9){
            editText_resultado.setText("Peso normal!!");

        }else if(media<=29.9){
            editText_resultado.setText("Acima do peso!!");

        }else if(media<=34.9){
            editText_resultado.setText("Obesidade Grau I!!");

        }
        else if(media<=39.9){
            editText_resultado.setText("Obesidade Grau II!!");

        }else{
            editText_resultado.setText("Obesidade Grau III!!");

        }

    }
    private void maskEdit() {
        SimpleMaskFormatter mskTel = new SimpleMaskFormatter("N.NN");
        MaskTextWatcher maskT = new MaskTextWatcher(editText_altura, mskTel);
        editText_altura.addTextChangedListener(maskT);

    }

}