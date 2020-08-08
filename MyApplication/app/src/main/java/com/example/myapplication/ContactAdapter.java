package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    List<Contact> contacts;
    Context context;

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from( context );
        convertView = inflater.inflate( R.layout.item_layout,parent,false );
        Contact contact = contacts.get( position );
        TextView tvName = convertView.findViewById( R.id.tvName );
        TextView tvPhoneNumber = convertView.findViewById( R.id.tvPhoneNumber );
        tvName.setText( contact.getName() );
        tvPhoneNumber.setText( contact.getPhoneNumber() );
        return convertView;
    }
}
