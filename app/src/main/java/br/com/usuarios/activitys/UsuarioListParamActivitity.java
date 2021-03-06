package br.com.usuarios.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.com.usuarios.R;

/**
 * Created by ANDRE on 07/09/2017.
 */

public class UsuarioListParamActivitity extends Activity{
    private EditText pesquisa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuarios_param_activity);
        TextView textViewUsuarioLogado = (TextView)findViewById(R.id.txt_usuario_logado_2);
        Intent intent = getIntent();
        textViewUsuarioLogado.setText("Olá "+intent.getStringExtra("usuarioLogado"));
        pesquisa = (EditText) findViewById(R.id.pesquisa);
        Button btnpesquisar = (Button) findViewById(R.id.btn_pesquisar);
        btnpesquisar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String stringNome = pesquisa.getText().toString();
                Intent intent = new Intent(UsuarioListParamActivitity.this,UsuarioListActivitity.class);
                intent.putExtra("nomeUsuario",stringNome);
                startActivity(intent);
            }
        });
    }
}
