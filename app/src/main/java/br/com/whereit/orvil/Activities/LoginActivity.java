package br.com.whereit.orvil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import br.com.whereit.orvil.Activities.SignInSteps.StepNameActivity;
import br.com.whereit.orvil.Helper.FacebookHelper;
import br.com.whereit.orvil.Helper.GoogleSignInHelper;
import br.com.whereit.orvil.Helper.SharedHelper;
import br.com.whereit.orvil.Model.User;
import br.com.whereit.orvil.R;

public class LoginActivity extends AppCompatActivity {


    CallbackManager callbackManager;
    GoogleSignInHelper googleSignInHelper;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        googleSignInHelper = new GoogleSignInHelper(LoginActivity.this);


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                SharedHelper.saveData(LoginActivity.this,"accessToken", gson.toJson(loginResult.getAccessToken()));
                startActivity(new Intent(LoginActivity.this, LivrosActivity.class));

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    public void login(View view) {
        startActivity(new Intent(this, LivrosActivity.class));
    }

    public void loginWithFacebook(View view){
        FacebookHelper.login(this);
    }

    public void loginWithGoogle(View view){
        Intent signInIntent = googleSignInHelper.getSignInIntent();
        startActivityForResult(signInIntent, googleSignInHelper.RC_SIGN_IN);
    }

    public void cadastro(View view){
        startActivity(new Intent(this, StepNameActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode == googleSignInHelper.RC_SIGN_IN) {
            User user = googleSignInHelper.setUserData(data);
            if(user !=null){
                startActivity(new Intent(LoginActivity.this, LivrosActivity.class));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
