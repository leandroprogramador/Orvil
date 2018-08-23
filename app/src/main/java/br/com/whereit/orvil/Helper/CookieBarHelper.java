package br.com.whereit.orvil.Helper;

import android.app.Activity;
import android.content.Context;

import org.aviran.cookiebar2.CookieBar;
import org.aviran.cookiebar2.OnActionClickListener;

import br.com.whereit.orvil.R;


/**
 * Created by leandro.araujo2 on 12/04/2018.
 */

public class CookieBarHelper {
    public static void showCookieToast(Activity context, String title, String message){
        CookieBar.build(context)
                .setTitle(title)
                .setMessage(message)
                .setDuration(3000)
                .setBackgroundColor(R.color.google_button)
                .setIcon(R.drawable.emotico_sad_white)
                .show();
    }

    public static void showCookieToast(Activity context, String title, String message, ICookieBarAction iCookieBarAction, String action){
        CookieBar.build(context)
                .setTitle(title)
                .setMessage(message)
                .setDuration(3000)
                .setBackgroundColor(R.color.google_button)
                .setIcon(R.drawable.emotico_sad_white)
                .setAction(action, new OnActionClickListener() {
                    @Override
                    public void onClick() {
                        iCookieBarAction.onClickAction();
                    }
                })
                .show();
    }

    public interface ICookieBarAction{
        void onClickAction();
    }
}
