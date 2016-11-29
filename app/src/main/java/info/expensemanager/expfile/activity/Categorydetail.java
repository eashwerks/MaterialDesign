package info.expensemanager.expfile.activity;

/**
 * Created by Rahul on 02/04/2016.
 */
public class Categorydetail {
    int id;
    String category;
    String category_desc;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    int icon;

    public String getIconname() {
        return iconname;
    }

    public void setIconname(String iconname) {
        this.iconname = iconname;
    }

    String iconname;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
    }
    public String getValue () {
        return category;
    }
    @Override
    public String toString () {
        return category;
    }
}
