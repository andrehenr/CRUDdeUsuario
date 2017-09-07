package br.com.usuarios.activitys;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import br.com.usuarios.R;
import br.com.usuarios.bean.Usuario;
import br.com.usuarios.dao.UsuarioDao;

/**
 * Created by ANDRE on 07/09/2017.
 */

public class UsuarioListParamActivitity extends Activity{
    private EditText editTextPesquisa;
    private UsuarioDao usuarioDao;
    private ListView ListaUsuarios;
    private ArrayAdapter<Usuario> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuarios_param_activity);
        TextView textViewUsuarioLogado = (TextView)findViewById(R.id.txt_usuario_logado_2);
        Intent intent = getIntent();
        textViewUsuarioLogado.setText("Olá "+intent.getStringExtra("usuarioLogado"));
        usuarioDao = new UsuarioDao(getBaseContext());
        editTextPesquisa = (EditText) findViewById(R.id.pesquisa);
        final Context con = getBaseContext();
        usuarioDao = new UsuarioDao(con);
        ListaUsuarios = (ListView) findViewById(R.id.listausu);
        final EditText pesquisa = (EditText)findViewById(R.id.pesquisa);

        Button btnpesquisar = (Button) findViewById(R.id.btn_pesquisar);
        btnpesquisar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String stringNome = pesquisa.getText().toString();
                adapter = new ArrayAdapter<Usuario>(con,android.R.layout.simple_list_item_1,usuarioDao.listarUsuariosPorNome(stringNome));
                ListaUsuarios.setAdapter(adapter);
            }
        });

    }
}
