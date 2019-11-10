package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.ufrpe.revcare.R;

public class PerfilProfissional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profissional);
        getSupportActionBar().hide();

    }
}
