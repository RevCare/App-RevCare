package br.ufrpe.revcare.servico.negocio;

import android.content.Context;

import br.ufrpe.revcare.servico.dominio.Servico;
import br.ufrpe.revcare.servico.persistencia.ServicoDAO;

public class ServicoService {
    private ServicoDAO dao;

    public ServicoService(Context context){
        dao = new ServicoDAO(context);
    }
    public void cadastrar(Servico servico){
        dao.cadastrarServico(servico);
    }
}
