package br.ufrpe.revcare.profissional.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.infra.gui.Validacao;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.SessaoProfissional;
import br.ufrpe.revcare.usuario.gui.CadastroUsuario;
import br.ufrpe.revcare.usuario.gui.HomeUsuario;

public class AtualizarCadastroProfissional extends AppCompatActivity {
    private TextView nome = findViewById(R.id.nomeAtualizarProfissional);
    private TextView cpf = findViewById(R.id.cpfAtualizarProfissional);
    private TextView telefone = findViewById(R.id.telefoneAtualizarProfissional);
    private TextView descricao = findViewById(R.id.descricaoAtualizarProfissional);
    private TextView email = findViewById(R.id.emailAtualizarProfissional);
    private TextView nascimento = findViewById(R.id.dataNascimentoAtualizarProfissional);
    private TextView senha = findViewById(R.id.senhaAtualizarProfissional);
    private TextView confirmaSenha = findViewById(R.id.confirmaSenhaAtualizarProfissional);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_atualizar_cadastro_profissional);
        trocaDicasPelosDados(SessaoProfissional.getProfissional());
        Button buttonAtulaizarPerfil = findViewById(R.id.botaoAtualizarPerfilProfissional);
        buttonAtulaizarPerfil.setOnClickListener(new View.OnClickListener() {
        Validacao validacao = new Validacao();
            @Override
            public void onClick(View v) {
                boolean senhasValidas = validacao.confirmarSenha(getApplicationContext(),senha.getText().toString(),confirmaSenha.getText().toString());
                if (senhasValidas){
                    //add no banco os novos dados
                    Toast.makeText(getApplicationContext(), "Perfil atualizado com sucesso. Por favor realize o login novamente", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AtualizarCadastroProfissional.this, MainActivity.class));

                }
            }

        });
    }


    public void trocaDicasPelosDados(Profissional profissional){
        nome.setHint(profissional.getNome());
        cpf.setHint(profissional.getCpf());
        telefone.setHint(profissional.getTelefone());
        descricao.setHint(profissional.getDescricao());
        email.setHint(profissional.getEmail());
        nascimento.setHint(profissional.getDataNascimento());

    }
}
