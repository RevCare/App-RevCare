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

public class CadastroUsuario extends AppCompatActivity {
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
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Não foi possível cadastrar.", Toast.LENGTH_LONG).show();
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
            startActivity(new Intent(CadastroUsuario.this, MainActivity.class));
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
        boolean emailValido =
                valido.validarEmail(nEmail);
        boolean camposValidos =
                valido.isValido(nNome, nDataNascimento, nEndereco, nTelefone, nEmail, nSenha, nConfirmarSenha);
        boolean senhasValidas =
                valido.confirmarSenha(getApplicationContext(),nSenha.getText().toString(),nConfirmarSenha.getText().toString());
        boolean cpfValido=
                valido.isCPF(nCpf) && confirmaCpf();
        boolean senhaCorreta=
                valido.senhaCorreta(nSenha);
        return emailValido && camposValidos && senhasValidas && cpfValido && senhaCorreta;
    }

    private  boolean confirmaEmail(){
        Usuario result = null;
        UsuarioServices services = new UsuarioServices(this);
        EditText nEmail = findViewById(R.id.emailTextField);
        String email = nEmail.getText().toString().trim();
        result = services.consultarEmail(email);
        if (result != null){
            nEmail.requestFocus();
            nEmail.setError("Preencha novamente o campo.");
            Toast.makeText(getApplicationContext(), "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private  boolean confirmaCpf(){
        Usuario result = null;
        UsuarioServices services = new UsuarioServices(this);
        EditText nCpf = findViewById(R.id.cpfTextField);
        String cpf = nCpf.getText().toString().trim();
        result = services.consultarCpf(cpf);
        if (result != null){
            nCpf.requestFocus();
            nCpf.setError("Preencha novamente o campo.");
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