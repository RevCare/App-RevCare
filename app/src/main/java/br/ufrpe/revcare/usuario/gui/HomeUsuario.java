package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.negocio.SessaoUsuario;

public class HomeUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
        getSupportActionBar().hide();
        preencheTela(SessaoUsuario.getUsuario());

        Button btnProcurarProfissional = findViewById(R.id.botao_procurar_usuario);
        btnProcurarProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeUsuario.this, RecyclerViewUsuario.class));
            }
        });

        Button buttonSair = findViewById(R.id.buttonSairUsuario);
        buttonSair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SessaoUsuario.reset();
                finish();
                startActivity(new Intent(HomeUsuario.this, MainActivity.class));
            }
        });
        }
    public void preencheTela(Usuario usuario){
        TextView nome = findViewById(R.id.nomeUsuarioHome);
        TextView cpf = findViewById(R.id.cpfUsuarioHome);
        TextView telefone = findViewById(R.id.telefoneUsuarioHome);
        TextView descricao = findViewById(R.id.enderecoUsuarioHome);
        TextView email = findViewById(R.id.emailUsuarioHome);

        nome.setText(usuario.getNome());
        cpf.setText(usuario.getCpf());
        telefone.setText(usuario.getTelefone());
        email.setText(usuario.getEmail());
        descricao.setText(usuario.getEndereco());
    }
 }

