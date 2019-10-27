package br.ufrpe.revcare.usuario.negocio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.revcare.infra.gui.MainActivity;
import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.usuario.dominio.Usuario;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_EMAIL_USUARIO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_USUARIO;

public class UsuarioServices {
    private DBHelper dbHelper;

    public UsuarioServices (Context context) {
        dbHelper = new DBHelper(context);
    }

    public Usuario searchUsuariobyEmail (String email){
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
