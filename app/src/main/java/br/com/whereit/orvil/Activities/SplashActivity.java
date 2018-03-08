package br.com.whereit.orvil.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.Profile;
<<<<<<< HEAD

=======
import com.google.android.gms.auth.api.signin.GoogleSignIn;

import br.com.whereit.orvil.Helper.FacebookHelper;
import br.com.whereit.orvil.Helper.GoogleSignInHelper;
>>>>>>> 0ca229a41a1c54f5c3241b6fff65421785a6df30
import br.com.whereit.orvil.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
<<<<<<< HEAD
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Profile.getCurrentProfile() != null){
=======
//        String hash = FacebookHelper.generateHash(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(FacebookHelper.isLogged() || GoogleSignInHelper.isLogged(SplashActivity.this)){
>>>>>>> 0ca229a41a1c54f5c3241b6fff65421785a6df30
                    startActivity(new Intent(SplashActivity.this, LivrosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                } else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                finish();
            }
        },2500);



    }
}
