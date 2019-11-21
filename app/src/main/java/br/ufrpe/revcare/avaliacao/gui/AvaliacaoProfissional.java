package br.ufrpe.revcare.avaliacao.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.avaliacao.dominio.Avaliacao;
import br.ufrpe.revcare.avaliacao.negocio.AvaliacaoServices;
import br.ufrpe.revcare.avaliacao.negocio.GuardaProfissional;
import br.ufrpe.revcare.usuario.gui.RecyclerViewUsuario;
import br.ufrpe.revcare.usuario.negocio.SessaoUsuario;


public class AvaliacaoProfissional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_profissional);
        getSupportActionBar().hide();
        Button btnLike = findViewById(R.id.buttonGostei);
        Button btnDeslike = findViewById(R.id.buttonNaoGostei);


        btnLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                long idUsuario = SessaoUsuario.getUsuario().getId();
                long idProfissional = GuardaProfissional.getProfissionalGuardado().getId();
                AvaliacaoServices services = new AvaliacaoServices(getApplicationContext());
                if (services.like(idUsuario, idProfissional)){
                    Toast.makeText(getApplicationContext(), "Você votou neste profissional.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Este profissional já foi votado por você .", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDeslike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                long idUsuario = SessaoUsuario.getUsuario().getId();
                long idProfissional = GuardaProfissional.getProfissionalGuardado().getId();

                AvaliacaoServices services = new AvaliacaoServices(getApplicationContext());
                if (services.deslike(idUsuario, idProfissional)){
                    Toast.makeText(getApplicationContext(), "Você votou neste profissional.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Este profissional já foi votado por você .", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(AvaliacaoProfissional.this, RecyclerViewUsuario.class));
            }
        });
    }
}
