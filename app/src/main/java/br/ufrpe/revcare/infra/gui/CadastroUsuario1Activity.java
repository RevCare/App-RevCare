package br.ufrpe.revcare.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;

public class CadastroUsuario1Activity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText cpf;
    private EditText endereco;
    private EditText senha;
    private EditText email;
    private EditText dataNascimento;
    private UsuarioDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_usuario1);

        nome = findViewById(R.id.nomeTextField);
        telefone = findViewById(R.id.telefoneTextField);
        cpf = findViewById(R.id.cpfTextField);
        endereco = findViewById(R.id.enderecoTextField);
        senha = findViewById(R.id.senhaTextField);
        email = findViewById(R.id.emailTextField);
        dataNascimento = findViewById(R.id.dataNascimentoTextField);

        dao = new UsuarioDAO(this);

    }
    public void cadastrar(View view){
        Usuario usuario = new Usuario();

        usuario.setNome(nome.getText().toString());
        usuario.setCpf(cpf.getText().toString());
        usuario.setEndereco(endereco.getText().toString());
        usuario.setTelefone(telefone.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setDataNascimento(dataNascimento.getText().toString());
        usuario.setSenha(senha.getText().toString());

        long id = dao.cadastrarUsuario(usuario);

        Toast.makeText(this, "Usuario salvo com sucesso " + id, Toast.LENGTH_SHORT).show();
    }
}
