package br.ufrpe.revcare.usuario.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.negocio.SessaoProfissional;

public class AtualizarCadastroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_atualizar_cadastro_usuario);
        trocaDicasPelosDados(SessaoProfissional.getProfissional());
    }

    public void trocaDicasPelosDados(Profissional profissional){
        TextView nome = findViewById(R.id.nomeAtualizarProfissional);
        TextView cpf = findViewById(R.id.cpfAtualizarProfissional);
        TextView telefone = findViewById(R.id.telefoneAtualizarProfissional);
        TextView descricao = findViewById(R.id.descricaoAtualizarProfissional);
        TextView email = findViewById(R.id.emailAtualizarProfissional);
        TextView nascimento = findViewById(R.id.dataNascimentoAtualizarProfissional);

        nome.setHint(profissional.getNome());
        cpf.setHint(profissional.getCpf());
        telefone.setHint(profissional.getTelefone());
        descricao.setHint(profissional.getDescricao());
        email.setHint(profissional.getEmail());
        nascimento.setHint(profissional.getDataNascimento());

    }
}
