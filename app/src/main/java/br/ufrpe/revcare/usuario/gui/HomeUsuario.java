package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufrpe.revcare.R;

public class HomeUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
        getSupportActionBar().hide();

        Button btnProcurarProfissional = findViewById(R.id.botao_procurar_profissional);
        btnProcurarProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeUsuario.this, RecyclerViewUsuario.class));
            }
        });
        Button btnEditar = findViewById(R.id.botao_att_perfil);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeUsuario.this, UpdateUsuario.class));
            }
        });

        };
 }

