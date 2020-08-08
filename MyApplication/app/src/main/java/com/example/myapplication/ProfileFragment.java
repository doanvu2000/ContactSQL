package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.ProfilefragmentLayoutBinding;

public class ProfileFragment extends Fragment {
    ProfilefragmentLayoutBinding binding;
    static Contact contact;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments( args );
        return fragment;
    }

    public static ProfileFragment newInstance(Contact contacts) {
        contact = contacts;
        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments( args );
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate( inflater, R.layout.profilefragment_layout, container, false );
        binding.imgAvt.setImageResource( contact.getImgAvatar() );
        binding.tvName.setText( contact.getName() );
        binding.tvPhoneNumber.setText( contact.getPhoneNumber() );
        binding.btnBackToListConTact.setOnClickListener( new View.OnClickListener() {// trở về fragment list view
            @Override
            public void onClick(View v) {
                Fragment fragment = ListViewFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.fragmenLayout,fragment).commit();
            }
        } );
        binding.btnDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLHelper sqlHelper = new SQLHelper( getContext() );
                boolean check = sqlHelper.deleteContact( contact.getName() ); // xoa contact
                // => quay ve listContact
                Fragment fragment = ListViewFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.fragmenLayout,fragment).commit();
            }
        } );
        binding.btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvName.setEnabled( true );
                binding.formEdit.setBackgroundColor( getResources().getColor( R.color.grey ) );
                binding.tvPhoneNumber.setEnabled( true );
                binding.btnSave.setVisibility( View.VISIBLE );
                binding.btnSave.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = binding.tvName.getText().toString();
                        String phoneNumber = binding.tvPhoneNumber.getText().toString();
                        Contact updateContact = new Contact( contact.getImgAvatar(),name,phoneNumber );
                        SQLHelper sqlHelper = new SQLHelper( getContext() );
                        sqlHelper.updateContact( contact.getName(),updateContact );
                        //sửa xong quay về listFragment
                        Fragment fragment = ListViewFragment.newInstance();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace( R.id.fragmenLayout,fragment).commit();
                    }
                } );
            }
        } );
//        binding.btnCall.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent( Intent.ACTION_CALL );
//                intent.setData( Uri.parse(contact.getPhoneNumber()) );
//                startActivity( intent );
//            }
//        } );
        return binding.getRoot();
    }
}
