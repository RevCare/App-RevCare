package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.SessaoProfissional;

public class HomeProfissional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profissional);
        getSupportActionBar().hide();
        preencheTela(SessaoProfissional.getProfissional());
        Button buttonSair = findViewById(R.id.buttonSairProfissional);
        buttonSair.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              SessaoProfissional.reset();
              startActivity(new Intent(HomeProfissional.this, MainActivity.class));
          }
      });


    }
    public void preencheTela(Profissional profissional){
        TextView nome = findViewById(R.id.nomeprofissional);
        TextView cpf = findViewById(R.id.cpfProfissionalHome);
        TextView telefone = findViewById(R.id.telefoneProfissionalHome);
        TextView descricao = findViewById(R.id.decricaoprofissional);
        TextView email = findViewById(R.id.emailProfissionalHome);

        nome.setText(profissional.getNome());
        cpf.setText(profissional.getCpf());
        telefone.setText(profissional.getTelefone());
        email.setText(profissional.getEmail());
        descricao.setHint(profissional.getDescricao());
    }
}
