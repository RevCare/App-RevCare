package br.ufrpe.revcare.usuario.persistencia;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.usuario.dominio.Usuario;


public class UsuarioDAO {

    private DBHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long cadastrarUsuario(Usuario usuario){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOME_USUARIO, usuario.getNome());
        values.put(DBHelper.COL_CPF_USUARIO, usuario.getCpf());
        values.put(DBHelper.COL_NASCIMENTO_USUARIO, usuario.getDataNascimento());
        values.put(DBHelper.COL_ENDERECO_USUARIO, usuario.getEndereco());
        values.put(DBHelper.COL_EMAIL_USUARIO, usuario.getEmail());
        values.put(DBHelper.COL_TELEFONE_USUARIO, usuario.getTelefone());
        values.put(DBHelper.COL_SENHA_USUARIO, usuario.getSenha());

        long id = db.insert(DBHelper.TABELA_USUARIO, null, values);
        db.close();
        return id;

    }
}
