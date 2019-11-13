package br.ufrpe.revcare.avaliacao.gui;

import android.content.Context;

import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;

public class GuardaProfissional {
    private static Profissional profissionalGuardado = null;

    public static Profissional getProfissionalGuardado(){ return profissionalGuardado; }

    public void setProfissionalGuardado(String email, Context context){
        ProfissionalDAO dao = new ProfissionalDAO(context);
        profissionalGuardado = dao.consultarEmail(email);
    }
}
