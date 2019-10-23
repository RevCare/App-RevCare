package br.ufrpe.revcare.usuario.negocio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import br.ufrpe.revcare.infra.persistencia.DBHelper;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_EMAIL_USUARIO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_SENHA_USUARIO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_USUARIO;

public class ValidarLogin {
    public static void search(Context context, String email, String senha){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String selecionaEmail = "SELECT * FROM " + TABELA_USUARIO + " WHERE " + COL_EMAIL_USUARIO +
            " LIKE '%" + email + "%'";
        String selescionaSenha = "SELECT * FROM " + TABELA_USUARIO + " WHERE " + COL_SENHA_USUARIO +
                " LIKE '%" + senha + "%'";

        Cursor cursor = db.rawQuery(selecionaEmail, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0){

            }
            else{
                String msg = "Email ou senha inválidos";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        }
    }

}
