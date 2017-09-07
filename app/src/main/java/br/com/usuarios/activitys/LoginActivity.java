package br.com.usuarios.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.com.usuarios.R;
import br.com.usuarios.bean.Usuario;
import br.com.usuarios.dao.UsuarioDao;

/**
 * Created by ANDRE on 06/09/2017.
 */

public class LoginActivity extends Activity {
    private UsuarioDao usuarioDao;
    private String StringUsuario;
    private String StringSenha;
    private EditText editTextUsuario;
    private EditText editTextSenha;
    private TextView textViewErroSenha;
    private TextView textViewErroUsuario;
    private TextView textViewMensagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        usuarioDao = new UsuarioDao(getBaseContext());
        editTextUsuario = (EditText) findViewById(R.id.txt_username);
        editTextSenha = (EditText) findViewById(R.id.txt_password);
        textViewErroSenha = (TextView) findViewById(R.id.txtErrorSenha);
        textViewErroUsuario = (TextView) findViewById(R.id.txtErrorUser);
        textViewMensagem = (TextView) findViewById(R.id.txtMensagem);
    }

    public void validaLogin(View view){
        StringUsuario = editTextUsuario.getText().toString();
        StringSenha = editTextSenha.getText().toString();
        Usuario usuario;
        if(StringSenha.isEmpty() || StringSenha.equals("")){
            textViewErroSenha.setText("A senha não pode ser vazia");
        }
        else if(StringUsuario.isEmpty() || StringUsuario.equals("")){
            textViewErroUsuario.setText("O usuário não pode ser vazio");
        }
        else {
            textViewErroSenha.setText("");
            textViewErroUsuario.setText("");
            usuario = new Usuario(StringUsuario,StringSenha);
            if(usuarioDao.validarUsuario(usuario)){
                Intent intent = new Intent(this,MenuActivity.class);
                intent.putExtra("usuarioLogado",usuario.getLogin());
                startActivity(intent);
            }
            else {
                textViewMensagem.setText("Revise seus dados, usuário e senha não batem");
            }
        }
    }

    public void adicionaUsuario(View view){
        StringUsuario = editTextUsuario.getText().toString();
        StringSenha = editTextSenha.getText().toString();

        if(StringSenha.isEmpty() || StringSenha.equals("")){
            textViewErroSenha.setText("A senha não pode ser vazia");
        }
        else if(StringUsuario.isEmpty() || StringUsuario.equals("")){
            textViewErroUsuario.setText("O usuário não pode ser vazio");
        }
        else {
            textViewErroSenha.setText("");
            textViewErroUsuario.setText("");
            Usuario usuario = new Usuario(StringUsuario,StringSenha);
            if(usuarioDao.buscarUsuarioPorNome(usuario.getLogin()) != null){
                textViewMensagem.setText("Usuário já existe");
            }
            else {
                Intent intent = new Intent(this,DadosUsuarioActivity.class);
                String[] usuarioArray = new String[]{"",usuario.getLogin(),usuario.getSenha()};
                intent.putExtra("usuario",usuarioArray);
                startActivity(intent);
            }
        }
    }
}
