package br.com.whereit.orvil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import br.com.whereit.orvil.Fragments.LivrosFragment;
import br.com.whereit.orvil.Helper.FacebookHelper;
import br.com.whereit.orvil.Helper.GoogleSignInHelper;
import br.com.whereit.orvil.Helper.SharedHelper;
import br.com.whereit.orvil.Interfaces.IFbData;
import br.com.whereit.orvil.Model.User;
import br.com.whereit.orvil.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static br.com.whereit.orvil.Helper.FacebookHelper.getUserDetailsFromFB;

public class LivrosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFbData{

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    View headerLayout;
    TextView txtUser, txtEmail;
    CircleImageView imgProfile;
    User user = new User();
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Livros");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        configureNavigation();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new LivrosFragment(), "Livros").commit();

    }

    private void configureNavigation() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        headerLayout = LayoutInflater.from(this).inflate(R.layout.nav_header_livros, navigationView);
        txtUser = headerLayout.findViewById(R.id.txt_user);
        txtEmail = headerLayout.findViewById(R.id.txt_email);
        imgProfile = headerLayout.findViewById(R.id.image_user);
        if(Profile.getCurrentProfile() != null){
            AccessToken accessToken = gson.fromJson(SharedHelper.getData(LivrosActivity.this,"accessToken"), AccessToken.class);
            getUserDetailsFromFB(accessToken, LivrosActivity.this);
        }

        if(GoogleSignIn.getLastSignedInAccount(LivrosActivity.this) != null)
        {
            User user = GoogleSignInHelper.getUserData(this);
            user.setName(user.getName());
            user.setEmail(user.getEmail());
            txtUser.setText(user.getName());
            txtEmail.setText(user.getEmail());
            if(user.getPicture() != null) {
                user.setPicture(user.getPicture());
                Picasso.with(LivrosActivity.this).load(user.getPicture()).into(imgProfile);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if(drawer==null) {
            drawer = findViewById(R.id.drawer_layout);
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.livros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        toggle.syncState();
        super.onPostCreate(savedInstanceState, persistentState);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_livros){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new LivrosFragment(), "Principal").commit();
        } else if(id == R.id.nav_sair){


            if(FacebookHelper.isLogged()){
                FacebookHelper.loggout();
                startActivity(new Intent(LivrosActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
            if(GoogleSignInHelper.isLogged(this)){
                GoogleSignInHelper.loggout(LivrosActivity.this);
                startActivity(new Intent(LivrosActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void getUserData(User myUser) {
        user = myUser;
        txtUser.setText(user.getName());
        txtEmail.setText(user.getEmail());
        Picasso.with(LivrosActivity.this).load("https://graph.facebook.com/" + user.getFacebookUserId()+ "/picture?type=large").into(imgProfile);
    }
}
