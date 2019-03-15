package edu.uta.leanmed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.uta.leanmed.pojo.UserPojo;
import edu.uta.leanmed.services.SharedPreferenceService;

public class InfoFragment extends Fragment {
    private CardView mLangPref,mContact,mPassword, mInfo, mLogout;
    private View mLanguageDailog, mContactDailog, mPasswordDailog;
    private TextView mName,mType;
    public InfoFragment() {
    }
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_info, container, false);
        mLangPref=view.findViewById(R.id.langPref);
        mContact=view.findViewById(R.id.contact);
        mPassword=view.findViewById(R.id.password);
        mInfo=view.findViewById(R.id.info);
        mLogout=view.findViewById(R.id.logout);
        mName=view.findViewById(R.id.name);
        mType=view.findViewById(R.id.description);
        UserPojo userPojo=(UserPojo) getActivity().getIntent().getSerializableExtra("user");
        mName.setText(userPojo.getName());
        switch (userPojo.getType()){
            case 1:
                mType.setText("GetDon");
                break;
            case 2:
                mType.setText("RecDon");
                break;
            case 3:
                mType.setText("Admin");
                break;
        }
        AlertDialog.Builder mLangPrefBuilder=new AlertDialog.Builder(getActivity());
        mLanguageDailog=getLayoutInflater().inflate(R.layout.dailog_language_pref, null);
        RadioButton engLangPref=mLanguageDailog.findViewById(R.id.langEng);
        RadioButton spanishLangPref=mLanguageDailog.findViewById(R.id.langSpanish);
        if(userPojo.getLanguagePref()==1)   engLangPref.setChecked(true);
        else    spanishLangPref.setChecked(true);
        ImageView cancelLangDailog=mLanguageDailog.findViewById(R.id.dailog_close);
                mLangPrefBuilder.setView(mLanguageDailog);
        final AlertDialog languageDailog=mLangPrefBuilder.create();
        cancelLangDailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageDailog.dismiss();
            }
        });
        Button selectPref=mLanguageDailog.findViewById(R.id.buttonSelect);
        selectPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageDailog.dismiss();
            }
        });
        AlertDialog.Builder mPassBuilder=new AlertDialog.Builder(getActivity());
        mPasswordDailog=getLayoutInflater().inflate(R.layout.dailog_password, null);
        mPassBuilder.setView(mPasswordDailog);
        final AlertDialog passwordDailog=mPassBuilder.create();
        AlertDialog.Builder mContactBuilder=new AlertDialog.Builder(getActivity() );
        mContactDailog=getLayoutInflater().inflate(R.layout.dailog_language_pref, null);
        mContactBuilder.setView(mContactDailog);
        final AlertDialog contactDailog=mContactBuilder.create();

        mLangPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageDailog.show();
            }
        });
        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactDailog.show();
            }
        });
        mPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordDailog.show();
            }
        });
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(),LoginActivity.class);
                SharedPreferenceService.removePreferences(getActivity().getApplicationContext());
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
