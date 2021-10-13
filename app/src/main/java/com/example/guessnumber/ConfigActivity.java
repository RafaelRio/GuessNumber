package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guessnumber.databinding.ActivityConfigBinding;
import com.example.model.Message;

public class ConfigActivity extends AppCompatActivity {
    ActivityConfigBinding binding;
    private EditText edNombre, edIntentos;

    /**
     * Al crear la ConfigActivity tenemos que inicializar los campos para luego usarlos en el metodo
     * de jugar para leer la informacion.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        edNombre = findViewById(R.id.edNombre);
        edIntentos = findViewById(R.id.edIntentos);

    }

    /**
     * Metodo controlador del evento del boton dependiendo de su R.id
     * @param view
     */
    public void getOnClick(View view) {
        switch (view.getId()) {
            case R.id.btnJugar:
                Jugar();
                break;
        }
    }

    /**
     * Metodo del evento del boton jugar
     * Lo primero que hay que comprobar es que ninguno de los campos sean vacios y que el campo intentos
     * sea un numero entero mayor que 0. Si se da el caso salta un mensaje flotante
     *
     * Si no se da mete los datos en la clase serializable Message y los mete en un intent para mandarlos
     * a la PlayActivity
     */
    private void Jugar() {
        if (!TextUtils.isEmpty(edNombre.getText()) && !TextUtils.isEmpty(edIntentos.getText())) {
            if (TextUtils.isDigitsOnly(edIntentos.getText()) && edIntentos.getText().toString() == "0") {
                Bundle bundle = new Bundle();
                Message message = new Message();
                message.setNombre(binding.edNombre.getText().toString());
                message.setIntentos(Integer.parseInt(binding.edIntentos.getText().toString()));
                bundle.putSerializable("message", message);

                Intent intent = new Intent(this, PlayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                showMessage(R.string.comprTipo);
            }
        } else {
            showMessage(R.string.campoVacio);
        }
    }

    /**
     * Metodo simple que lanza el mensaje flotante en caso de fallo
     * @param mensaje
     */
    private void showMessage(int mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}