package br.com.whereit.orvil.Activities.SignInSteps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import br.com.whereit.orvil.Helper.ValidationHelper;
import br.com.whereit.orvil.R;

public class StepPassActivity extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatEditText editPass, editConfPass;
    TextInputLayout inputPassLayout, inputPassConfLayout;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_pass);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editPass = findViewById(R.id.input_senha);
        editConfPass = findViewById(R.id.input_conf_senha);
        inputPassLayout = findViewById(R.id.input_layout_senha);
        inputPassConfLayout = findViewById(R.id.input_layout_conf_senha);

        editPass.addTextChangedListener(new MyTextWatcher(editPass));
        editConfPass.addTextChangedListener(new MyTextWatcher(editConfPass));
    }

    private boolean validPass(){
        String pass = editPass.getText().toString();
        if(!ValidationHelper.validSize(pass, 8)){
            inputPassLayout.setError(getString(R.string.erro_senha_size));
            requestFocus(editPass);
            return false;
        } else if(!ValidationHelper.validPass(pass)){
            inputPassLayout.setError(getString(R.string.erro_senha));
            requestFocus(editPass);
            return false;
        } else{
            inputPassLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validConfPass(){
        String pass = editPass.getText().toString();
        String conf_pass = editConfPass.getText().toString();
        if(!ValidationHelper.validConfPass(pass,conf_pass)){
            inputPassConfLayout.setError(getString(R.string.erro_conf_senha));
            requestFocus(editConfPass);
            return false;
        } else{
            inputPassConfLayout.setErrorEnabled(false);
            return true;
        }
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void nextStep(View view) {
        if(validPass() && validConfPass() ) {
            startActivity(new Intent(this, StepPhotoActivity.class));
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
        return super.onSupportNavigateUp();
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;
        public MyTextWatcher(View view) {
            this.view = view;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.input_senha:
                    validPass();
                    break;
                case R.id.input_conf_senha:
                    validConfPass();
                    break;
            }
        }
    }
}
