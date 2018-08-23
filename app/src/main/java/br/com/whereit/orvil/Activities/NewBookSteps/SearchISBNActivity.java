package br.com.whereit.orvil.Activities.NewBookSteps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.whereit.orvil.Helper.CookieBarHelper;
import br.com.whereit.orvil.Model.Livro;
import br.com.whereit.orvil.OrvilApplication;
import br.com.whereit.orvil.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchISBNActivity extends AppCompatActivity implements CookieBarHelper.ICookieBarAction {

    private static final int BARCODE_REQUEST = 100;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_isbn)
    EditText editISBN;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_isbn);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Livro");


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void pesquisar(View view) {
        String isbn = editISBN.getText().toString();

        if(!isbn.equals("")){
            searchISBN(isbn);
        }
    }

    private void searchISBN(String isbn) {
        progressBar.setVisibility(View.VISIBLE);
        OrvilApplication.livroService.getLivroByISBN(isbn, new Callback<Livro>() {
            @Override
            public void onResponse(Call<Livro> call, Response<Livro> response) {
                if(response.body() != null) {
                    Livro livro = response.body();
                    goToActivityResult(livro);
                } else{

                    CookieBarHelper.showCookieToast(SearchISBNActivity.this, "Ops..", "Não foi possívl encontrar o livro pelo ISBN", SearchISBNActivity.this, "Criar livro");
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Livro> call, Throwable t) {
                CookieBarHelper.showCookieToast(SearchISBNActivity.this, "Ops..", "Não foi possívl encontrar o livro pelo ISBN", SearchISBNActivity.this, "Criar livro");
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void goToActivityResult(Livro livro) {
        String json = new Gson().toJson(livro);
        Intent intent = new Intent(SearchISBNActivity.this, ResultBookActivity.class);
        intent.putExtra("livro", json);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BARCODE_REQUEST){
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("isbn");
                searchISBN(result);
            }
        }
    }

    @Override
    public void onClickAction() {

    }

    public void openBarcodeScan(View view) {
        Intent intent = new Intent(SearchISBNActivity.this, BarcodeScannerActivity.class);
        startActivityForResult(intent, BARCODE_REQUEST);
    }
}
