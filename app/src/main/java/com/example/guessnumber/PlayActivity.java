package com.example.guessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.databinding.ActivityPlayBinding;
import com.example.model.Message;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    ActivityPlayBinding binding;
    Random rnd = new Random();
    int alea = 1 + rnd.nextInt(101);
    int intentostotales;
    boolean isGanado = false;
    int intentosConsumidos;


    /**
     * Al crear la PlayActivity recogemos el intent de la ConfigActivity y creamos el objeto serializable
     * de la clase Message, con ello inicializamos algunas variables que nos harán falta para el
     * desarrollo de la aplicacion.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Message message = (Message) intent.getExtras().getSerializable("message");
        intentostotales = message.getIntentos();
        intentosConsumidos = message.getIntentos();    }

    /**
     * Metodo que llama a los metodos de los eventos onClick de los botones dependiendo de sus R.id
     * @param view
     */
    public void GetOnClick(View view) {
        switch (view.getId()) {
            case R.id.btnIntento:
                Jugando();
                break;

            case R.id.btnBorrar:
                Borrar();
                break;
        }
    }

    /**
     * Metodo del evento onClick del boton borrar
     * Pone invisible los TextView de los avisos de que el numero es mayor o menor
     * y vacia el EditText donde hay que introducirle el numero
     */
    private void Borrar() {
        binding.edIntentado.setText("");
        binding.tvMayor.setVisibility(View.INVISIBLE);
        binding.tvMenor.setVisibility(View.INVISIBLE);
    }

    /**
     * Metodo del evento onClick del boton Jugar
     * Lo primero que hace es comprobar que el campo no esté vacio y que el parametro introducido sea un
     * entero. Si algo de esto no se cumple muestra un texto flotante
     * Luego lo unico más que tiene es el conteo de intentos restantes y la llamada a la clase EndPlayActivity
     * con un valor de booleano diferente si has ganado o no
     */
    public void Jugando() {
        if (!TextUtils.isEmpty(binding.edIntentado.getText())) {
            if (TextUtils.isDigitsOnly(binding.edIntentado.getText())) {
                int numero = Integer.parseInt(binding.edIntentado.getText().toString());
                if (numero == alea) {
                    isGanado = true;
                    ultimaActividad();
                } else {
                    intentostotales--;
                    if (intentostotales == 0) {
                        ultimaActividad();
                        return;
                    }
                    if (numero < alea) {
                        binding.tvMayor.setVisibility(View.VISIBLE);
                    } else {
                        binding.tvMenor.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                Toast.makeText(this, R.string.comprTipo, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, R.string.campoVacio, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Metodo que recoge los datos de la PlayActivity los mete en el bundle y este en el intent
     * para mandarselos a la EndPlayActivity
     */
    public void ultimaActividad() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("ganado", isGanado);
        bundle.putInt("intentos", (intentosConsumidos-intentostotales) + 1);
        bundle.putInt("alea", alea);
        Intent intent = new Intent(this, EndPlayActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}