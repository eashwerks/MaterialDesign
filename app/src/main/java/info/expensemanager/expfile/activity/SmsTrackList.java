package info.expensemanager.expfile.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

public class SmsTrackList extends AppCompatActivity {

    ListView LST;
    public SmsTrackList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sms_provider_view);
        LST=(ListView) findViewById(R.id.LIST);
        funLoadData();

/*
        LST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "pos =", Toast.LENGTH_LONG).show();
            }
        });
        */
        LST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getBaseContext(),"cate",Toast.LENGTH_SHORT).show();
            }
        });


    }


public void funLoadData()
{
  //  LST=(ListView) findViewById(R.id.LIST);
    QueryClass qc=new QueryClass(getBaseContext());
    Cursor cur=qc.getAllProvider();
    ArrayList<Smsprovideritem> ARR=new ArrayList<Smsprovideritem>();
    Smsprovideritem smsp;
    while(cur.moveToNext())
    {
     smsp= new Smsprovideritem();
        smsp.setId(Integer.parseInt(cur.getString(cur.getColumnIndex("trackid"))));
        smsp.setProvider(cur.getString(cur.getColumnIndex("trackno")));
        smsp.setNth(cur.getInt(cur.getColumnIndex("rupeenth")));

        ARR.add(smsp);

    }
    if(ARR.size()!=0) {
        LST.setAdapter(new CustomeListviewSMSproviderAdapter(ARR, getBaseContext()));


    }
    else
    {
        Toast.makeText(getBaseContext(),"No Sms tracker number found",Toast.LENGTH_LONG).show();
    }

}

}
