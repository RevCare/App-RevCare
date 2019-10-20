package br.ufrpe.revcare.infra.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.usuario.dominio.Usuario;

public class CadastroProfissional1Activity extends AppCompatActivity {
    private EditText nNome;
    private EditText nDataNascimento;
    private EditText nEndereco;
    private EditText nCpf;
    private EditText nEmail;
    private EditText nTelefone;
    private EditText nSenha;
    private EditText nConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_profissional1);
        Button botao_continuar_cadastro = findViewById(R.id.botaoFinalizarCadastro);
        botao_continuar_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nNome = findViewById(R.id.nomeTextField);
                nDataNascimento = findViewById(R.id.dataNascimentoTextField);
                nCpf = findViewById(R.id.cpfTextField);
                nEndereco = findViewById(R.id.enderecoTextField);
                nTelefone = findViewById(R.id.telefoneTextField);
                nEmail = findViewById(R.id.emailTextField);
                nSenha = findViewById(R.id.caixaTxtSenhaLogin);
                nConfirmarSenha = findViewById(R.id.caixaConfirmaSenha);

                Validacao valido = new Validacao();

                if (valido.validacao(nNome, nDataNascimento, nCpf, nEndereco,nTelefone,nEmail,nSenha,nConfirmarSenha)) {
                    startActivity(new Intent(CadastroProfissional1Activity.this, HomeProfissional.class));
                }


            }

        });

    }
}
