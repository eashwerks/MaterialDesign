package info.expensemanager.expfile.activity;



 
//import java.io.FileOutputStream;
//import java.io.InputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName="DbExpManager";
	 
	
	//table Admin_login
	static final String adminTable="adminlogin";
	static final String lname="admin_name";
	static final String lpwd="admin_pwd";

	 
	
	

	public DatabaseHelper(Context context) {
		super(context, dbName, null,33);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE "+ adminTable + "("+lname+" TEXT, "+lpwd+" TEXT )";
		db.execSQL(sql);
		 sql = "CREATE TABLE tblcategory(category_id integer primary key AUTOINCREMENT,category_name TEXT,category_desc TEXT,icon TEXT NULL)";
		db.execSQL(sql);
		sql = "CREATE TABLE tblpayee(payeeid integer primary key AUTOINCREMENT,payeename TEXT,payeeaddress TEXT NULL,payeenumber NUMERIC NULL )";
		db.execSQL(sql);

		sql = "CREATE TABLE tblsms(smsid integer primary key AUTOINCREMENT,smscenter TEXT,smscontent TEXT NULL,smsstatus integer )";
		db.execSQL(sql);

		sql = "CREATE TABLE tblsmsmanager(trackid integer primary  key AUTOINCREMENT,trackno TEXT NULL,template TEXT NULL,rupeenth integer NULL,transtype integer NULL)";
		db.execSQL(sql);

		db.execSQL("create table tbltransaction (transid integer primary key AUTOINCREMENT, transtype integer, transdate datetime,amount real, payee integer,reference_checkno text NULL,details text NULL,category integer,entry_date datetime,status integer NULL,entrytype integer NULL,refsmsid integer NULL,integer functionmode NULL)");

		sql = "CREATE TABLE tblwishlist(wishid integer primary key AUTOINCREMENT,wishcontent TEXT,currentamount double NULL,status integer NULL )";
		db.execSQL(sql);
		//db.execSQL("CREATE VIEW "+viewEmps+" AS SELECT m.memoid AS _id, m.mdate, m.subject, m.matter, m.status FROM memo m "
			//	);

		InsertLogin(db);
		insertCategory(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+adminTable);
		db.execSQL("DROP TABLE IF EXISTS tblcategory");
		db.execSQL("DROP TABLE IF EXISTS tblpayee");
		db.execSQL("DROP TABLE IF EXISTS tblsms");
		db.execSQL("DROP TABLE IF EXISTS tbltransaction");
		db.execSQL("DROP TABLE IF EXISTS tblwishlist");

		
		
	//	db.execSQL("DROP VIEW IF EXISTS "+viewEmps);
		onCreate(db);
	}
	int getLogResult(String uname, String pwd)
	{
		SQLiteDatabase db=this.getWritableDatabase();

		Cursor cur= db.rawQuery("Select * from "+adminTable +" where "+lname +"='"+uname+"' and "+lpwd+" = '"+pwd+"'" , null);
		int x= cur.getCount();
		cur.close();
		db.close();
		return x;
	}
