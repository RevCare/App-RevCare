package br.ufrpe.revcare.usuario.persistencia;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.revcare.infra.persistencia.DBHelper;


public class UsuarioDAO {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public UsuarioDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public String cadastrarUsuario(String nome, String cpf, String endereco, String dataNascimento, String telefone, String email, String senha ){
        ContentValues values = new ContentValues();
        long resultado;

        values.put(DBHelper.COL_NOME_USUARIO, nome);
        values.put(DBHelper.COL_CPF_USUARIO, cpf);
        values.put(DBHelper.COL_NASCIMENTO_USUARIO, dataNascimento);
        values.put(DBHelper.COL_ENDERECO_USUARIO, endereco);
        values.put(DBHelper.COL_EMAIL_USUARIO, email);
        values.put(DBHelper.COL_TELEFONE_USUARIO, telefone);
        values.put(DBHelper.COL_SENHA_USUARIO, senha);

        resultado =  db.insert(DBHelper.TABELA_USUARIO, null, values);
        db.close();
        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }
}
