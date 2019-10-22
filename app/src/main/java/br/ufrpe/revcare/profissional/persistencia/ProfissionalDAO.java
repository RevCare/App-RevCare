package br.ufrpe.revcare.profissional.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.profissional.dominio.Profissional;

public class ProfissionalDAO {
    private Context context;
    private DBHelper dbHelper = new DBHelper(context);

    public void cadastrarProfissional(Profissional profissional){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.COL_NOME_PROFISSIONAL, profissional.getNome());
        values.put(DBHelper.COL_CPF_PROFISSIONAL, profissional.getCpf());
        values.put(DBHelper.COL_NASCIMENTO_PROFISSIONAL, profissional.getDataNascimento());
        values.put(DBHelper.COL_ENDERECO_PROFISSIONAL, profissional.getEndereco());
        values.put(DBHelper.COL_EMAIL_PROFISSIONAL, profissional.getEmail());
        values.put(DBHelper.COL_TELEFONE_PROFISSIONAL, profissional.getTelefone());
        values.put(DBHelper.COL_REGULAMENTACAO_PROFISSIONAL, profissional.getCertificado());
        values.put(DBHelper.COL_SENHA_PROFISSIONAL, profissional.getSenha());


        db.insert(DBHelper.TABELA_PROFISSIONAL, null, values);
        db.close();
    }
}
