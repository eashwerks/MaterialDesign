package info.expensemanager.expfile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import info.expensemanager.expfile.R;


public class SettingsList extends Fragment {
LinearLayout LL2,LLSMS,liner_backup,liner_password;
    CheckBox chkautologin,chktrackall;
    public SettingsList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings_list, container, false);
chkautologin=(CheckBox) rootView.findViewById(R.id.checkBox3);
        chktrackall=(CheckBox) rootView.findViewById(R.id.checkBox5);

        chktrackall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chktrackall.isChecked())
                {
                    LoginManager.setTrackall("1", getActivity());
                    Toast.makeText(getActivity(),"Track all sms activated",Toast.LENGTH_LONG).show();
                }
                else
                {
                    LoginManager.setTrackall("0", getActivity());
                    Toast.makeText(getActivity(),"Track all sms decativated",Toast.LENGTH_LONG).show();
                }
            }
        });

        try
        {
            String as= LoginManager.getLogstatus(getActivity());
            if(as.equals("1") || as.equals(""))
            {
                chkautologin.setChecked(true);
            }
        }
        catch (Exception ex){

        }
        try
        {
            String as= LoginManager.getTrackall(getActivity());
            if(as.equals("1"))
            {
                chktrackall.setChecked(true);
            }
        }
        catch (Exception ex){

        }

        chkautologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkautologin.isChecked())
                {
                    LoginManager.setLogstatus("1",getActivity());
                }
                else
                {
                    LoginManager.setLogstatus("0",getActivity());
                }
            }
        });
LL2=(LinearLayout)rootView.findViewById(R.id.LL2);
        LLSMS=(LinearLayout)rootView.findViewById(R.id.LLSMS);
        liner_backup=(LinearLayout)rootView.findViewById(R.id.liner_backup);
        liner_password=(LinearLayout)rootView.findViewById(R.id.liner_password);
        liner_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent N= new Intent(getActivity(),MainActivity.class);
                N.putExtra("Ftype","11");
                startActivity(N);
            }
        });
        liner_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent N= new Intent(getActivity(),MainActivity.class);
                N.putExtra("Ftype","13");
                startActivity(N);
            }
        });
        LL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent N= new Intent(getActivity(),Viewsmsprovide.class);
                startActivity(N);
            }
        });


        LLSMS=(LinearLayout)rootView.findViewById(R.id.LLSMS);
        LLSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent N= new Intent(getActivity(),MainActivity.class);
                N.putExtra("Ftype","7");
                startActivity(N);
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
