package info.expensemanager.expfile.activity;

/**
 * Created by Rahul on 30/03/2016.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

public class CustomeListviewPayeeAdapter extends BaseAdapter {

    private ArrayList<Payee> _data;
    Context _c;

    CustomeListviewPayeeAdapter(ArrayList<Payee> data, Context c){
        _data = data;
        _c = c;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return _data.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _data.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.payeelist, null);
        }


        TextView txtpayeename = (TextView)v.findViewById(R.id.txtpayeename);
        TextView txtaddress = (TextView)v.findViewById(R.id.txtaddress);
        TextView txtmobile = (TextView)v.findViewById(R.id.txtmobile);
        TextView txtpayeeid = (TextView)v.findViewById(R.id.txtpayeeid);



        Payee msg = _data.get(position);

        txtpayeename.setText("" + msg.payeename);
        txtmobile.setText("" + msg.mobile);
        txtaddress.setText("" + msg.address);
        txtpayeeid.setText("" + msg.payid);






        return v;
    }


}










