package the.fiesta.OysterBake.Companion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String[] headerNames = {"Oyster Bake Hours", "Parking", "Park & Ride", "Family Friendly Event", "Safety at Oyster Bake", "Allowed & Prohibited Items", "Security & Entry", "Exit & Re-entry", "Payment Types", "Rain Or Shine?", "Refund & Policy", "Contact Us"};
    private String[][] childNames = {{"Friday April 1: 5:00-11:00 PM                                                                " +
            "(Integrity  Roofing and Siding Fireworks Spectacular start at 10:30 PM)                                                 " +
            "Saturday April 2: 12:00-11:00 PM                                                                                       " +
            "For Both Days                                                                                                           " +
            "  - Gates close at 10:30 PM                                                                                             " +
            "  - Ticket sales end at 10:30 PM                                                                                        " +
            "  - Entertainment ends at 10:30 PM                                                                                      " +
            "  - Alcohol Beverage sales end at 10:30 PM                                                                              " +
            "  - Food and Beverage sales end at 10:30 PM                                                                             " +
            "  - Coupon sales end at 10:30 PM                                                                                        "},
            {"We appreciate the patience of our neighbors each year as we conduct the Oyster Bake. Because we are located in a residential area, parking is somewhat congested around the event. We offer limited on-campus parking for $20."},
            {"We highly encourage everyone to take the VIA Park and Ride located at Crossroads. Each passenger will be charged a $5 roundtrip fee and will receive a coupon for a free soda, water or Red Bull at the Oyster Bake." +
                    " VIA Park and Ride to the Fiesta Oyster Bake will operate the following:                                                                              " +
                    " Hours of Operation:                                                                                                                          " +
                    "Friday, April 1st                                                                                                " +
                    "4:30 to 11:30 PM                                                                                                " +
                    "                                                                                                                                              " +
                    "                                                                                                               " +
                    "Saturday, April 2nd                                                                                              " +
                    " 11:30 AM  to  11:30 PM                                                                                         "},
            {"The Oyster Bake is a family friendly event. We encourage families to attend Friday evening as we feature entertainment for families and the Integrity Roofing and Siding Fireworks Spectacular at the end of the night. On both days, kids and 'kids at heart' can visit our carnival area known as Shuckie Street, named for our Oyster Bake mascot. We offer rides and games for children of all ages. Also, children 12 years old and younger, receive FREE admission to Oyster Bake."},
            {"Your safety is our NUMBER ONE priority.                                                                                " +
                    "IF YOU SEE SOMETHING, SAY SOMETHING.                                                                            " +
                    "The Oyster Bake works closely with local, state, and federal authorities to make sure it is a safe and fun time for everyone." +
                    "For the safety of everyone at the Bake, ALL patrons are subject to a full and complete search prior to entry. Screening of children will always take place with the consent of a parent or guardian."},
            {" Items ALLOWED at bake are:                                                                                            " +
                    "- Binoculars, Sheets, Towels                                                                                    " +
                    "- Blankets                                                                                                     " +
                    "- Strollers                                                                                                    " +
                    "- Basic consumer-grade cameras                                                                                 " +
                    "- Portable/collapsible chair                                                                                  " +
                    "- Sunscreen lotion not in aerosol containers                                                                   " +
                    "- Bug repellent(no aerosol containers)                                                                         " +
                    "- Service Animals that aid the patron physically are allowed on the grounds                                                               " +
                    "                                                                                                               " +
                    "Items NOT ALLOWED at the Bake are:                                                                             " +
                    "- Professional Video Cameras                                                                                   " +
                    "- Professional recording equipment                                                                             " +
                    "- Laser pointers                                                                                               " +
                    "- Weapons of any kind                                                                                          " +
                    "- Oyster Knives                                                                                                " +
                    "- Illegal drugs                                                                                                " +
                    "- Pets                                                                                                         " +
                    "- Patrons wearing steel toe boots                                                                              " +
                    "- Pepper Spray                                                                                                 " +
                    "                                                                                                               " +
                    "** Please note that officers will confiscate these and other items if they are illegal, and will discard and not return any items." +
                    "                                                                                                               " +
                    "All Fiesta Oyster Bake  patrons and their belongings are subject to search.                                    " +
                    "                                                                                                               " +
                    "No Video or Audio Taping Fiesta Oyster Bake entertainers allowed! Violators of this policy are subject to removal from the Fiesta Oyster Bake grounds. " +
                    "No outside food or drink are allowed on the Oyster Bake Grounds. Items are subject to confiscation. No refunds on Admission Tickets and Food and Beverage coupons, Volunteer wristbands are NOT for sale.",
                    " You may be photographed/ videotaped and your picture or likeness may be used for Fiesta Oyster Bake publicity and promotions."},
            {"All bags will be searched before entry.                                                                               " +
                    "                                                                                                               " +
                    "PRO-TIP:                                                                                                       " +
                    "Leave bags at home and be able to take advantage of the express: No Bag Entry lanes.                           " +
                    "By purchasing a ticket, you agree to submit to a full body pat down and metal detector search before entry. Screening of children will always take place with the consent of the parent or guardian." +
                    "Persons that refuse to comply with the search policy will be refused into the event."},
            {"Leaving any exit at this event at any time, you will not be allowed to re-enter.                                      " +
                    "If you purchase a 2- day ticket, on Friday you will be given a 2-day admission wristband that must NOT be removed in order for entrance on Saturday.   "},
            {"*Note: All Admission tickets and coupons are non- refundable*                                                                    " +
                    "ATMs are available near each entrance gate and throughout the grounds of the event.                                                                " +
                    "                                                                                                               " +
                    "Credit Cards:                                                                                                                                      " +
                    "We accept credit cards at gates and coupon booths. There will be a $20 minimum purchase for all credit card  transactions.                                                                                         " +
                    "Checks:                                                                                                                   " +
                    "Checks are NOT accepted.                                                                                                  " +
                    "Coupons:                                                                                                                  " +
                    "All food and beverage items are purchased with coupons. Coupon sales end at 10:30 p.m. Coupons are available in $1.00 increments."},
            {"YES! Oyster Bake will still go on rain or shine.                                                                      "},
            {"There will be NO REFUNDS. The Oyster Bake is rain or shine and all bands are subject to change. "},
            {"Fiesta Oyster Bake                                                                                 " +
                    "St. Mary's University Alumni Association                                                    " +
                    "One Camino Santa Maria                                                                      " +
                    "San Antonio, TX  78228                                                                      " +
                    "210-436-3324                                                                                " +
                    "oysterbake@stmarytx.edu                                                                      "}
    };

    public ExpandableListViewAdapter(Context context) {
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
        textView.setPadding(100, 0, 0, 0);
        textView.setTextSize(24);

        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);

        textView.setText(childNames[groupPosition][childPosition]);
        textView.setPadding(25, 0, 0, 0);
        textView.setTextSize(16);

        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}