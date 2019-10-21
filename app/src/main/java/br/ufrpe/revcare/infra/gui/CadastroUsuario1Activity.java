package br.ufrpe.revcare.infra.gui;
//
import androidx.appcompat.app.AppCompatActivity;
//
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;
//
import android.widget.Button;
//
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

        Button botao_continuar_cadastro = findViewById(R.id.botaoFinalizarCadastro);
        botao_continuar_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO crud = new UsuarioDAO(getBaseContext());

                EditText nNome = findViewById(R.id.nomeTextField);
                EditText nDataNascimento = findViewById(R.id.dataNascimentoTextField);
                EditText nCpf = findViewById(R.id.cpfTextField);
                EditText nEndereco = findViewById(R.id.enderecoTextField);
                EditText nTelefone = findViewById(R.id.telefoneTextField);
                EditText nEmail = findViewById(R.id.emailTextField);
                EditText nSenha = findViewById(R.id.caixaTxtSenhaLogin);
                EditText nConfirmarSenha = findViewById(R.id.confirmarSenhaTextField);
                String res;


                Validacao valido = new Validacao();

                if (valido.validacao(nNome, nDataNascimento, nCpf, nEndereco, nTelefone, nEmail, nSenha, nConfirmarSenha)) {
                    String txtNome = nNome.getText().toString().trim();
                    String txtDataNascimento = nDataNascimento.getText().toString().trim();
                    String txtCpf = nCpf.getText().toString().trim();
                    String txtEndereco = nEndereco.getText().toString().trim();
                    String txxTelefone = nTelefone.getText().toString().trim();
                    String txtEmail = nEmail.getText().toString().trim();
                    String txtSenha = nSenha.getText().toString().trim();

                    res = crud.cadastrarUsuario(txtNome, txtCpf, txtEndereco,  txtDataNascimento, txxTelefone, txtEmail, txtSenha);
                    Toast.makeText(getApplicationContext(),res, Toast.LENGTH_LONG).show();

                    startActivity(new Intent(CadastroUsuario1Activity.this, HomeUsuario.class));
                }

            }
        });
    }
}


////
////
////        });
////    public void cadastrar(View view){
////        Usuario usuario = new Usuario();
////
////        usuario.setNome(nNome.getText().toString());
////        usuario.setCpf(nCpf.getText().toString());
////        usuario.setEndereco(nEndereco.getText().toString());
////        usuario.setTelefone(nTelefone.getText().toString());
////        usuario.setEmail(nEmail.getText().toString());
////        usuario.setDataNascimento(nDataNascimento.getText().toString());
////        usuario.setSenha(nSenha.getText().toString());
////
////        long id = dao.cadastrarUsuario(usuario);
////        Toast.makeText(CadastroUsuario1Activity.this, "Usuario salvo com sucesso " + id, Toast.LENGTH_SHORT).show();
////
////        }
//}
//
//
////Toast.makeText(this, "Usuario salvo com sucesso " + id, Toast.LENGTH_SHORT).show();