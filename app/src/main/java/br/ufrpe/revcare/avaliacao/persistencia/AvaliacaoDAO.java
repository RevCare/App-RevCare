package br.ufrpe.revcare.avaliacao.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.revcare.avaliacao.dominio.Avaliacao;
import br.ufrpe.revcare.infra.persistencia.DBHelper;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_FK_ID_PROFISSIONAL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_FK_ID_USUARIO;
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

    public boolean votado(long idUsuario, long idProfissonal){
        boolean result = false;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query =
                " SELECT * FROM " + TABELA_AVALIACAO +
                        " WHERE " + COL_FK_ID_USUARIO + " = ? " +
                        " AND " + COL_FK_ID_PROFISSIONAL + " = ? ";


        String stringIdUsuario = String.valueOf(idUsuario);
        String stringIdProfissional = String.valueOf(idProfissonal);
        Cursor cursor = db.rawQuery(query,new String[] {stringIdUsuario ,stringIdProfissional});
        if (cursor.moveToFirst()){
            result = true;
        }
        db.close();
        cursor.close();
        return result;

    }

    public Avaliacao criarAvaliacao(Cursor cursor){
        Avaliacao result = new Avaliacao();

        result.setId(cursor.getInt(0));
        result.setIdUsuario(cursor.getInt(1));
        result.setIdProfissional(cursor.getInt(2));
        result.setLike(cursor.getInt(3));
        result.setDeslike(cursor.getInt(4));

        return result;
    }
}
