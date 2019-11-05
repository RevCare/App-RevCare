package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.infra.gui.Validacao;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.negocio.UsuarioServices;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;

public class CadastroUsuarioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_usuario);

        Button btnFinalizarCadastro = findViewById(R.id.botaoFinalizarCadastro);
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cadastrar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void cadastrar() throws Exception {
        if (validarCampos() && confirmaEmail()) {
            Usuario usuario = criarUsuario();
            UsuarioServices services = new UsuarioServices(getBaseContext());
            services.cadastrar(usuario);
            Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(CadastroUsuarioActivity.this, MainActivity.class));
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
            Toast.makeText(getApplicationContext(), "Senhas diferentes", Toast.LENGTH_LONG).show();
        }
        return result;

    }
    private  boolean confirmaEmail(){
        Usuario result = null;
        UsuarioDAO dao = new UsuarioDAO(this);
        EditText nEmail = findViewById(R.id.emailTextField);
        String email = nEmail.getText().toString().trim();
        result = dao.consultar(email);
        if (result != null){
            nEmail.requestFocus();
            nEmail.setError("Preencha novamente o Campo.");
            Toast.makeText(getApplicationContext(), "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
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
        result.setNome(nNome.getText().toString().trim());
        result.setCpf(nCpf.getText().toString().trim());
        result.setEndereco(nEndereco.getText().toString().trim());
        result.setTelefone(nTelefone.getText().toString().trim());
        result.setEmail(nEmail.getText().toString().trim());
        result.setDataNascimento(nDataNascimento.getText().toString().trim());
        result.setSenha(nSenha.getText().toString().trim());
        return result;
    }
}