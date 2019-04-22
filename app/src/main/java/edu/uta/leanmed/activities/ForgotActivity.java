package edu.uta.leanmed.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.uta.leanmed.services.RetrofitService;
import edu.uta.leanmed.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotActivity extends AppCompatActivity {
    private EditText email;
    private Button btnRecover;
    private TextView returnLogin;
    private UserService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot);
        returnLogin=findViewById(R.id.login);
        btnRecover=findViewById(R.id.btn_recover);
        email=findViewById(R.id.email);
        returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForgotActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        service = RetrofitService.newInstance().create(UserService.class);
        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setError(null);
                String emailString = email.getText().toString();
                if (!TextUtils.isEmpty(emailString)){
                    email.setError(getString(R.string.error_invalid_email));
                    email.requestFocus();
                }else if (!isEmailValid(emailString)) {
                    email.setError(getString(R.string.error_invalid_email));
                    email.requestFocus();
                }
                else{
                    Call<Boolean> userCall = service.forgotPass(emailString);
                    userCall.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body())
                                Toast.makeText(ForgotActivity.this,getString(R.string.forgot_pass_message),Toast.LENGTH_LONG);
                            else{
                                email.setError(getString(R.string.error_invalid_email));
                                email.requestFocus();
                            }
                        }
                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            email.setError(getString(R.string.error_invalid_email));
                            email.requestFocus();
                        }
                    });
                }

            }
        });
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
