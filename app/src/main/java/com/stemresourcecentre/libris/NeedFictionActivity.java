package com.stemresourcecentre.libris;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class NeedFictionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DATABASE_PATH = "/data/data/com.stemresourcecentre.libris/databases/";
    private static final String DATABASE_PATH2 = "/data/data/com.stemresourccecentre.libris/databases"; // no/ at end of path !!!
    private static final String DATABASE_NAME = "libri.db";
    private static final String LOG_TAG = "LIBRI_DB";

    Context ctx;

    DatabaseHelper sqh;
    SQLiteDatabase sqdb;

    CheckBox checkBoxNeedFTitle, checkBoxNeedFSeries, checkBoxNeedFCategory/*, checkBoxNeedFLocation*/;
    Spinner spinnerNeedFTitle, spinnerNeedFSeries, spinnerNeedFCategory/*, spinnerNeedFLocation*/;

    String getTitle = "", getSeries = "", getCategory = ""/*, getLocation = ""*/;

    Button buttonNeedFApplyFilter, buttonNeedFResetFilter, buttonNeedFListAll;

    ListView listViewNeedFiction;
    ArrayList<String> allNeedFictionBooks, filteredNeedFictionBooks;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_fiction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SetupDatabase();
        InitDataBase();

        SetupControls();

    }

    public void SetupControls() {
        getTitle = "";
        getSeries = "";
        getCategory = "";
        //getLocation = "";

        addListenerOnCheckBoxNeedFTitle();
        addListenerOnCheckBoxNeedFSeries();
        addListenerOnCheckBoxNeedFCategory();
        //addListenerOnCheckBoxNeedFLocation();

        listViewNeedFiction = (ListView)findViewById(R.id.listViewNeedFiction);

        allNeedFictionBooks = new ArrayList<String>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allNeedFictionBooks);

        listViewNeedFiction.setAdapter( adapter );

        buttonNeedFListAll = (Button)findViewById(R.id.buttonNeedFListAll);
        buttonNeedFListAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RefreshNeedListViewForAllRecords();
            }
        });

        buttonNeedFApplyFilter = (Button)findViewById(R.id.buttonNeedFApplyFilter);
        buttonNeedFApplyFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FilteredListViewForAllRecords();
            }
        });

        buttonNeedFResetFilter = (Button)findViewById(R.id.buttonNeedFResetFilter);
        buttonNeedFResetFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resetFilters();
                FilteredListViewForAllRecords();
            }
        });
    }


    public void InitDataBase() {
        //Initialise the SQLite Helper Class and retrieve a readable and writable database
        sqh = new DatabaseHelper(this);
        sqdb = sqh.getWritableDatabase();

    }

    public void SetupDatabase() {
        ctx = this.getBaseContext();
        try {
            CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void CopyDataBaseFromAsset() throws IOException {
        // Get the sqlite databse in the assets folder
        InputStream in = ctx.getAssets().open(DATABASE_NAME);

        Log.w(LOG_TAG, "Starting copying...");
        String outputFileName = DATABASE_PATH + DATABASE_NAME;
        File databaseFolder = new File(DATABASE_PATH2);

        // databases folder exists ? No - Create it and copy !!!
        //if (!databaseFolder.exists())
        {
            databaseFolder.mkdir();

            OutputStream out = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            } // while ( (length = in.read(buffer)) > 0 )
            out.flush();
            out.close();
            in.close();
            Log.w(LOG_TAG, "Completed.");

        }


    }



    // Methods to populate the Spinners
    private void loadSpinnerNeedFTitleData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allNeedFictionTitles = db.populateNeedFictionTitles();

        // Use prompt on Spinner
        spinnerNeedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allNeedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerNeedFTitle.setAdapter(dataAdapterTitle);

        spinnerNeedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getTitle = (parent.getItemAtPosition(position).toString());
                Log.w("TITLE_SPINNER_RESULT", "Spinner selected: " + getTitle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
                getTitle = "";
            }
        });

    }


    private void loadSpinnerNeedFSeriesData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allNeedFictionSeries = db.populateNeedFictionSeries();

        // Use prompt on Spinner
        spinnerNeedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allNeedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerNeedFSeries.setAdapter(dataAdapterSeries);

        spinnerNeedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getSeries = (parent.getItemAtPosition(position).toString());
                Log.w("SERIES_SPINNER_RESULT", "Spinner selected: " + getSeries);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
                getSeries = "";
            }
        });

    }


    private void loadSpinnerNeedFCategoryData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allCategoriesF = db.getAllCategoriesF();

        // Use prompt on Spinner
        spinnerNeedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCategoriesF);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerNeedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerNeedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getCategory = (parent.getItemAtPosition(position).toString());
                Log.w("CATEGORY_SPINNER_RESULT", "Spinner selected: " + getCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
                getCategory = "";
            }
        });

    }


    /* Not currently required.
    private void loadSpinnerNeedFLocationData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allLocationsNeedF = db.getAllLocationsNeedF();

        // Use prompt on Spinner
        spinnerNeedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allLocationsNeedF);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerNeedFLocation.setAdapter(dataAdapterLocation);

        spinnerNeedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getLocation = (parent.getItemAtPosition(position).toString());
                Log.w("LOCATION_SPINNER_RESULT", "Spinner selected: " + getLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
                getLocation = "";
            }
        });

    }*/


    //Methods to control the Spinners
    public void addListenerOnCheckBoxNeedFTitle() {

        checkBoxNeedFTitle = (CheckBox) findViewById(R.id.checkBoxNeedFTitle);

        checkBoxNeedFTitle.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerNeedFTitle = (Spinner) findViewById(R.id.spinnerNeedFTitle);
                    loadSpinnerNeedFTitleData();
                    loadSpinnerNeedFTitleData();

                    spinnerNeedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getTitle = (parent.getItemAtPosition(position).toString());
                            Log.w("TITLE_SPINNER_RESULT", "Spinner selected: " + getTitle);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //Another interface callback
                            getTitle = "";
                        }
                    });


                    Log.w("TITLE_CHECK", "Title checkbox is ticked - Title string has been set to: " + getTitle);
                } else {
                    spinnerNeedFTitle = (Spinner) findViewById(R.id.spinnerNeedFTitle);
                    spinnerNeedFTitle.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getTitle = "";
                    Log.w("TITLE_RESET", "Title string has been set to: " + getTitle);
                }

            }
        });

    }


    public void addListenerOnCheckBoxNeedFSeries() {

        checkBoxNeedFSeries = (CheckBox) findViewById(R.id.checkBoxNeedFSeries);

        checkBoxNeedFSeries.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerNeedFSeries = (Spinner) findViewById(R.id.spinnerNeedFSeries);
                    loadSpinnerNeedFSeriesData();
                    loadSpinnerNeedFSeriesData();

                    spinnerNeedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getSeries = (parent.getItemAtPosition(position).toString());
                            Log.w("SERIES_SPINNER_RESULT", "Spinner selected: " + getSeries);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //Another interface callback
                            getSeries = "";
                        }
                    });

                    Log.w("SERIES_CHECK", "Series checkbox is ticked - Series string has been set to: " + getSeries);
                } else {
                    spinnerNeedFSeries = (Spinner) findViewById(R.id.spinnerNeedFSeries);
                    spinnerNeedFSeries.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getSeries = "";
                    Log.w("SERIES_RESET", "Series string has been set to: " + getSeries);
                }

            }
        });

    }


    public void addListenerOnCheckBoxNeedFCategory() {

        checkBoxNeedFCategory = (CheckBox) findViewById(R.id.checkBoxNeedFCategory);

        checkBoxNeedFCategory.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerNeedFCategory = (Spinner) findViewById(R.id.spinnerNeedFCategory);
                    loadSpinnerNeedFCategoryData();
                    loadSpinnerNeedFCategoryData();

                    spinnerNeedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getCategory = (parent.getItemAtPosition(position).toString());
                            Log.w("CATEGORY_SPINNER_RESULT", "Spinner selected: " + getCategory);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //Another interface callback
                            getCategory = "";
                        }
                    });

                    Log.w("CATEGORY_CHECK", "Category checkbox is ticked - Category string has been set to: " + getCategory);
                } else {
                    spinnerNeedFCategory = (Spinner) findViewById(R.id.spinnerNeedFCategory);
                    spinnerNeedFCategory.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getCategory = "";
                    Log.w("CATEGORY_RESET", "Category string has been set to: " + getCategory);
                }

            }
        });

    }


    /* Not currently required.
    public void addListenerOnCheckBoxNeedFLocation() {

        checkBoxNeedFLocation = (CheckBox) findViewById(R.id.checkBoxNeedFLocation);

        checkBoxNeedFLocation.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerNeedFLocation = (Spinner) findViewById(R.id.spinnerNeedFLocation);
                    loadSpinnerNeedFLocationData();
                    loadSpinnerNeedFLocationData();

                    spinnerNeedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getLocation = (parent.getItemAtPosition(position).toString());
                            Log.w("LOCATION_SPINNER_RESULT", "Spinner selected: " + getLocation);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //Another interface callback
                            getLocation = "";
                        }
                    });

                    Log.w("LOCATION_CHECK", "Location checkbox is ticked - Location string has been set to: " + getLocation);
                } else {
                    spinnerNeedFLocation = (Spinner) findViewById(R.id.spinnerNeedFLocation);
                    spinnerNeedFLocation.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getLocation = "";
                    Log.w("LOCATION_RESET", "Location string has been set to: " + getLocation);
                }

            }
        });

    }*/





    // Methods to reset Spinners and Filters
    public void resetSpinnerTitleData(View view) {
        loadSpinnerNeedFTitleData();
    }

    public void resetSpinnerSeriesData(View view) {
        loadSpinnerNeedFSeriesData();
    }

    public void resetSpinnerCategoryData(View view) {
        loadSpinnerNeedFCategoryData();
    }

    /* Not currently required.
    public void resetSpinnerLocationData(View view) {
        loadSpinnerNeedFLocationData();
    }*/

    public void resetFilters() {
        checkBoxNeedFTitle.setChecked(false);
        spinnerNeedFTitle = (Spinner) findViewById(R.id.spinnerNeedFTitle);
        spinnerNeedFTitle.setAdapter(null);
        adapter.notifyDataSetChanged();
        getTitle = "";
        Log.w("TITLE_RESET", "Title string has been set to: " + getTitle);

        checkBoxNeedFSeries.setChecked(false);
        spinnerNeedFSeries = (Spinner) findViewById(R.id.spinnerNeedFSeries);
        spinnerNeedFSeries.setAdapter(null);
        adapter.notifyDataSetChanged();
        getSeries = "";
        Log.w("SERIES_RESET", "Series string has been set to: " + getSeries);

        checkBoxNeedFCategory.setChecked(false);
        spinnerNeedFCategory = (Spinner) findViewById(R.id.spinnerNeedFCategory);
        spinnerNeedFCategory.setAdapter(null);
        adapter.notifyDataSetChanged();
        getCategory = "";
        Log.w("CATEGORY_RESET", "Category string has been set to: " + getCategory);

        /* Not currently required.
        checkBoxNeedFLocation.setChecked(false);
        spinnerNeedFLocation = (Spinner) findViewById(R.id.spinnerNeedFLocation);
        spinnerNeedFLocation.setAdapter(null);
        adapter.notifyDataSetChanged();
        getLocation = "";
        Log.w("LOCATION_RESET", "Location string has been set to: " + getLocation);
        */
    }


    // Method to display the books list prior to filtering
    public void RefreshNeedListViewForAllRecords()
    {

        allNeedFictionBooks = new ArrayList<String>();

        allNeedFictionBooks.addAll( sqh.populateNeedFictionResults(  )   );

        // Construct an ArrayAdapter,
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allNeedFictionBooks);

        // Link the ArrayAdapter to the listview
        listViewNeedFiction.setAdapter( adapter );

    }


    // Method to display the filtered books list
    public void FilteredListViewForAllRecords()
    {

        filteredNeedFictionBooks = new ArrayList<String>();

        // Check if all Checkboxes are ticked:
        if /*(checkBoxNeedFTitle.isChecked() && checkBoxNeedFSeries.isChecked() && checkBoxNeedFCategory.isChecked() && checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll( sqh.filterNeedFictionResultsAll( sqdb, getTitle, getSeries, getCategory, getLocation )   );
        } // Check if Title, Series and Category are ticked:
        else if*/ (checkBoxNeedFTitle.isChecked() && checkBoxNeedFSeries.isChecked() && checkBoxNeedFCategory.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsTSC(sqdb, getTitle, getSeries, getCategory));

        } // Check if Title, Series and Location are ticked:
        /*else if (checkBoxNeedFTitle.isChecked() && checkBoxNeedFSeries.isChecked() && checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsTSC(sqdb, getTitle, getSeries, getLocation));

        } // Check if Title, Category and Location are ticked:
        else if (checkBoxNeedFTitle.isChecked() && checkBoxNeedFCategory.isChecked() && checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsTCL(sqdb, getTitle, getCategory, getLocation));

        }*/ // Check if Title and Series are ticked:
        else if (checkBoxNeedFTitle.isChecked() && checkBoxNeedFSeries.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsTS(sqdb, getTitle, getSeries));

        } // Check if Title and Category are ticked:
        else if (checkBoxNeedFTitle.isChecked() && checkBoxNeedFCategory.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsTC(sqdb, getTitle, getCategory));

        } // Check if Title and Location are ticked:
        /*else if (checkBoxNeedFTitle.isChecked() && checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsTL(sqdb, getTitle, getLocation));

        } // Check if Series, Category and Location are ticked:
        else if (checkBoxNeedFSeries.isChecked() && checkBoxNeedFCategory.isChecked() && checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsSCL(sqdb, getSeries, getCategory, getLocation));

        }*/ // Check if Series and Category are ticked:
        else if (checkBoxNeedFSeries.isChecked() && checkBoxNeedFCategory.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsSC(sqdb, getSeries, getCategory));

        } // Check if Series and Location are ticked:
        /*else if (checkBoxNeedFSeries.isChecked() && checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsSL(sqdb, getSeries, getLocation));

        } // Check if Category and Location are ticked:
        else if (checkBoxNeedFCategory.isChecked() && checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsCL(sqdb, getCategory, getLocation));

        }*/ // Check if Title only is ticked:
        else if (checkBoxNeedFTitle.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsT(sqdb, getTitle));

        } // Check if Series only is ticked:
        else if (checkBoxNeedFSeries.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsS(sqdb, getSeries));

        } // Check if Category only is ticked:
        else if (checkBoxNeedFCategory.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsC(sqdb, getCategory));

        } // Check if Location only is ticked:
        /*else if (checkBoxNeedFLocation.isChecked() ) {

            filteredNeedFictionBooks.addAll(sqh.filterNeedFictionResultsT(sqdb, getLocation));

        }*/

        // Construct an ArrayAdapter,
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredNeedFictionBooks);

        // Link the ArrayAdapter to the listview
        listViewNeedFiction.setAdapter( adapter );

    }




    // Intents to Activities displaying Fiction Books
    public void intentOwnedFiction(){

        Intent intentOwnedFiction = new Intent(this, OwnedFictionActivity.class);
        startActivity(intentOwnedFiction);

    }

    public void intentNeedFiction(){

        Intent intentNeedFiction = new Intent(this, NeedFictionActivity.class);
        startActivity(intentNeedFiction);

    }

    public void intentWishListFiction(){

        Intent intentWishListFiction = new Intent(this, WishListFictionActivity.class);
        startActivity(intentWishListFiction);

    }

    public void intentBorrowedFiction(){

        Intent intentBorrowedFiction = new Intent(this, BorrowedFictionActivity.class);
        startActivity(intentBorrowedFiction);

    }

    public void intentSellFiction(){

        Intent intentSellFiction = new Intent(this, SellFictionActivity.class);
        startActivity(intentSellFiction);

    }


    // Intents to Activities displaying Non-Fiction Books
    public void intentOwnedNonFiction(){

        Intent intentOwnedNonFiction = new Intent(this, OwnedNonFictionActivity.class);
        startActivity(intentOwnedNonFiction);

    }

    public void intentNeedNonFiction(){

        Intent intentNeedNonFiction = new Intent(this, NeedNonFictionActivity.class);
        startActivity(intentNeedNonFiction);

    }

    public void intentWishListNonFiction(){

        Intent intentWishListNonFiction = new Intent(this, WishListNonFictionActivity.class);
        startActivity(intentWishListNonFiction);

    }

    public void intentBorrowedNonFiction(){

        Intent intentBorrowedNonFiction = new Intent(this, BorrowedNonFictionActivity.class);
        startActivity(intentBorrowedNonFiction);

    }

    public void intentSellNonFiction(){

        Intent intentSellNonFiction = new Intent(this, SellNonFictionActivity.class);
        startActivity(intentSellNonFiction);

    }


    //Intents to activities displaying Borrowed Books
    public void intentBorrowedWGU(){

        Intent intentBorrowedWGU = new Intent(this, BorrowedWGUActivity.class);
        startActivity(intentBorrowedWGU);

    }

    public void intentBorrowedWCBC(){

        Intent intentBorrowedWCBC = new Intent(this, BorrowedWCBCActivity.class);
        startActivity(intentBorrowedWCBC);

    }

    public void intentBorrowedCC(){

        Intent intentBorrowedCC = new Intent(this, BorrowedCCActivity.class);
        startActivity(intentBorrowedCC);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.need_fiction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (item.getItemId()) {
            case R.id.nav_owned_fiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_main), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentOwnedFiction = new Intent(MainActivity.this, OwnedFictionActivity.class);
                //startActivity(intentOwnedFiction);
                intentOwnedFiction();
                return true;
            case R.id.nav_need_fiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_whats_on), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentNeedFiction = new Intent(MainActivity.this, NeedFictionActivity.class);
                //startActivity(intentNeedFiction);
                intentNeedFiction();
                return true;
            case R.id.nav_wishlist_fiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_event_map), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentWishListFiction = new Intent(MainActivity.this, WishListFictionActivity.class);
                //startActivity(intentWishListFiction);
                intentWishListFiction();
                return true;
            case R.id.nav_borrowed_fiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_weather), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentBorrowedFiction = new Intent(MainActivity.this, BorrowedFictionActivity.class);
                //startActivity(intentBorrowedFiction);
                intentBorrowedFiction();
                return true;
            case R.id.nav_sell_fiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_daily_parade), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentSellFiction = new Intent(MainActivity.this, SellFictionActivity.class);
                //startActivity(intentSellFiction);
                intentSellFiction();
                return true;
            case R.id.nav_owned_nonfiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_competitions), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentOwnedNonFiction = new Intent(MainActivity.this, OwnedNonFictionActivity.class);
                //startActivity(intentOwnedNonFiction);
                intentOwnedNonFiction();
                return true;
            case R.id.nav_need_nonfiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_entertainment), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentNeedNonFiction = new Intent(MainActivity.this, NeedNonFictionActivity.class);
                //startActivity(intentNeedNonFiction);
                intentNeedNonFiction();
                return true;
            case R.id.nav_wishlist_nonfiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_gallery), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentWishListNonFiction = new Intent(MainActivity.this, WishListNonFictionActivity.class);
                //startActivity(intentWishListNonFiction);
                intentWishListNonFiction();
                return true;
            case R.id.nav_borrowed_nonfiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_faqs), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentBorrowedNonFiction = new Intent(MainActivity.this, BorrowedNonFictionActivity.class);
                //startActivity(intentBorrowedNonFiction);
                intentBorrowedNonFiction();
                return true;
            case R.id.nav_sell_nonfiction:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_volunteer), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentSellNonFiction = new Intent(MainActivity.this, SellNonFictionActivity.class);
                //startActivity(intentSellNonFiction);
                intentSellNonFiction();
                return true;
            case R.id.nav_borrowed_from_wgu:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_committee), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentBorrowedFromWGU = new Intent(MainActivity.this, BorrowedWGUActivity.class);
                //startActivity(intentBorrowedFromWGU);
                intentBorrowedWGU();
                return true;
            case R.id.nav_borrowed_from_wcbc:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_sponsors), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentBorrowedFromWCBC = new Intent(MainActivity.this, BorrowedWCBCActivity.class);
                //startActivity(intentBorrowedFromWCBC);
                intentBorrowedWCBC();
                return true;
            case R.id.nav_borrowed_from_cc:
                item.setChecked(true);
                //Toast.makeText(MainActivity.this, this.getString(R.string.menu_toast_sponsors), Toast.LENGTH_LONG).show();
                drawer.closeDrawer(GravityCompat.START);
                //Intent intentBorrowedFromCC = new Intent(MainActivity.this, BorrowedCCActivity.class);
                //startActivity(intentBorrowedFromCC);
                intentBorrowedCC();
                return true;

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
