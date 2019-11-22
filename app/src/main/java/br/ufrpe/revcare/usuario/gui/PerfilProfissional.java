package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.avaliacao.gui.AvaliacaoProfissional;
import br.ufrpe.revcare.avaliacao.negocio.GuardaProfissional;

public class PerfilProfissional extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profissional);
        getSupportActionBar().hide();
        getIncomingIntent();
        Button btnAvaliarProfissional = findViewById(R.id.btnAvaliar);
        btnAvaliarProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView emailTela = findViewById(R.id.emailProfissionalPerfil);
                String txtEmail = emailTela.getText().toString().trim();
                GuardaProfissional.setProfissionalGuardado(txtEmail, getApplicationContext());
                startActivity(new Intent(PerfilProfissional.this, AvaliacaoProfissional.class));
            }
        });


    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("nome") && getIntent().hasExtra("endereco") && getIntent().hasExtra("telefone") && getIntent().hasExtra("email") && getIntent().hasExtra("cidade")){
            String nome = getIntent().getStringExtra("nome");
            String endereco = getIntent().getStringExtra("endereco");
            String telefone = getIntent().getStringExtra("telefone");
            String email = getIntent().getStringExtra("email");
            String descricao = getIntent().getStringExtra("cidade");

            setDadosTela(nome, endereco,telefone, email, descricao);
        }
    }
    private void setDadosTela(String nome, String endereco, String telefone, String email, String descricao){
        TextView nomeTela = findViewById(R.id.nomeProfissionalPerfil);
        TextView enderecoTela = findViewById(R.id.enderecoProfissional);
        TextView telefoneTela = findViewById(R.id.telefoneProfissionalHome);
        TextView descricaoTela = findViewById(R.id.descricaoprofissionalPerfil);
        TextView emailTela = findViewById(R.id.emailProfissionalPerfil);
        enderecoTela.setText(endereco);
        nomeTela.setText(nome);
        telefoneTela.setText(telefone);
        descricaoTela.setText(descricao);
        emailTela.setText(email);
    }


}