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
public class Wishlist extends Fragment {

    EditText txtdesc,txtamount,txtamount2;
    Context context;
    View rootView;
    Button btnsubmit;
  CheckBox chkreminder;
    public Wishlist() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wishlist, container, false);
        context=getActivity();
        txtdesc=(EditText) rootView.findViewById(R.id.txtdescription);
        txtamount=(EditText) rootView.findViewById(R.id.txtamount);
        txtamount2=(EditText) rootView.findViewById(R.id.txtamount2);
        btnsubmit=(Button) rootView.findViewById(R.id.btnsubmit);
        chkreminder=(CheckBox) rootView.findViewById(R.id.chkreminder);
QueryClass qc=new QueryClass(context);
        double curam=qc.getavailableAmount();
        txtamount2.setText(String.valueOf(LoginManager.getreminderAmount(context)));

        if(LoginManager.getreminderAmount(context).equals("0"))
        {
            chkreminder.setChecked(false);
        }
        else
        {
            chkreminder.setChecked(true);
        }

        chkreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtamount2.getText().toString().equals("")) {
                    if (chkreminder.isChecked()) {
                        LoginManager.setreminderStatus("1", context);
                        LoginManager.setreminderAmount(txtamount2.getText().toString(), context);
                        Toast.makeText(getActivity(),"Reminder Added", Toast.LENGTH_LONG).show();
                    } else {
                        LoginManager.setreminderStatus("0", context);
                        LoginManager.setreminderAmount(txtamount2.getText().toString(), context);
                        Toast.makeText(getActivity(),"Reminder Removed", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"Please Enter amount", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QueryClass qc=new QueryClass(context);

                String desc=txtdesc.getText().toString();
                String amount=txtamount.getText().toString();

                if((!desc.equals("")) && (! amount.equals(""))) {
                    qc.insert_wishlist(desc, amount);
                    txtamount.setText("");
                    txtdesc.setText("");

                    Toast.makeText(getActivity(),"Wish list added", Toast.LENGTH_LONG).show();
                }
                else {
                Toast.makeText(getActivity(),"Please fill all fields", Toast.LENGTH_LONG).show();
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
