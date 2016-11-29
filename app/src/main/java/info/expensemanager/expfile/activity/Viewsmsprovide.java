package info.expensemanager.expfile.activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

public class Viewsmsprovide extends AppCompatActivity {

    ListView LST;
    private Toolbar mToolbar;
     CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    Context context;
    String txtpid;
    public Viewsmsprovide() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.fragment_sms_track_list);
        LST=(ListView) findViewById(R.id.LIST);
       coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        setSupportActionBar(mToolbar);
      //  showsnack();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showsnack();
            }
        });
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SMS Numbers");
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
                Toast.makeText(getBaseContext(), "cate", Toast.LENGTH_SHORT).show();
                 txtpid=(String)((TextView) view.findViewById(R.id.txtid)).getText().toString();

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure,You wanted to delete number");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        QueryClass qc = new QueryClass(context);
                        qc.delete_number(txtpid);
                        Toast.makeText(getBaseContext(), "Payee Deleted", Toast.LENGTH_LONG).show();
                        funLoadData();

                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


    }

public void showsnack()
{
    Snackbar snackbar = Snackbar
            .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
            .setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
    // Changing message text color
    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
    View sbView = snackbar.getView();
    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(Color.YELLOW);
    snackbar.show();
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
  if(cur.getInt(cur.getColumnIndex("transtype"))==1)
  {
      smsp.setTypename("Credit");
  }
            else
  {
      smsp.setTypename("Withdrawn");
  }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       // Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();

        //Toast.makeText(getApplicationContext(), ""+R.id.home, Toast.LENGTH_SHORT).show();
        if(id == 16908332){
        onBackPressed();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Action !", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.home){
            Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.showHome){
            Toast.makeText(getApplicationContext(), "home back", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.action_search2){
            Toast.makeText(getApplicationContext(), "Search action is selected!0000000000", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
