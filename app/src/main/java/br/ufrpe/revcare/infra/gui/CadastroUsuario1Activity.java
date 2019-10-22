package br.ufrpe.revcare.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import android.widget.Button;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;
//
public class CadastroUsuario1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_usuario1);

        Button btnFinalizarCadastro = findViewById(R.id.botaoFinalizarCadastro);
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }

    private void cadastrar() {
        if (validarCampos()) {
            Usuario usuario = criarUsuario();

            startActivity(new Intent(CadastroUsuario1Activity.this, HomeUsuario.class));
        }
    }

    private boolean validarCampos() {
        EditText nNome = findViewById(R.id.nomeTextField);
        EditText nDataNascimento = findViewById(R.id.dataNascimentoTextField);
        EditText nCpf = findViewById(R.id.cpfTextField);
        EditText nEndereco = findViewById(R.id.enderecoTextField);
        EditText nTelefone = findViewById(R.id.telefoneTextField);
        EditText nEmail = findViewById(R.id.emailTextField);
        EditText nSenha = findViewById(R.id.senhaTextField);
        EditText nConfirmarSenha = findViewById(R.id.confirmarSenhaTextField);
        Validacao valido = new Validacao();
        boolean camposValidos =
                valido.isValido(nNome, nDataNascimento, nCpf, nEndereco, nTelefone, nEmail, nSenha, nConfirmarSenha);
        return camposValidos && confirmarSenha();
    }

    private boolean confirmarSenha() {
        boolean result = true;
        EditText nSenha = findViewById(R.id.senhaTextField);
        EditText nConfirmarSenha = findViewById(R.id.confirmarSenhaTextField);
        if (!nSenha.getText().toString().equals(nConfirmarSenha.getText().toString())) {
            result = false;
            Toast.makeText(getApplicationContext(),"Senhas diferentes", Toast.LENGTH_LONG).show();
        }
        return result;
    }

    private Usuario criarUsuario() {
        EditText nNome = findViewById(R.id.nomeTextField);
        EditText nDataNascimento = findViewById(R.id.dataNascimentoTextField);
        EditText nCpf = findViewById(R.id.cpfTextField);
        EditText nEndereco = findViewById(R.id.enderecoTextField);
        EditText nTelefone = findViewById(R.id.telefoneTextField);
        EditText nEmail = findViewById(R.id.emailTextField);
        EditText nSenha = findViewById(R.id.senhaTextField);

        Usuario result = new Usuario();
        result.setNome(nNome.getText().toString());
        result.setCpf(nCpf.getText().toString());
        result.setEndereco(nEndereco.getText().toString());
        result.setTelefone(nTelefone.getText().toString());
        result.setEmail(nEmail.getText().toString());
        result.setDataNascimento(nDataNascimento.getText().toString());
        result.setSenha(nSenha.getText().toString());
        return result;
    }
}