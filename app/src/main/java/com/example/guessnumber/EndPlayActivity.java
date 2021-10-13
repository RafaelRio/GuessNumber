package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.guessnumber.databinding.ActivityEndPlayBinding;

public class EndPlayActivity extends AppCompatActivity {

    ActivityEndPlayBinding binding;

    /**
     * Al crear la EndPlayActivity se comprueba el valor booleano del bundle que recoge
     * el intent desde la clase PlayActivity y dependiendo de su valor (true|false) se
     * haga visible un TextView u otro
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        boolean hasGanado = false;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (hasGanado != bundle.getBoolean("ganado")) {
            binding.tvGanado.setVisibility(View.VISIBLE);
            //El metodo getString es simplemente para que muestre el string con id = R.id.recurso
            //Si no se hace asi muestra el id en si, y no es la idea
            binding.tvGanado.setText(getString(R.string.hasGanadoEn) + " " + bundle.getInt("intentos") + " " + getString(R.string.intentos));
        } else {
            binding.tvPerdiste.setVisibility(View.VISIBLE);
            binding.tvPerdiste.setText(getString(R.string.PerdidoFinal) + " " + bundle.getInt("alea"));
        }

    }
}