package br.ufrpe.revcare.usuario.persistencia;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import br.ufrpe.revcare.infra.gui.CadastroUsuario1Activity;
import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.usuario.dominio.Usuario;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_EMAIL_USUARIO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_USUARIO;


public class UsuarioDAO  {

    private SQLiteDatabase db;

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
        values.put(COL_EMAIL_USUARIO, usuario.getEmail());
        values.put(DBHelper.COL_TELEFONE_USUARIO, usuario.getTelefone());
        values.put(DBHelper.COL_SENHA_USUARIO, usuario.getSenha());

        long id = db.insert(TABELA_USUARIO, null, values);
        db.close();
        return id;

    }

//    public Usuario getUsuarioById(long id) {
//        Usuario result = null;
//        db = this.dbHelper.getReadableDatabase();
//        String sql = "SELECT * FROM " + DBHelper.TABELA_USUARIO + " WHERE " + this.dbHelper.getReadableDatabase() + " LIKE ?;";
//        Cursor cursor = db.rawQuery(sql, new String[]{Long.toString(id)});
//        if (cursor.moveToFirst()) {
//            result = createUsuario(cursor);
//        }
//        return result;
//    }

        public Usuario searchUsuario (String email){
            String query = " SELECT * FROM " + TABELA_USUARIO + " WHERE " + COL_EMAIL_USUARIO + " LIKE ?;";
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(query,null);
            if (cursor.moveToFirst()){
                Usuario usuario = new Usuario();
                usuario.setEmail(cursor.getString(5));
                usuario.setSenha(cursor.getString(9));
                return usuario;
            }
        return null;
    }

    }
