package br.com.whereit.orvil.Service.Login;

import br.com.whereit.orvil.Helper.ConstantesHelper;
import br.com.whereit.orvil.Model.Login;
import br.com.whereit.orvil.Model.ModelRequest.LoginRetorno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService {
    private static ILoginApi service;
    private static LoginService loginService;

    private LoginService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesHelper.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ILoginApi.class);
    }

    public static LoginService getInstance(){
        if(loginService == null){
            loginService = new LoginService();
        }
        return loginService;
    }

    public void createUser(Login login, Callback<LoginRetorno> callback){
        Call<LoginRetorno> loginRetornoCall = service.login(login.getGrant_type(), login.getUsername(), login.getPassword());
        loginRetornoCall.enqueue(callback);

    }

}
