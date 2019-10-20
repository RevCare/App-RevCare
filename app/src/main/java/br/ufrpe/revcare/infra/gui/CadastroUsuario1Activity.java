package br.ufrpe.revcare.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;

public class CadastroUsuario1Activity extends AppCompatActivity {

    private EditText nNome;
    private EditText nDataNascimento;
    private EditText nEndereco;
    private EditText nCpf;
    private EditText nEmail;
    private EditText nTelefone;
    private EditText nSenha;
    private EditText nConfirmarSenha;
    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_usuario1);

        nNome = findViewById(R.id.nomeTextField);
        nTelefone = findViewById(R.id.telefoneTextField);
        nCpf = findViewById(R.id.cpfTextField);
        nEndereco = findViewById(R.id.enderecoTextField);
        nSenha = findViewById(R.id.senhaTextField);
        nEmail = findViewById(R.id.emailTextField);
        nDataNascimento = findViewById(R.id.dataNascimentoTextField);

        dao = new UsuarioDAO(this);

    }
    public void cadastrar(View view){
        Usuario usuario = new Usuario();

        usuario.setNome(nNome.getText().toString());
        usuario.setCpf(nCpf.getText().toString());
        usuario.setEndereco(nEndereco.getText().toString());
        usuario.setTelefone(nTelefone.getText().toString());
        usuario.setEmail(nEmail.getText().toString());
        usuario.setDataNascimento(nDataNascimento.getText().toString());
        usuario.setSenha(nSenha.getText().toString());

        long id = dao.cadastrarUsuario(usuario);

        Toast.makeText(this, "Usuario salvo com sucesso " + id, Toast.LENGTH_SHORT).show();

        Button botao_continuar_cadastro = findViewById(R.id.botaoFinalizarCadastro);
        botao_continuar_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nNome = findViewById(R.id.nomeTextField);
                nDataNascimento = findViewById(R.id.dataNascimentoTextField);
                nCpf = findViewById(R.id.cpfTextField);
                nEndereco = findViewById(R.id.enderecoTextField);
                nTelefone = findViewById(R.id.telefoneTextField);
                nEmail = findViewById(R.id.emailTextField);
                nSenha = findViewById(R.id.caixaTxtSenhaLogin);
                nConfirmarSenha = findViewById(R.id.confirmarSenhaTextField);
                
                Validacao valido = new Validacao();

                if (valido.validacao(nNome, nDataNascimento, nCpf, nEndereco,nTelefone,nEmail,nSenha,nConfirmarSenha)) {
                    startActivity(new Intent(CadastroUsuario1Activity.this, HomeUsuario.class));
                }


            }

        });

    }
}