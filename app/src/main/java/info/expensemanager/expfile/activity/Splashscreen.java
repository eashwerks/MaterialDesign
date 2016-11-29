package info.expensemanager.expfile.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Handler;
import android.widget.TextView;

import info.expensemanager.expfile.R;

public class Splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    TextView txttitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Typeface font = Typeface.createFromAsset(getAssets(), "olivier_demo.ttf");
        txttitle=(TextView)findViewById(R.id.textView5);
        txttitle.setTypeface(font);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                try
                {
                    String as= LoginManager.getLogstatus(Splashscreen.this);
                    if(as.equals("1") || as.equals(""))
                    {
                        Intent n=new Intent(Splashscreen.this,MainActivity.class);
                        n.putExtra("Ftype","0");
                        startActivity(n);
                        finish();
                    }
                    else
                    {
                        Intent i = new Intent(Splashscreen.this, LoginActivity.class);
                        startActivity(i);


                        // close this activity
                        finish();
                    }
                }
                catch (Exception ex){

                }


            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splashscreen, menu);
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
