package info.expensemanager.expfile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.expensemanager.expfile.R;

public class UpdateTransaction extends AppCompatActivity {
    EditText txtdate,txtamount,txtcheckno,txtreason,txtdetails;
    Button button2;
    ImageView btncalendar;
    RadioButton rb_expense,rb_income;
     int mYear, mMonth, mDay, mHour, mMinute;
    TextView txtbillno,txtbilllabel;
    private DatePicker datePicker;
    private Calendar calendar;
    private Toolbar mToolbar;
    private int year, month, day;
    Spinner sp_category,sp_payee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaction);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Trans");

      QueryClass qc=new QueryClass(getBaseContext());

        txtdate=(EditText) findViewById(R.id.txtdate);
        txtbillno=(TextView) findViewById(R.id.txtbillno);
        btncalendar=(ImageView)findViewById(R.id.btncalendar);
        DatabaseHelper dh=new DatabaseHelper(this);

        sp_category=(Spinner) findViewById(R.id.sp_category);
        sp_payee=(Spinner) findViewById(R.id.sp_payee);
        txtamount=(EditText) findViewById(R.id.txtamount);
        txtreason=(EditText) findViewById(R.id.txtreason);
        txtcheckno=(EditText) findViewById(R.id.txtcheckno);
        txtbilllabel=(TextView) findViewById(R.id.txtbilllabel);


        LoadData();
        button2=(Button)findViewById(R.id.button2);
        rb_expense=(RadioButton)findViewById(R.id.rd_expense);
        rb_income=(RadioButton)findViewById(R.id.rd_income);
        load_spinner_payee();
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        /**/
        rb_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rb_income.setChecked(false);
                rb_expense.setChecked(true);
            }
        });
        rb_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_income.setChecked(true);
                rb_expense.setChecked(false);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Process to get Current Date
                QueryClass qc = new QueryClass(getBaseContext());
                String date = txtdate.getText().toString();
                String amount = txtamount.getText().toString();
                int payee = ((SpinnerObject) sp_payee.getSelectedItem()).getId();
                String refcheckno = txtcheckno.getText().toString();
                String details = txtreason.getText().toString();
                String transid=txtbillno.getText().toString();
                int typeoftrans = 1;
                if (rb_income.isChecked()) {
                    typeoftrans = 1;
                } else {
                    typeoftrans = 2;
                }
                int category = ((Categorydetail) sp_category.getSelectedItem()).getId();
                int status = 1;
                int entrytype = 1;
                int refsmsid = 0;

                qc.update_transaction(
                        typeoftrans, date, amount, payee, refcheckno, details, category, status, entrytype, refsmsid, Integer.parseInt(transid));
                int dddd = qc.getCountTrans();
                Toast.makeText(getBaseContext(), "Count =" + dddd, Toast.LENGTH_LONG).show();
                //(String date,String amount,int payee,String refcheckno,String details,
                // int category,int status,int entrytype,int refsmsid)


            }
        });


        btncalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);


            }
        });



   /*
  txtdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {

                    // Process to get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    // Launch Date Picker Dialog
                    DatePickerDialog dpd = new DatePickerDialog(getBaseContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // Display Selected date in textbox
                                    txtdate.setText(dayOfMonth + "-"
                                            + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    dpd.show();

                }
            }
        });




*/
        Intent getintent =getIntent();
        String id=getintent.getStringExtra("id");

        try
        {
          Cursor cursor= qc.getTransactionByid(Integer.parseInt(id));
            String billno="";
            int category_id=0;
            int type=0;
            int payee=0;
            String date="";
            String reason="";
            double amount =0;
            String checkno="";
            String payeename="";
cursor.moveToFirst();
            billno=cursor.getString(cursor.getColumnIndex("transid"));
            type=cursor.getInt(cursor.getColumnIndex("transtype"));
            payee=cursor.getInt(cursor.getColumnIndex("payee"));
            payeename=cursor.getString(cursor.getColumnIndex("payeename"));
            date=cursor.getString(cursor.getColumnIndex("transdate"));
            reason=cursor.getString(cursor.getColumnIndex("details"));
            amount=cursor.getDouble(cursor.getColumnIndex("amount"));
            checkno=cursor.getString(cursor.getColumnIndex("reference_checkno"));
            category_id=cursor.getInt(cursor.getColumnIndex("category"));
  cursor.close();
            date=qc.formatDate(date,"yyyy-MM-dd","dd-MM-yyyy");

            Toast.makeText(getBaseContext(),""+payee,Toast.LENGTH_SHORT).show();
            txtamount.setText("" + amount);
            txtcheckno.setText(""+checkno);
            txtreason.setText("" + reason);
            txtdate.setText("" + date);
            txtbillno.setText(""+id);
            txtbilllabel.setText("Bill No : "+id);
  try{

     sp_category.setSelection(getIndexOfCategory(sp_category, category_id));
      sp_payee.setSelection(getIndexOfPayee(sp_payee, payee));
     // sp_payee.setSelection(payee);
  }
  catch (Exception ex){

  }

            if(type==2){
                rb_income.setChecked(false);
                rb_expense.setChecked(true);
            }
            else
            {
                rb_income.setChecked(true);
                rb_expense.setChecked(false);
            }

        }
        catch (Exception ex){

        }

    }
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }
    private int getIndexOfCategory(Spinner spinner, int idd)
    {
        int index = 0;
//  int payee = ((SpinnerObject) sp_payee.getSelectedItem()).getId();
        int id=0;
try {
    for (int i = 0; i < spinner.getCount(); i++) {
        id = ((Categorydetail) spinner.getItemAtPosition(i)).getId();
        if (id == idd) {
            index = i;
            break;
        }
    }
}
catch (Exception ex){

    Toast.makeText(getBaseContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
}
        return index;
    }
    private int getIndexOfPayee(Spinner spinner, int idd)
    {
        int index = 0;
//  int payee = ((SpinnerObject) sp_payee.getSelectedItem()).getId();
        int id=0;
        try {
            for (int i = 0; i < spinner.getCount(); i++) {
                id = ((SpinnerObject) spinner.getItemAtPosition(i)).getId();
                if (id == idd) {
                    index = i;
                    break;
                }
            }
        }
        catch (Exception ex){

            Toast.makeText(getBaseContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        return index;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_transaction, menu);
        return true;
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
    public void LoadData()
    {
        QueryClass qc=new QueryClass(getBaseContext());
        ArrayList<Categorydetail> arrCategory=new ArrayList<Categorydetail>();
        try
        {

            Cursor cursor = qc.getAllcategory(0);

            // startManagingCursor(cursor);
            cursor.moveToFirst();
            sp_category=(Spinner) findViewById(R.id.sp_category);
            String imgname="";

            while(cursor.moveToNext()) {
                try
                {
                    imgname = cursor.getString(3);
                }catch (Exception ex){

                }
                imgname = cursor.getString(3);
                Categorydetail cd = new Categorydetail();
                cd.setCategory(cursor.getString(1));
                cd.setCategory_desc(cursor.getString(2));
                cd.setIcon(getImageId(getBaseContext(), imgname));
                cd.setId(cursor.getInt(0));

                arrCategory.add(cd);
            }


            cursor.close();
            sp_category.setAdapter(new CustomeSpinnerCategoryAdapter(arrCategory , getBaseContext()));




        }
        catch(Exception ex)
        {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void load_spinner_payee() {


        // Spinner Drop down elements
        List<SpinnerObject> lables = getPayees();
        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(getBaseContext(),
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        sp_payee.setAdapter(dataAdapter);
    }
    public List < SpinnerObject> getPayees(){
        List < SpinnerObject > labels = new ArrayList < SpinnerObject > ();
        // Select All Query
        QueryClass qc=new QueryClass(getBaseContext());
        Cursor cursor=qc.getAllPayee(0);
        int eid=0;
        String ename="--No Payee--";
        // labels.clear();
        // labels.add(new SpinnerObject(eid,ename));
        //  cursor.moveToFirst();
        while(cursor.moveToNext()) {
            try
            {
                eid= Integer.parseInt(cursor.getString(0));
                ename= String.valueOf(cursor.getString(1));

                labels.add ( new SpinnerObject ( eid ,ename) );

            }catch (Exception ex){

            }

        }
        cursor.close();


        return labels;
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
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        txtdate.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));


    }

}
