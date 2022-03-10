package the.fiesta.OysterBake.Companion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListViewAdapterForBackupMap extends BaseExpandableListAdapter {

    private Context context;
    private String[] headerNames = {
            "Water",
            "Red Bull",
            "Beer/Flavored Malt Beverages",
            "Soft Drinks",
            "Chicken on a stick",
            "ATM Machine",
            "Coupon Booth",
            "Gate",
            "Ambulance",
            "First Aid",
            "Medical Trainer",
            "Port-A-Johns/ Hand Sanitizing"};
    private String[][] childNames = {{"Bottled Water, Price: 4 Coupons"},
            {"Red Bull, Price: 4 Coupons"},
            {"Beer/Flavored Malt Beverages, Price: 7 Coupons"},
            {"Soft Drinks, Price: 4 Coupons"},
            {"Chicken on a stick: Price : 6 Coupons"},
            {"ATM Machine"},
            {"Coupon Booth: Price $1.00 per coupon"},
            {"Gate: Entry Tickets available at gate for $30"},
            {"Ambulance: Medical Assistance available"},
            {"First Aid: Medical Assistance available"},
            {"Medical Trainer: Medical Assistance available"},
            {"Port-A-Johns/ Hand Sanitizing: Restrooms and Sanitizing stations complementary for patrons"}};

    public ExpandableListViewAdapterForBackupMap(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return headerNames.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childNames[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerNames[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childNames[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);

        textView.setText(headerNames[groupPosition]);
        textView.setPadding(100, 0, 5, 10);
        textView.setTextSize(24);

        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);

        textView.setText(childNames[groupPosition][childPosition]);
        textView.setPadding(25, 0, 20, 10);
        textView.setTextSize(16);

        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}