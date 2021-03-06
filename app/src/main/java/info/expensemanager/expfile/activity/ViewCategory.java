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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.expensemanager.expfile.R;

/**
 * Created by Ravi on 29/07/15.
 */
public class ViewCategory extends Fragment {

    ListView listView_cat;
Context context;
    View rootView;

    public ViewCategory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_view_category, container, false);
        listView_cat=(ListView) rootView.findViewById(R.id.listview_cat);
        context=getActivity();

        LoadData();

        // Inflate the layout for this fragment
        return rootView;
    }
    public void LoadData()
    {
        ArrayList<Categorydetail> arrCategory=new ArrayList<Categorydetail>();
        QueryClass qc=new QueryClass(getActivity());
        try
        {

            Cursor cursor = qc.getAllcategory(0);





            // startManagingCursor(cursor);


            String imgname="";
            while(cursor.moveToNext()) {
                try
                {
                    imgname = cursor.getString(3);
                }catch (Exception ex){

                }
              //  imgname = cursor.getString(3);
                Categorydetail cd = new Categorydetail();
                cd.setCategory(cursor.getString(1));
                cd.setCategory_desc(cursor.getString(2));
                cd.setIcon(getImageId(getActivity(), imgname));
                cd.setId(cursor.getInt(0));

                arrCategory.add(cd);
            }

            cursor.close();
            listView_cat.setAdapter(new CustomeListviewCategoryAdapter(arrCategory, getActivity()));
          listView_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                  String txtcatid=(String)((TextView) view.findViewById(R.id.txtcatid)).getText().toString();

                  final Dialog dialog = new Dialog(context);
                  dialog.setContentView(R.layout.popupcategorylist);
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
                          Toast.makeText(getActivity(),"MY ID="+text.getText().toString(), Toast.LENGTH_SHORT).show();
                          String id=text.getText().toString();

                          Intent n=new Intent(getActivity(),UpdateCategory.class);
                          n.putExtra("id",id);

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

                          final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                          alertDialogBuilder.setMessage("Are you sure,You wanted to delete category");

                          alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface arg0, int arg1) {
                                  String id = text.getText().toString();
                                  if(Integer.parseInt(id)>16) {
                                      QueryClass qc = new QueryClass(context);
                                      qc.delete_category(Integer.parseInt(id));
                                      Toast.makeText(getActivity(), "Category Deleted", Toast.LENGTH_LONG).show();
                                  }
                                  else
                                  {
                                      Toast.makeText(getActivity(), "Category cannot be deleted", Toast.LENGTH_LONG).show();
                                  }

                              }
                          });

                          alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  //finish();

                              }
                          });

                          AlertDialog alertDialog = alertDialogBuilder.create();
                          alertDialog.show();

                      }
                  });

                  // set the custom dialog components - text, image and button

                  text.setText(txtcatid);

                  dialog.show();

                  //
              }
          });


            // startManagingCursor(cursor);
/*
            listView_cat=(ListView) rootView.findViewById(R.id.listview_cat);

            // ListView myGV=(ListView)findViewById(R.id.LISTBILL);
            String[] cols = new String[] {"_id" ,"category_name","category_desc"};
            int[]   views = new int[] {R.id.txtcatid,R.id.txtcategory_name,R.id.txtcategory_description};


            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.category_list_row, cursor, cols, views);


            listView_cat.setAdapter(adapter);
*/



        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
