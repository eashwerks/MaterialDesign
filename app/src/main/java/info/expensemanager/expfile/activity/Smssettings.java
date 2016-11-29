package info.expensemanager.expfile.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import info.expensemanager.expfile.R;


public class Smssettings extends Fragment {

    EditText txtprovide,txtwithdrawn,txtcredit;
    Button btnadd1,btnadd2,btnminus1,btnminus2,btnsubmit;
    ImageButton btnold;
    CheckBox chkwithdrwn,chkcredit;

    public Smssettings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_smssettings, container, false);

          txtprovide=(EditText) rootView.findViewById(R.id.txtprovider);
        txtwithdrawn=(EditText) rootView.findViewById(R.id.txtwithdrwn);
        txtcredit=(EditText) rootView.findViewById(R.id.txtcredit);

        btnadd1=(Button) rootView.findViewById(R.id.btnadd1);
        btnadd2=(Button) rootView.findViewById(R.id.btnadd2);

        btnminus1=(Button) rootView.findViewById(R.id.btnminus1);
        btnminus2=(Button) rootView.findViewById(R.id.btnminus2);

        btnsubmit=(Button) rootView.findViewById(R.id.btnsubmit);
        chkwithdrwn=(CheckBox) rootView.findViewById(R.id.chkwithdrawn);
        chkcredit=(CheckBox) rootView.findViewById(R.id.chkcredit);

        txtcredit.setText("1");
        txtwithdrawn.setText("1");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryClass qc=new QueryClass(getActivity());
                int withdraw_nth=1;
                int cred_nth=1;
                String provider=txtprovide.getText().toString();
                String withdrawn=txtwithdrawn.getText().toString();
                String credi=txtcredit.getText().toString();
                try{
                    withdraw_nth=Integer.parseInt(withdrawn);
                    cred_nth=Integer.parseInt(credi);
                }
                catch (Exception ex){

                }
                if(chkwithdrwn.isChecked())
                {

                    String res=qc.insert_sms_settings(provider,withdraw_nth,2);
                    Toast.makeText(getActivity(),""+res,Toast.LENGTH_SHORT).show();
                }
                if(chkcredit.isChecked())
                {

                    String res=qc.insert_sms_settings(provider,cred_nth,1);
                    Toast.makeText(getActivity(),""+res,Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=0;
                try {
                     pos = Integer.parseInt(txtwithdrawn.getText().toString());
                }catch (Exception ex){
                    pos=0;
                }
            pos++;
                txtwithdrawn.setText(""+pos);


            }
        });
        btnadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=0;
                try {
                    pos = Integer.parseInt(txtcredit.getText().toString());
                }catch (Exception ex){
                    pos=0;
                }
                pos++;
                txtcredit.setText("" + pos);


            }
        });
        btnminus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=0;
                try {
                    pos = Integer.parseInt(txtwithdrawn.getText().toString());
                }catch (Exception ex){
                    pos=0;
                }
                pos--;
                if(pos==-1)
                {
                    pos=0;
                }
                txtwithdrawn.setText("" + pos);


            }
        });
        btnminus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=0;
                try {
                    pos = Integer.parseInt(txtcredit.getText().toString());
                }catch (Exception ex){
                    pos=0;
                }
                pos--;
                if(pos==-1)
                {
                    pos=0;
                }
                txtcredit.setText("" + pos);


            }
        });
        // Inflate the layout for this fragment
        return rootView;
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
