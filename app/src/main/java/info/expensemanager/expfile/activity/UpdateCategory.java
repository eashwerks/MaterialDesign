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
import android.widget.Toast;

import info.expensemanager.expfile.R;

public class UpdateCategory extends AppCompatActivity {
    private Toolbar mToolbar;
    EditText txtcategory,txtdesc;
    TextView txtid;

    Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);

        txtcategory=(EditText) findViewById(R.id.txtcategory_name);
        txtdesc=(EditText) findViewById(R.id.txtdesc);
        txtid=(TextView) findViewById(R.id.txtid);

        btnsubmit=(Button) findViewById(R.id.btnsubmit);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //  getSupportActionBar().setDisplayShowHomeEnabled(true);
      QueryClass qc=new QueryClass(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Category");

        Intent get = getIntent();
        try
        {
            if(get.getStringExtra("id").equals(""))
            {
                Intent n=new Intent(getBaseContext(),MainActivity.class);
                n.putExtra("fType","3");
                startActivity(n);
            }
            txtid.setText(get.getStringExtra("id"));
            Cursor cur=qc.getAllcategory(Integer.parseInt(get.getStringExtra("id")));
            cur.moveToFirst();
            txtcategory.setText(cur.getString(cur.getColumnIndex("category_name")));
            txtdesc.setText(cur.getString(cur.getColumnIndex("category_desc")));
            cur.close();

        }catch (Exception ex)
        {

        }

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category_name = txtcategory.getText().toString().trim();
                String catdesc = txtdesc.getText().toString().trim();
                String id = txtid.getText().toString().trim();

                if (!category_name.equals("")) {
                    QueryClass qc = new QueryClass(getBaseContext());
                    long res = qc.update_category(category_name, catdesc, id);
                   // LoadData();
                    if(res>0) {
                        Toast.makeText(getBaseContext(), "Category Updated", Toast.LENGTH_SHORT).show();
                        txtdesc.setText("");
                        txtid.setText("");
                        txtcategory.setText("");

                        Intent n=new Intent(getBaseContext(),MainActivity.class);
                        n.putExtra("fType","3");
                        startActivity(n);

                    }

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_category, menu);
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
