package edu.uta.leanmed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uta.leanmed.pojo.UserPojo;
import edu.uta.leanmed.services.SharedPreferenceService;

public class InfoFragment extends Fragment {
    private CardView mLangPref,mContact,mPassword, mInfo, mLogout;
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
        mLangPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
