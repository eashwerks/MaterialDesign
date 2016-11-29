package info.expensemanager.expfile.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import info.expensemanager.expfile.R;

public class Updatepayee extends AppCompatActivity {
    EditText txtpayeename,txtaddress,txtmobile;
  TextView txtid;
    private Toolbar mToolbar;
    Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepayee);
        txtpayeename=(EditText) findViewById(R.id.txtpayeename);
        txtaddress=(EditText) findViewById(R.id.txtaddress);

        txtmobile=(EditText) findViewById(R.id.txtmobile);
        txtid=(TextView) findViewById(R.id.txtid);
        btnsubmit=(Button) findViewById(R.id.btnsubmit);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
 QueryClass qc=new QueryClass(getBaseContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Payee");
        Intent get = getIntent();
        try
        {
            if(get.getStringExtra("id").equals(""))
            {
                Intent n=new Intent(getBaseContext(),MainActivity.class);
                n.putExtra("fType","10");
                startActivity(n);
            }
            txtid.setText(get.getStringExtra("id"));
            Cursor cur=qc.getAllPayee(Integer.parseInt(get.getStringExtra("id")));
            cur.moveToFirst();
            txtmobile.setText(cur.getString(cur.getColumnIndex("payeenumber")));
            txtaddress.setText(cur.getString(cur.getColumnIndex("payeeaddress")));
            txtpayeename.setText(cur.getString(cur.getColumnIndex("payeename")));
            cur.close();

        }catch (Exception ex)
        {

        }


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String payeename = txtpayeename.getText().toString().trim();
                String address = txtaddress.getText().toString().trim();
                String mobile=txtmobile.getText().toString().trim();
                String id=txtid.getText().toString().trim();
                if (!payeename.equals("")) {
                    QueryClass qc = new QueryClass(getBaseContext());
                    long res = qc.update_payee(payeename, address, mobile, id);
                    //   LoadData();
                    Intent n=new Intent(getBaseContext(),MainActivity.class);
                    n.putExtra("fType","10");
                    startActivity(n);

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_updatepayee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
