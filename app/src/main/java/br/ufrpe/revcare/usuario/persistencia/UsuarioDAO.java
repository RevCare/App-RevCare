package br.ufrpe.revcare.usuario.persistencia;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.usuario.dominio.Usuario;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_EMAIL_USUARIO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_SENHA_USUARIO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_USUARIO;


public class UsuarioDAO  {

    private DBHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long cadastrar(Usuario usuario){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOME_USUARIO, usuario.getNome());
        values.put(DBHelper.COL_CPF_USUARIO, usuario.getCpf());
        values.put(DBHelper.COL_NASCIMENTO_USUARIO, usuario.getDataNascimento());
        values.put(DBHelper.COL_ENDERECO_USUARIO, usuario.getEndereco());
        values.put(DBHelper.COL_EMAIL_USUARIO, usuario.getEmail());
        values.put(DBHelper.COL_TELEFONE_USUARIO, usuario.getTelefone());
        values.put(DBHelper.COL_SENHA_USUARIO, usuario.getSenha());

        long id = db.insert(TABELA_USUARIO, null, values);
        db.close();
        return id;

    }

    public Usuario consultar(String email,String senha){
        Usuario result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_USUARIO +
                        " WHERE " + COL_EMAIL_USUARIO + " = ? " +
                        " AND " + COL_SENHA_USUARIO + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email,senha});
        if (cursor.moveToFirst()){
            result = criarUsuario(cursor);
        }
        return result;
    }


    public Usuario consultar(String email) {
        Usuario result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_USUARIO +
                        " WHERE " + COL_EMAIL_USUARIO + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.moveToFirst()){
            result = criarUsuario(cursor);
        }
        return result;
    }

    private Usuario criarUsuario(Cursor cursor) {
        Usuario result = new Usuario();
        result.setEmail(cursor.getString(5));
        result.setSenha(cursor.getString(9));

        return result;
    }
}
