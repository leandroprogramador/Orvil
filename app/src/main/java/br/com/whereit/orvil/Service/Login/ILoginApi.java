package br.com.whereit.orvil.Service.Login;

import br.com.whereit.orvil.Model.Login;
import br.com.whereit.orvil.Model.ModelRequest.LoginRetorno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ILoginApi {
    @FormUrlEncoded
    @POST("/Login")
    Call<LoginRetorno> login(@Field("grant_type") String grantType,@Field("username") String username, @Field("password") String password);
}
