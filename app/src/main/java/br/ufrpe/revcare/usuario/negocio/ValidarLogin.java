package br.ufrpe.revcare.usuario.negocio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.ufrpe.revcare.infra.persistencia.DBHelper;

public class ValidarLogin {
    public  boolean search(Context context, String email, String senha){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String seleciona = "SELECT COL_EMAIL_USUARIO, COL_SENHA_USUARIO FROM TABELA_USUARIO WHERE COL_EMAIL_USUARIO = email AND COL_SENHA_USUARIO = senha";

        //Cursor cursor = db.rawQuery(seleciona, null);
        //cursor = db.

//        if (cursor != null) {
//            cursor.moveToFirst();
//            if (cursor.getCount() > 0){
        db.close();
        return (!seleciona.equals(email)) && (seleciona.equals(senha));
        

    }

}
