package br.ufrpe.revcare.infra.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufrpe.revcare.avaliacao.persistencia.AvaliacaoDAO;
import br.ufrpe.revcare.profissional.dominio.Profissional;
import br.ufrpe.revcare.profissional.persistencia.ProfissionalDAO;
import br.ufrpe.revcare.usuario.dominio.Usuario;
import br.ufrpe.revcare.usuario.persistencia.UsuarioDAO;

public class PopularBanco {
    private UsuarioDAO usuarioDAO;
    private ProfissionalDAO profissionalDAO;
    private AvaliacaoDAO avaliacaoDAO;
    private Context nContext;

    public PopularBanco(Context context){
        usuarioDAO = new UsuarioDAO(context);
        profissionalDAO = new ProfissionalDAO(context);
        avaliacaoDAO = new AvaliacaoDAO(context);
        nContext = context;

        if (!bancoIsPopulado()) {
            popularUsuario();
        }

        if (!bancoIsPopuladoProfissional()) {
            popularProfissional();
        }

        if (!bancoIsPopuladoAvaliacao()){
            popularAvaliacao();
        }

    }

    private boolean bancoIsPopulado() {
        String query = "SELECT * FROM TABELA_USUARIO";
        Cursor cursor = this.execQuery(query);
        boolean populado = false;
        if (cursor.moveToFirst()) {
            populado = true;
        }
        return populado;
    }
    private boolean bancoIsPopuladoAvaliacao() {
        String query = "SELECT * FROM TABELA_AVALIACAO";
        Cursor cursor = this.execQuery(query);
        boolean populado = false;
        if (cursor.moveToFirst()) {
            populado = true;
        }
        return populado;
    }
    private boolean bancoIsPopuladoProfissional(){
        String query = "SELECT * FROM TABELA_PROFISSIONAL";
        Cursor cursor = this.execQuery(query);
        boolean populado = false;
        if (cursor.moveToFirst()) {
            populado = true;
        }
        return populado;
    }

    private Cursor execQuery(String query) {
        DBHelper dbHelper;
        dbHelper = new DBHelper(nContext);
        SQLiteDatabase leitorBanco = dbHelper.getReadableDatabase();
        return leitorBanco.rawQuery(query, null);
    }

    private void popularUsuario(){
        criarUsuario("Usuario 1", "09807339448", "17/09/1995/","Rua 1",
                "user1@gmail.com","81818181","1");
        criarUsuario("Usuario 2", "44311063083", "10/09/1995/","Rua 2",
                "user2@gmail.com","81818182","1");
        criarUsuario("Usuario 3", "79558097004", "11/09/1995/","Rua 3",
                "user3@gmail.com","81818183","1");
        criarUsuario("Usuario 4", "00930465024", "12/09/1995/","Rua 4",
                "user4@gmail.com","81818184","1");
        criarUsuario("Usuario 5", "43758223091", "13/09/1995/","Rua 5",
                "user5@gmail.com","81818185","1");
        criarUsuario("Usuario 6", "40469093072", "14/09/1995/","Rua 6",
                "user6@gmail.com","81818186","1");
        criarUsuario("Usuario 7", "72030524000", "15/09/1995/","Rua 7",
                "user7@gmail.com","81818187","1");

    }
    private void popularProfissional(){
        criarProfissional("profissional 1","16530439030","01/01/1990",
                "Eu amo batata","prof1@gmail.com","91919191",  "","1","Pernambuco","Abreu e Lima");
        criarProfissional("profissional 2","12892393043","02/02/1990",
                "Eu gosto muito de batata","prof2@gmail.com","91919192",
                "","1","Pernambuco","Recife");
        criarProfissional("profissional 3","12594541001","03/03/1990",
                "Eu gosto demais de batata","prof3@gmail.com","91919193",
                "","1","Pernambuco","Recife");
        criarProfissional("profissional 4","76205781026","04/04/1990",
                "Eu gosto mesmo de batata","prof4@gmail.com","91919194",
                "","1","Pernambuco","Recife");
        criarProfissional("profissional 5","11744475083","05/05/1990",
                "Eu gosto pra caramba de batata","prof5@gmail.com","91919195",
                "","1","Pernambuco","Recife");
        criarProfissional("profissional 6","95732263080","06/06/1990",
                "Eu gosto horrores de batata","prof6@gmail.com","91919196",
                "","1","Pernambuco","Recife");
        criarProfissional("profissional 7","23136712080","07/07/1990",
                "Eu gosto bastante de batata","prof7@gmail.com","91919197",
                "","1","Pernambuco","Recife");
    }
    private void popularAvaliacao(){
        criarAvaliacao(1,3,1);
        criarAvaliacao(1,4,0);
        criarAvaliacao(1,5,0);
        criarAvaliacao(1,1,0);
        criarAvaliacao(1,7,1);

        criarAvaliacao(2,2,1);
        criarAvaliacao(2,5,0);
        criarAvaliacao(2,6,1);
        criarAvaliacao(2,4,0);
        criarAvaliacao(2,7,1);

        criarAvaliacao(4,2,1);
        criarAvaliacao(4,7,1);
        criarAvaliacao(4,1,0);
        criarAvaliacao(4,3,1);
        criarAvaliacao(4,5,1);

        criarAvaliacao(6,4,1);
        criarAvaliacao(6,5,0);
        criarAvaliacao(6,6,0);
        criarAvaliacao(6,7,0);
        criarAvaliacao(6,1,0);

        criarAvaliacao(7,3,1);
        criarAvaliacao(7,4,0);
        criarAvaliacao(7,5,0);
        criarAvaliacao(7,1,1);
        criarAvaliacao(7,7,1);

    }







    private void criarUsuario(String nome, String cpf, String nascimento, String endereco, String email,
                              String telefone, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setDataNascimento(nascimento);
        usuario.setEndereco(endereco);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSenha(senha);
        usuarioDAO.cadastrar(usuario);
    }
    private void criarProfissional(String nome, String cpf, String nascimento, String descricao, String email,
                              String telefone, String certificado, String senha, String estado, String cidade) {
        Profissional profissional = new Profissional();
        profissional.setNome(nome);
        profissional.setCpf(cpf);
        profissional.setDataNascimento(nascimento);
        profissional.setDescricao(descricao);
        profissional.setEmail(email);
        profissional.setTelefone(telefone);
        profissional.setCertificado(certificado);
        profissional.setSenha(senha);
        profissional.setEstado(estado);
        profissional.setCidade(cidade);
        profissionalDAO.cadastrar(profissional);
    }

    private void criarAvaliacao (long idUsuario, long idProfissional, int like){
        if (like == 1) {
            avaliacaoDAO.like(idUsuario,idProfissional);
        }else{
            avaliacaoDAO.deslike(idUsuario,idProfissional);
        }
    }
}
