package br.com.usuarios.activitys;


import android.app.ListActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioDao = new UsuarioDao(getBaseContext());
        String[] de ={"login", "status", "tipo"};
        int[] para = {R.id.login, R.id.status, R.id.tipo};
        SimpleAdapter adapter = new SimpleAdapter(this,usuarioDao.listarUsuarios(),R.layout.lista_usuarios_activity,de,para);
        setListAdapter(adapter);
    }
}
