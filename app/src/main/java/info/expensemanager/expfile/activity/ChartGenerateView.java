package info.expensemanager.expfile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Calendar;

import info.expensemanager.expfile.R;

public class ChartGenerateView extends AppCompatActivity {

    ListView ExpensListview;
    Button btnsubmit;
    RadioButton RB1,RB2;
    Spinner SP_MONTH,SP_YEAR;
    EditText txtsearchdate,txttosearchdate;
    QueryClass qc;
    ImageView imgv,imgbtncal2;
    Button bv;
    int mYear, mMonth, mDay, mHour, mMinute;

    private DatePicker datePicker;
    private Calendar calendar;
    CardView cardView1,cardView2;
    private int year1, month1, day1;
  Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_generate_view);
 context=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Graph View");

        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.White));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.White));

        ExpensListview=(ListView) findViewById(R.id.ExpenseList);
        RB1=(RadioButton) findViewById(R.id.RADIO1);
        RB2=(RadioButton) findViewById(R.id.RADIO2);
        cardView1=(CardView)findViewById(R.id.CARDVIew2);
        cardView2=(CardView)findViewById(R.id.CARDVIew3);

        SP_MONTH=(Spinner) findViewById(R.id.sp_month);
        SP_YEAR=(Spinner) findViewById(R.id.sp_year);
        txtsearchdate=(EditText) findViewById(R.id.txtsearchdate);
        txttosearchdate=(EditText) findViewById(R.id.txttosearchdate);

        imgbtncal2=(ImageView) findViewById(R.id.btncal2);

        imgbtncal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c=Calendar.getInstance();
                int myear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);





                DatePickerDialog dpd = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                String tmpdate=  dayOfMonth + "-"   + (monthOfYear + 1) + "-" + year;
                                try
                                {
                                    String mydate=qc.formatDate(tmpdate,"d-M-yyyy","dd-MM-yyyy");
                                    txttosearchdate.setText(mydate);
                                }catch (Exception ex){

                                }




                            }
                        }, myear, mMonth, mDay);
                dpd.show();
            }
        });
        RB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RB2.isChecked())
                {
                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.GONE);
                }
                else
                {
                    cardView2.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.GONE);
                }
            }
        });
        RB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RB2.isChecked())
                {
                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.GONE);
                }
                else
                {
                    cardView2.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.GONE);
                }
            }
        });

        RB2.setChecked(true);
        cardView1.setVisibility(View.VISIBLE);
        cardView2.setVisibility(View.GONE);

        Calendar c = Calendar.getInstance();
        int currentyear = c.get(Calendar.YEAR);
        int currentmonth = c.get(Calendar.MONTH);

        SP_MONTH.setSelection(currentmonth);
        //  selectSpinnerItemByValue(SP_YEAR,currentyear);
        SP_YEAR.setSelection(getIndex(SP_YEAR, String.valueOf(currentyear)));
        btnsubmit=(Button) findViewById(R.id.btnsubmit);




        calendar = Calendar.getInstance();
        year1 = calendar.get(Calendar.YEAR);

        month1 = calendar.get(Calendar.MONTH);
        day1 = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year1, month1+1, day1);




        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String type="";
                if(RB2.isChecked())
                {
                    type="MONTH_REPORT";
                }
                else  if(RB1.isChecked())
                {
                    type="DATE_REPORT";
                }
                else
                {
                    type="YEAR_REPORT";
                }

                String spmonthval=SP_MONTH.getSelectedItem().toString();
                String spyearval=SP_YEAR.getSelectedItem().toString();
                String searchdate=txtsearchdate.getText().toString();
                String searchtodate=txttosearchdate.getText().toString();
                Intent n=new Intent(getBaseContext(),Barchart.class);
                n.putExtra("TYPE",type);
                n.putExtra("MONTH",spmonthval);
                n.putExtra("YEAR",spyearval);
                n.putExtra("DATE",searchdate);
                n.putExtra("FROMDATE",searchdate);
                n.putExtra("TODATE",searchtodate);
                startActivity(n);
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }
    @SuppressWarnings("deprecation")
    public void setDate2(View view) {
        showDialog(999);

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year1, month1, day1);
        }
        return null;
    }
    public void showDatePicker(View view)
    {




        /*

        // Process to get Current Date



        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getBaseContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox

                      String tmpdate=  dayOfMonth + "-"   + (monthOfYear + 1) + "-" + year;
                       try
                       {
                           String mydate=qc.formatDate(tmpdate,"d-M-yyyy","dd-MM-yyyy");
                           txtsearchdate.setText(mydate);
                       }catch (Exception ex){

                       }




                    }
                }, mYear, mMonth, mDay);
        dpd.show();
        */

    }

    private void showDate(int year, int month, int day) {
String tmpdate=day+"-"+month+"-"+year;
        QueryClass qc=new QueryClass(context);
        try {
            tmpdate = qc.formatDate(tmpdate, "d-M-yyyy", "dd-MM-yyyy");
        }
        catch (Exception ex){}

        txtsearchdate.setText(tmpdate);
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense__report, menu);
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
