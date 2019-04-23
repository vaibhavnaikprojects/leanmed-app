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
import edu.uta.leanmed.adapters.MedicineAdapter;
import edu.uta.leanmed.pojo.CartItem;
import edu.uta.leanmed.pojo.InventoryResponse;
import edu.uta.leanmed.pojo.User;
import edu.uta.leanmed.services.MedicineAPIService;
import edu.uta.leanmed.services.SharedPreferenceService;
import retrofit2.Call;

public class CartFragment extends Fragment {
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private MedicineAPIService service;
    private User user;
    public CartFragment() {
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        user = SharedPreferenceService.getSavedObjectFromPreference(getContext(),SharedPreferenceService.getUserName());
        cartItems=SharedPreferenceService.getCart(getContext());
        setView(view);
        return view;
    }

    public void setView(final View view) {
        showpDialog();
        recyclerView=view.findViewById(R.id.cart_card_view);
        StaggeredGridLayoutManager mStaggeredLayoutManager=new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        cartAdapter= new CartAdapter(getContext(), cartItems);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.setOnItemClickListener(onItemClickListener);
        hidepDialog();
    }
    CartAdapter.OnItemClickListener onItemClickListener=new CartAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {
            cartItems.remove(position);
            setView(view);
            /*Intent intent= new Intent(getContext(),InventoryDetailActivity.class);
            intent.putExtra("medicineInventory",inventoryList.get(position));
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/
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
