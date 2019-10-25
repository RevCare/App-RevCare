package br.ufrpe.revcare.usuario.negocio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufrpe.revcare.infra.persistencia.DBHelper;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_USUARIO;

public class ValidarLogin {
    public boolean searchEmail(Context context, String email) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String seleciona = "SELECT * FROM TABELA_USUARIO WHERE email  = ?";

        Cursor cursor = db.rawQuery(seleciona, null);


        if (cursor != null) {
            cursor.moveToFirst();
            while(cursor != null){
                if cursor ;

            }

            //cursor.close();
            db.close();
            return (seleciona.equals(email));


        }

    }
}
