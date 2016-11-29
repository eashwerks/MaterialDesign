package info.expensemanager.expfile.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import info.expensemanager.expfile.R;

public class CategoryManage extends android.support.v4.app.Fragment {

EditText txtcategory,txtdesc;
    ListView listView_cat;
Button btnsubmit;
    View rootView;
    public CategoryManage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.fragment_category_manage, container, false);
   DatabaseHelper dh=new DatabaseHelper(getActivity());

        txtcategory=(EditText) rootView.findViewById(R.id.txtcategory_name);
        txtdesc=(EditText) rootView.findViewById(R.id.txtdesc);
        listView_cat=(ListView) rootView.findViewById(R.id.listview_cat);
        btnsubmit=(Button) rootView.findViewById(R.id.btnsubmit);
        LoadData();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category_name = txtcategory.getText().toString().trim();
                String catdesc = txtdesc.getText().toString().trim();

                if (!category_name.equals("")) {
                    QueryClass qc = new QueryClass(getActivity());
                    long res = qc.insert_category(category_name, catdesc);
                    LoadData();
                    Toast.makeText(getActivity(), String.valueOf(res), Toast.LENGTH_SHORT).show();

                }

            }
        });

                return  rootView;

    }

    public void LoadData()
    {
        QueryClass qc=new QueryClass(getActivity());
        try
        {

            Cursor cursor = qc.getAllcategory(0);

           // startManagingCursor(cursor);

            listView_cat=(ListView) rootView.findViewById(R.id.listview_cat);

           // ListView myGV=(ListView)findViewById(R.id.LISTBILL);
            String[] cols = new String[] {"_id" ,"category_name","category_desc"};
            int[]   views = new int[] {R.id.txtcatid,R.id.txtcategory_name,R.id.txtcategory_description};


            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.category_list_row, cursor, cols, views);


            listView_cat.setAdapter(adapter);




        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
