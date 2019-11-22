package br.ufrpe.revcare.profissional.dominio;

public class Profissional {
    private long id;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String descricao;
    private String certificado;
    private String senha;
    private String estado;
    private String cidade;
    private byte[] foto;

    public Double getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(Double avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }

    private Double avaliacaoUsuario;

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }


    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Profissional() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
