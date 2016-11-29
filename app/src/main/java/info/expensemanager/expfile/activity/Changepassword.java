package info.expensemanager.expfile.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

/**
 * Created by Ravi on 29/07/15.
 */
public class Changepassword extends Fragment {

    EditText txtpassword;
    Context context;
    View rootView;
    Button btnsubmit;
    CheckBox chkreminder;
    public Changepassword() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_changepassword, container, false);
        context=getActivity();

        txtpassword=(EditText) rootView.findViewById(R.id.txtpassword);

        btnsubmit=(Button) rootView.findViewById(R.id.btnsubmit);

        QueryClass qc=new QueryClass(context);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QueryClass qc=new QueryClass(context);

                String pass="";
                pass=txtpassword.getText().toString();

                if((!pass.equals("")) && (! pass.equals("0"))) {
                    qc.update_password(pass);

                    Toast.makeText(getActivity(),"Password changed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"Error in password change", Toast.LENGTH_LONG).show();
                }

            }
        });


        //context=getActivity();


        // Inflate the layout for this fragment
        return rootView;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
