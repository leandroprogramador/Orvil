package br.com.whereit.orvil.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import br.com.whereit.orvil.Activities.LivrosActivity;
import br.com.whereit.orvil.IFbData;
import br.com.whereit.orvil.Model.User;

/**
 * Created by leani on 03/02/2018.
 */

public class FacebookHelper {

    public static void login(Activity context){
        LoginManager.getInstance().logInWithReadPermissions(context, Arrays.asList("public_profile", "email", "user_birthday"));
    }

    public static String generateHash(Context context){
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            return "";
        }
        catch (NoSuchAlgorithmException e) {
            return "";
        }
        return "";
    }

    public static void getUserDetailsFromFB(AccessToken accessToken, final IFbData iFbData) {
        final User user = new User();
        GraphRequest req=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if(response != null) {
                    try {
                        user.setName((String) object.get("name"));
                        user.setEmail((String) object.get("email"));
                        user.setFacebookUserId((String) object.get("id"));
                        user.setBirthDay((String) object.get("birthday"));
                        user.setGender((String) object.get("gender"));
                        iFbData.getUserData(user);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday,picture");
        req.setParameters(parameters);
        req.executeAsync();

    }

    public static boolean isLogged(){
        return AccessToken.getCurrentAccessToken() != null;
    }

    public static void loggout(){
        LoginManager.getInstance().logOut();
    }
}
