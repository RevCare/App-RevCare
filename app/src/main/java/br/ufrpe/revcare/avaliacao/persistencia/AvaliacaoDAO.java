package br.ufrpe.revcare.avaliacao.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.revcare.avaliacao.dominio.Avaliacao;
import br.ufrpe.revcare.infra.persistencia.DBHelper;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_AVALIACAO;

public class AvaliacaoDAO {
    private DBHelper dbHelper;
    public AvaliacaoDAO(Context context) {dbHelper = new DBHelper(context);}

    public void like (Long idUsuario,Long idProfissional){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_FK_ID_PROFISSIONAL, idProfissional);
        values.put(DBHelper.COL_FK_ID_USUARIO, idUsuario);
        values.put(DBHelper.COL_LIKE, 1);
        values.put(DBHelper.COL_DESLIKE, 0);
        db.insert(TABELA_AVALIACAO, null, values);
        db.close();
    }

    public void deslike (Long idUsuario,Long idProfissional){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_FK_ID_PROFISSIONAL, idProfissional);
        values.put(DBHelper.COL_FK_ID_USUARIO, idUsuario);
        values.put(DBHelper.COL_LIKE, 0);
        values.put(DBHelper.COL_DESLIKE, 1);
        db.insert(TABELA_AVALIACAO, null, values);
        db.close();
    }
//    public void verSeJaVotou(long idUsuario, long idProfissonal){
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String query = ""
//
//    }

}
