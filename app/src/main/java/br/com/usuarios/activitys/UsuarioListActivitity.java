package br.com.usuarios.activitys;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SimpleAdapter;
import br.com.usuarios.R;
import br.com.usuarios.dao.UsuarioDao;

/**
 * Created by ANDRE on 07/09/2017.
 */

public class UsuarioListActivitity extends ListActivity{

    private UsuarioDao usuarioDao;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioDao = new UsuarioDao(getBaseContext());
        Intent intent = getIntent();
        String[] de ={"login","nome","status", "tipo"};
        int[] para = {R.id.login,R.id.nome, R.id.status, R.id.tipo};

        if(intent.hasExtra("nomeUsuario")){
            adapter = new SimpleAdapter(this,usuarioDao.listarUsuariosPorNome(intent.getStringExtra("nomeUsuario")),R.layout.lista_usuarios_activity,de,para);
        }
        else {
            adapter = new SimpleAdapter(this,usuarioDao.listarUsuarios(),R.layout.lista_usuarios_activity,de,para);
        }
        setListAdapter(adapter);
    }
}
