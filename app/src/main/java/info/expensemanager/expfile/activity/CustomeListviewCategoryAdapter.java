package info.expensemanager.expfile.activity;

/**
 * Created by Rahul on 30/03/2016.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

public class CustomeListviewCategoryAdapter extends BaseAdapter {

    private ArrayList<Categorydetail> _data;
    Context _c;

    CustomeListviewCategoryAdapter(ArrayList<Categorydetail> data, Context c){
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
            v = vi.inflate(R.layout.category_list_row, null);
        }


        TextView txtcategory_name = (TextView)v.findViewById(R.id.txtcategory_name);
        TextView txtcategory_description = (TextView)v.findViewById(R.id.txtcategory_description);
        TextView txtcatid = (TextView)v.findViewById(R.id.txtcatid);
        ImageView  img =(ImageView)v.findViewById(R.id.list_image);

        Categorydetail msg = _data.get(position);

        txtcategory_name.setText("" + msg.category);
        txtcategory_description.setText("" + msg.category_desc);
        txtcatid.setText("" + msg.id);
        if(!(""+msg.icon).equals(""))
        {
            img.setImageResource(msg.icon);
        }






        return v;
    }


}










