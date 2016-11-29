package info.expensemanager.expfile.activity;

/**
 * Created by Rahul on 30/03/2016.
 */


        import java.util.ArrayList;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import info.expensemanager.expfile.R;
        import info.expensemanager.expfile.adapter.MessageDetails;

public class CustomeAdapter extends BaseAdapter {

    private ArrayList<MessageDetails> _data;
    Context _c;

    CustomeAdapter (ArrayList<MessageDetails> data, Context c){
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
            v = vi.inflate(R.layout.item_row, null);
        }


        TextView txtamount = (TextView)v.findViewById(R.id.txtamount);
        TextView txtreason = (TextView)v.findViewById(R.id.txtreason);

        MessageDetails msg = _data.get(position);

        txtamount.setText(""+msg.amount);
        txtreason.setText(""+ msg.reason);




        return v;
    }


}










