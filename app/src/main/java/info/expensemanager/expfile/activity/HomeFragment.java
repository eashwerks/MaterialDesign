package info.expensemanager.expfile.activity;

/**
 * Created by Ravi on 29/07/15.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import info.expensemanager.expfile.R;


public class HomeFragment extends Fragment {

    ImageView IMG1,IMG2,IMG3,IMG4,IMG5,IMG6,IMG7,IMG8;
    LinearLayout LB1,LB2,LB3,LB4,LB5,LB6,LB7,LB8,LB9;
 Context context;
    TextView txtamountview;
    View rootView;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_home, container, false);
context=getActivity();

        txtamountview=(TextView) rootView.findViewById(R.id.txtamountview);
        try
        {
            QueryClass qc=new QueryClass(getActivity());
            String lbl ="Available Balance : Rs "+String.format( "%.2f",qc.getavailableAmount());
            txtamountview.setText(lbl);
        }
        catch(Exception ex)
        {
            LinearLayout LL4=(LinearLayout) rootView.findViewById(R.id.LL4);
            txtamountview.setVisibility(View.INVISIBLE);
            LL4.setVisibility(View.INVISIBLE);
        }
       LB1=(LinearLayout) rootView.findViewById(R.id.LB1);
        LB2=(LinearLayout) rootView.findViewById(R.id.LB2);
        LB3=(LinearLayout) rootView.findViewById(R.id.LB3);
        LB4=(LinearLayout) rootView.findViewById(R.id.LB4);
        LB5=(LinearLayout) rootView.findViewById(R.id.LB5);
        LB6=(LinearLayout) rootView.findViewById(R.id.LB6);
        LB7=(LinearLayout) rootView.findViewById(R.id.LB7);
        LB8=(LinearLayout) rootView.findViewById(R.id.LB8);
        LB9=(LinearLayout) rootView.findViewById(R.id.LB9);

        LB9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(getActivity(),MainActivity.class);
                n.putExtra("Ftype", "12");
                startActivity(n);
            }
        });
        LB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(getActivity(),MainActivity.class);
                n.putExtra("Ftype", "0");
                startActivity(n);
            }
        });
        LB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(getActivity(),MainActivity.class);
                n.putExtra("Ftype", "4");
                startActivity(n);
            }
        });
        LB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.popreport);
                dialog.setTitle("Menu");
                LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.Linear1);
                LinearLayout dialogButton2 = (LinearLayout) dialog.findViewById(R.id.Linear2);
                LinearLayout dialogButton3 = (LinearLayout) dialog.findViewById(R.id.Linear3);
                LinearLayout dialogButton4 = (LinearLayout) dialog.findViewById(R.id.Linear4);
                final TextView text = (TextView) dialog.findViewById(R.id.TXTID);
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();

                        Intent n=new Intent(getActivity(),MainActivity.class);
                        n.putExtra("Ftype", "5");
                        startActivity(n);
                    }
                });
                dialogButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        dialog.dismiss();
                    }
                });
                dialogButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        Intent n=new Intent(getActivity(),MainActivity.class);
                        n.putExtra("Ftype", "6");
                        startActivity(n);

                    }
                });

                // set the custom dialog components - text, image and button



                dialog.show();


            }
        });
        LB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.popcategory);
                dialog.setTitle("Menu");
                LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.Linear1);
                LinearLayout dialogButton2 = (LinearLayout) dialog.findViewById(R.id.Linear2);
                LinearLayout dialogButton3 = (LinearLayout) dialog.findViewById(R.id.Linear3);
                LinearLayout dialogButton4 = (LinearLayout) dialog.findViewById(R.id.Linear4);
                final TextView text = (TextView) dialog.findViewById(R.id.TXTID);
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();

                        Intent n = new Intent(getActivity(), MainActivity.class);
                        n.putExtra("Ftype", "2");
                        startActivity(n);
                    }
                });
                dialogButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        dialog.dismiss();
                    }
                });
                dialogButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        Intent n = new Intent(getActivity(), MainActivity.class);
                        n.putExtra("Ftype", "3");
                        startActivity(n);

                    }
                });

                // set the custom dialog components - text, image and button



                dialog.show();
            }
        });
        LB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(getActivity(),ChartGenerateView.class);

                startActivity(n);
            }
        });
        LB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(getActivity(),Viewsmsprovide.class);

                startActivity(n);
            }
        });
        LB8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getActivity(), MainActivity.class);
                n.putExtra("Ftype", "8");
                startActivity(n);
            }
        });
        LB7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.poppayee);
                dialog.setTitle("Menu");
                LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.Linear1);
                LinearLayout dialogButton2 = (LinearLayout) dialog.findViewById(R.id.Linear2);
                LinearLayout dialogButton3 = (LinearLayout) dialog.findViewById(R.id.Linear3);
                LinearLayout dialogButton4 = (LinearLayout) dialog.findViewById(R.id.Linear4);
                final TextView text = (TextView) dialog.findViewById(R.id.TXTID);
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();

                        Intent n=new Intent(getActivity(),MainActivity.class);
                        n.putExtra("Ftype", "10");
                        startActivity(n);
                    }
                });
                dialogButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        dialog.dismiss();
                    }
                });
                dialogButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        //  String id=(String)((TextView) view2.findViewById(R.id.txtno)).getText().toString();
                        Intent n=new Intent(getActivity(),MainActivity.class);
                        n.putExtra("Ftype", "1");
                        startActivity(n);

                    }
                });

                // set the custom dialog components - text, image and button



                dialog.show();
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
