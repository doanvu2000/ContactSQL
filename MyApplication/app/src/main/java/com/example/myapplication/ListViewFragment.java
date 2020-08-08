package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.ListviewFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {
    ListviewFragmentBinding binding;
    List<Contact> contacts;

    public static ListViewFragment newInstance() {

        Bundle args = new Bundle();

        ListViewFragment fragment = new ListViewFragment();
        fragment.setArguments( args );
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate( inflater, R.layout.listview_fragment, container, false );
        contacts = new ArrayList<>(  );
        SQLHelper sqlHelper = new SQLHelper( getContext() );
        contacts = sqlHelper.gettAllContact();
        ContactAdapter contactAdapter = new ContactAdapter( contacts, getContext() );
        binding.lvContact.setAdapter( contactAdapter );
        binding.lvContact.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//chuyển sang fragment profile
                Toast.makeText( getContext(), "name: " +
                        contacts.get( position ).getName() + " \n" +
                        "phone: " + contacts.get( position ).getPhoneNumber(), Toast.LENGTH_LONG ).show();
                Fragment fragment = ProfileFragment.newInstance(contacts.get( position ));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.fragmenLayout, fragment ).commit();
            }
        } );
        return binding.getRoot();
    }
}
