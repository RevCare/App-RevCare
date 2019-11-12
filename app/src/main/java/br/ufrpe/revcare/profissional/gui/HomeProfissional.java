package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.SessaoProfissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;

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
        Button btnAtualizarPerfil = findViewById(R.id.botaoAtualizarPerfilProfissional);
        btnAtualizarPerfil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                atualizarPerfil();

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
    public void atualizarPerfil(){
        Profissional profissional = SessaoProfissional.getProfissional();
        EditText descricao = findViewById(R.id.decricaoprofissional);
        if (descricao.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "A descrição não foi atualizada.", Toast.LENGTH_LONG).show();
        }else{
            profissional.setDescricao(descricao.getText().toString().trim());
            ProfissionalDAO dao = new ProfissionalDAO(getApplicationContext());
            dao.updateDescricaoProfissional(profissional);
            Toast.makeText(getApplicationContext(), "A descrição foi atualizada.", Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());

        }
    }
}
