package br.com.whereit.orvil.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.google.android.gms.auth.api.signin.GoogleSignIn;

import br.com.whereit.orvil.Helper.FacebookHelper;
import br.com.whereit.orvil.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        String hash = FacebookHelper.generateHash(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Profile.getCurrentProfile() != null || GoogleSignIn.getLastSignedInAccount(SplashActivity.this) != null){
                    startActivity(new Intent(SplashActivity.this, LivrosActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                } else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                finish();
            }
        },2500);



    }
}
