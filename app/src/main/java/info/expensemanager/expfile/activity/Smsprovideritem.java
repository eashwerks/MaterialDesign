package info.expensemanager.expfile.activity;

/**
 * Created by Rahul on 16/04/2016.
 */
public class Smsprovideritem {
    int id;
    String provider;
    int typeid;
    String typename="";
    int nth;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    boolean selected = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getNth() {
        return nth;
    }

    public void setNth(int nth) {
        this.nth = nth;
    }
}
