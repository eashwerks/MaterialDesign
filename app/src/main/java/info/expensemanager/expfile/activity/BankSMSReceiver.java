package info.expensemanager.expfile.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class BankSMSReceiver extends BroadcastReceiver
{
private boolean screenOff;
    private static final String TAG = "Message recieved";
public int checkExist(String [] arr,String word)
{
    int status=0;
    for(int i=0;i<arr.length;i++){
        if(arr[i].equals(word)){
            status++;
        }
    }
    return status;
}
    public double getRupee(String [] arr,int nth)
    {
        String word1="rs";
        String word2="rs-";
        String word3="inr";
        String word4="rs.";
        String word5="rs/-";
        String word6="inr.";
        String word7="inr-";



        double rupee=0;
        int status=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i].trim().equals(word1) || arr[i].trim().equals(word2) || arr[i].trim().equals(word3) || arr[i].trim().equals(word4) || arr[i].trim().equals(word5) || arr[i].trim().equals(word6) || arr[i].trim().equals(word7) ){
                status++;
                if(status==nth)
                {
                    rupee=Double.parseDouble(arr[i+1].trim());
                    break;
                }
            }
        }
        return rupee;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdus[0]);
        Log.i(TAG, messages.getMessageBody());

        String smsBody = messages.getMessageBody().toString();
        String address = messages.getOriginatingAddress();
        String saveSMS=smsBody;
        smsBody=smsBody.toLowerCase();
       // Toast.makeText(context, "SMS Received from  : "+address+"...."+messages.getMessageBody(),
       //         Toast.LENGTH_LONG).show();

        QueryClass qc=new QueryClass(context);
        String [] ArrofContent;
        int entrytype=2; //by sms
        int category=1;
        int typeoftrans=0;
        String refcheckno="";
        int payee=1;
        int ext=qc.numberexist(address.trim());
        int trakall=0;
        try
        {
            trakall=Integer.parseInt(LoginManager.getTrackall(context));
        }
        catch(Exception ex)
        {
            trakall=0;
        }

        if(ext>0 || trakall>0)
        {


            //Both Type in same provider
            ArrofContent=smsBody.split("( )|(\n)|(\r)");



            int TYPE_OF_PROVIDER_ENTRY_EXP=qc.numberexist_withtype(address.trim(), 2);
            int TYPE_OF_PROVIDER_ENTRY_INC=qc.numberexist_withtype(address.trim(), 1);

            if(trakall>0 && ext==0)
            {
                TYPE_OF_PROVIDER_ENTRY_EXP=1;
                TYPE_OF_PROVIDER_ENTRY_INC=1;
            }

            if(TYPE_OF_PROVIDER_ENTRY_INC>0 && TYPE_OF_PROVIDER_ENTRY_EXP>0){
                int withdrawnstatus=checkExist(ArrofContent, "withdrawn");
                if(withdrawnstatus==0){
                    withdrawnstatus=checkExist(ArrofContent, "withdraw");
                }
                if(withdrawnstatus==0){
                    withdrawnstatus=checkExist(ArrofContent, "debit");
                }
                if(withdrawnstatus==0){
                    withdrawnstatus=checkExist(ArrofContent, "debited");
                }

                int creditstatus=checkExist(ArrofContent, "credit");
                if(creditstatus==0){
                    creditstatus=checkExist(ArrofContent,"credited");
                }
                if(withdrawnstatus>0)
                {
                    category=2;
                    typeoftrans=2; // expense
                    Cursor cu=qc.getSmsmanager(address.trim(), 2);
                    cu.moveToNext();
                    int nth=1;
                    try {
                        if(trakall>0 && ext==0)
                        {
                            nth=1;
                        }
                        nth = cu.getInt(cu.getColumnIndex("rupeenth"));
                    }
                    catch(Exception ex)
                    {
                        if(trakall>0 && ext==0)
                        {
                           nth=1;
                        }
                    }

                    cu.close();
                    double rupees=getRupee(ArrofContent, nth);
                  //  Toast.makeText(context, "Amount  : "+rupees,
                   //         Toast.LENGTH_LONG).show();
                    long id=  qc.insert_sms(address,saveSMS);
                    int idd=Integer.parseInt(String.valueOf(id));
                  //  Toast.makeText(context, "SMS ID  : "+idd,
                    //        Toast.LENGTH_LONG).show();
                    String date=qc.getDateTimeDDMMYYYY();
                    qc.insert_expense(typeoftrans,date, String.valueOf(rupees), payee, refcheckno, smsBody, category, 0, entrytype, idd);

                }
                if(creditstatus>0){
                    category=1;
                    typeoftrans=1; // income
                    Cursor cu=qc.getSmsmanager(address.trim(), 1);
                    cu.moveToNext();
                    int nth = 1;

                    try {
                        if(trakall>0 && ext==0)
                        {
                            nth=1;
                        }
                        nth = cu.getInt(cu.getColumnIndex("rupeenth"));
                    }
                    catch(Exception ex)
                    {
                        if(trakall>0 && ext==0)
                        {
                            nth=1;
                        }
                    }

                    cu.close();
                    double rupees=getRupee(ArrofContent, nth);

                    long id=  qc.insert_sms(address, saveSMS);
                    int idd=Integer.parseInt(String.valueOf(id));

                    String date=qc.getDateTimeDDMMYYYY();
                    qc.insert_expense(typeoftrans,date, String.valueOf(rupees), payee, refcheckno, smsBody, category, 0, entrytype, idd);

                }

            }
            else if(TYPE_OF_PROVIDER_ENTRY_INC>0)
            {
                category=1;
                typeoftrans=1; // income
                Cursor cu=qc.getSmsmanager(address.trim(), 1);
                cu.moveToNext();
                int nth = 1;

                try {
                    if(trakall>0 && ext==0)
                    {
                        nth=1;
                    }
                    nth = cu.getInt(cu.getColumnIndex("rupeenth"));
                }
                catch(Exception ex)
                {
                    if(trakall>0 && ext==0)
                    {
                        nth=1;
                    }
                }
                cu.close();
                double rupees=getRupee(ArrofContent, nth);

                long id=  qc.insert_sms(address,saveSMS);
                int idd=Integer.parseInt(String.valueOf(id));

                String date=qc.getDateTimeDDMMYYYY();
                qc.insert_expense(typeoftrans,date, String.valueOf(rupees), payee, refcheckno, smsBody, category, 0, entrytype, idd);

            }
            else if(TYPE_OF_PROVIDER_ENTRY_EXP>0)
            {
                category=2;
                typeoftrans=2; // expense
                Cursor cu=qc.getSmsmanager(address.trim(), 2);
                cu.moveToNext();
                int nth = 1;

                try {
                    if(trakall>0 && ext==0)
                    {
                        nth=1;
                    }
                    nth = cu.getInt(cu.getColumnIndex("rupeenth"));
                }
                catch(Exception ex)
                {
                    if(trakall>0 && ext==0)
                    {
                        nth=1;
                    }
                }
                cu.close();
                double rupees=getRupee(ArrofContent, nth);

                long id=  qc.insert_sms(address,saveSMS);
                int idd=Integer.parseInt(String.valueOf(id));

                String date=qc.getDateTimeDDMMYYYY();
                qc.insert_expense(typeoftrans,date, String.valueOf(rupees), payee, refcheckno, smsBody, category, 0, entrytype, idd);

            }


            double current_amount=0;
            double reminder_amount=0;

            current_amount=qc.getavailableAmount();

            String a="";
            try{
                a =LoginManager.getreminderAmount(context);
            }catch(Exception ex){
                a="0";
            }
            try {
                reminder_amount = Double.parseDouble(a);
            }
            catch(Exception ex)
            {
                reminder_amount=0;
            }
            if(LoginManager.getreminderStatus(context).equals("1"))
            {
                if(current_amount<=reminder_amount)
                {
                    if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                        screenOff = true;
                    } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                        screenOff = false;
                    }
                    Intent i = new Intent(context, Alarmpage.class);
                    i.putExtra("screen_state", screenOff);
                    i.putExtra("msg", "Balance low.. Current amount : "+current_amount);
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
                try {
                    checkcuramount = Double.parseDouble(cur.getString(cur.getColumnIndex("currentamount")));
                }
                catch(Exception ex)
                {
                    checkcuramount=0;
                }

                if(current_amount>=checkcuramount)
                {
                    if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                        screenOff = true;
                    } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                        screenOff = false;
                    }
                    Intent i = new Intent(context, Alarmpage.class);
                    i.putExtra("screen_state", screenOff);
                    i.putExtra("msg", cur.getString(cur.getColumnIndex("wishcontent")));
                    i.putExtra("id", cur.getString(cur.getColumnIndex("wishid")));
                    i.putExtra("title", "Your wish list");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }



        }
   /* ALARM FOR REMINDER*/




    }
}
