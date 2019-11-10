package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.ufrpe.revcare.R;

public class PerfilProfissional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profissional);
        getSupportActionBar().hide();
        getIncomingIntent();
    }
    //Esse método pega os dados que a gente passou no Intent lá no adapter
    private void getIncomingIntent(){
        if (getIntent().hasExtra("nome") && getIntent().hasExtra("endereco") && getIntent().hasExtra("id")){
            String nome = getIntent().getStringExtra("nome");
            String endereco = getIntent().getStringExtra("endereco");
            String id = getIntent().getStringExtra("id");

            mudar_dados(nome, endereco);
        }
    }
    private void mudar_dados(String nome, String endereco){
        TextView name = findViewById(R.id.nomeProfissionalPerfil);
        TextView enderecoTela = findViewById(R.id.enderecoProfissional);
        TextView telefone = findViewById(R.id.telefoneProfissionalHome);
        TextView descricao = findViewById(R.id.descricaoprofissionalPerfil);
        TextView email = findViewById(R.id.emailProfissionalPerfil);
        enderecoTela.setText(endereco);
        name.setText(nome);
    }

}
