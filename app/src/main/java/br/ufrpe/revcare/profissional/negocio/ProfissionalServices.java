package br.ufrpe.revcare.profissional.negocio;

import android.content.Context;
import java.util.Date;
import br.ufrpe.revcare.infra.Sessao;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;


public class ProfissionalServices {
    private ProfissionalDAO dao;

    public ProfissionalServices(Context context) {
        dao = new ProfissionalDAO(context);
    }

    public long cadastrar(Profissional profissional) throws Exception {
        Profissional profissionalBD = dao.consultar(profissional.getEmail());
        if (profissionalBD != null) {
            throw new Exception("Email já cadastrado.");
        }
        return dao.cadastrar(profissional);
    }

    public void logout() {
        Sessao.reset();
    }

    public void logar(String email, String senha) throws Exception {
        Profissional profissional = dao.consultar(email,senha);
        if (profissional == null) {
            Sessao.usuarioLogado = null;
            throw new Exception("Usuário/senha inválidos.");
        }
    }
    }
