package info.expensemanager.expfile.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import info.expensemanager.expfile.R;

public class Alarmpage extends AppCompatActivity {
    MediaPlayer mPlayer=new MediaPlayer();
    TextView txtmsg,txttitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_alarmpage);
        txtmsg=(TextView) findViewById(R.id.txtmsg);
        txttitle=(TextView) findViewById(R.id.txttitle);
        try
        {
           txtmsg.setText("");
            Intent in=getIntent();
            txtmsg.setText(in.getStringExtra("msg"));
            txttitle.setText(in.getStringExtra("title"));
            try
            {
                QueryClass qc=new QueryClass(getBaseContext());
                String id =in.getStringExtra("id");
                qc.delete_wish(id);
            }
            catch (Exception ex)
            {

            }

        }
        catch (Exception ex)
        {

        }
       //  mPlayer = MediaPlayer.create(Alarmpage.this, R.raw.alarm);
        //mPlayer.start();
try {
    mPlayer.setDataSource("mnt/sdcard/ExpenseManager/alarm/abc.mp3");
    mPlayer.prepare();

    mPlayer.start();
}
catch(Exception ex)
{

}


    }
    public void onDestroy(View v) {
 try {
     mPlayer.stop();
     super.onDestroy();
     finish();
 }
 catch(Exception ex)
 {
     finish();
 }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarmpage, menu);
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
