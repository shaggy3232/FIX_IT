package com.example.noureldinzeina.fixit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<Account> accounts = new ArrayList<>();
    Context context;
    public CustomAdapter(Context context, ArrayList<Account> accounts){
        this.accounts=accounts;
        this.context=context;
    }
    @Override
    public int getCount() {
        return accounts.size();
    }

    @Override
    public Object getItem(int i) {
        return accounts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_of_users,viewGroup,false);
        }

        Account tempAccount= (Account) getItem(i);
        TextView textView = (TextView) view.findViewById(R.id.ListUsername);
        TextView textView2 = (TextView) view.findViewById(R.id.Type);

        textView.setText(tempAccount.getUsername());
        textView2.setText(tempAccount.getType());


        return view;
    }
}
