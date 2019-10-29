package br.ufrpe.revcare.usuario.negocio;

import android.content.Context;

import java.util.Date;

import br.ufrpe.revcare.infra.Sessao;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;


public class UsuarioServices {
    private UsuarioDAO dao;

    public UsuarioServices(Context context) {
        dao = new UsuarioDAO(context);
    }

    public long cadastrar(Usuario usuario) throws Exception {
        Usuario usuarioBD = dao.consultar(usuario.getEmail());
        if (usuarioBD != null) {
            throw new Exception("Email já cadastrado.");
        }
        return dao.cadastrar(usuario);
    }

    public void logout() {
        Sessao.reset();
    }

    public void logar(String email, String senha) throws Exception {
        Usuario usuario = dao.consultar(email,senha);
        if (usuario == null) {
            Sessao.usuarioLogado = null;
            throw new Exception("Usuário/senha inválidos.");
        }
        Sessao.usuarioLogado = usuario;
        Sessao.horaLogin = new Date();
    }
}