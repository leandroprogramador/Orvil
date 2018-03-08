package br.com.whereit.orvil.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import br.com.whereit.orvil.Activities.LivrosActivity;
import br.com.whereit.orvil.Activities.LoginActivity;
import br.com.whereit.orvil.Model.User;

/**
 * Created by jonas.vieira on 06/02/2018.
 */

public class GoogleSignInHelper {
    GoogleSignInClient googleSignInClient;
    public int RC_SIGN_IN = 100;
    Context context;

    public GoogleSignInHelper(Context context){
        this.context = context;
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
    }

    public Intent getSignInIntent(){
        return googleSignInClient.getSignInIntent();
    }

    public User setUserData(Intent data){
        Task<GoogleSignInAccount> taskGoogleSignIn = GoogleSignIn.getSignedInAccountFromIntent(data);
        try{
            GoogleSignInAccount account = taskGoogleSignIn.getResult(ApiException.class);
            User user = new User();
            user.setName(account.getDisplayName());
            user.setEmail(account.getEmail());
            if(account.getPhotoUrl() != null) {
                user.setPicture(account.getPhotoUrl().toString());
            }
            user.setGoogleUserId(account.getId());
            return user;

        } catch (ApiException e){
            return null;
        } catch (Exception e){
            return null;
        }
    }

    public static User getUserData(Context context){
        User user = null;
        if(GoogleSignIn.getLastSignedInAccount(context) != null)
        {
            user = new User();
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
            user.setName(account.getDisplayName());
            user.setEmail(account.getEmail());
            user.setGoogleUserId(account.getId());
            if(account.getPhotoUrl() != null) {
                user.setPicture(account.getPhotoUrl().toString());
            }
        }
        return user;
    }

    public static boolean isLogged(Context context){
        return GoogleSignIn.getLastSignedInAccount(context)!= null;
    }

    public static void loggout(final Activity context){


        GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(context, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                });


    }
}
