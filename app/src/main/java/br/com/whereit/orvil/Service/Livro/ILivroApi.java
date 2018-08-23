package br.com.whereit.orvil.Service.Livro;

import br.com.whereit.orvil.Model.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ILivroApi {
    @GET("/api/Livro/GetByIsbn")
    Call<Livro> getLivroByISBN(@Query("isbn") String isbn);
}
