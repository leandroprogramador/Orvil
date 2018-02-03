package br.com.whereit.orvil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import br.com.whereit.orvil.Adapters.LivrosCardAdapter;
import br.com.whereit.orvil.Helper.SharedHelper;
import br.com.whereit.orvil.Model.User;
import br.com.whereit.orvil.R;

public class LoginActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LivrosCardAdapter adapter;
    CallbackManager callbackManager;
    User user = new User();
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
