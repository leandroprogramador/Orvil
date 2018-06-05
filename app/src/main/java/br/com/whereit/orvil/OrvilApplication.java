package br.com.whereit.orvil;

import android.app.Application;

import br.com.whereit.orvil.Service.Login.LoginService;

public class OrvilApplication extends Application{


    public static LoginService loginService;

    @Override
    public void onCreate() {
        super.onCreate();
        loginService = LoginService.getInstance();
    }
}
