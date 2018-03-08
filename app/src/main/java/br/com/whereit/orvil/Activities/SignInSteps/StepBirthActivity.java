package br.com.whereit.orvil.Activities.SignInSteps;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import br.com.whereit.orvil.Helper.ValidationHelper;
import br.com.whereit.orvil.R;

public class StepBirthActivity extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatEditText editData;
    TextInputLayout inputLayoutData;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_birth);
        toolbar = findViewById(R.id.toolbar);
        editData = findViewById(R.id.input_data);
        inputLayoutData = findViewById(R.id.input_layout_data);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Calendar calendar = Calendar.getInstance();
        editData.addTextChangedListener(new MyTextWatcher(editData));
        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(StepBirthActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        editData.setText(String.format("%02d/%02d/%04d", day, month + 1, year ));

                        validDate();
                    }
                },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    public boolean validDate(){
        editData.setHint("");
        inputLayoutData.setHint("");
        if(!ValidationHelper.validDate(editData.getText().toString(),"dd/MM/yyyy")){
            inputLayoutData.setError(getString(R.string.erro_date_valid));
            return false;
        }
       else{
            inputLayoutData.setErrorEnabled(false);
            return true;
        }
    }

    @Override
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
    public void nextStep(View view){
        if(validDate()) {
            startActivity(new Intent(this, StepPassActivity.class));
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
                case R.id.input_data:
                    validDate();

                    break;
            }
        }
    }
}
