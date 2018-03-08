package br.com.whereit.orvil.Helper;

import android.util.Patterns;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jonas.vieira on 07/02/2018.
 */

public class ValidationHelper {

    public static boolean validSize(String text, int size){
        if(text.trim().length() < size){
            return false;
        }
        return true;
    }

    public static boolean validEmail(String text){
        return Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    public static boolean validDate(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean validPass(String text){
        try{
            if(text.matches(".*\\d+.*") && text.matches(".*[a-z].*")){
                return true;
            }
        } catch (Exception ex){
            return false;
        }
        return false;
    }

    public static boolean validConfPass(String pass, String confPass){
        if(confPass.equals(pass)){
            return true;
        }
        return false;
    }


}
