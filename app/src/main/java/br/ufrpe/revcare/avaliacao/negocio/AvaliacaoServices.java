package br.ufrpe.revcare.avaliacao.negocio;

import android.content.Context;

import br.ufrpe.revcare.avaliacao.persistencia.AvaliacaoDAO;

public class AvaliacaoServices {
    private AvaliacaoDAO dao;

    public AvaliacaoServices(Context context) {
        dao = new AvaliacaoDAO(context);
    }

    public boolean like(Long idUsuario, Long idProfissional){
        boolean result = false;
        if(!dao.votado(idUsuario,idProfissional)){
            dao.like(idUsuario,idProfissional);
            result = true;
        }
        return result;
    }
    public boolean deslike(Long idUsuario, Long idProfissional){
        boolean result = false;
        if(!dao.votado(idUsuario, idProfissional)){
            dao.deslike(idUsuario, idProfissional);
            result = true;
        }
        return result;
    }
}
