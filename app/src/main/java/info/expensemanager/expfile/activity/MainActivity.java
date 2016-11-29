package info.expensemanager.expfile.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import info.expensemanager.expfile.R;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        try
        {
            Intent n=getIntent();
            int type=0;
             type=Integer.parseInt(n.getStringExtra("Ftype"));

           // Toast.makeText(getBaseContext(),"A="+type,Toast.LENGTH_LONG).show();

             displayView(0);

                displayView(type);


        }
        catch (Exception ex){
            displayView(0);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.action_search2){
            Toast.makeText(getApplicationContext(), "Search action is selected!0000000000", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
       DatabaseHelper dh=new DatabaseHelper(this);
        switch (position) {
            case 0:
                CopyrawtoSdcard();
                fragment = new HomeFragment();
                title = "Dashboard";
                break;

            case 1:

                fragment = new Payeemanage();
                title = "Payee Details";

   break;

            case 2:

                fragment = new CategoryManage();
                title = "New Category";
                break;



            case 3:
                fragment = new ViewCategory();
                title = "View Category";
                break;
            case 4:
                fragment = new Addexpense();
                title = "New Transaction";
                break;

              //  fragment = new ExpenseView();
               // title = "Expense List";

            case 5:
                Intent n1=new Intent(getBaseContext(),IncomeReport.class);
                startActivity(n1);
                break;
            case 6:

                Intent n=new Intent(getBaseContext(),Expense_Report.class);
                startActivity(n);
                break;
            case 7:
                fragment = new Smssettings();
                title = "Sms manage";
                break;
            case 8:

                fragment = new SettingsList();
                title = "Settings";
                break;
            case 9:
                Intent n3=new Intent(getApplication(),ChartGenerateView.class);
                startActivity(n3);

                break;
            case 10:

                fragment = new ViewPayee();
                title = "View Payee";
                break;
            case 11:

                fragment = new Backupdb();
                title = "Backup & Restore";
                break;
            case 12:

                fragment = new Wishlist();
                title = "Wish List & Reminder";
                break;
            case 13:

                fragment = new Changepassword();
                title = "Change password";
                break;
            default:
                fragment = new HomeFragment();
                title = "Dashboard";

                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
    public void CopyrawtoSdcard()
    {

        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        String basepath = extStorageDirectory ;

        File clipartdir = new File(basepath + "/ExpenseManager/alarm/");
        if (!clipartdir.exists()) {
            clipartdir.mkdirs();


            byte[] buffer = null;
            InputStream fIn = getBaseContext().getResources()
                    .openRawResource(R.raw.alarm);
            int size = 0;
            System.out.println("<<<<<<<SIZE>>>>>>>>>>>>>>>>>>>>" + fIn);
            try {
                size = fIn.available();
                System.out
                        .println("<<<<<<<SIZE>>>>>>>>>>>>>>>>>>>>" + size);
                buffer = new byte[size];
                fIn.read(buffer);
                fIn.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block

            }
            String path = "/sdcard/ExpenseManager/alarm/";
            String filename = "abc" + ".mp3";

            boolean exists = (new File(path)).exists();
            if (!exists) {
                System.out
                        .println("<<<<<<<FALSE SO INSIDE THE CONDITION>>>>>>>>>>>>>>>>>>>>");
                new File(path).mkdirs();
            }

            FileOutputStream save;
            try {
                save = new FileOutputStream(path + filename);
                System.out
                        .println("<<<<<<<SAVE>>>>>>>>>>>>>>>>>>>>" + save);
                save.write(buffer);
                save.flush();
                save.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block

            } catch (IOException e) {
                // TODO Auto-generated catch block

            }
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + path + filename)));

            File k = new File(path, filename);
            System.out.println("<<<<<<<SAVE>>>>>>>>>>>>>>>>>>>>" + k);


        }

    }
}