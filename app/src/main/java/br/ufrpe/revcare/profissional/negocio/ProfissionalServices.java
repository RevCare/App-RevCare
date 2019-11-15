package br.ufrpe.revcare.profissional.negocio;

import android.content.Context;

import java.util.Date;

import br.ufrpe.revcare.usuario.negocio.SessaoUsuario;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;


public class ProfissionalServices {
    private ProfissionalDAO dao;

    public ProfissionalServices(Context context) {
        dao = new ProfissionalDAO(context);
    }

    public long cadastrar(Profissional profissional) throws Exception {
        Profissional profissionalBD = dao.consultarEmail(profissional.getEmail());
        if (profissionalBD != null) {
            throw new Exception("Email já cadastrado.");
        }
        return dao.cadastrar(profissional);
    }

    public void logout() {
        SessaoUsuario.reset();
    }

    public void logar(String email, String senha) throws Exception {
        Profissional profissional = dao.consultarEmail(email,senha);
        if (profissional == null) {
            SessaoUsuario.usuarioLogado = null;
            throw new Exception("Usuário/senha inválidos.");
        }else{
            SessaoProfissional.profissionalLogado = profissional;
            SessaoProfissional.horaLogin = new Date();
            }
    }
    public static void alteraFotoProfissional(Profissional profissional){
        ProfissionalDAO.alteraFotoProfissional(profissional);
    }
    public int porcentagemDeAprovacao(long idProfissional){
        int result = 0;
        int qttLikes = dao.contarDeslikes(idProfissional);
        int qttDeslikes = dao.contarLikes(idProfissional);
        result = (qttLikes * 100)/(qttDeslikes+qttLikes);
        return result;


    }
}
