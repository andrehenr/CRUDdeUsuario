package br.com.usuarios.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ANDRE on 06/09/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "Usuarios";
    private static final int VERSAO = 1;

    public static class Usuario{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String NOME = "nome";
        public static final String STATUS = "status";
        public static final String TIPO = "tipo";
        public static final String[] COLUNAS = new String[]{_ID,LOGIN,SENHA,STATUS,TIPO,NOME};
    }

    public DatabaseHelper(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, senha TEXT, nome TEXT, status TEXT, tipo TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

}
