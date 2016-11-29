package info.expensemanager.expfile.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

public class Barchart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        BarChart barChart = (BarChart) findViewById(R.id.chart);


        Intent inte=getIntent();
        String TYPE,MONTH,DATE,YEAR;
        Cursor cursor=null;
       final QueryClass qc=new QueryClass(getBaseContext());
        ArrayList<BarEntry> income_group = new ArrayList<BarEntry>();
        ArrayList<BarEntry> exp_group = new ArrayList<BarEntry>();
        ArrayList<String> labelsgraph = new ArrayList<String>();



        TYPE=inte.getStringExtra("TYPE");
        if(TYPE.equals("MONTH_REPORT")) {
            MONTH = inte.getStringExtra("MONTH");

            int Monthint = 0;
            int maxdate=0;
            switch (MONTH) {
                case "January":
                    Monthint = 1;
                    maxdate=31;
                    break;
                case "February":
                    Monthint = 2;
                    maxdate=28;
                    break;
                case "March":
                    Monthint = 3;
                    maxdate=31;
                    break;
                case "April":
                    Monthint = 4;
                    maxdate=30;
                    break;
                case "May":
                    Monthint = 5;
                    maxdate=31;
                    break;
                case "June":
                    Monthint = 6;
                    maxdate=30;
                    break;
                case "July":
                    Monthint = 7;
                    maxdate=31;
                    break;
                case "August":
                    Monthint = 8;
                    maxdate=31;
                    break;
                case "September":
                    Monthint = 9;
                    maxdate=30;
                    break;
                case "October":
                    Monthint = 10;
                    maxdate=31;
                    break;
                case "November":
                    Monthint = 11;
                    maxdate=30;
                    break;
                case "December":
                    Monthint = 12;
                    maxdate=31;
                    break;
            }
            YEAR= inte.getStringExtra("YEAR");


            double tot_income=0;
            double tot_exp=0;
            float qqq=77;
            String datestr="";
            for(int i=1;i<=maxdate;i++)
            {

 QueryClass q1=new QueryClass(getBaseContext());
                datestr=YEAR+"-"+Monthint+"-"+i;
       try {
           datestr = q1.formatDate(datestr, "yyyy-M-d", "yyyy-MM-dd");
       }
       catch (Exception ex){}
                 tot_income=0f;
                 tot_exp=0f;
                try
                {
                    tot_income=qc.getAmountPerPerday(datestr, 1);

                }
                catch (Exception ex){

                }
                try
                {
                    tot_exp=qc.getAmountPerPerday(datestr, 2);
                }
                catch (Exception ex){

                }
                labelsgraph.add(""+i);
                income_group.add(new BarEntry(Float.parseFloat(String.valueOf(tot_income)), i-1));
                exp_group.add(new BarEntry(Float.parseFloat(String.valueOf(tot_exp)), i-1));
            }




        }
        else if(TYPE.equals("DATE_REPORT"))
        {
            ArrayList<String> Arr_date=new ArrayList<String>();
            String fromdate=inte.getStringExtra("FROMDATE");
            String curdate=fromdate;
            String todate= inte.getStringExtra("TODATE");
            boolean fill=true;
            try{
                String datestr="";
                double tot_income=0;
                double tot_exp=0;
                int i=0;
                 while(fill)
                 {
                     Arr_date.add(curdate);
                     labelsgraph.add(curdate);
                     try {
                         datestr = qc.formatDate(curdate, "dd-MM-yyyy", "yyyy-MM-dd");
                     }
                     catch (Exception ex){}


                     tot_income=0f;
                     tot_exp=0f;
                     try
                     {
                         tot_income=qc.getAmountPerPerday(datestr, 1);

                     }
                     catch (Exception ex){

                     }
                     try
                     {
                         tot_exp=qc.getAmountPerPerday(datestr, 2);
                     }
                     catch (Exception ex){

                     }

                     income_group.add(new BarEntry(Float.parseFloat(String.valueOf(tot_income)), i));
                     exp_group.add(new BarEntry(Float.parseFloat(String.valueOf(tot_exp)), i));

                     if(curdate.equals(todate))
                     {
                         fill=false;
                     }
                     else
                     {
                         curdate=qc.getNextDate(curdate);
                     }
                     i++;
                 }
            }
            catch (Exception ex){

            }

        }
      else
        {

            labelsgraph.add("January");
            labelsgraph.add("February");
            labelsgraph.add("March");
            labelsgraph.add("April");
            labelsgraph.add("May");
            labelsgraph.add("June");
            labelsgraph.add("July");
            labelsgraph.add("Aug");
            labelsgraph.add("Sep");
            labelsgraph.add("OCt");
            labelsgraph.add("Nov");
            labelsgraph.add("Dec");
           double tot_income;
            double tot_exp;
            for(int i=1;i<=12;i++){

                int year=Integer.parseInt(inte.getStringExtra("YEAR"));
                tot_income=0f;
                tot_exp=0f;
                try
                {
                    tot_income=qc.getAmountPerPerMonth(year, i, 1);

                }
                catch (Exception ex){

                }
                try
                {
                    tot_exp=qc.getAmountPerPerMonth(year, i, 2);
                }
                catch (Exception ex){

                }
                income_group.add(new BarEntry(Float.parseFloat(String.valueOf(tot_income)), i-1));
                exp_group.add(new BarEntry(Float.parseFloat(String.valueOf(tot_exp)), i-1));

            }
        }





     /*   ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");



        BarData data = new BarData(labels, dataset);
        barChart.setData(data); // set the data and list of lables into chart

        barChart.setDescription("Description");  // set the description
     */



        BarDataSet barDataSet1 = new BarDataSet(income_group, "Income");  // creating dataset for group1
        //barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet2 = new BarDataSet(exp_group, "Expense"); // creating dataset for group1
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<BarDataSet> dataSets = new ArrayList<>();  // combined all dataset into an arraylist
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        BarData data = new BarData(labelsgraph, dataSets); // initialize the Bardata with argument labels and dataSet
        barChart.setData(data);

        barChart.animateXY(2000,2000);
        barChart.invalidate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barchart, menu);
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
