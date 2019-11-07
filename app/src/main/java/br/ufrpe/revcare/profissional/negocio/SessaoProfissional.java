package br.ufrpe.revcare.profissional.negocio;

import java.util.Date;

import br.ufrpe.revcare.profissional.dominio.Profissional;

public class SessaoProfissional {
    public static Profissional profissionalLogado;
    public static Date horaLogin;

    public static Profissional getProfissional() {
        return profissionalLogado;
    }

    public static void reset() {
        profissionalLogado = null;
        horaLogin = null;
    }
}
