package br.ufrpe.revcare.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "revtestt.bd";
    private static final int DB_VERSION = 12;
    
    public static final String TABELA_USUARIO = "Tabela_Usuario";
    public static final String COL_ID_USUARIO = "id";
    public static final String COL_NOME_USUARIO = "nome";
    public static final String COL_NASCIMENTO_USUARIO = "nascimento";
    public static final String COL_TELEFONE_USUARIO = "telefone";
    public static final String COL_EMAIL_USUARIO = "email";
    public static final String COL_CPF_USUARIO = "cpf";
    public static final String COL_SENHA_USUARIO = "senha";
    public static final String COL_ENDERECO_USUARIO = "endereco";

    public static final String TABELA_PROFISSIONAL = "Tabela_Profissional";
    public static final String COL_ID_PROFISSIONAL = "id";
    public static final String COL_NOME_PROFISSIONAL = "nome";
    public static final String COL_NASCIMENTO_PROFISSIONAL = "nascimento";
    public static final String COL_TELEFONE_PROFISSIONAL = "telefone";
    public static final String COL_EMAIL_PROFISSIONAL = "email";
    public static final String COL_CPF_PROFISSIONAL = "cpf";
    public static final String COL_CERTIFICADO = "certificado";
    public static final String COL_SENHA_PROFISSIONAL = "senha";
    public static final String COL_DESCRICAO_PROFISSIONAL = "descricao";
    public static final String COL_ESTADO_PROFISSIONAL = "estado";
    public static final String COL_CIDADE_PROFISSIONAL = "cidade";
    public static final String COL_FOTO_PROFISSIONAL = "foto";

    public static final String TABELA_AVALIACAO = "Tabela_Avaliacao";
    public static final String COL_ID_AVALIACAO = "id_avaliacao";
    public static final String COL_FK_ID_USUARIO = "fk_id_usuario";
    public static final String COL_FK_ID_PROFISSIONAL = "fk_id_profissional";
    public static final String COL_LIKE = "like";
    public static final String COL_DESLIKE = "deslike";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE %1$s ";
    private static final String SQL_INTEGER_AUTOINCREMENT = "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static final String[] TABELAS = {
            TABELA_PROFISSIONAL, TABELA_USUARIO,
            TABELA_AVALIACAO
    };


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUsuario(db);
        createTableProfissional(db);
        createTableAvaliacao(db);
    }

    private void createTableUsuario(SQLiteDatabase db) {
        String sqlTbUsuario = SQL_CREATE_TABLE + "( " +
                SQL_INTEGER_AUTOINCREMENT +
                " %3$s TEXT NOT NULL, " +
                " %4$s TEXT NOT NULL, " +
                " %5$s TEXT NOT NULL, " +
                " %6$s TEXT NOT NULL, " +
                " %7$s TEXT NOT NULL, " +
                " %8$s TEXT NOT NULL, " +
                " %9$s TEXT NOT NULL " +
                ");";
        sqlTbUsuario = String.format(sqlTbUsuario,
                TABELA_USUARIO, COL_ID_USUARIO, COL_NOME_USUARIO,
                COL_NASCIMENTO_USUARIO, COL_TELEFONE_USUARIO,
                COL_EMAIL_USUARIO, COL_CPF_USUARIO, COL_ENDERECO_USUARIO, COL_SENHA_USUARIO);
        db.execSQL(sqlTbUsuario);
    }

    private void createTableProfissional(SQLiteDatabase db) {
        String sqlTbProfissional = SQL_CREATE_TABLE + "( " +
                SQL_INTEGER_AUTOINCREMENT +
                " %3$s TEXT NOT NULL, " +
                " %4$s TEXT NOT NULL, " +
                " %5$s TEXT NOT NULL, " +
                " %6$s TEXT NOT NULL, " +
                " %7$s TEXT NOT NULL, " +
                " %8$s TEXT NOT NULL, " +
                " %9$s TEXT NOT NULL, " +
                " %10$s TEXT NOT NULL, " +
                " %11$s TEXT NOT NULL, " +
                " %12$s TEXT NOT NULL, " +
                " %13$s BLOB " +
                ");";
        sqlTbProfissional= String.format(sqlTbProfissional,
                TABELA_PROFISSIONAL, COL_ID_PROFISSIONAL, COL_NOME_PROFISSIONAL,
                COL_NASCIMENTO_PROFISSIONAL, COL_TELEFONE_PROFISSIONAL,
                COL_EMAIL_PROFISSIONAL, COL_CPF_PROFISSIONAL,COL_DESCRICAO_PROFISSIONAL ,
                COL_CERTIFICADO,COL_SENHA_PROFISSIONAL, COL_ESTADO_PROFISSIONAL,COL_CIDADE_PROFISSIONAL,COL_FOTO_PROFISSIONAL);

        db.execSQL(sqlTbProfissional);
    }

    private void createTableAvaliacao(SQLiteDatabase db) {
        String sqlTbAvaliacao = SQL_CREATE_TABLE + "( " +
                SQL_INTEGER_AUTOINCREMENT +
                " %3$s INTEGER NOT NULL, " +
                " %4$s INTEGER NOT NULL, " +
                " %9$s INTEGER NOT NULL, " +
                " %10$s INTEGER NOT NULL, " +
                " FOREIGN KEY(%3$s) REFERENCES %5$s(%6$s), " +
                " FOREIGN KEY(%4$s) REFERENCES %7$s(%8$s) " +
                ");";
        sqlTbAvaliacao = String.format(sqlTbAvaliacao,
                TABELA_AVALIACAO, COL_ID_AVALIACAO, COL_FK_ID_USUARIO, COL_FK_ID_PROFISSIONAL,
                TABELA_USUARIO, COL_ID_USUARIO, TABELA_PROFISSIONAL, COL_ID_PROFISSIONAL, COL_LIKE, COL_DESLIKE);
        db.execSQL(sqlTbAvaliacao);
    }

    public void dropTables(SQLiteDatabase db) {
        String dropSql = "DROP TABLE IF EXISTS %1$s;";
        for (String tabela : TABELAS) {
            db.execSQL(String.format(dropSql,tabela));
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTables(db);
        onCreate(db);

    }

}