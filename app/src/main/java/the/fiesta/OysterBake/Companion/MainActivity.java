package the.fiesta.OysterBake.Companion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ERROR_DIALOG_REQUEST = 9001;
    public static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9002;
    private static final String TAG = "MainActivity";

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // check if a user is logged in

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent AdminHome = new Intent(MainActivity.this, AdminHomePage.class);
            startActivity(AdminHome);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup spinner
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Home Page",
                        "Maps",
                        "Stages",
                        //"Developers", ***not sure we need this
                        "FAQs",
                        "Souvenirs"
                }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                if (position == 0) // Home tab content
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, HomeTab.newInstance(position + 1))
                            .commit();
                } else if (position == 1) // Map content
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, MapTab.newInstance(position + 1))
                            .commit();
                } else if (position == 2) // Showtimes content
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, SelectDate.newInstance(position + 1))
                            .commit();
                } else if (position == 3) // About content
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, AboutTab.newInstance(position + 1))
                            .commit();
                } else if (position == 4) // FAQ content
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, FAQTab.newInstance(position + 1))
                            .commit();
                } else if (position == 5) // Merch content
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, MerchTab.newInstance(position + 1))
                            .commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //The following is for the map --------------------------------------------------------------

    private boolean checkMapServices() {
        if (isServicesOK()) {
            return isMapsEnabled();
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    //this ends the map section! --------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_admin_login) {
            Intent intent = new Intent(MainActivity.this, AdminLogin.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class HomeTab extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public Button buyTicketsButton;
        public Button aboutOysterBakeButton;
        public Button followOnTwitterButton;
        public Button followOnInstagramButton;
        public Button likeOnFacebookButton;

        public HomeTab() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static HomeTab newInstance(int sectionNumber) {
            HomeTab fragment = new HomeTab();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            String welcomeTextString = "Welcome to the ";
            welcomeTextString = welcomeTextString + Integer.toString(currentYear);
            welcomeTextString = welcomeTextString + " Fiesta Oyster Bake!!!";

            int yearOnesDigit = (currentYear - 1916) % 100;
            String yearCountTextString = "The heartbeat of Fiesta beats again for the " + Integer.toString(currentYear - 1916);
            switch (yearOnesDigit) {
                case 1:
                case 21:
                case 31:
                case 41:
                case 51:
                case 61:
                case 71:
                case 81:
                case 91:
                    yearCountTextString = yearCountTextString + "st ";
                    break;
                case 2:
                case 22:
                case 32:
                case 42:
                case 52:
                case 62:
                case 72:
                case 82:
                case 92:
                    yearCountTextString = yearCountTextString + "nd ";
                    break;
                case 3:
                case 23:
                case 33:
                case 43:
                case 53:
                case 63:
                case 73:
                case 83:
                case 93:
                    yearCountTextString = yearCountTextString + "rd ";
                    break;
                default:
                    yearCountTextString = yearCountTextString + "th ";
                    break;
            }
            yearCountTextString = yearCountTextString + "time. 35+ bands. 100,000 oysters. 2 days. 1 good time.";

            View rootView = inflater.inflate(R.layout.home_tab, container, false);
            TextView welcomeText = rootView.findViewById(R.id.welcome_text);
            TextView yearCountText = rootView.findViewById(R.id.year_count_text);
            buyTicketsButton = rootView.findViewById(R.id.buy_tickets_button);
            aboutOysterBakeButton = rootView.findViewById(R.id.about_oyster_bake_button);
            followOnTwitterButton = rootView.findViewById(R.id.twitter_button);
            followOnInstagramButton = rootView.findViewById(R.id.instagram_button);
            likeOnFacebookButton = rootView.findViewById(R.id.facebook_button);

            welcomeText.setText(welcomeTextString);
            yearCountText.setText(yearCountTextString);
            buyTicketsButton.setOnClickListener(this);
            aboutOysterBakeButton.setOnClickListener(this);
            followOnTwitterButton.setOnClickListener(this);
            followOnInstagramButton.setOnClickListener(this);
            likeOnFacebookButton.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onClick(View view) {
            Uri webpage = null;
            Intent buttonIntent;
            PackageManager packageManager;
            List<ResolveInfo> buttonActivities;
            boolean isIntentSafe;

            if ((view.getId()) != (R.id.about_oyster_bake_button)) {
                switch (view.getId()) {
                    case R.id.buy_tickets_button:
                        webpage = Uri.parse("http://oysterbake.com/tickets/");
                        break;
                    case R.id.twitter_button:
                        webpage = Uri.parse("https://twitter.com/Oyster_Bake");
                        break;
                    case R.id.instagram_button:
                        webpage = Uri.parse("https://www.instagram.com/oysterbake/");
                        break;
                    case R.id.facebook_button:
                        webpage = Uri.parse("https://www.facebook.com/FiestaOysterBake/");
                        break;
                    default:
                        Toast.makeText(view.getContext(), "Could not complete action", Toast.LENGTH_SHORT).show();
                }

                buttonIntent = new Intent(Intent.ACTION_VIEW, webpage);
                packageManager = view.getContext().getPackageManager();
                buttonActivities = packageManager.queryIntentActivities(buttonIntent, PackageManager.MATCH_DEFAULT_ONLY);
                isIntentSafe = (buttonActivities.size() > 0);
                if (isIntentSafe) {
                    startActivity(buttonIntent);
                }
            } else {
                buttonIntent = new Intent(getActivity(), AboutOysterBake.class);

                startActivity(buttonIntent);
            }
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MapTab extends Fragment implements View.OnClickListener {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public Button fridayButton;
        public Button saturdayButton;

        public MapTab() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MapTab newInstance(int sectionNumber) {
            MapTab fragment = new MapTab();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_maps, container, false);

            fridayButton = rootView.findViewById(R.id.fridayMap);
            saturdayButton = rootView.findViewById(R.id.saturdayMap);

            fridayButton.setOnClickListener(this);
            saturdayButton.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onClick(View view) {
            String day = "";

            switch (view.getId()) {
                case R.id.fridayMap:
                    day = "1";
                    break;
                case R.id.saturdayMap:
                    day = "2";
                    break;
            }

            // New intent to go to ScheduleList.java Activity
            Intent intent = new Intent(getActivity(), MapsActivity.class);

            // Send the stage number as a string.
            intent.putExtra("day", day);
            startActivity(intent);
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SelectDate extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public Button fri;
        public Button sat;


        public SelectDate() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SelectDate newInstance(int sectionNumber) {
            SelectDate fragment = new SelectDate();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_select_date, container, false);

            fri = rootView.findViewById(R.id.friB);
            sat = rootView.findViewById(R.id.satB);


            sat.setOnClickListener(this);
            fri.setOnClickListener(this);


            return rootView;
        }

        @Override
        public void onClick(View view) {
            String Day = "";

            switch (view.getId()) {
                case R.id.friB:
                    Day = "1";
                    break;
                case R.id.satB:
                    Day = "2";
                    break;

            }

            // New intent to go to StageSelect.java Activity
            Intent intent = new Intent(getActivity(), StageSelect.class);

            // Send the stage number as a string.
            intent.putExtra("Day", Day);
            startActivity(intent);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class AboutTab extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public AboutTab() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static AboutTab newInstance(int sectionNumber) {
            AboutTab fragment = new AboutTab();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.about_tab, container, false);

            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FAQTab extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private ExpandableListView expandableListView;
        private ExpandableListViewAdapter expandableListViewAdapter;

        public FAQTab() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FAQTab newInstance(int sectionNumber) {
            FAQTab fragment = new FAQTab();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.faq_tab, container, false);

            expandableListView = rootView.findViewById(R.id.expandableListView);
            expandableListViewAdapter = new ExpandableListViewAdapter(this.getContext());

            expandableListView.setAdapter(expandableListViewAdapter);

            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MerchTab extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public MerchTab() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MerchTab newInstance(int sectionNumber) {
            MerchTab fragment = new MerchTab();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.merch_tab, container, false);

            return rootView;
        }
    }
}