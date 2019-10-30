package br.ufrpe.revcare.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.profissional.gui.CadastroProfissional1Activity;
import br.ufrpe.revcare.profissional.gui.HomeProfissional;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.gui.CadastroUsuario1Activity;
import br.ufrpe.revcare.usuario.gui.HomeUsuario;
import br.ufrpe.revcare.usuario.negocio.UsuarioServices;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Button btnEntrar = findViewById(R.id.botaoEntrar);
        Button btnCadastrar = findViewById(R.id.botaoCadastro);
        final Switch switchUsuarioProfissional = findViewById(R.id.switchUsuarioProfissional);
        final EditText campoEmail = findViewById(R.id.caixatxtEmailLogin);
        final EditText campoSenha = findViewById(R.id.caixatxtSenhaLogin);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            if (switchUsuarioProfissional.isChecked()) {
                startActivity(new Intent(MainActivity.this, CadastroProfissional1Activity.class));
            } else {
                startActivity(new Intent(MainActivity.this, CadastroUsuario1Activity.class));
            }
            }

        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar(switchUsuarioProfissional, campoEmail, campoSenha);
            }
      });}

    private void entrar(Switch switchUsuarioProfissional, EditText campoEmail, EditText campoSenha) {
        if (switchUsuarioProfissional.isChecked()) {
            startActivity(new Intent(MainActivity.this, HomeProfissional.class));
        } else {

            entrarUsuario(campoEmail, campoSenha);
        }
    }

    private void entrarUsuario(EditText campoEmail, EditText campoSenha) {
        UsuarioServices services = new UsuarioServices(getBaseContext());
        Validacao validacao = new Validacao();
        if (validacao.isValido(campoEmail,campoSenha)){
            String email = campoEmail.getText().toString().trim();
            String senha = campoSenha.getText().toString().trim();
            try {
                services.logar(email,senha);
                startActivity(new Intent(MainActivity.this, HomeUsuario.class));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Email/senha incorretos.", Toast.LENGTH_LONG).show();
            }
        }
    }
}












//    private boolean camposValidos() {
////        boolean result = true;
////        email = campoEmail.getText().toString().trim();
////
////        View focusView = null;
////        if (TextUtils.isEmpty(senha)) {
////            campoSenha.setError("Preencha a senha");
////            focusView = campoSenha;
////            result = false;
////        }
////        if (TextUtils.isEmpty(email)) {
////            campoEmail.setError("Campo obrigatorio");
////            focusView = campoEmail;
////            result = false;
////        } else if (!validaEmail(email)) {
////            campoEmail.setError("Email inválido");
////            focusView = campoEmail;
////            result = false;
////        }
////        if (!result) {
////            focusView.requestFocus();
////        }
////        return result;
////    }
////
////
////    private boolean validaEmail(String email) {
////        return (!(TextUtils.isEmpty(email)) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
////    }
//       Validacao validacao = new Validacao();
//       validacao.isValido(campoEmail,campoSenha);
//}