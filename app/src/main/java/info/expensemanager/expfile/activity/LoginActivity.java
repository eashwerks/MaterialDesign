package info.expensemanager.expfile.activity;


import android.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import info.expensemanager.expfile.R;

/**
 * A login screen that offers login via email/password and via Google+ sign in.
 * <p/>
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */
public class LoginActivity extends Activity {

    EditText txtusername,txtpassword;
    Button btnlogin;
    private Toolbar toolbar;
    private EditText inputName, inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    TextView txttitle;
   CheckBox chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*try {
    String act = LoginManager.getLogstatus(this);
    if (act.equals("1")) {
        Intent n = new Intent(getBaseContext(), MainActivity.class);
        startActivity(n);
    }
}
catch (Exception ex){

}*/

        setContentView(R.layout.activity_login2);

        btnlogin = (Button) findViewById(R.id.btnsubmit);
        Typeface font = Typeface.createFromAsset(getAssets(), "olivier_demo.ttf");
        txttitle=(TextView)findViewById(R.id.txttitle);
        txttitle.setTypeface(font);
          txtpassword = (EditText)findViewById(R.id.txtpassword);
        inputLayoutName = (TextInputLayout) findViewById(R.id.txtinputname);
        txtpassword.addTextChangedListener(new MyTextWatcher(inputLayoutName));
   chk=(CheckBox) findViewById(R.id.chklogin);

        try
        {
           String as= LoginManager.getLogstatus(this);
            if(as.equals("1"))
            {
                Intent n=new Intent(this,MainActivity.class);
                n.putExtra("Ftype","0");
                startActivity(n);
            }
        }
        catch (Exception ex){

        }
   //   txtusername = (EditText)findViewById(R.id.txtusername);
     //   txtpassword = (EditText)findViewById(R.id.txtpassword);

        //getSupportActionBar(toolbar);
/*
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignUp = (Button) findViewById(R.id.btnlogin);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


*/
       btnlogin=(Button)findViewById(R.id.btnsubmit);
        btnlogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm()
                ;
               //   exportDB();
                QueryClass qc=new QueryClass(getBaseContext());
            int res=qc.getLogin(txtpassword.getText().toString());
                if(res>0) {

                       if(chk.isChecked())
                       {
                           LoginManager.setLogstatus("1", getBaseContext());
                       }


                     Intent n = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(n);
                }
                else
                {
                    inputLayoutName.setError("Invalid password");
                    requestFocus(txtpassword);

                }

            }
        });

    }
    private void submitForm() {
        if (!validateName()) {
            return;
        }




    }

    private boolean validateName() {
        if (txtpassword.getText().toString().trim().equals("")) {
            inputLayoutName.setError("Enter your password");
            requestFocus(txtpassword);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }




    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;

            }
        }
    }


    private void exportDB() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "info.androidhive.materialdesign"
                        + "//databases//" + "DbExpManager";
                String backupDBPath = "9645/abcd";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getApplicationContext(), "Backup Successful!",
                        Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Backup Failed!", Toast.LENGTH_SHORT)
                    .show();

        }
    }

}



