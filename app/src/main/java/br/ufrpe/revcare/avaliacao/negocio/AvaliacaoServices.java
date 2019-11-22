package br.ufrpe.revcare.avaliacao.negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import br.ufrpe.revcare.avaliacao.dominio.Avaliacao;
import br.ufrpe.revcare.avaliacao.persistencia.AvaliacaoDAO;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.negocio.SessaoUsuario;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;
/**
 * Manipulações do slopeOne adaptadas do traineeApp
 *
 */
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
        if(!dao.votado(idUsuario,idProfissional)){
            dao.deslike(idUsuario,idProfissional);
            result = true;
        }
        return result;
    }
    private Profissional profissionalByID(String nomeProfissonal, Context context) {
        ProfissionalDAO dao = new ProfissionalDAO(context);
        return dao.getProfissionalById(Integer.parseInt(nomeProfissonal));
    }
    public ArrayList<Profissional> getRecomendacao(Context context) {
        Usuario usarioLogado = SessaoUsuario.getUsuario();
        Map<Usuario, Map<String, Double>> dados = getAvaliacoesUsuario(context);
        HashMap<String, Double> avaliacoesUsuario = avaliacaoPorUsuario(usarioLogado, context);
        SlopeOne slopeOne = new SlopeOne(dados);
        Map<String, Double> predicoes = slopeOne.predict(avaliacoesUsuario);
        return getProfissionaisRecomendadas(predicoes, context);
    }

    private ArrayList<Profissional> getProfissionaisRecomendadas(Map<String, Double> predicoes, Context context) {

        ArrayList<Profissional> recomendados = new ArrayList<>();
        for (Map.Entry<String,Double> entry : predicoes.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            Profissional profissionalAtual = profissionalByID(key, context);
            profissionalAtual.setAvaliacaoUsuario(value);
            Double nota = avaliacaoProfissionalUsuario(profissionalAtual, context);
            if (nota == null && (profissionalAtual.getAvaliacaoUsuario() >= 1.0)) {
                recomendados.add(profissionalAtual);
            }
        }
        Collections.sort(recomendados, new Comparator<Profissional>() {
            @Override
            public int compare(Profissional v1, Profissional v2) {
                return v2.getAvaliacaoUsuario().intValue() - v1.getAvaliacaoUsuario().intValue();
            }
        });
        return recomendados;
    }
    public Double avaliacaoProfissionalUsuario(Profissional profissional, Context context){
        Usuario usuario = SessaoUsuario.getUsuario();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(context);
        return profissionalDAO.getNotaProfissional(usuario.getId(), profissional.getId());
    }
    private  Map<Usuario, Map<String, Double>> getAvaliacoesUsuario(Context context) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(context);
        Usuario usuarioLogado = SessaoUsuario.getUsuario();
        Map<Usuario, Map<String, Double>> dados = new HashMap<>();
        ArrayList<Usuario> usuarios = usuarioDAO.carregarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getId() != usuarioLogado.getId()) {
                dados.put(usuario, avaliacaoPorUsuario(usuario, context));
            }
        }
        return dados;
    }
    private HashMap<String,Double> avaliacaoPorUsuario(Usuario usuario, Context context){
        AvaliacaoDAO dao = new AvaliacaoDAO(context);
        return  dao.getAvaliacaoProfissional(usuario);
    }
}
