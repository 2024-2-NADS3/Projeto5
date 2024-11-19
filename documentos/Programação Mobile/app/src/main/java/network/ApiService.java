package network;

import models.LoginModel;
import models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET; // Importe GET
import retrofit2.http.POST;
import retrofit2.http.Query; // Importe Query

public interface ApiService {
    @POST("usuarios/cadastro")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

    @GET("usuarios/login") // Use GET para login
    Call<Usuario> loginUsuario(
            @Query("email") String email, // Use @Query para os parâmetros
            @Query("senha") String senha
    );
}