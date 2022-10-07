package com.corp.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.corp.imc.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText editText_altura, editText_peso, editText_resultado, editText_ideal;
    private Button button_calcular;
    private double usuario_altura, usuario_peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_altura = findViewById(R.id.text_altura);
        editText_peso = findViewById(R.id.text_peso);
        editText_resultado = findViewById(R.id.text_resultado);
        editText_ideal = findViewById(R.id.text_ideal);
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
            calcularPesoIdeal();

        }else if(media<=24.9){
            editText_resultado.setText("Peso normal!!");
            editText_ideal.setText("Peso ok!");

        }else if(media<=29.9){
            editText_resultado.setText("Acima do peso!!");
            calcularPesoIdeal();

        }else if(media<=34.9){
            editText_resultado.setText("Obesidade Grau I!!");
            calcularPesoIdeal();
        }
        else if(media<=39.9){
            editText_resultado.setText("Obesidade Grau II!!");
            calcularPesoIdeal();

        }else{
            editText_resultado.setText("Obesidade Grau III!!");
            calcularPesoIdeal();

        }

    }

    private void calcularPesoIdeal(){
        usuario_altura = Double.parseDouble(editText_altura.getText().toString());
        double peso_ideal = 0;
        double res_altura = 0.0;
        res_altura = Math.pow(usuario_altura, 2);
        peso_ideal = (25*res_altura)-1;
        String peso = new DecimalFormat("##").format(peso_ideal);
        editText_ideal.setText(peso+"Kg");

    }

    private void maskEdit() {
        SimpleMaskFormatter mskTel = new SimpleMaskFormatter("N.NN");
        MaskTextWatcher maskT = new MaskTextWatcher(editText_altura, mskTel);
        editText_altura.addTextChangedListener(maskT);

    }

}