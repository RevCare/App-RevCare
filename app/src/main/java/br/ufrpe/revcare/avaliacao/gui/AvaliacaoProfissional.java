package br.ufrpe.revcare.avaliacao.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import br.ufrpe.revcare.R;
import br.ufrpe.revcare.infra.persistencia.DBHelper;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_AVALIACAO;

public class AvaliacaoProfissional extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_profissional);
        getSupportActionBar().hide();

    }
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

}
