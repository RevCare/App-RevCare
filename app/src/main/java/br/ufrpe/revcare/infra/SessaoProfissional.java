package br.ufrpe.revcare.infra;

import java.util.Date;

import br.ufrpe.revcare.profissional.dominio.Profissional;

public class SessaoProfissional {
    public static Profissional profissionalLogado;
    public static Date horaLogin;

    public static Profissional getUsuario() {
        return profissionalLogado;
    }

    public static void reset() {
        profissionalLogado = null;
        horaLogin = null;
    }
}
