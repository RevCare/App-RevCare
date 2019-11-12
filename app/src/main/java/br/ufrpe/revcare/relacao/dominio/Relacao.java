package br.ufrpe.revcare.relacao.dominio;

public class Relacao {
    private long id;
    private long idUsuarioRelacao;
    private long idProfissionalRelacao;
    private int like;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuarioRelacao() {
        return idUsuarioRelacao;
    }

    public void setIdUsuarioRelacao(long idUsuarioRelacao) {
        this.idUsuarioRelacao = idUsuarioRelacao;
    }

    public long getIdProfissionalRelacao() {
        return idProfissionalRelacao;
    }

    public void setIdProfissionalRelacao(long idProfissionalRelacao) {
        this.idProfissionalRelacao = idProfissionalRelacao;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
