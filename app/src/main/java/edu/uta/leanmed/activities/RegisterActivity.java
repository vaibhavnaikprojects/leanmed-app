package edu.uta.leanmed.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.uta.leanmed.pojo.User;
import edu.uta.leanmed.pojo.Zone;
import edu.uta.leanmed.services.RetrofitService;
import edu.uta.leanmed.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{
    private EditText mName, mContact, mEmail, mPassword, mConfirmPass;
    private RadioGroup mType,mZone;
    private RadioButton rType;
    private Button mBtnZone,mBtnRegister;
    private View mProgressView,mView;
    private UserService service;
    private TextView login;
    private List<String> zoneList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        mName=findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mContact=findViewById(R.id.contact);
        mPassword = findViewById(R.id.password);
        mConfirmPass=findViewById(R.id.confirmPassword);
        mType=findViewById(R.id.type);
        mBtnZone=findViewById(R.id.selectZone);
        mBtnRegister = findViewById(R.id.btnRegister);
        service = RetrofitService.newInstance().create(UserService.class);
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(RegisterActivity.this);
        mView=getLayoutInflater().inflate(R.layout.dailog_zone, null);
        ImageView close=mView.findViewById(R.id.dailog_close);
        Button selectZone=mView.findViewById(R.id.buttonSelect);
        mZone=mView.findViewById(R.id.add_zone);
        mBuilder.setView(mView);
        final AlertDialog dailog=mBuilder.create();
        mType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id=radioGroup.getCheckedRadioButtonId();
                rType=findViewById(id);
                if(rType.getText().toString().equalsIgnoreCase(getString(R.string.prompt_recDon))){
                    mBtnZone.setVisibility(View.VISIBLE);
                }
                else{
                    mBtnZone.setText(getString(R.string.select_zone));
                    mBtnZone.setVisibility(View.GONE);
                }
            }
        });
        mBtnZone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dailog.show();
            }
        });
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dailog.dismiss();
            }
        });
        mBtnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        List<String> zones=getZones();
        for(int i=0;i<zones.size();i++){
            RadioButton button  = new RadioButton(dailog.getContext());
            button.setText(zones.get(i));
            button.setId(123123+i);
            mZone.addView(button);
        }

        selectZone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int c=mZone.getCheckedRadioButtonId();
                RadioButton radioButton=mView.findViewById(c);
                mBtnZone.setText("Zone: "+radioButton.getText().toString());
                dailog.dismiss();
            }
        });
        mProgressView = findViewById(R.id.login_progress);
        login=findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void attemptLogin() {
        mName.setError(null);
        mEmail.setError(null);
        mContact.setError(null);
        mPassword.setError(null);
        mConfirmPass.setError(null);
        String name=mName.getText().toString();
        String email = mEmail.getText().toString();
        String contact=mContact.getText().toString();
        String password = mPassword.getText().toString();
        String confirmPass=mConfirmPass.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        } else if(!TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPass) && !password.equalsIgnoreCase(confirmPass)) {
            mConfirmPass.setError(getString(R.string.error_invalid_confirm_password));
            focusView = mConfirmPass;
            cancel = true;
        } else if(TextUtils.isEmpty(confirmPass)){
            mConfirmPass.setError(getString(R.string.error_field_required));
            focusView = mConfirmPass;
            cancel = true;
        }else if(TextUtils.isEmpty(password)){
            mPassword.setError(getString(R.string.error_field_required));
            focusView = mPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(contact)) {
            mContact.setError(getString(R.string.error_field_required));
            focusView = mContact;
            cancel = true;
        } else if (!isContactValid(contact)) {
            mContact.setError(getString(R.string.error_invalid_contact));
            focusView = mContact;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmail.setError(getString(R.string.error_field_required));
            focusView = mEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEmail;
            cancel = true;
        }
        if(TextUtils.isEmpty(name)){
            mName.setError(getString(R.string.error_field_required));
            focusView=mName;
            cancel=true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            User user =new User(name,email,contact,password,rType.getText().equals(getString(R.string.prompt_getDon))?1:2);
            if(user.getType()==2){
                int c=mZone.getCheckedRadioButtonId();
                RadioButton radioButton=mView.findViewById(c);
                user.setZone(new Zone(radioButton.getText().toString()));
                mBtnZone.setText("Zone: "+radioButton.getText().toString());
            }
            user.setStatus(2);
            Call<Boolean> userCall = service.addUser(user);
            userCall.enqueue(new Callback<Boolean>(){
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    showProgress(false);
                    Boolean status=response.body();
                    if(status)
                        Toast.makeText(RegisterActivity.this,getString(R.string.register_status),Toast.LENGTH_LONG);
                    else
                        Toast.makeText(RegisterActivity.this,getString(R.string.register_failed_status),Toast.LENGTH_LONG);

                }
                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    showProgress(false);
                    Toast.makeText(RegisterActivity.this,getString(R.string.register_failed_status),Toast.LENGTH_LONG);
                }
            });
        }
    }

    private List<String> getZones(){
        List<String> zones=new ArrayList<>();
        zones.add("LEAN BARCELONA 1");
        zones.add("LEAN ANZOÁTEGUI 1");
        zones.add("LEAN ARAGUA 1");
        zones.add("LEAN ARAGUA 2");
        zones.add("LEAN VALENCIA 1");
        zones.add("LEAN CARABOBO 1");
        zones.add("LEAN CARABOBO 2");
        zones.add("LEAN CARACAS 1");
        zones.add("LEAN CARACAS 2");
        zones.add("LEAN CARACAS 3");
        zones.add("LEAN CARACAS 4");
        zones.add("LEAN CARACAS 5");
        zones.add("LEAN CARACAS 7");
        zones.add("LEAN CARACAS 9");
        zones.add("LEAN CARACAS 11");
        zones.add("LEAN LARA 1");
        return zones;
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 6;
    }
    private boolean isContactValid(String contact) {
        return contact.length()>9 && contact.length() < 14;
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
}

