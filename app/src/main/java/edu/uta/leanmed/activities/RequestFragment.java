package edu.uta.leanmed.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.uta.leanmed.adapters.CartAdapter;
import edu.uta.leanmed.adapters.RequestAdapter;
import edu.uta.leanmed.pojo.CartItem;
import edu.uta.leanmed.pojo.Request;
import edu.uta.leanmed.pojo.User;
import edu.uta.leanmed.services.MedicineAPIService;
import edu.uta.leanmed.services.SharedPreferenceService;

public class RequestFragment extends Fragment {
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private RequestAdapter requestAdapter;
    private List<Request> requests;
    private MedicineAPIService service;
    private User user;
    public RequestFragment() {
    }

    public static RequestFragment newInstance() {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_request, container, false);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage(getString(R.string.please_wait_dailog));
        pDialog.setCancelable(false);
        setView(view);
        return view;
    }

    public void setView(final View view) {
        showpDialog();
        recyclerView=view.findViewById(R.id.request_card_view);
        StaggeredGridLayoutManager mStaggeredLayoutManager=new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        requestAdapter= new RequestAdapter(getContext(), requests);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(requestAdapter);
        requestAdapter.setOnItemClickListener(onItemClickListener);
        hidepDialog();
    }

    RequestAdapter.OnItemClickListener onItemClickListener=new RequestAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {
            Intent intent= new Intent(getContext(),RequestDetailActivity.class);
            intent.putExtra("medicineInventory",requests.get(position));
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    };
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
