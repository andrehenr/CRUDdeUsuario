package br.com.usuarios.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usuarios.R;

/**
 * Created by ANDRE on 07/09/2017.
 */

public class MenuActivity extends Activity {
    TextView editTextUsuarioLogado;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        Intent intent = getIntent();
        editTextUsuarioLogado = (TextView) findViewById(R.id.txt_usuario_logado);
        editTextUsuarioLogado.setText("Olá "+intent.getStringExtra("usuarioLogado"));

        Button buttonListaTodos = (Button) findViewById(R.id.btn_listar_todos);
        buttonListaTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MenuActivity.this,UsuarioListActivitity.class));
            }}
        );

        Button buttonNovoUsuario = (Button) findViewById(R.id.btn_adduser);
        buttonNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MenuActivity.this,DadosUsuarioActivity.class));
            }}
        );

        Button buttonEditarUsuario = (Button) findViewById(R.id.btn_editar);
        buttonEditarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MenuActivity.this,DadosUsuarioActivity.class);
                intent.putExtra("usuarioLogado",editTextUsuarioLogado.getText().toString().substring(4,editTextUsuarioLogado.getText().toString().length()));
                startActivity(intent);
            }}
        );

        Button buttonPesquisa = (Button) findViewById(R.id.btn_lista_por_parametro);
        buttonPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MenuActivity.this,UsuarioListParamActivitity.class);
                intent.putExtra("usuarioLogado",editTextUsuarioLogado.getText().toString().substring(4,editTextUsuarioLogado.getText().toString().length()));
                startActivity(intent);
            }}
        );


    }
}
