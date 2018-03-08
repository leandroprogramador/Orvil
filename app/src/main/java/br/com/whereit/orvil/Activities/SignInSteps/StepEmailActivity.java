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

public class StepEmailActivity extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatEditText editEmail;
    TextInputLayout inputEmailLayout;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_email);
        toolbar = findViewById(R.id.toolbar);
        editEmail = findViewById(R.id.input_email);
        inputEmailLayout = findViewById(R.id.input_layout_email);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editEmail.addTextChangedListener(new MyTextWatcher(editEmail));
    }

    private boolean validEmail(){
        if(!ValidationHelper.validSize(editEmail.getText().toString(), 1)){
            inputEmailLayout.setError(getString(R.string.erro_email_size));
            requestFocus(editEmail);
            return false;
        }
        else if(!ValidationHelper.validEmail(editEmail.getText().toString())){
            inputEmailLayout.setError(getString(R.string.erro_email_valid));
            requestFocus(editEmail);
            return false;
        } else{
            inputEmailLayout.setErrorEnabled(false);
            return true;
        }
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    public void nextStep(View view) {
        if(validEmail()) {
            startActivity(new Intent(this, StepBirthActivity.class));
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
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
                case R.id.input_email:
                    validEmail();
                    break;
            }
        }
    }
}
