package br.ufrpe.revcare.avaliacao.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.avaliacao.persistencia.AvaliacaoDAO;


public class AvaliacaoProfissional extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_profissional);
        getSupportActionBar().hide();
        Button btnLike = findViewById(R.id.buttonGostei);
        Button btnDeslike = findViewById(R.id.buttonNaoGostei);
        AvaliacaoDAO dao = new AvaliacaoDAO(getApplicationContext());

        btnLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            dao.like();
            }
        });
        btnDeslike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

    }


}
