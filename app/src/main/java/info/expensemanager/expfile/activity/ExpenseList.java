package info.expensemanager.expfile.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

public class ExpenseList extends AppCompatActivity {
    ListView ExpensListview;
    private static String TAG = MainActivity.class.getSimpleName();
    TextView txttotal;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    final Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ExpensListview=(ListView) findViewById(R.id.ExpenseList);
        txttotal=(TextView) findViewById(R.id.txttotal);
        Intent  inte=getIntent();
        String TYPE,MONTH,DATE,YEAR;
        Cursor cursor=null;
        QueryClass qc=new QueryClass(getBaseContext());

        ExpensListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Intent n=new Intent(getApplication(),Popup.class);
                // startActivity(n);
                String id=(String)((TextView) view.findViewById(R.id.txtno)).getText().toString();
                Toast.makeText(getBaseContext(),id, Toast.LENGTH_SHORT).show();


                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.popup2);
                dialog.setTitle("Menu");
                LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.Linear1);
                LinearLayout dialogButton2 = (LinearLayout) dialog.findViewById(R.id.Linear2);
                LinearLayout dialogButton3 = (LinearLayout) dialog.findViewById(R.id.Linear3);
                LinearLayout dialogButton4 = (LinearLayout) dialog.findViewById(R.id.Linear4);
                final TextView text = (TextView) dialog.findViewById(R.id.TXTID);
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        Toast.makeText(getBaseContext(),"MY ID="+text.getText().toString(), Toast.LENGTH_SHORT).show();
                        String id=text.getText().toString();
                        String [] arr=id.split(":");
                        id=arr[1].trim();
                        Intent n=new Intent(getBaseContext(),UpdateTransaction.class);
                        n.putExtra("id",id);
                        startActivity(n);
                    }
                });
                dialogButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        dialog.dismiss();
                    }
                });
                dialogButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage("Are you sure,You wanted to delete the transaction");

                        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                String id = text.getText().toString();
                                String[] arr = id.split(":");
                                id = arr[1].trim();
                                QueryClass qc = new QueryClass(context);
                                qc.delete_trans(Integer.parseInt(id));
                                Toast.makeText(getBaseContext(), "Transaction Deleted", Toast.LENGTH_LONG).show();

                            }
                        });

                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                });

                // set the custom dialog components - text, image and button

                text.setText(id);

                dialog.show();






            }
        });




         TYPE=inte.getStringExtra("TYPE");
        if(TYPE.equals("MONTH_REPORT")) {
            MONTH = inte.getStringExtra("MONTH");

            int Monthint=0;
            switch (MONTH)
            {
                case "January":
                    Monthint=1;
                    break;
                case "February":
                    Monthint=2;
                    break;
                case "March":
                    Monthint=3;
                    break;
                case "April":
                    Monthint=4;
                    break;
                case "May":
                    Monthint=5;
                    break;
                case "June":
                    Monthint=6;
                    break;
                case "July":
                    Monthint=7;
                    break;
                case "August":
                    Monthint=8;
                    break;
                case "September":
                    Monthint=9;
                    break;
                case "October":
                    Monthint=10;
                    break;
                case "November":
                    Monthint=11;
                    break;
                case "December":
                    Monthint=12;
                    break;
            }


            YEAR= inte.getStringExtra("YEAR");
            try
            {
                cursor=qc.getAllExpenseMonthly(Monthint, Integer.parseInt(YEAR));
            }
            catch (Exception ex){

            }

        }
        else {
            DATE = inte.getStringExtra("DATE");
            String  DATE2 = inte.getStringExtra("TODATE");
            if(DATE2.equals("")) {
                try {
                    cursor = qc.getAllExpenseBydate(DATE);
                } catch (Exception ex) {
                    cursor.close();
                }
            }
            else
            {
                try {
                    cursor = qc.getAllExpenseByTwodate(DATE,DATE2);
                } catch (Exception ex) {
                   cursor.close();
                }
            }
        }

        try
        {

            ArrayList<Expense_details> arrExpense=new ArrayList<Expense_details>();
           //  cursor=qc.getAllExpense();
          int length_cursor=cursor.getCount();
            double total=0;
            if(length_cursor==0)
            {
                Toast.makeText(getBaseContext(),"No Records Found",Toast.LENGTH_LONG).show();
                Intent n=new Intent(getBaseContext(),Expense_Report.class);
                startActivity(n);
                finish();
            }
            else {
                while (cursor.moveToNext()) {
                   try
                   {
                       total=total+Double.parseDouble(cursor.getString(cursor.getColumnIndex("amount")));
                   }
                   catch (Exception ex){

                   }
                    //  imgname = cursor.getString(3);
                    Expense_details cd = new Expense_details();
                    cd.setTrans_id(cursor.getInt(cursor.getColumnIndex("transid")));
                    cd.setAmount(Double.parseDouble(cursor.getString(cursor.getColumnIndex("amount"))));
                    cd.setDatelabel(cursor.getString(cursor.getColumnIndex("transdate")));
                    cd.setCategory(cursor.getString(cursor.getColumnIndex("category_name")));
                    cd.setDetail(cursor.getString(cursor.getColumnIndex("details")));

                    arrExpense.add(cd);
                }

                //  ExpensListview.setAdapter();
                ExpensListview.setAdapter(new ExpenseAdapter(arrExpense, getBaseContext()));
            }

  txttotal.setText("Total Expense : "+String.format( "%.2f",total));
        }
        catch(Exception ex)
        {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == 16908332){
            onBackPressed();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