void insertCategory(SQLiteDatabase db)
{
	ContentValues cv=new ContentValues();
//CREATE TABLE tblsmsmanager(trackid integer primary  key AUTOINCREMENT,trackno TEXT NULL,template
// TEXT NULL,rupeenth integer NULL,transtype integer NULL)";

	cv = new ContentValues();
	cv.put("trackno", "DZ-WAYSMS");
	cv.put("template", "-");
	cv.put("rupeenth", 1);
	cv.put("transtype", 1);
	db.insert("tblsmsmanager", "trackid", cv);

	cv = new ContentValues();
	cv.put("trackno", "DZ-WAYSMS");
	cv.put("template", "-");
	cv.put("rupeenth", 1);
	cv.put("transtype", 2);
	db.insert("tblsmsmanager", "trackid", cv);


	cv = new ContentValues();
	cv.put("category_name", "Bank Credit ");
	cv.put("category_desc", "");
	cv.put("icon", "rent");
	db.insert("tblcategory", "category_id", cv);





	cv = new ContentValues();
	cv.put("category_name", "Bank Withdraw ");
	cv.put("category_desc", "");
	cv.put("icon", "rent");
	db.insert("tblcategory", "category_id", cv);

	cv = new ContentValues();
	cv.put("category_name", "Bank Loan");
	cv.put("category_desc", "Loan details");
	cv.put("icon", "bank");
	db.insert("tblcategory", "category_id", cv);

	cv = new ContentValues();
	cv.put("category_name", "Food ");
	cv.put("category_desc", "");
	cv.put("icon", "food");
	db.insert("tblcategory", "category_id", cv);

	cv = new ContentValues();
	cv.put("category_name", "Shopping");
	cv.put("category_desc", "");
	cv.put("icon", "shopping");
	db.insert("tblcategory", "category_id", cv);

	cv = new ContentValues();
	cv.put("category_name", "Petrol Pumb");
	cv.put("category_desc", "");
	cv.put("icon", "pumb");
	db.insert("tblcategory", "category_id", cv);


	cv = new ContentValues();
	cv.put("category_name", "Travel Expense");
	cv.put("category_desc", "Bus,car rent details");
	cv.put("icon", "travel");
	db.insert("tblcategory", "category_id", cv);


	cv = new ContentValues();
	cv.put("category_name", "Salary");
	cv.put("category_desc", "");
	cv.put("icon", "salary");
	db.insert("tblcategory", "category_id", cv);


	cv = new ContentValues();
	cv.put("category_name", "Rent");
	cv.put("category_desc", "Home or car rent");
	cv.put("icon", "rent");
	db.insert("tblcategory", "category_id", cv);


	cv = new ContentValues();
	cv.put("category_name", "Donation");
	cv.put("category_desc", "");
	cv.put("icon", "rent");
	db.insert("tblcategory", "category_id", cv);


	cv = new ContentValues();
	cv.put("category_name", "Petty cash");
	cv.put("category_desc", "");
	cv.put("icon", "pettycash");
	db.insert("tblcategory", "category_id", cv);


	cv = new ContentValues();
	cv.put("category_name", "Current Bill");
	cv.put("category_desc", "");
	cv.put("icon", "currentbill");
	db.insert("tblcategory", "category_id", cv);

	cv = new ContentValues();
	cv.put("category_name", "Water ");
	cv.put("category_desc", "");
	cv.put("icon", "water");
	db.insert("tblcategory", "category_id", cv);

	cv = new ContentValues();
	cv.put("category_name", "Cable Charge");
	cv.put("category_desc", "");
	cv.put("icon", "cable");
	db.insert("tblcategory", "category_id", cv);

	cv = new ContentValues();
	cv.put("category_name", "Gas");
	cv.put("category_desc", "");
	cv.put("icon", "gas");
	db.insert("tblcategory", "category_id", cv);

	cv=new ContentValues();
	cv.put("category_name", "Hospital");
	cv.put("category_desc", "");
	cv.put("icon", "hospital");
	db.insert("tblcategory", "category_id", cv);


	 cv=new ContentValues();
	cv.put("payeename", "No Payee");
	db.insert("tblpayee", "payeeid", cv);


}
	void InsertLogin(SQLiteDatabase db)
	{
		ContentValues cv=new ContentValues();
		cv.put(lname, "admin");
		cv.put(lpwd, "123");
		db.insert(adminTable, lname, cv);

/*
		cv=new ContentValues();
		cv.put(fname, "Full Name");
		cv.put(addrs, "Address");
		cv.put(idno, "Identity No:");
		cv.put(pp, "Passport Number");
		cv.put(ppe, "Pasport Expiry");
		cv.put(drivell, "Driving License No:");
		cv.put(drivelle, "Expiry Date");
		cv.put(carReg, "Car Regsitration");
		db.insert(personalTable, pipn, cv);


		cv=new ContentValues();
		cv.put(pipn, "Personal Insurance Policy No");
		cv.put(pipnr, "Renewal Date");
		cv.put(mipn, "Medical Insurance Policy No:");
		cv.put(mipnr, "Renewal Date");
		cv.put(hipn, "House Insurance Policy No:");
		cv.put(hipnr, "Renewal Date");
		cv.put(cipn, "Car Insurance Policy No:");
		cv.put(cipnr, "Renewal Date");
		db.insert(insuTable, pipn, cv);

		cv=new ContentValues();
		cv.put(bank1, "Enter Bank 1");
		cv.put(bank2, "Enter Bank 2");
		cv.put(bank1AC, "Enter Bank 1 A/c No:");
		cv.put(bank2AC, "Enter Bank 2 A/c No:");
		cv.put(itPan, "Enter PAN No:");
		cv.put(creditcn, "Enter Credit Card No:");
		cv.put(atmcn, "Enter ATM CArd No:");
		db.insert(finTable, bank1, cv);
		*/

	}
	/*
	 Cursor getAllmemos(int position)
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 

		 Cursor cur= db.rawQuery("SELECT m.memoid AS _id, m.mdate, m.subject, m.matter, m.status FROM memo m where m.status = " + position,null);
		 return cur;
		 
	 }

	 public int UpdateFinTable(String[] p)
	 {
		 

		 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(bank1, p[0]);
		 cv.put(bank2, p[1]);
		
	     cv.put(bank1AC,p[2]);
		 cv.put(bank2AC, p[3]);
		 cv.put(itPan,p[4]);
		 cv.put(creditcn,p[5]);
		 cv.put(atmcn,p[6]);
		
		 return db.update(finTable, cv, null, null);
	 }
	 public int UpdateInsTable(String[] p)
	 {
		 

		 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put("pipn", p[0]);
		 cv.put("pipnr", p[1]);
	     cv.put("mipn",p[2]);
		 cv.put("mipnr", p[3]);
		 cv.put("hipn",p[4]);
		 cv.put("hipnr",p[5]);
		 cv.put("cipn",p[6]);
		 cv.put("cipnr",p[7]);
		
		 return db.update("insuTable", cv, null, null);
	 }
	 void Addmemo(String mdate, String subject, String matter)
		{
			 
			 SQLiteDatabase db= this.getWritableDatabase();
			ContentValues cv=new ContentValues();
			cv.put("mdate", mdate);
			cv.put("subject", subject);
			cv.put("matter", matter);
			cv.put("status", 0);
			db.insert("memo", "memoid", cv);
			db.close();
			
			
		}

	 int getLogResult(String uname, String pwd)
	 {
		SQLiteDatabase db=this.getWritableDatabase();
	
		Cursor cur= db.rawQuery("Select * from "+adminTable +" where "+lname +"='"+uname+"' and "+lpwd+" = '"+pwd+"'" , null);
		int x= cur.getCount();
		cur.close();
		db.close();
		return x;
	 }
	 
	 public int PasswordChange(String[] p)
	 {
		
		
		 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put("admin_name", p[0]);
		 cv.put("admin_pwd", p[1]);
		
	     
		 return db.update("adminlogin", cv, null, null);
	 }

	 

	 
	 float getTotal(int position,String From,String To)
	 {
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select sum(amount) from tblaccounts where acctype = " + position +" and accdate between '"+From+"' and '"+To+"'", null);
	
		  cur.moveToFirst();
		 float total = cur.getFloat(0);
		    cur.close();
		
		return total;
	 }
	 
	 
	 
	
	 public Cursor fetchAllScores() {
		 SQLiteDatabase db=this.getWritableDatabase();
		    return db.query("memo", new String[] { "memoid", "mdate", "subject", "matter", "status" }, null, null, null, null, null);
		}

	 

	 
	 public int UpdateMemo(int id, String mdate, String subject, String matter, boolean status)
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put("mdate", mdate);
		 cv.put("subject", subject);
		 cv.put("matter", matter);
		 cv.put("status", status);
		 return db.update("memo", cv, "memoid=?", new String []{id+""});
		 
	 }

	 
	 
	 public void DeleteMemo(int id)
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 db.delete("memo","memoid=?", new String [] {String.valueOf(id)});
		 db.close();
				
	 }
	 */

}
