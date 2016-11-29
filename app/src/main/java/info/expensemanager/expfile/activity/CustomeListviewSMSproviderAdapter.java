package info.expensemanager.expfile.activity;

/**
 * Created by Rahul on 30/03/2016.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

public class CustomeListviewSMSproviderAdapter extends BaseAdapter {

    private ArrayList<Smsprovideritem> _data;
    Context _c;

    CustomeListviewSMSproviderAdapter(ArrayList<Smsprovideritem> data, Context c){
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
    private class ViewHolder {
        TextView code;
        CheckBox name;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;

        ViewHolder holder = null;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.layout_smstrack_list, null);
/*
            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.txtid);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
            v.setTag(holder);

            holder.name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Smsprovideritem country = (Smsprovideritem) cb.getTag();
                    Toast.makeText(_c,
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    country.setSelected(cb.isChecked());
                }
            });
        }
        else
        {
          //  holder = (ViewHolder) v.getTag();
        }

*/
        }
        CheckBox chk = (CheckBox)v.findViewById(R.id.checkBox1);
        TextView txttype = (TextView)v.findViewById(R.id.txttype);
        TextView txtnth = (TextView)v.findViewById(R.id.txtnth);
        TextView txtid = (TextView)v.findViewById(R.id.txtid);


        Smsprovideritem msg = _data.get(position);

        txttype.setText("" + msg.typename);
        txtnth.setText("" + msg.nth);
        txtid.setText("" + msg.id);
        chk.setText(""+msg.provider);
      //  holder.name.setText(msg.getProvider());
        //holder.name.setChecked(msg.isSelected());
/*
   chk.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
       //    Smsprovideritem msg = _data.get(position);

           Toast.makeText(_c,"check box",Toast.LENGTH_SHORT).show();
       }
   });*/

        return v;
    }


}










