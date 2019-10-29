package br.ufrpe.revcare.profissional.negocio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.profissional.dominio.Profissional;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_EMAIL_PROFISSIONAL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_PROFISSIONAL;

public class ProfissionalServices {
    private DBHelper dbHelper;
    private ArrayList<Profissional> ListaProfissonal;

    public ProfissionalServices (Context context) {
        dbHelper = new DBHelper(context);
    }

    public Profissional searchProfissonalbyEmail (String email){
        String query = " SELECT * FROM " + TABELA_PROFISSIONAL + " WHERE " + COL_EMAIL_PROFISSIONAL + " LIKE ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            Profissional profissional = new Profissional();
            profissional.setEmail(cursor.getString(6));
            profissional.setSenha(cursor.getString(10));

            return profissional;
        }
        else{
            return null;
        }

    }
}
