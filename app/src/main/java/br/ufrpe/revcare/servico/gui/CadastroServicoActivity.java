package br.ufrpe.revcare.servico.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.ufrpe.revcare.R;

public class CadastroServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);
        getSupportActionBar().hide();

    }
}
