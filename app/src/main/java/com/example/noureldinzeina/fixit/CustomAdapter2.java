package com.example.noureldinzeina.fixit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {

    ArrayList<Service> services = new ArrayList<>();
    Context context;
    public CustomAdapter2(Context context, ArrayList<Service> services){
        this.services=services;
        this.context=context;
    }
    @Override
    public int getCount() {
        return services.size();
    }

    @Override
    public Object getItem(int i) {
        return services.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_of_services,viewGroup,false);
        }

        Service tempService= (Service) getItem(i);

        TextView textView = (TextView) view.findViewById(R.id.Name);
        TextView textView2 = (TextView) view.findViewById(R.id.Rate);

        textView.setText(tempService.getName());
        textView2.setText(tempService.getHourlyRate());


        return view;
    }
}
