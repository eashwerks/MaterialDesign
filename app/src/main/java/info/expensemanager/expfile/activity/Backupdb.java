package info.expensemanager.expfile.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.nio.channels.FileChannel;

import info.expensemanager.expfile.R;

/**
 * Created by Rahul on 12/05/2016.
 */
public class Backupdb extends Fragment {

    private static final int ACTIVITY_CHOOSE_FILE = 3;


    private String[] mFileList;
    private File mPath = new File(Environment.getExternalStorageDirectory() + "//yourdir//");
    private String mChosenFile;
    private static final String FTYPE = ".txt";
    private static final int DIALOG_LOAD_FILE = 1000;
    Button btn,btnrestore,btnselect;
    Context context;
    EditText editfilepath;
    int REQUESTCODE_PICK_VIDEO;
    public Backupdb() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_backup, container, false);
    btn=(Button) rootView.findViewById(R.id.btnbackup);

        btnrestore=(Button) rootView.findViewById(R.id.btnrestore);
        btnselect=(Button) rootView.findViewById(R.id.btnselect);
        editfilepath=(EditText) rootView.findViewById(R.id.edtextPath);
        context=getActivity();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDB();
            }
        });


        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String filepath=editfilepath.getText().toString();
                 String [] arr=filepath.split("/");
                filepath="";
                for(int i=2;i<arr.length;i++)
                {
                    filepath=filepath+"/"+arr[i];
                }
                if(!filepath.equals(""))
                 restoreDB(filepath);
            }
            });

                btnrestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("*/*");
                if (Build.VERSION.SDK_INT < 19) {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent = Intent.createChooser(intent, "Select file");
                } else {
                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
//                    String[] mimetypes = { "audio/*", "video/*" };
                  //  intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                }
                startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);

            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void restoreDB(String fromfile)
    {
        QueryClass qc=new QueryClass(getActivity());
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            String timestamp=qc.getDateTimeDDMMYYYYHHMMSS();




            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "info.expensemanager.expfile"
                        + "//databases//" + "DbExpManager";
                String backupDBPath = fromfile;//"ExpenseManager/"+timestamp+"/databasefile";



                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(backupDB).getChannel();
                FileChannel dst = new FileOutputStream(currentDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getActivity(), "Restore Successful!",
                        Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            Toast.makeText(getActivity(), "Restore Failed!", Toast.LENGTH_SHORT)
                    .show();

        }
    }
    private void exportDB() {
        QueryClass qc=new QueryClass(getActivity());
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            String timestamp=qc.getDateTimeDDMMYYYYHHMMSS();
            File folder = new File(Environment.getExternalStorageDirectory() + "/ExpenseManager/"+timestamp+"/");
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }




            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "info.expensemanager.expfile"
                        + "//databases//" + "DbExpManager";
                String backupDBPath = "ExpenseManager/"+timestamp+"/databasefile";



                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getActivity(), "Backup Successful!",
                        Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {

            Toast.makeText(getActivity(), "Backup Failed!", Toast.LENGTH_SHORT)
                    .show();

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

    private void loadFileList() {
        try {
            mPath.mkdirs();
        }
        catch(SecurityException e) {
          //  Log.e(TAG, "unable to write on the sd card " + e.toString());
        }
        if(mPath.exists()) {
            FilenameFilter filter = new FilenameFilter() {

                @Override
                public boolean accept(File dir, String filename) {
                    File sel = new File(dir, filename);
                    return filename.contains(FTYPE) || sel.isDirectory();
                }

            };
            mFileList = mPath.list(filter);
        }
        else {
            mFileList= new String[0];
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        String path     = "";
        if(requestCode == ACTIVITY_CHOOSE_FILE)
        {
            Uri uri = data.getData();
            String FilePath = getRealPathFromURI(uri);
            editfilepath.setText(FilePath);

        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor       = context.getContentResolver().query( contentUri, proj, null, null,null);
        if (cursor == null) return null;
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
