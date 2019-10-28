package br.ufrpe.revcare.servico.dominio;

import java.sql.Time;
import java.util.Date;

import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.usuario.dominio.Usuario;

public class Servico {
    private String nome;
    private long id;
    private Usuario solicitante;
    private Profissional profissional;
    private String horarioInicial;
    private String horarioFinal;
    private String data;

    public long getId() {

        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {

        this.solicitante = solicitante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {

        this.horarioFinal = horarioFinal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
