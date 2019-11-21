package br.ufrpe.revcare.profissional.persistencia;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.revcare.infra.persistencia.DBHelper;
import br.ufrpe.revcare.profissional.dominio.Profissional;

import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_CPF_PROFISSIONAL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_DESLIKE;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_EMAIL_PROFISSIONAL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_FK_ID_PROFISSIONAL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_ID_PROFISSIONAL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_LIKE;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.COL_SENHA_PROFISSIONAL;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_AVALIACAO;
import static br.ufrpe.revcare.infra.persistencia.DBHelper.TABELA_PROFISSIONAL;

public class ProfissionalDAO {

    private static DBHelper dbHelper;

    public ProfissionalDAO(Context context) {

        dbHelper = new DBHelper(context);
    }

    public long cadastrar(Profissional profissional) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOME_PROFISSIONAL, profissional.getNome());
        values.put(DBHelper.COL_CPF_PROFISSIONAL, profissional.getCpf());
        values.put(DBHelper.COL_NASCIMENTO_PROFISSIONAL, profissional.getDataNascimento());
        values.put(DBHelper.COL_DESCRICAO_PROFISSIONAL, profissional.getDescricao());
        values.put(DBHelper.COL_EMAIL_PROFISSIONAL, profissional.getEmail());
        values.put(DBHelper.COL_TELEFONE_PROFISSIONAL, profissional.getTelefone());
        values.put(DBHelper.COL_CERTIFICADO, profissional.getCertificado());
        values.put(DBHelper.COL_SENHA_PROFISSIONAL, profissional.getSenha());
        values.put(DBHelper.COL_ESTADO_PROFISSIONAL, profissional.getEstado());
        values.put(DBHelper.COL_CIDADE_PROFISSIONAL, profissional.getCidade());
        values.put(DBHelper.COL_FOTO_PROFISSIONAL, profissional.getFoto());

        long id = db.insert(TABELA_PROFISSIONAL, null, values);
        db.close();
        return id;

    }

    public Profissional consultarEmail(String email, String senha) {
        Profissional result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_PROFISSIONAL +
                        " WHERE " + COL_EMAIL_PROFISSIONAL + " = ? " +
                        " AND " + COL_SENHA_PROFISSIONAL + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email, senha});
        if (cursor.moveToFirst()) {
            result = criarProfissional(cursor);
        }
        db.close();
        cursor.close();
        return result;
    }


    public Profissional consultarEmail(String email) {
        Profissional result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_PROFISSIONAL +
                        " WHERE " + COL_EMAIL_PROFISSIONAL + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.moveToFirst()) {
            result = criarProfissional(cursor);
        }
        cursor.close();
        db.close();

        return result;
    }

    public Profissional consultarCpf(String cpf) {
        Profissional result = null;
        String query =
                " SELECT * " +
                        " FROM " + TABELA_PROFISSIONAL +
                        " WHERE " + COL_CPF_PROFISSIONAL + " = ? ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{cpf});
        if (cursor.moveToFirst()) {
            result = criarProfissional(cursor);
        }
        cursor.close();
        db.close();
        return result;
    }

    private Profissional criarProfissional(Cursor cursor) {
        Profissional result = new Profissional();
        result.setId(cursor.getInt(0));
        result.setNome(cursor.getString(1));
        result.setTelefone(cursor.getString(3));
        result.setDataNascimento(cursor.getString(2));
        result.setDescricao(cursor.getString(6));
        result.setCpf(cursor.getString(5));
        result.setEmail(cursor.getString(4));
        result.setCertificado(cursor.getString(7));
        result.setSenha(cursor.getString(8));
        result.setEstado(cursor.getString(9));
        result.setCidade(cursor.getString(10));
        result.setFoto(cursor.getBlob(11));

        return result;
    }

    public List<Profissional> getAllProfissional() {
        List<Profissional> profissionalArrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = " SELECT * " +
                " FROM " + TABELA_PROFISSIONAL;
        String[] args = {};
        Cursor cursor = db.rawQuery(query, args);
        Profissional profissional = null;
        if (cursor.moveToFirst()) {
            do {
                profissional = criarProfissional(cursor);
                profissionalArrayList.add(profissional);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            return profissionalArrayList;
        }
        cursor.close();
        db.close();
        return profissionalArrayList;
    }

    public void updateDescricaoProfissional(Profissional profissional) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descricao", profissional.getDescricao());
        db.update("Tabela_Profissional", valores, "id = ?", new String[]{String.valueOf(profissional.getId())});
        db.close();
    }

    public static void alteraFotoProfissional(Profissional profissional){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foto", profissional.getFoto());
        db.update(TABELA_PROFISSIONAL,values, "id = ?",
                new String[]{String.valueOf(profissional.getId())});
        db.close();

    }

    public int contarDeslikes(long idProfissonal){
        int result = 0;
        String query = " SELECT * FROM " + TABELA_AVALIACAO +
                " WHERE " + COL_FK_ID_PROFISSIONAL + " = " + idProfissonal +
                "  AND " + COL_LIKE + " = 0 ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = {};
        Cursor cursor = db.rawQuery(query, args);
        if (cursor.moveToFirst()) {
            do {
                result+=1;
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            return result;
        }
        cursor.close();
        db.close();
        return result;
    }
    public int contarLikes(long idProfissonal){
        int result = 0;
        String query = " SELECT * FROM " + TABELA_AVALIACAO +
                " WHERE " + COL_FK_ID_PROFISSIONAL + " = " + idProfissonal +
                "  AND " + COL_DESLIKE + " = 0 ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = {};
        Cursor cursor = db.rawQuery(query, args);
        if (cursor.moveToFirst()) {
            do {
                result+=1;
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            return result;
        }
        cursor.close();
        db.close();
        return result;
    }
    public Double getNotaProfissional(long usuario, long profissional) {
        String query = "SELECT * FROM Tabela_Avaliacao " +
                "WHERE fk_id_profissional = ? " +
                "AND fk_id_usuario = ? ";
        String idUsuario = String.valueOf(usuario);
        String idProfissional = String.valueOf(profissional);
        String[] args = {idProfissional, idUsuario};
        SQLiteDatabase leitorBanco = dbHelper.getWritableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Double nota = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int indexNota = cursor.getColumnIndex("like");
            nota = cursor.getDouble(indexNota);
        }
        return nota;
    }
    private Profissional carregarObjeto(String query, String[] args) {
        SQLiteDatabase leitorBanco = dbHelper.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Profissional profis = null;
        if (cursor.moveToNext()) {
            profis = criarProfissional(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return profis;
    }
    public Profissional getProfissionalById(long id) {
        String query = "SELECT * FROM Tabela_Profissional " +
                "WHERE id = ?";
        String[] args = {String.valueOf(id)};
        return this.carregarObjeto(query, args);
    }
}
