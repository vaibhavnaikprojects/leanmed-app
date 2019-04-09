package edu.uta.leanmed.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.uta.leanmed.pojo.User;
import edu.uta.leanmed.services.RetrofitService;
import edu.uta.leanmed.services.SharedPreferenceService;
import edu.uta.leanmed.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{
    private EditText mEmailView,mPasswordView;
    private View mProgressView;
    private UserService service;
    private TextView mForgot,mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        if(SharedPreferenceService.getUserName().length() != 0){
            User user = SharedPreferenceService.getSavedObjectFromPreference(LoginActivity.this, SharedPreferenceService.getUserName());
            nextActivity(user);
            finish();
        }
        else {
            mEmailView = findViewById(R.id.email);
            mPasswordView = findViewById(R.id.password);
            mForgot=findViewById(R.id.forgotPass);
            mRegister=findViewById(R.id.register);
            mRegister.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            mForgot.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(LoginActivity.this,ForgotActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
            service = RetrofitService.newInstance().create(UserService.class);
            mEmailSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user =new User();
                    user.setUserName("Vaibhav Naik");
                    user.setType(2);
                    user.setLanguagePref(1);
                    SharedPreferenceService.setLocale(getBaseContext(), user.getLanguagePref()==1?"en":"es");
                    SharedPreferenceService.loadLocale(getBaseContext());
                    nextActivity(user);
                    //attemptLogin();
                }
            });
            mProgressView = findViewById(R.id.login_progress);
        }
    }
    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            Call<User> userCall = service.getUser(email,password);
            userCall.enqueue(new Callback<User>(){
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    showProgress(false);
                    User user =response.body();
                    if(user !=null) {
                        if(user.getUserStatus()==1) {
                            SharedPreferenceService.saveObjectToSharedPreference(LoginActivity.this, user.getEmailId(), user);
                            nextActivity(user);
                            finish();
                        }
                        else{
                            showProgress(false);
                            mPasswordView.setError(getString(R.string.inactive_user));
                            mPasswordView.requestFocus();
                        }
                    }
                    else{
                        showProgress(false);
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                        mPasswordView.requestFocus();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    showProgress(false);
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                }
            });
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 6;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void nextActivity(User user){
        Intent intent=null;
        if(user.getType()==2)   intent=new Intent(LoginActivity.this,RecdonActivity.class);
        else if(user.getType()==1) intent=new Intent(LoginActivity.this,GetdonActivity.class);
        else intent=new Intent(LoginActivity.this,AdminActivity.class);
        intent.putExtra("user", user);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

