package br.com.usuarios.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usuarios.bean.Usuario;

/**
 * Created by ANDRE on 06/09/2017.
 */

public class UsuarioDao {
    private static DatabaseHelper helper = null;
    private static SQLiteDatabase db = null;
    private List<Map<String,Object>> usuarios;

    public UsuarioDao(Context context){
        helper = new DatabaseHelper(context);
    }

    public Usuario buscarUsuarioPorNome(String username){
        db = helper.getReadableDatabase();
        String selectQuery = "SELECT * FROM usuarios WHERE login = ?" ;
        String[] whereArgs = new String [] {username};
        Cursor cursor = db.rawQuery(selectQuery, whereArgs);
        if(cursor.moveToNext()){
            Usuario usuario = criarUsuario(cursor);
            cursor.close();
            db.close();
            return usuario;
        }
        return null;
    }
    private Usuario criarUsuario(Cursor cursor){
        Usuario usuario = new Usuario(
                cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Usuario._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuario.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuario.SENHA)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuario.STATUS)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuario.TIPO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuario.NOME)));
        return usuario;
    }

    public long inserir(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();
        long resultado;
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Usuario.LOGIN,
                usuario.getLogin());

        values.put(DatabaseHelper.Usuario.SENHA,
                usuario.getSenha());

        values.put(DatabaseHelper.Usuario.STATUS,
                usuario.getStatus());

        values.put(DatabaseHelper.Usuario.TIPO,
                usuario.getTipo());

        values.put(DatabaseHelper.Usuario.NOME,
                usuario.getNome());


        if(usuario.get_id() == null){
            resultado = db.insert(DatabaseHelper.Usuario.TABELA,
                    null, values);
        }
        else {
            String[] whereArgs = new String[]{usuario.get_id().toString()};
            resultado = db.update(DatabaseHelper.Usuario.TABELA,values," _id = ?", whereArgs);
        }
        db.close();
        return resultado;
    }
    public boolean validarUsuario(Usuario usuario){
        db = helper.getReadableDatabase();
        String selectQuery = "SELECT * FROM usuarios WHERE login = ? AND senha = ?" ;
        String[] whereArgs = new String [] {usuario.getLogin(),usuario.getSenha()};
        Cursor cursor = db.rawQuery(selectQuery, whereArgs);
        if(cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        return false;
    }
    public List<Map<String, Object>> listarUsuariosPorNome(String nome){
        db = helper.getReadableDatabase();
        String selectQuery = "SELECT _id, nome, login, status, tipo FROM usuarios WHERE login LIKE '%"+nome+"%'" ;
        String[] whereArgs = new String [] {nome};
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        usuarios = new ArrayList<>();

        for(int i=0;i<cursor.getCount();i++){
            Map<String,Object> item = new HashMap<>();
            String id = cursor.getString(0);
            String nomeComplero = cursor.getString(1);
            String login = cursor.getString(2);
            String status = cursor.getString(3);
            String tipo = cursor.getString(4);

            item.put("id",id);
            item.put("nome", "Nome: "+nomeComplero);
            item.put("login", "Login: "+login);
            item.put("status", "Status: "+status);
            item.put("tipo", "Tipo: "+tipo);

            usuarios.add(item);

            cursor.moveToNext();
        }
        return usuarios;
    }
    public List<Map<String, Object>> listarUsuarios(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome, login, status, tipo FROM usuarios",null);
        cursor.moveToFirst();
        usuarios = new ArrayList<>();

        for(int i=0;i<cursor.getCount();i++){
            Map<String,Object> item = new HashMap<>();
            String id = cursor.getString(0);
            String nomeComplero = cursor.getString(1);
            String login = cursor.getString(2);
            String status = cursor.getString(3);
            String tipo = cursor.getString(4);

            item.put("id",id);
            item.put("nome", "Nome: "+nomeComplero);
            item.put("login", "Login: "+login);
            item.put("status", "Status: "+status);
            item.put("tipo", "Tipo: "+tipo);

            usuarios.add(item);

            cursor.moveToNext();
        }
        return usuarios;
    }
}
