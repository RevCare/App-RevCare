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
    private EditText nTelefone;
    private EditText nCertificado;
    private EditText nEmail;
    private EditText nSenha;
    private EditText nConfirmaSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional1);
        getSupportActionBar().hide();
        Button botao_finalizar_cadastro = findViewById(R.id.botaoFinalizarCadastro);
        botao_finalizar_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nNome = findViewById(R.id.nomeTextField);
                nDataNascimento = findViewById(R.id.dataNascimentoTextField);
                nCpf = findViewById(R.id.cpfTextField);
                nTelefone = findViewById(R.id.telefoneTextField);
                nEndereco = findViewById(R.id.enderecoTextField);
                nEmail = findViewById(R.id.emailTextField);
                nSenha = findViewById(R.id.senhaTextField);
                nConfirmaSenha = findViewById(R.id.caixaConfirmaSenha);
                nCertificado = findViewById(R.id.certificadoTextField);

                //nCpf.setInputType(InputType.TYPE_CLASS_NUMBER);

                boolean valido = validacao(nNome, nCpf, nDataNascimento, nTelefone, nEndereco, nEmail, nSenha, nConfirmaSenha);
                if (valido) {
                    Profissional profissional = new Profissional();
                    preencheObjeto(profissional);

                    startActivity(new Intent(CadastroProfissional1Activity.this, HomeProfissional.class));
                }
                startActivity(new Intent(CadastroProfissional1Activity.this, HomeProfissional.class));

            }


            public boolean validacao(EditText nNome, EditText nDataNascimento, EditText nCpf, EditText nTelefone, EditText nEndereco, EditText nEmail, EditText nSenha, EditText nConfirmaSenha) {


                boolean teveCampoVazio = false;
                String txtNome = nNome.getText().toString().trim();
                String txtDataNascimento = nDataNascimento.getText().toString().trim();
                String txtCpf = nCpf.getText().toString().trim();
                String txtTelefone = nTelefone.getText().toString().trim();
                String txtEndereco = nEndereco.getText().toString().trim();
                String txtEmail = nEmail.getText().toString().trim();
                String txtSenha = nSenha.getText().toString().trim();
                String txtConfirmaSenha = nConfirmaSenha.getText().toString().trim();

                if (txtNome.equals("")) {
                    nNome.requestFocus();
                    nNome.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }
                if (txtDataNascimento.equals("")) {
                    nDataNascimento.requestFocus();
                    nDataNascimento.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }
                if (txtCpf.equals("")) {
                    nCpf.requestFocus();
                    nCpf.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }
                if (txtTelefone.equals("")) {
                    nTelefone.requestFocus();
                    nTelefone.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }
                if (txtEndereco.equals("")) {
                    nEndereco.requestFocus();
                    nEndereco.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }
                if (txtEmail.equals("")) {
                    nEmail.requestFocus();
                    nEmail.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }
                if (txtSenha.equals("")) {
                    nSenha.requestFocus();
                    nSenha.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }
                if (txtConfirmaSenha.equals("")) {
                    nConfirmaSenha.requestFocus();
                    nConfirmaSenha.setError("Preencha o Campo.");
                    teveCampoVazio = true;

                }

                if (!teveCampoVazio) {

                    if (!(txtSenha.equals(txtConfirmaSenha))) {
                        nSenha.requestFocus();
                        nSenha.setError("As senhas devem ser iguais.");
                        nConfirmaSenha.setError("As senhas devem ser iguais.");

                    } else {
                        return true;
                    }
                }


                return false;
            }


            public void preencheObjeto(Profissional profissional) {

                profissional.setNome(nNome.getText().toString().trim());
                profissional.setDataNascimento(nDataNascimento.getText().toString().trim());
                profissional.setTelefone(nTelefone.getText().toString().trim());
                profissional.setEndereco(nEndereco.getText().toString().trim());
                profissional.setEmail(nEmail.getText().toString().trim());
                profissional.setCpf(nCpf.getText().toString().trim());
                profissional.setSenha(nSenha.getText().toString().trim());

            }
        });


    }


}
