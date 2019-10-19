package br.ufrpe.revcare.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import br.ufrpe.revcare.R;

public class MainActivity extends AppCompatActivity {
    private EditText lCpf;
    private EditText lSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Button botao_entrar = findViewById(R.id.botaoEntrar);
        Button botao_cadastrar = findViewById(R.id.botaoCadastro);
        EditText campoLogin = findViewById(R.id.caixatxtEmailLogin);
        EditText campoSenha = findViewById(R.id.caixatxtSenhaLogin);
        final Switch switchUsuarioProfissional = findViewById(R.id.switchUsuarioProfissional);


        botao_cadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (switchUsuarioProfissional.isChecked()) {
                    startActivity(new Intent(MainActivity.this, CadastroProfissional1Activity.class));

                } else {
                    startActivity(new Intent(MainActivity.this, CadastroUsuario1Activity.class));

                }
            }

        });

        botao_entrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (switchUsuarioProfissional.isChecked()) {
                    startActivity(new Intent(MainActivity.this, HomeProfissional.class));

                } else {
                    startActivity(new Intent(MainActivity.this, HomeUsuario.class));

                }

            }

        });

    }
}