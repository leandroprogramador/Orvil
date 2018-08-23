package br.com.whereit.orvil.Activities.NewBookSteps;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import br.com.whereit.orvil.Helper.PicassoHelper;
import br.com.whereit.orvil.Model.Livro;
import br.com.whereit.orvil.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultBookActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgCover)
    ImageView imgCover;
    @BindView(R.id.txtTitle)
    TextView txtTitulo;
    @BindView(R.id.txtAutores)
    TextView txtAutor;
    @BindView(R.id.txtEditora)
    TextView txtEditora;
    @BindView(R.id.txtAno)
    TextView txtAno;
    @BindView(R.id.txtCategoria)
    TextView txtCategoria;
    @BindView(R.id.txtDescricao)
    TextView txtDescricao;
    @BindView(R.id.txtPaginas)
    TextView txtPaginas;
    @BindView(R.id.txtIdioma)
    TextView txtIdioma;
    @BindView(R.id.txtTags)
    TextView txtTags;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_book);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resultado da sua busca");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String jsonLivro = getIntent().getStringExtra("livro");

        Livro livro = new Gson().fromJson(jsonLivro, Livro.class);

        createDataBind(livro);
    }

    private void createDataBind(Livro livro) {
        txtTitulo.setText(livro.getTituloFormatado());
        txtAutor.setText(livro.getAutorFormatado());
        txtEditora.setText(livro.getEditora());
        txtAno.setText(livro.getAno());
        txtCategoria.setText(livro.getCategoriasFormatado());
        txtDescricao.setText(livro.getDescricao());
        txtPaginas.setText(String.valueOf(livro.getNumeroPaginas()));
        txtIdioma.setText(livro.getIdioma());
        txtTags.setText(livro.getTags());

        PicassoHelper.downloadImage(ResultBookActivity.this, imgCover, livro.getSmallThumbnail());
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
