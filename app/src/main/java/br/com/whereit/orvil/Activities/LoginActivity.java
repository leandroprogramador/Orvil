package br.com.whereit.orvil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.whereit.orvil.Adapters.LivrosCardAdapter;
import br.com.whereit.orvil.R;

public class LoginActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LivrosCardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void login(View view) {
        startActivity(new Intent(this, LivrosActivity.class));
    }
}
