package info.expensemanager.expfile.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rahul on 02/04/2016.
 */
public class QueryClass  {

    DatabaseHelper dh;
    SQLiteDatabase db;
    Context cont;
    QueryClass(Context context)
    {
        this.cont=context;
        dh=new DatabaseHelper(context);
        db=dh.getWritableDatabase();
    }
    public long insert_category(String catname,String catdesc)
    {
        ContentValues cv=new ContentValues();
        cv.put("category_name", catname);
        cv.put("category_desc", catdesc);
         long x= db.insert("tblcategory", "category_id", cv);
        db.close();
        return  x;

    }
    public long update_category(String catname,String catdesc,String id)
    {
        ContentValues cv=new ContentValues();
        cv.put("category_name", catname);
        cv.put("category_desc", catdesc);
        long x= db.update("tblcategory", cv, "category_id=?", new String[]{id});
        db.close();
        return  x;

    }
    public long update_password(String pass)
    {
        ContentValues cv=new ContentValues();
        cv.put("admin_pwd", pass);
        cv.put("admin_name", "admin");
        String id="admin";

        long x= db.update("adminlogin", cv, "admin_name=?", new String[]{id});
        db.close();
        return  x;

    }
    public long delete_payeement(String id)
    {
        if(!id.equals("1"))
        {
            ContentValues cv=new ContentValues();
            long x= db.delete("tblpayee",  "payeeid=?",new String[] {id});
            db.close();
            return  x;
        }
        return 0;

    }
    public long delete_number(String id)
    {

            ContentValues cv=new ContentValues();
            long x= db.delete("tblsmsmanager", "trackid=?", new String[]{id});
            db.close();
            return  x;


    }
    public long insert_sms(String address,String content)
    {
        ContentValues cv=new ContentValues();
        cv.put("smscenter", address);
        cv.put("smscontent", content);
        cv.put("smsstatus", 0);
        long x= db.insert("tblsms", "smsid", cv);
        return  x;

    }
    public long insert_payee(String payeename,String payeeaddress,String number)
    {
        ContentValues cv=new ContentValues();
        cv.put("payeename", payeename);
        cv.put("payeeaddress", payeeaddress);
        cv.put("payeenumber", number);
        long x= db.insert("tblpayee", "payeeid", cv);
        return  x;

    }
    public long insert_wishlist(String desc,String amount)
    {
        ContentValues cv=new ContentValues();
        cv.put("wishcontent", desc);
        cv.put("currentamount", amount);
        cv.put("status", 1);
        long x= db.insert("tblwishlist", "wishid", cv);
        return  x;

    }
    public long update_payee(String payeename,String payeeaddress,String number,String id)
    {
        ContentValues cv=new ContentValues();
        cv.put("payeename", payeename);
        cv.put("payeeaddress", payeeaddress);
        cv.put("payeenumber", number);
        long x= db.update("tblpayee", cv, "payeeid=?", new String[]{id});
        return  x;

    }
    public String insert_sms_settings(String provider,int nthsm,int type)
    {
        String q="";
        String res="";

         q="select * from tblsmsmanager where trackno='"+provider+"' and transtype="+type+" ";


        Cursor cur= db.rawQuery(q, null);
        int cou= cur.getCount();
        if(cou==0) {

            q = "insert into tblsmsmanager (trackno,rupeenth,transtype) values ('" + provider + "'," + nthsm + "," + type + ")";
            res="New Sms provider added";
        }
        else
        {
            q="update tblsmsmanager set rupeenth="+nthsm+" where trackno='"+provider+"' and transtype="+type+" ";
            res="Provide details updated";
        }
        db.execSQL(q);
        return res;
    }
public Cursor getAllProvider()
{
    String q="select * from tblsmsmanager ";


    Cursor cur= db.rawQuery(q, null);
    return cur;


}
    public int getLogin(String pass)
    {
        String q="select count(*) from adminlogin where admin_pwd='"+pass+"' ";


        Cursor cur= db.rawQuery(q, null);
        cur.moveToFirst();
        int res =cur.getInt(0);
        cur.close();
        return  res;


    }
    public Cursor getTransactionByid(int id){
        String q="select * from tbltransaction as t inner join tblpayee as p on p.payeeid=t.payee where transid="+id+"";


        Cursor cur= db.rawQuery(q, null);
        return cur;
    }
    public Cursor getWishlist(){
        String q="select * from tblwishlist where status =1";


        Cursor cur= db.rawQuery(q, null);
        return cur;
    }
    public int delete_trans(int id)
    {
        String q="delete from tbltransaction where transid ="+id+"";
        db.execSQL(q);
        return 1;
    }
    public int delete_wish(String id)
    {
        String q="delete from tblwishlist where wishid ="+id+"";
        db.execSQL(q);
        return 1;
    }


