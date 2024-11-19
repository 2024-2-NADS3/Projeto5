package com.example.myapplication;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import javax.crypto.SecretKey;

import models.Usuario;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import com.example.myapplication.utils.EncryptionUtil;

public class pgcadastro extends AppCompatActivity {

    private EditText nameCreate;
    private EditText usernameCreate;
    private EditText passwordCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgcadastro);

        nameCreate = findViewById(R.id.nameCreate);
        usernameCreate = findViewById(R.id.usernameCreate);
        passwordCreate = findViewById(R.id.passwordCreate);

        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome_completo = nameCreate.getText().toString();
                String email = usernameCreate.getText().toString();
                String senha = passwordCreate.getText().toString();

                try {
                    SecretKey key = EncryptionUtil.generateKey();
                    byte[] encryptedPassword = EncryptionUtil.encrypt(key, senha.getBytes());
                    String encryptedPasswordBase64 = Base64.encodeToString(encryptedPassword, Base64.DEFAULT);

                    Usuario usuario = new Usuario(nome_completo, email, encryptedPasswordBase64);

                    RetrofitClient.apiService.cadastrarUsuario(usuario).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(pgcadastro.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                // Limpar os campos ou navegar para outra activity
                            } else {
                                try {
                                    Log.e("CadastroErro", "Erro na requisição: " + response.code() + " - " + response.errorBody().string());
                                    Toast.makeText(pgcadastro.this, "Erro ao cadastrar usuário: " + response.code() + " - " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Log.e("CadastroErro", "Erro ao ler o corpo da resposta: " + e.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            if (t instanceof HttpException) {
                                HttpException httpException = (HttpException) t;
                                int code = httpException.code();
                                try {
                                    String errorBody = httpException.response().errorBody().string();
                                    Log.e("CadastroErro", "Erro na requisição: " + code + " - " + errorBody);
                                    Toast.makeText(pgcadastro.this, "Erro ao cadastrar usuário: " + code + " - " + errorBody, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Log.e("CadastroErro", "Erro ao ler o corpo da resposta: " + e.getMessage());
                                    Toast.makeText(pgcadastro.this, "Erro ao cadastrar usuário: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.e("CadastroErro", "Erro na requisição: " + t.getMessage());
                                Toast.makeText(pgcadastro.this, "Erro ao cadastrar usuário: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(pgcadastro.this, "Erro ao criptografar a senha: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
