package br.ufrpe.revcare.profissional.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.profissional.dominio.Profissional;



public class ProfissionalDAO {

    private DBHelper dbHelper;

    public ProfissionalDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long cadastrarProfissional(Profissional profissional){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOME_PROFISSIONAL, profissional.getNome());
        values.put(DBHelper.COL_CPF_PROFISSIONAL, profissional.getCpf());
        values.put(DBHelper.COL_NASCIMENTO_PROFISSIONAL, profissional.getDataNascimento());
        values.put(DBHelper.COL_ENDERECO_PROFISSIONAL, profissional.getEndereco());
        values.put(DBHelper.COL_EMAIL_PROFISSIONAL, profissional.getEmail());
        values.put(DBHelper.COL_TELEFONE_PROFISSIONAL, profissional.getTelefone());
        values.put(DBHelper.COL_CERTIFICADO, profissional.getCertificado());
        values.put(DBHelper.COL_SENHA_PROFISSIONAL, profissional.getSenha());

        long id = db.insert(DBHelper.TABELA_PROFISSIONAL, null, values);
        db.close();
        return id;

    }
    public List<Profissional> getListaSenhaProfissional(){

        List<Profissional> listaSenhaProfissionais = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;
        String query = "SELECT * FROM TABELA_PROFISSIONAL;";

        try{
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(query,null);

            if(cursor.moveToFirst()){
                Profissional profissionalTemporario = null;

                do {
                    profissionalTemporario = new Profissional();
                    profissionalTemporario.setEmail(cursor.getString(5));
                    profissionalTemporario.setSenha(cursor.getString(9));

                    listaSenhaProfissionais.add(profissionalTemporario);


                }while (cursor.moveToNext());

            }

        }catch (Exception e){
            Log.d("ERRO LISTA PROFISSIONAL","ERRO AO RETORNAR OS PROFISSIONAIS");
            return null;

        }finally {
             if (db != null){
                 db.close();
             }
        }
        return listaSenhaProfissionais;
    }
}
