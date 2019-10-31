package br.ufrpe.revcare.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "revcare.bd";
    private static final int DB_VERSION = 9;


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
    public static final String COL_ENDERECO_PROFISSIONAL = "endereco";

    public static final String TABELA_SERVICO = "Tabela Servico";
    public static final String COL_NOME_SERVICO = "nome";
    public static final String COL_ID_SERVICO = "id";
    public static final String COL_SOLICITANTE = "id_solicitante";
    public static final String COL_PROFISSIONAL = "id_profissional";
    public static final String COL_HORARIO_INICIAL = "horario_inicial";
    public static final String COL_HORARIO_FINAL = "horario_final";
    public static final String COL_DATA = "data";
    //public static final String COL_PRECO = "preco";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE %1$s ";
    private static final String SQL_INTEGER_AUTOINCREMENT = "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static final String[] TABELAS = {
            TABELA_PROFISSIONAL, TABELA_USUARIO,
            TABELA_SERVICO
    };


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUsuario(db);
        createTableProfissional(db);
        createTableServicos(db);
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
                " %9$s TEXT, " +
                " %10$s TEXT NOT NULL " +
                ");";
        sqlTbProfissional= String.format(sqlTbProfissional,
                TABELA_PROFISSIONAL, COL_ID_PROFISSIONAL, COL_NOME_PROFISSIONAL,
                COL_NASCIMENTO_PROFISSIONAL, COL_TELEFONE_PROFISSIONAL,
                COL_EMAIL_PROFISSIONAL, COL_CPF_PROFISSIONAL,COL_ENDERECO_PROFISSIONAL ,
                COL_CERTIFICADO,COL_SENHA_PROFISSIONAL);
        db.execSQL(sqlTbProfissional);
    }

    private void createTableServicos(SQLiteDatabase db){
        String sqlTbServicos = SQL_CREATE_TABLE + "( " +
                SQL_INTEGER_AUTOINCREMENT +
                " %3$s TEXT NOT NULL, " +
                " %4$s TEXT NOT NULL, " +
                " %5$s TEXT NOT NULL, " +
                " %6$s TEXT NOT NULL, " +
                " %7$s TEXT NOT NULL, " +
                " %8$s TEXT NOT NULL " +
                ");";
        sqlTbServicos = String.format(sqlTbServicos,
                TABELA_SERVICO, COL_ID_SERVICO, COL_NOME_SERVICO, COL_SOLICITANTE,
                COL_PROFISSIONAL, COL_HORARIO_INICIAL,
                COL_HORARIO_FINAL, COL_DATA);
        db.execSQL(sqlTbServicos);

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
