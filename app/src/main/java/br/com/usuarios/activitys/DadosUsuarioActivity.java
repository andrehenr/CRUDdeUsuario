package br.com.usuarios.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.usuarios.R;
import br.com.usuarios.bean.Usuario;
import br.com.usuarios.dao.UsuarioDao;

/**
 * Created by ANDRE on 06/09/2017.
 */

public class DadosUsuarioActivity extends Activity {
    private UsuarioDao usuarioDao;
    private EditText editTextUsuario;
    private EditText editTextSenha;
    private EditText editTextNome;
    private EditText editTextTipo;
    private EditText editTextStatus;
    private String stringUsuario ;
    private String stringSenha ;
    private String stringTipo ;
    private String stringStatus ;
    private String stringNome ;
    Usuario usuarioEdicao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dados_usuario_activity);
        usuarioDao = new UsuarioDao(getBaseContext());
        usuarioEdicao = new Usuario(null,null);
        Intent intent = getIntent();
        Button btnExcluir = (Button) findViewById(R.id.btn_excluir);
        btnExcluir.setVisibility(View.INVISIBLE);
        if(intent.hasExtra("usuarioLogado")){
            btnExcluir.setVisibility(View.VISIBLE);
            usuarioEdicao = usuarioDao.buscarUsuarioPorNome(intent.getStringExtra("usuarioLogado"));
            editTextTipo = (EditText) findViewById(R.id.txt_tipo);
            editTextStatus = (EditText) findViewById(R.id.txt_status);
            editTextUsuario = (EditText) findViewById(R.id.txt_login);
            editTextSenha = (EditText) findViewById(R.id.txt_senha);
            editTextNome = (EditText) findViewById(R.id.txt_nome);
            editTextTipo.setText(usuarioEdicao.getTipo());
            editTextStatus.setText(usuarioEdicao.getStatus());
            editTextUsuario.setText(usuarioEdicao.getLogin());
            editTextSenha.setText(usuarioEdicao.getSenha());
            editTextNome.setText(usuarioEdicao.getNome());

        }
        if(intent.hasExtra("usuario")) {
            String[] usuario = intent.getStringArrayExtra("usuario");
            if (usuario[0].equals("")) {
                editTextUsuario = (EditText) findViewById(R.id.txt_login);
                editTextSenha = (EditText) findViewById(R.id.txt_senha);
                editTextUsuario.setText(usuario[1]);
                editTextSenha.setText(usuario[2]);
            }
        }
    }

    public void deletaUsuario(View view){
        String mensagemErro;
        if(usuarioDao.delete(usuarioEdicao) == 1){
           mensagemErro = "Usuário deletado com sucesso";
        }
        else{
            mensagemErro = "O Usuário não foi deletado";
        }
        Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
        toast.show();
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void salvaUsuario(View view){
        editTextTipo = (EditText) findViewById(R.id.txt_tipo);
        editTextStatus = (EditText) findViewById(R.id.txt_status);
        editTextUsuario = (EditText) findViewById(R.id.txt_login);
        editTextSenha = (EditText) findViewById(R.id.txt_senha);
        editTextNome = (EditText) findViewById(R.id.txt_nome);
        stringUsuario = editTextUsuario.getText().toString();
        stringSenha = editTextSenha.getText().toString();
        stringTipo = editTextTipo.getText().toString();
        stringStatus = editTextStatus.getText().toString();
        stringNome = editTextNome.getText().toString();
        if(stringStatus.isEmpty() || stringStatus.equals("")){
            String mensagemErro = "o campo STATUS não pode ficar em branco";
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(stringTipo.isEmpty() || stringTipo.equals("")){
            String mensagemErro = "o campo TIPO não pode ficar em branco";
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(stringSenha.isEmpty() || stringSenha.equals("")){
            String mensagemErro = "o campo SENHA não pode ficar em branco";
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(stringUsuario.isEmpty() || stringUsuario.equals("")){
            String mensagemErro = "o campo USUARIO não pode ficar em branco";
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(stringNome.isEmpty() || stringNome.equals("")){
            String mensagemErro = "o campo NOME não pode ficar em branco";
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            if(usuarioEdicao.getLogin() != null){
                Usuario usuario = new Usuario(usuarioEdicao.get_id(),
                        stringUsuario,stringSenha,stringStatus,stringTipo,stringNome);
                if (usuarioDao.inserir(usuario) != -1) {
                    Toast toast = Toast.makeText(this, "Usuário alterado com sucesso", Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    Toast toast = Toast.makeText(this, "Usuário não foi alterado", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else {
                Usuario usuario = new Usuario(stringUsuario, stringSenha, stringStatus, stringTipo, stringNome);
                if (usuarioDao.buscarUsuarioPorNome(stringUsuario) != null) {
                    Toast toast = Toast.makeText(this, "Usuário já existe", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (usuarioDao.inserir(usuario) != -1) {
                        Toast toast = Toast.makeText(this, "Usuário adicionado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    else {
                        Toast toast = Toast.makeText(this, "Usuário não foi adicionado", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        }
    }
}
