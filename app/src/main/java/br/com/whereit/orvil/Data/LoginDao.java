package br.com.whereit.orvil.Data;

import android.hardware.usb.UsbRequest;

import java.util.List;

import br.com.whereit.orvil.Model.Login;
import br.com.whereit.orvil.Model.User;

public class LoginDao {

    public static boolean save(Login login){
        try{
            login.save();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public static boolean exists(){
        try{
            List<Login> login = Login.listAll(Login.class);
            if(login.size() > 0){
                return true;
            } else{
                throw new Exception();
            }
        } catch (Exception ex){
            return false;
        }
    }

    public static void deleteAll(){
        try {
            Login.deleteAll(Login.class);
        } catch (Exception ex){

        }
    }

}
