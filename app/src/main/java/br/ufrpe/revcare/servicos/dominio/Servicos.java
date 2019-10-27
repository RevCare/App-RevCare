package br.ufrpe.revcare.servicos.dominio;

import java.sql.Time;
import java.util.Date;

import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.usuario.dominio.Usuario;

public class Servicos {
    private String nome;
    private long id;
    private Usuario solicitante;
    private Profissional profissional;
    private Time horarioInicial;
    private Time horarioFinal;
    private Date data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Time getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(Time horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public Time getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(Time horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
