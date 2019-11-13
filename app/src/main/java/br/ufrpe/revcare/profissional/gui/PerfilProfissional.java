package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.avaliacao.gui.AvaliacaoProfissional;
import br.ufrpe.revcare.avaliacao.gui.GuardaProfissional;

public class PerfilProfissional extends AppCompatActivity {
    public TextView nomeTela = findViewById(R.id.nomeProfissionalPerfil);
    public TextView enderecoTela = findViewById(R.id.enderecoProfissional);
    public TextView telefoneTela = findViewById(R.id.telefoneProfissionalHome);
    public TextView descricaoTela = findViewById(R.id.descricaoprofissionalPerfil);
    public TextView emailTela = findViewById(R.id.emailProfissionalPerfil);
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
                GuardaProfissional guardaProfissional = new GuardaProfissional();
                String email = emailTela.getText().toString();
                guardaProfissional.setProfissionalGuardado(email,getApplicationContext());
                startActivity(new Intent(PerfilProfissional.this, AvaliacaoProfissional.class));
            }
        });
    }
    //Esse método pega os dados que a gente passou no Intent lá no adapter
    private void getIncomingIntent(){
        if (getIntent().hasExtra("nome") && getIntent().hasExtra("endereco") && getIntent().hasExtra("telefone") && getIntent().hasExtra("email") && getIntent().hasExtra("descricao")){
            String nome = getIntent().getStringExtra("nome");
            String endereco = getIntent().getStringExtra("endereco");
            String telefone = getIntent().getStringExtra("telefone");
            String email = getIntent().getStringExtra("email");
            String descricao = getIntent().getStringExtra("descricao");

            mudar_dados(nome, endereco,telefone, email, descricao);
        }
    }
    private void mudar_dados(String nome, String endereco, String telefone, String email, String descricao){
        enderecoTela.setText(endereco);
        nomeTela.setText(nome);
        telefoneTela.setText(telefone);
        descricaoTela.setText(descricao);
        emailTela.setText(email);
    }

}
