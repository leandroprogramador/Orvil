package br.com.whereit.orvil.Service.Livro;

import br.com.whereit.orvil.Helper.ConstantesHelper;
import br.com.whereit.orvil.Model.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LivroService {
    private static ILivroApi service;
    private static LivroService livroService;

    private LivroService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesHelper.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ILivroApi.class);
    }

    public static LivroService getInstance(){
        if(livroService == null){
            livroService = new LivroService();
        }
        return livroService;
    }

    public void getLivroByISBN(String isbn, Callback<Livro> callback){
        Call<Livro> livroRetornoCall = service.getLivroByISBN(isbn);
        livroRetornoCall.enqueue(callback);
    }
}
