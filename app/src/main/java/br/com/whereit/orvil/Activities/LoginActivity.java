package br.com.whereit.orvil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.whereit.orvil.Activities.SignInSteps.StepNameActivity;
import br.com.whereit.orvil.Data.LoginDao;
import br.com.whereit.orvil.Helper.ConstantesHelper;
import br.com.whereit.orvil.Helper.CookieBarHelper;
import br.com.whereit.orvil.Helper.FacebookHelper;
import br.com.whereit.orvil.Helper.GoogleSignInHelper;
import br.com.whereit.orvil.Helper.SharedHelper;
import br.com.whereit.orvil.Model.Login;
import br.com.whereit.orvil.Model.ModelRequest.ErrorBody;
import br.com.whereit.orvil.Model.ModelRequest.LoginRetorno;
import br.com.whereit.orvil.Model.User;
import br.com.whereit.orvil.OrvilApplication;
import br.com.whereit.orvil.R;
import br.com.whereit.orvil.Service.JsonRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements JsonRequest.PostCommentResponseListener {


    CallbackManager callbackManager;
    GoogleSignInHelper googleSignInHelper;
    Gson gson = new Gson();
    @BindView(R.id.edit_email)
    EditText editUser;
    @BindView(R.id.edit_senha)
    EditText editSenha;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setProgressColor();

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

    private void setProgressColor() {
        if (progressBar.getIndeterminateDrawable() != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorWhite), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    public void login(View view) {
        String user = editUser.getText().toString();
        String senha = editSenha.getText().toString();

        if(user.equals("") || senha.equals("")){
            CookieBarHelper.showCookieToast(LoginActivity.this,"Ops...", "Acho que você esqueceu de preencher alguma coisa!");
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            Login login = new Login(user, senha, "password");
            requestLogin(login);


        }
    }

    private void requestLogin(Login login) {
        OrvilApplication.loginService.createUser(login, new Callback<LoginRetorno>() {
            @Override
            public void onResponse(Call<LoginRetorno> call, Response<LoginRetorno> response) {

                if(response.errorBody() != null){
                    ErrorBody errorBody = null;
                    try {
                        errorBody = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                        CookieBarHelper.showCookieToast(LoginActivity.this, "Ops...",errorBody.getError_description());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                  progressBar.setVisibility(View.INVISIBLE);
              } else{
                  progressBar.setVisibility(View.INVISIBLE);
                  loginPasswordSuccess(response.body(), login);
              }
            }

            @Override
            public void onFailure(Call<LoginRetorno> call, Throwable t) {
                CookieBarHelper.showCookieToast(LoginActivity.this, "Ops...", t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void loginPasswordSuccess(LoginRetorno response, Login login) {
        login.setPassword("");
        login.setAccess_token(response.getAccess_token());
        login.setToken_type(response.getToken_type());
        login.setExpires_in(response.getExpires_in());
        if(LoginDao.save(login)){
            startActivity(new Intent(LoginActivity.this, LivrosActivity.class));
        } else{
            CookieBarHelper.showCookieToast(LoginActivity.this,"Ops...", "Não foi possível fazer o login. Por favor, tente novamente.");
        }


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
//                Login login = new Login(user.getEmail(), "", "google");
                startActivity(new Intent(LoginActivity.this, LivrosActivity.class));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void requestCompleted(String json, String request) {

    }

    @Override
    public void requestError(String error, String request) {
        LoginRetorno loginRetorno = new Gson().fromJson(error, LoginRetorno.class);

        CookieBarHelper.showCookieToast(LoginActivity.this,"Ops", loginRetorno.getError_description());
    }
}
