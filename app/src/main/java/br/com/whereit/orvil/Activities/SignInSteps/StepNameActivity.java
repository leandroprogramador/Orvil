package br.com.whereit.orvil.Activities.SignInSteps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import br.com.whereit.orvil.Helper.ValidationHelper;
import br.com.whereit.orvil.R;

public class StepNameActivity extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatEditText editName;
    TextInputLayout inputNameLayout;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_name);
        toolbar = findViewById(R.id.toolbar);
        editName = findViewById(R.id.input_name);
        inputNameLayout = findViewById(R.id.input_layout_name);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName.addTextChangedListener(new MyTextWatcher(editName));
    }

    private boolean validName(){
        if(!ValidationHelper.validSize(editName.getText().toString(),3)){
           inputNameLayout.setError(getString(R.string.erro_name));
           requestFocus(editName);
           return false;
        } else{
            inputNameLayout.setErrorEnabled(false);
            return true;
        }
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void nextStep(View view) {
        if(validName()) {
            startActivity(new Intent(this, StepEmailActivity.class));
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    private class MyTextWatcher implements TextWatcher{

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
                case R.id.input_name:
                    validName();
                    break;
            }
        }
    }
}
