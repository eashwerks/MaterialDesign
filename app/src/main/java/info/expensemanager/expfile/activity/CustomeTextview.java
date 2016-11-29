package info.expensemanager.expfile.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Rahul on 20/04/2016.
 */
public class CustomeTextview extends TextView {
  public   CustomeTextview(Context context)
    {
        super(context);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
        this.setTypeface(face);

    }
    public   CustomeTextview(Context context, AttributeSet attr)
    {
        super(context,attr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
        this.setTypeface(face);
    }
    public   CustomeTextview(Context context, AttributeSet attr,int defStyle)
    {
        super(context,attr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
        this.setTypeface(face);
    }
}
