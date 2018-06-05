package br.com.whereit.orvil.Service;

import android.content.Context;
import android.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Leandro.Araujo on 30/10/17.
 */

public class JsonRequest {

    public static void jsonObjectRequest(Context context, int method, final String url, JSONObject jsonObject, final Map<String, String> header, final PostCommentResponseListener responseListener){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(method,url, jsonObject, response -> responseListener.requestCompleted(response.toString(), url), error -> {
            try {

                String mError = error.getCause().getMessage();
                if(mError != null) {
                    if(mError.contains("Unable to resolve host")){
                        responseListener.requestError("Verifique sua conexão de internet!", url);
                    } else {
                        responseListener.requestError(error.getCause().getMessage(), url);

                    }
                }
                else{
                    throw new Exception();
                }
            }
            catch (Exception e){
                if (error.toString().contains("TimeoutError"))
                {
                    responseListener.requestError("Sua conexão está intermitente. Verifique e tente novamente!", url);
                }
                else if(error.toString().contains("AuthFailureError")){
                    responseListener.requestError("Não foi possível autenticar. Verifique seus dados e tente novamente!", url);
                }
                else {
                    responseListener.requestError("Não foi possível realizar esta ação. Tente novamente!", url);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (header != null) {
                    return header;
                } else {
                    Map<String, String> genericHeader = new ArrayMap<>();
                    genericHeader.put("accept", "text/html");
                    return genericHeader;
                }
            }
        };


        queue.add(request);

    }

    public interface PostCommentResponseListener{

        void requestCompleted(String json, String request);
        void requestError(String error, String request);
    }

}
