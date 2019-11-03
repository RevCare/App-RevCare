package br.ufrpe.revcare.servico.persistencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.infra.persistencia.GenericDAO;
import br.ufrpe.revcare.servico.dominio.Servico;

import static br.ufrpe.revcare.infra.Sessao.getNomeUsuario;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_SERVICO;

public class ServicoDAO {

    private SQLiteDatabase db;

    private DBHelper dbHelper;

    public ServicoDAO(Context context) {
        dbHelper = new DBHelper(context);
   }

    public long cadastrarServico(Servico servico) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.COL_NOME_SERVICO, servico.getNome());
        values.put(DBHelper.COL_HORARIO_INICIAL, servico.getHorarioInicial());
        values.put(DBHelper.COL_HORARIO_FINAL, servico.getHorarioFinal());
        values.put(DBHelper.COL_DATA, servico.getData());
        //values.put(DBHelper.COL_SOLICITANTE, servico.getSolicitante().getId());
        //values.put(DBHelper.COL_PROFISSIONAL, servico.getProfissional().getId());
        long id = db.insert(TABELA_SERVICO, null, values);
        db.close();
        return id;
    }

    public void excluirServico(Servico servico){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABELA_SERVICO, DBHelper.COL_ID_SERVICO + " = ?",
                new String[]{String.valueOf(servico.getId())});
        db.close();
    }
}

