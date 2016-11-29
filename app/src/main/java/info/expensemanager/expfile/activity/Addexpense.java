package info.expensemanager.expfile.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.expensemanager.expfile.R;

/**
 * Created by Ravi on 29/07/15.
 */
public class Addexpense extends Fragment {
EditText txtdate,txtamount,txtcheckno,txtreason,txtdetails;
    Button button2;
    ImageView btncalendar;
    RadioButton rb_expense,rb_income;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Spinner sp_category,sp_payee;
    View rootView;
Context context;
    public Addexpense() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_addexpense, container, false);
        context=getActivity();
  txtdate=(EditText)rootView.findViewById(R.id.txtdate);
  btncalendar=(ImageView)rootView.findViewById(R.id.btncalendar);
        DatabaseHelper dh=new DatabaseHelper(getActivity());

        sp_category=(Spinner) rootView.findViewById(R.id.sp_category);
        sp_payee=(Spinner) rootView.findViewById(R.id.sp_payee);
        txtamount=(EditText) rootView.findViewById(R.id.txtamount);
        txtreason=(EditText) rootView.findViewById(R.id.txtreason);
        txtcheckno=(EditText) rootView.findViewById(R.id.txtcheckno);
       // txtdetails=(EditText) rootView.findViewById(R.id.txtdesc);
        LoadData();
        button2=(Button)rootView.findViewById(R.id.button2);
        rb_expense=(RadioButton)rootView.findViewById(R.id.rd_expense);
        rb_income=(RadioButton)rootView.findViewById(R.id.rd_income);
        load_spinner_payee();
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
            QueryClass qc=new QueryClass(getActivity());
                String date=txtdate.getText().toString();
                String amount =txtamount.getText().toString();
                int payee = ( (SpinnerObject) sp_payee.getSelectedItem () ).getId() ;
                String refcheckno=txtcheckno.getText().toString();
                String details=txtreason.getText().toString();
                int typeoftrans=1;
                if(rb_income.isChecked())
                {
                    typeoftrans=1;
                }
                else
                {
                    typeoftrans=2;
                }
                int category = ( (Categorydetail) sp_category.getSelectedItem () ).getId() ;
                int status=1;
                int entrytype=1;
                int refsmsid=0;
if(!amount.equals("")) {
    qc.insert_expense(typeoftrans, date, amount, payee, refcheckno, details, category, status, entrytype, refsmsid);
    int dddd = qc.getCountTrans();
    Toast.makeText(getActivity(), "Transaction saved", Toast.LENGTH_LONG).show();
}
                else {
    Toast.makeText(getActivity(), "Enter amount", Toast.LENGTH_LONG).show();
}


                double current_amount=0;
                double reminder_amount=0;

                current_amount=qc.getavailableAmount();
                String a="";
                try{
                     a =LoginManager.getreminderAmount(context);
                    if(a.equals("")) {
                        a = "0";
                    }
                }catch(Exception ex){
                    a="0";
                }
                reminder_amount=Double.parseDouble(a);
                if(LoginManager.getreminderStatus(context).equals("1"))
                {
                    if(current_amount<=reminder_amount)
                    {

                        Intent i = new Intent(context, Alarmpage.class);

                        i.putExtra("msg", "Money is low. ");
                        i.putExtra("title", "Reminder");
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                        //context.startService(i);

                    }
                }

                Cursor cur =qc.getWishlist();
                while(cur.moveToNext())
                {
                    double checkcuramount=0;
                    checkcuramount=Double.parseDouble(cur.getString(cur.getColumnIndex("currentamount")));

                    if(current_amount>=checkcuramount)
                    {

                        Intent i = new Intent(context, Alarmpage.class);

                        i.putExtra("msg", cur.getString(cur.getColumnIndex("wishcontent")));
                        i.putExtra("id", cur.getString(cur.getColumnIndex("wishid")));
                        i.putExtra("title", "Your wishlist");
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                }


                //(String date,String amount,int payee,String refcheckno,String details,
              // int category,int status,int entrytype,int refsmsid)

            }
        });

btncalendar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
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
});
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
                    DatePickerDialog dpd = new DatePickerDialog(getActivity(),
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


        // Inflate the layout for this fragment
        return rootView;
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
    public void LoadData()
    {
        QueryClass qc=new QueryClass(getActivity());
        ArrayList<Categorydetail> arrCategory=new ArrayList<Categorydetail>();
        try
        {

            Cursor cursor = qc.getAllcategory(0);

            // startManagingCursor(cursor);
    cursor.moveToFirst();
            sp_category=(Spinner) rootView.findViewById(R.id.sp_category);
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
                cd.setIcon(getImageId(getActivity(), imgname));
                cd.setId(cursor.getInt(0));

                arrCategory.add(cd);
            }


            cursor.close();
            sp_category.setAdapter(new CustomeSpinnerCategoryAdapter(arrCategory , getActivity()));




        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void load_spinner_payee() {


        // Spinner Drop down elements
        List<SpinnerObject> lables = getPayees();
        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(getActivity(),
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        sp_payee.setAdapter(dataAdapter);
    }
    public List < SpinnerObject> getPayees(){
        List < SpinnerObject > labels = new ArrayList < SpinnerObject > ();
        // Select All Query
        QueryClass qc=new QueryClass(getActivity());
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
