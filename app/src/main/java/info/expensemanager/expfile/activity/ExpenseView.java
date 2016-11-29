package info.expensemanager.expfile.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.expensemanager.expfile.R;
import info.expensemanager.expfile.adapter.MessageDetails;


public class ExpenseView extends Fragment {


ListView lv;
    public ExpenseView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expense_view, container, false);

      lv=(ListView) rootView.findViewById(R.id.listView);
        ArrayList<MessageDetails> details;
MessageDetails md=new MessageDetails();
        details = new ArrayList<MessageDetails>();
        for(int i=100;i<106;i++) {
            md=new MessageDetails();
            md.setAmount(i);
            md.setReason("No Reason");
            details.add(md);
        }

        lv.setAdapter(new CustomeAdapter(details , getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView a, View v, int position, long id) {

               String gid = (String) ((TextView) v.findViewById(R.id.txtamount)).getText();

                Toast.makeText(getActivity(),"hiii"+gid,Toast.LENGTH_LONG).show();

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
