package br.com.whereit.orvil;

import android.app.Application;

import com.orm.SugarApp;
import com.orm.SugarConfig;
import com.orm.SugarDb;
import com.orm.SugarRecord;

import br.com.whereit.orvil.Service.Livro.LivroService;
import br.com.whereit.orvil.Service.Login.LoginService;

public class OrvilApplication extends SugarApp{


    public static LoginService loginService;
    public static LivroService livroService;

    @Override
    public void onCreate() {
        super.onCreate();
        loginService = LoginService.getInstance();
        livroService = LivroService.getInstance();

    }
}
