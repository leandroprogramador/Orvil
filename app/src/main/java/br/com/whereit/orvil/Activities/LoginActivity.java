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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import br.com.whereit.orvil.Adapters.LivrosCardAdapter;
import br.com.whereit.orvil.Helper.FacebookHelper;
import br.com.whereit.orvil.Helper.SharedHelper;
import br.com.whereit.orvil.Model.User;
import br.com.whereit.orvil.R;

public class LoginActivity extends AppCompatActivity {


    CallbackManager callbackManager;
    GoogleSignInClient googleSignInClient;
    Gson gson = new Gson();
    int RC_SIGN_IN = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);


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
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> taskGoogleSignIn = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(taskGoogleSignIn);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> taskGoogleSignIn) {
        try{
            GoogleSignInAccount account = taskGoogleSignIn.getResult(ApiException.class);
            User user = new User();
            user.setName(account.getDisplayName());
            user.setEmail(account.getEmail());
            user.setPicture(account.getPhotoUrl().toString());
            user.setGoogleUserId(account.getId());
            String accountJson = gson.toJson(user);
            Intent intent = new Intent(this, LivrosActivity.class);
            intent.putExtra("google_account", account);
            startActivity(intent);
        } catch (ApiException e){

        }
    }
}
