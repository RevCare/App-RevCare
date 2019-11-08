package br.ufrpe.revcare.usuario.negocio;

import java.util.Date;

import br.ufrpe.revcare.usuario.dominio.Usuario;

public class SessaoUsuario {
    public static Usuario usuarioLogado;
    public static Date horaLogin;

    public static Usuario getUsuario() {
        return usuarioLogado;
    }

    public static void reset() {
        usuarioLogado = null;
        horaLogin = null;
    }
}
