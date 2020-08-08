package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.AddcontactlayoutBinding;

public class AddContactFragment extends Fragment {
    AddcontactlayoutBinding binding;
    public static AddContactFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AddContactFragment fragment = new AddContactFragment();
        fragment.setArguments( args );
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.addcontactlayout,container,false);
        binding.btnBackToListConTact.setOnClickListener( new View.OnClickListener() {// trở về fragment listview
            @Override
            public void onClick(View v) {
                Fragment listContactFragment = ListViewFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.fragmenLayout,listContactFragment ).commit();
            }
        } );
        binding.btnSave.setOnClickListener( new View.OnClickListener() { // lấy dữ liệu rồi add vào list sau đó lưu vào sqlite
            @Override
            public void onClick(View v) {
                String name = binding.etName.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                Contact new_Contact = new Contact( R.drawable.profile,name,phoneNumber );
                SQLHelper sqlHelper = new SQLHelper( getContext() );
                sqlHelper.insertContact( new_Contact );
                // truyền sang listview
                Fragment listContactFragment = ListViewFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.fragmenLayout,listContactFragment ).commit();
            }
        } );
        return binding.getRoot();
    }
}
