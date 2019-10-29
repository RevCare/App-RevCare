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
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.negocio.UsuarioServices;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;

public class MainActivity extends AppCompatActivity {
    private String email;
    private String senha;
    private EditText campoEmail;
    private EditText campoSenha;

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

                if (switchUsuarioProfissional.isChecked()) {
                    email = campoEmail.getText().toString().trim();
                    senha = campoSenha.getText().toString().trim();

                    startActivity(new Intent(MainActivity.this, HomeProfissional.class));
                } else {
                    UsuarioServices services = new UsuarioServices(getBaseContext());
                    Validacao validacao = new Validacao();
                    if (validacao.isValido(campoEmail,campoSenha)){
                        String email = campoEmail.getText().toString().trim();
                        Usuario procuraUser = services.searchUsuariobyEmail(email);
                        if (procuraUser != null){
                            String senhaDB = procuraUser.getSenha();
                            senha = campoSenha.getText().toString().trim();
                            if (senha.equals(senhaDB)){
                                startActivity(new Intent(MainActivity.this, HomeUsuario.class));
                            }else{
                                Toast.makeText(getApplicationContext(), "A senha está incorreta.", Toast.LENGTH_LONG).show();
                            }


                        }else{
                            Toast.makeText(getApplicationContext(), "Email não cadastrado.", Toast.LENGTH_LONG).show();
                        }
                    }



                }
            }
      });}}












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