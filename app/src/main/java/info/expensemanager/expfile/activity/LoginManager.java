package info.expensemanager.expfile.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginManager 
{
	public static String username="0";
	public static String password="0";
	public static String withoutlogin="1";
	public static String logid="";
	public static String reminderAmount="0";
	public static String reminderStatus="0";
	public static String trackallsms="0";
    static int PRIVATE_MODE = 0;

	public static void setTrackall(String itm,Context mContext)
	{
		final 	SharedPreferences preferences=mContext.getSharedPreferences("trackallsms",PRIVATE_MODE);
		Editor editor=preferences.edit();
		editor.putString(trackallsms, itm);
		editor.commit();
	}
	public static String getTrackall(Context mContext)
	{
		final SharedPreferences preferences=mContext.getSharedPreferences("trackallsms",PRIVATE_MODE);
		return preferences.getString(trackallsms,"");
	}


	public static void setUserName(String itm,Context mContext)
	{
	final 	SharedPreferences preferences=mContext.getSharedPreferences("username",PRIVATE_MODE);
		Editor editor=preferences.edit();
		editor.putString(username, itm);
		editor.commit();
	}
	public static String getUserName(Context mContext)
	{
	final SharedPreferences preferences=mContext.getSharedPreferences("username",PRIVATE_MODE);
	return preferences.getString(username,"");
	}




	public static String getreminderAmount(Context mContext)
	{
		final SharedPreferences preferences=mContext.getSharedPreferences("reminderAmount",PRIVATE_MODE);
		return preferences.getString(reminderAmount,"");
	}
	public static void setreminderAmount(String amount,Context mContext)
	{
		final 	SharedPreferences preferences=mContext.getSharedPreferences("reminderAmount",PRIVATE_MODE);
		Editor editor=preferences.edit();
		editor.putString(reminderAmount, amount);
		editor.commit();
	}
	public static String getreminderStatus(Context mContext)
	{
		final SharedPreferences preferences=mContext.getSharedPreferences("reminderStatus",PRIVATE_MODE);
		return preferences.getString(reminderStatus,"");
	}
	public static void setreminderStatus(String amount,Context mContext)
	{
		final 	SharedPreferences preferences=mContext.getSharedPreferences("reminderStatus",PRIVATE_MODE);
		Editor editor=preferences.edit();
		editor.putString(reminderStatus, amount);
		editor.commit();
	}
	public static void setLogstatus(String status,Context mContext)
	{
		final 	SharedPreferences preferences=mContext.getSharedPreferences("logstatus",PRIVATE_MODE);
		Editor editor=preferences.edit();
		editor.putString(withoutlogin, status);
		editor.commit();
	}
	public static String getLogstatus(Context mContext)
	{
		final SharedPreferences preferences=mContext.getSharedPreferences("logstatus",PRIVATE_MODE);
		return preferences.getString(withoutlogin,"");
	}


	public static void setlogid(String movie,Context mContext)
	{
	final 	SharedPreferences preferences=mContext.getSharedPreferences("logid",PRIVATE_MODE);
		Editor editor=preferences.edit();
		editor.putString(logid, movie);
		editor.commit();
	}
	public static String getlogid(Context mContext)
	{
	final SharedPreferences preferences=mContext.getSharedPreferences("logid",PRIVATE_MODE);
	return preferences.getString(logid,"");
	}
	
	public static void setpass(String pass,Context mContext)
	{
	final 	SharedPreferences preferences=mContext.getSharedPreferences("pass",PRIVATE_MODE);
		Editor editor=preferences.edit();
		editor.putString(password, pass);
		editor.commit();
	}
	public static String getpass(Context mContext)
	{
	final SharedPreferences preferences=mContext.getSharedPreferences("pass",PRIVATE_MODE);
	return preferences.getString(password,"");
	}
	
}
