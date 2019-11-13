package br.ufrpe.revcare.avaliacao.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.avaliacao.persistencia.AvaliacaoDAO;
import br.ufrpe.revcare.usuario.negocio.SessaoUsuario;


public class AvaliacaoProfissional extends AppCompatActivity {
    public AvaliacaoDAO dao = new AvaliacaoDAO(getApplicationContext());

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
                dao.like(idUsuario,idProfissional);
            }
        });
        btnDeslike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                long idUsuario = SessaoUsuario.getUsuario().getId();
                long idProfissional = GuardaProfissional.getProfissionalGuardado().getId();
                dao.deslike(idUsuario,idProfissional);
            }
        });

    }


}