    public int delete_category(int id)
    {
        if(id>16) {
            String q = "delete from tblcategory where category_id =" + id + " ";
            db.execSQL(q);
            return 1;
        }
        else
        {
            return 0;
        }

    }
    public double getavailableAmount()
    {
        double res;
        double totinc ;
        double totexp;
        String q="select sum(amount) from tbltransaction where transtype =1";
        String q2="select sum(amount) from tbltransaction where transtype =2";


        Cursor cur= db.rawQuery(q, null);
        cur.moveToFirst();
        totinc=cur.getDouble(0);
        cur.close();

         cur= db.rawQuery(q2, null);
        cur.moveToFirst();
        totexp=cur.getDouble(0);
        cur.close();

        res=totinc-totexp;
        return res;
    }

    public double getAmountPerPerMonth(int year,int  month,int type)
    {
        Cursor cur=null;
        try {
            String q = "select ifnull(sum(amount),0) as amount from tbltransaction  where cast(strftime('%Y',transdate) as integer) ="+year+" and cast(strftime('%m',transdate) as integer) ="+month+" and transtype="+type+"";




            cur = db.rawQuery(q, null);
            cur.moveToFirst();
            double value = 0;
            value = Double.parseDouble(cur.getString(0));
            cur.close();
            return value;
        }
        catch (Exception ex)
        {
            cur.close();
            return 0;
        }

    }
    public double getAmountPerPerday(String date,int type)
    {
        Cursor cur=null;
        try {
            String q = "select ifnull(sum(amount),0) as amount from tbltransaction where transdate='" + date + "' and transtype=" + type + "";
            cur = db.rawQuery(q, null);
            cur.moveToFirst();
            double value = 0;
            value = Double.parseDouble(cur.getString(0));
            cur.close();
            return value;
        }
        catch (Exception ex)
        {
            cur.close();
            return 0;
        }

    }
    public static String getNextDate(String curDate) {
        try {
            final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = format.parse(curDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            return format.format(calendar.getTime());
        } catch (Exception ex) {
            return "";
        }
    }
    public int insert_expense(int typeoftrans, String date,String amount,int payee,String refcheckno,String details,int category,int status,int entrytype,int refsmsid)
    {
        //typeoftrans 1 FOR INCOME 2 for Expense
        String q="";
        String d="";
        String entry_date=getDateTime();
        try {
             d=formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex)
        {

        }

        try {
            q = "insert into tbltransaction (transtype,transdate,amount,payee,reference_checkno," +
                    "details,category,entry_date,status,entrytype,refsmsid) values "
                    + " ("+typeoftrans+",'" + d + "','" + amount + "'," + payee + ",'" + refcheckno + "','" + details + "'," + category + ",'" + entry_date + "'," + status + ", "+ entrytype + "," + refsmsid + ")";

            db.execSQL(q);
        }
        catch (Exception ex){
          Toast.makeText(cont,""+ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        return  1;
    }
    public int update_transaction(int typeoftrans, String date,String amount,int payee,String refcheckno,String details,int category,int status,int entrytype,int refsmsid,int transid)
    {
        //typeoftrans 1 FOR INCOME 2 for Expense
        String q="";
        String d="";
        String entry_date=getDateTime();
        try {
            d=formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex)
        {

        }

        try {
            q = "update tbltransaction set transtype="+typeoftrans+"," +
                    "transdate='" + d + "',amount='" + amount + "',payee=" + payee + ",reference_checkno='" + refcheckno + "'," +
                    "details='" + details + "',category=" + category + ",entry_date='" + entry_date + "',status=" + status + ",entrytype="+ entrytype + ",refsmsid =" + refsmsid + " where transid="+transid+"";


            db.execSQL(q);
        }
        catch (Exception ex){
            Toast.makeText(cont,""+ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        return  1;
    }

    int numberexist(String phno)
    {
        String q="select * from tblsmsmanager where trackno ='"+phno+"'";


        Cursor cur= db.rawQuery(q, null);
        return cur.getCount();
    }
    int numberexist_withtype(String phno,int type)
    {
        String q="";
        if(type==1 || type==2) {
            q = "select * from tblsmsmanager where trackno ='" + phno + "' and transtype=" + type + "";
        }
        else
        {
            q = "select * from tblsmsmanager where trackno ='" + phno + "' and type";
        }


        Cursor cur= db.rawQuery(q, null);
        return cur.getCount();
    }


int getCountTrans()
{
    String q="select * from tbltransaction ";


    Cursor cur= db.rawQuery(q, null);
    return cur.getCount();
}
    Cursor getAllcategory(int position)
    {
        String q="";
        if(position==0)
        {
            q="select category_id as _id,category_name,category_desc,icon from tblcategory order by category_name asc";
        }
        else
        {
            q="select category_id,category_name,category_desc,icon from tblcategory where category_id="+position+"";
        }

        Cursor cur= db.rawQuery(q, null);
        return cur;

    }

    Cursor getAllPayee(int position)
    {
        String q="";
        if(position==0)
        {
            q="select payeeid,payeename,payeeaddress,payeenumber from tblpayee order by payeename asc";
        }
        else
        {
            q="select payeeid,payeename,payeeaddress,payeenumber from tblpayee where payeeid="+position+"";
        }

        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllPayee2(int position)
    {
        String q="";
        if(position==0)
        {
            q="select payeeid,payeename,payeeaddress,payeenumber from tblpayee where payeeid not in (1) order by payeename asc";
        }

        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getSmsmanager(String trackno,int type)
    {
        String q="";
  if(type==0)
  {
      q="select trackid,trackno,rupeenth,transtype  from tblsmsmanager where trackno='"+trackno+"' ";
  }
        else
  {
      q="select trackid,trackno,rupeenth,transtype  from tblsmsmanager where trackno='"+trackno+"' and transtype="+type+"";
  }



        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getDateTimeDDMMYYYY() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getDateTimeDDMMYYYYHHMMSS() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "ddMMyyyyhhmmss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public Date convertToDatetime(String date,String format)
    {
        Date d=null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
             d = sdf.parse(date);
        } catch (ParseException ex) {

        }
        return d;

    }
    public Date convertToDatetime(String date)
    {
        Date d=null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
             d = sdf.parse(date);
        } catch (ParseException ex) {

        }
        return d;

    }
    public static String formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {

        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);

        return parsedDate;
    }
    /*--------------------------------------------------------------------------------*/
    Cursor getAllIncome()
    {
        String q="";

        q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee where transtype=1";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllExpense()
    {
        String q="";

            q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee where transtype=2";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllExpenseBydate(String date)
    {
        String q="";
        String thedate="";
        try {
            thedate = formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex){

        }

        q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t "
                +" inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee" +
         " where transdate='"+thedate+"' and transtype=2";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllIncomeByTwodate(String date,String date2)
    {
        String q="";
        String thedate="";
        String thedate2="";
        try {
            thedate = formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex){

        }
        try {
            thedate2 = formatDate(date2, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex){

        }
        q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t "
                +" inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee" +
                " where  transtype=1 and transdate between '"+thedate+"'  and '"+thedate2+"' ";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllExpenseByTwodate(String date,String date2)
    {
        String q="";
        String thedate="";
        String thedate2="";
        try {
            thedate = formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex){

        }
        try {
            thedate2 = formatDate(date2, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex){

        }
        q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t "
                +" inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee" +
                " where  transtype=2 and transdate between '"+thedate+"'  and '"+thedate2+"' ";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllIncomeBydate(String date)
    {
        String q="";
        String thedate="";
        try {
            thedate = formatDate(date, "dd-MM-yyyy", "yyyy-MM-dd");
        }
        catch (Exception ex){

        }

        q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t "
                +" inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee" +
                " where transdate='"+thedate+"' and transtype=1";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllExpenseMonthly(int month,int year)
    {
        String q="";
//cast(strftime('%Y',transdate) as integer)
        q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t "+
                " inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee"+
                " where cast(strftime('%Y',transdate) as integer) ="+year+" and cast(strftime('%m',transdate) as integer) ="+month+" and transtype=2";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
    Cursor getAllIncomeMonthly(int month,int year)
    {
        String q="";
//cast(strftime('%Y',transdate) as integer)
        q="select transid,transdate,amount,details,c.category_name,p.payeename from tbltransaction as t "+
                " inner join tblcategory as c on c.category_id=t.category inner join tblpayee as p on p.payeeid=t.payee"+
                " where cast(strftime('%Y',transdate) as integer) ="+year+" and cast(strftime('%m',transdate) as integer) ="+month+" and transtype=1";


        Cursor cur= db.rawQuery(q, null);
        return cur;

    }
}
