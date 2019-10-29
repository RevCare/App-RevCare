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
<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes

                } else {
                    startActivity(new Intent(MainActivity.this, CadastroUsuario1Activity.class));

                }
            }

        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
