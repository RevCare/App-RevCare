package br.ufrpe.revcare.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.usuario.negocio.ValidarLogin;

public class MainActivity extends AppCompatActivity {
    private EditText lCpf;
    private EditText lSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Button btnEntrar = findViewById(R.id.botaoEntrar);
        Button btnCadastrar = findViewById(R.id.botaoCadastro);
        final EditText campoEmail = findViewById(R.id.caixatxtEmailLogin);
        final EditText campoSenha = findViewById(R.id.caixatxtSenhaLogin);
        final Switch switchUsuarioProfissional = findViewById(R.id.switchUsuarioProfissional);


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
               Validacao validarcampos = new Validacao();
                boolean respostaLogin = validarcampos.isCampoValido(campoEmail);
                boolean respostaSenha = validarcampos.isCampoValido(campoSenha);
                boolean result = false;

                if(respostaLogin && respostaSenha){
                    ValidarLogin validar = new ValidarLogin();
                    String txtEmail = campoEmail.getText().toString().trim();
                    String txtSenha = campoSenha.getText().toString().trim();
                    result = validar.searchEmail(getBaseContext(),txtEmail);
                }

                if(result){
                    if (switchUsuarioProfissional.isChecked()) {
                        startActivity(new Intent(MainActivity.this, HomeProfissional.class));

                    } else {
                        startActivity(new Intent(MainActivity.this, HomeUsuario.class));

                    }

                }
                else
                    Toast.makeText(getBaseContext(), "Email não cadastrado",Toast.LENGTH_SHORT).show();

            }

        });

    }
}