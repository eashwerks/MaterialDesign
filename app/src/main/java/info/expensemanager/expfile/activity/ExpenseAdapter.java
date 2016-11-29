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

public class ExpenseAdapter extends BaseAdapter {

    private ArrayList<Expense_details> _data;
    Context _c;

    ExpenseAdapter(ArrayList<Expense_details> data, Context c){
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
            v = vi.inflate(R.layout.expense_sheet_row, null);
        }


        TextView txtamount = (TextView)v.findViewById(R.id.txtamountview);
        TextView txtcategory = (TextView)v.findViewById(R.id.txtcategory_view);
        TextView txtno = (TextView)v.findViewById(R.id.txtno);
        TextView txtdetail = (TextView)v.findViewById(R.id.txtdescview);
        TextView txtdate = (TextView)v.findViewById(R.id.txtdateview);


        Expense_details msg = _data.get(position);

        txtamount.setText("Rs " + String.format("%.2f",msg.amount));
        txtcategory.setText("Category :" + msg.category);
        txtno.setText(" No : " + msg.trans_id+" ");
        txtdate.setText("Date : "+msg.datelabel);
        if(!msg.detail.equals("")) {
            txtdetail.setText("Details :" + msg.detail);
        }






        return v;
    }


}










