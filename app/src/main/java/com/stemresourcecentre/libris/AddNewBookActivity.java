package com.stemresourcecentre.libris;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class AddNewBookActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DATABASE_PATH = "/data/data/com.stemresourcecentre.libris/databases/";
    private static final String DATABASE_PATH2 = "/data/data/com.stemresourccecentre.libris/databases"; // no/ at end of path !!!
    private static final String DATABASE_NAME = "libri.db";
    private static final String LOG_TAG = "LIBRI_DB";

    Context ctx;

    DatabaseHelper sqh;
    SQLiteDatabase sqdb;

    CheckBox checkBoxOwnedFTitle, checkBoxOwnedFSeries, checkBoxOwnedFCategory, checkBoxOwnedFLocation;
    Spinner spinnerOwnedFTitle, spinnerOwnedFSeries, spinnerOwnedFCategory, spinnerOwnedFLocation;

    String getTitle = "", getSeries = "", getCategory = "", getLocation = "", checkboxesAnalyser;

    Button buttonOwnedFApplyFilter, buttonOwnedFResetFilter, buttonOwnedFListAll;

    ListView listViewOwnedFiction;
    ArrayList<String> allOwnedFictionBooks, filteredOwnedFictionBooks;
    ArrayAdapter adapter;

    Cursor cursor;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

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
        getLocation = "";
        checkboxesAnalyser = "";

        //CheckBox checkBoxOwnedFTitle = (CheckBox) findViewById(R.id.checkBoxOwnedFTitle);
        //CheckBox checkBoxOwnedFSeries = (CheckBox) findViewById(R.id.checkBoxOwnedFSeries);
        //CheckBox checkBoxOwnedFCategory = (CheckBox) findViewById(R.id.checkBoxOwnedFCategory);
        //CheckBox checkBoxOwnedFLocation = (CheckBox) findViewById(R.id.checkBoxOwnedFLocation);

        addListenerOnCheckBoxOwnedFTitle();
        addListenerOnCheckBoxOwnedFSeries();
        addListenerOnCheckBoxOwnedFCategory();
        addListenerOnCheckBoxOwnedFLocation();

        listViewOwnedFiction = (ListView) findViewById(R.id.listViewOwnedFiction);

        allOwnedFictionBooks = new ArrayList<String>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allOwnedFictionBooks);

        listViewOwnedFiction.setAdapter(adapter);

        buttonOwnedFListAll = (Button) findViewById(R.id.buttonOwnedFListAll);
        buttonOwnedFListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshListViewForAllRecords();
            }
        });

        buttonOwnedFApplyFilter = (Button) findViewById(R.id.buttonOwnedFApplyFilter);
        buttonOwnedFApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilteredListViewForAllRecords();
            }
        });

        buttonOwnedFResetFilter = (Button) findViewById(R.id.buttonOwnedFResetFilter);
        buttonOwnedFResetFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    /**
     * Populate Title Spinners.
     */
    private void loadSpinnerAddNewBookTypeData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitles();

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFTitleDataS() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitlesS(sqdb, getSeries);

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFTitleDataC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitlesC(sqdb, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFTitleDataL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitlesL(sqdb, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFTitleDataSC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitlesSC(sqdb, getSeries, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFTitleDataSL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitlesSL(sqdb, getSeries, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFTitleDataCL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitlesSL(sqdb, getCategory, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFTitleDataSCL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements

        List<String> allOwnedFictionTitles = db.populateOwnedFictionTitlesSCL(sqdb, getSeries, getCategory, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFTitle.setPrompt("Select a book title...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionTitles);

        // Drop down layout style - list view with radio button
        dataAdapterTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFTitle.setAdapter(dataAdapterTitle);

        spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



    /**
     * Populate Series Spinners.
     */
    private void loadSpinnerOwnedFSeriesData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeries();

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    private void loadSpinnerOwnedFSeriesDataT() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeriesT(sqdb, getTitle);

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFSeriesDataC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeriesC(sqdb, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFSeriesDataL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeriesL(sqdb, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFSeriesDataTC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeriesTC(sqdb, getTitle, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFSeriesDataTL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeriesTL(sqdb, getTitle, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFSeriesDataCL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeriesCL(sqdb, getCategory, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFSeriesDataTCL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionSeries = db.populateOwnedFictionSeriesTCL(sqdb, getTitle, getCategory, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFSeries.setPrompt("Select a book series...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterSeries = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionSeries);

        // Drop down layout style - list view with radio button
        dataAdapterSeries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFSeries.setAdapter(dataAdapterSeries);

        spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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




    private void loadSpinnerOwnedFCategoryData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategories();

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFCategoryDataT() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategoriesT(sqdb, getTitle);

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFCategoryDataS() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategoriesS(sqdb, getSeries);

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFCategoryDataL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategoriesL(sqdb, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFCategoryDataTS() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategoriesTS(sqdb, getTitle, getSeries);

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFCategoryDataTL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategoriesTL(sqdb, getTitle, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    private void loadSpinnerOwnedFCategoryDataSL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategoriesSL(sqdb, getSeries, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void loadSpinnerOwnedFCategoryDataTSL() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionCategories = db.populateOwnedFictionCategoriesTSL(sqdb, getTitle, getSeries, getLocation);

        // Use prompt on Spinner
        spinnerOwnedFCategory.setPrompt("Select a category...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCategoryF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionCategories);

        // Drop down layout style - list view with radio button
        dataAdapterCategoryF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFCategory.setAdapter(dataAdapterCategoryF);

        spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    private void loadSpinnerOwnedFLocationData() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocations();

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }

    private void loadSpinnerOwnedFLocationDataT() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocationsT(sqdb, getTitle);

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }


    private void loadSpinnerOwnedFLocationDataS() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocationsS(sqdb, getSeries);

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }


    private void loadSpinnerOwnedFLocationDataC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocationsC(sqdb, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }


    private void loadSpinnerOwnedFLocationDataTS() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocationsTS(sqdb, getTitle, getSeries);

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }


    private void loadSpinnerOwnedFLocationDataTC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocationsTC(sqdb, getTitle, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }


    private void loadSpinnerOwnedFLocationDataSC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocationsSC(sqdb, getSeries, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }


    private void loadSpinnerOwnedFLocationDataTSC() {
        // Database Helper
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop-down elements
        List<String> allOwnedFictionLocations = db.populateOwnedFictionLocationsTSC(sqdb, getTitle, getSeries, getCategory);

        // Use prompt on Spinner
        spinnerOwnedFLocation.setPrompt("Select a Location...");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allOwnedFictionLocations);

        // Drop down layout style - list view with radio button
        dataAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the data adapter to the spinner
        spinnerOwnedFLocation.setAdapter(dataAdapterLocation);

        spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }



    //Methods to control the Spinners
    public void addListenerOnCheckBoxOwnedFTitle() {

        checkBoxOwnedFTitle = (CheckBox) findViewById(R.id.checkBoxOwnedFTitle);

        checkBoxOwnedFTitle.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerOwnedFTitle = (Spinner) findViewById(R.id.spinnerOwnedFTitle);

                    if (checkBoxOwnedFSeries.isChecked()){
                        loadSpinnerOwnedFTitleDataS();
                        loadSpinnerOwnedFTitleDataS();
                    } else if (checkBoxOwnedFCategory.isChecked()){
                        loadSpinnerOwnedFTitleDataC();
                        loadSpinnerOwnedFTitleDataC();
                    } else if (checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFTitleDataL();
                        loadSpinnerOwnedFTitleDataL();
                    } else if (checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFCategory.isChecked()) {
                        loadSpinnerOwnedFTitleDataSC();
                        loadSpinnerOwnedFTitleDataSC();
                    } else if (checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFTitleDataSL();
                        loadSpinnerOwnedFTitleDataSL();
                    } else if (checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFCategory.isChecked() && checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFTitleDataSCL();
                        loadSpinnerOwnedFTitleDataSCL();
                    } else {
                        loadSpinnerOwnedFTitleData();
                        loadSpinnerOwnedFTitleData();
                    }

                    spinnerOwnedFTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    spinnerOwnedFTitle = (Spinner) findViewById(R.id.spinnerOwnedFTitle);
                    spinnerOwnedFTitle.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getTitle = "";
                    Log.w("TITLE_RESET", "Title string has been set to: " + getTitle);
                }

            }
        });

    }


    public void addListenerOnCheckBoxOwnedFSeries() {

        checkBoxOwnedFSeries = (CheckBox) findViewById(R.id.checkBoxOwnedFSeries);

        checkBoxOwnedFSeries.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerOwnedFSeries = (Spinner) findViewById(R.id.spinnerOwnedFSeries);
                    if (checkBoxOwnedFTitle.isChecked()){
                        loadSpinnerOwnedFSeriesDataT();
                        loadSpinnerOwnedFSeriesDataT();
                    } else if (checkBoxOwnedFCategory.isChecked()){
                        loadSpinnerOwnedFSeriesDataC();
                        loadSpinnerOwnedFSeriesDataC();
                    } else if (checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFSeriesDataL();
                        loadSpinnerOwnedFSeriesDataL();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFCategory.isChecked()) {
                        loadSpinnerOwnedFSeriesDataTC();
                        loadSpinnerOwnedFSeriesDataTC();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFSeriesDataTL();
                        loadSpinnerOwnedFSeriesDataTL();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFCategory.isChecked() && checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFSeriesDataTCL();
                        loadSpinnerOwnedFSeriesDataTCL();
                    } else {
                        loadSpinnerOwnedFSeriesData();
                        loadSpinnerOwnedFSeriesData();
                    }

                    spinnerOwnedFSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    spinnerOwnedFSeries = (Spinner) findViewById(R.id.spinnerOwnedFSeries);
                    spinnerOwnedFSeries.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getSeries = "";
                    Log.w("SERIES_RESET", "Series string has been set to: " + getSeries);
                }

            }
        });

    }


    public void addListenerOnCheckBoxOwnedFCategory() {

        checkBoxOwnedFCategory = (CheckBox) findViewById(R.id.checkBoxOwnedFCategory);

        checkBoxOwnedFCategory.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerOwnedFCategory = (Spinner) findViewById(R.id.spinnerOwnedFCategory);
                    if (checkBoxOwnedFTitle.isChecked()){
                        loadSpinnerOwnedFCategoryDataT();
                        loadSpinnerOwnedFCategoryDataT();
                    } else if (checkBoxOwnedFSeries.isChecked()){
                        loadSpinnerOwnedFCategoryDataS();
                        loadSpinnerOwnedFCategoryDataS();
                    } else if (checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFCategoryDataL();
                        loadSpinnerOwnedFCategoryDataL();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked()) {
                        loadSpinnerOwnedFCategoryDataTS();
                        loadSpinnerOwnedFCategoryDataTS();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFCategoryDataTL();
                        loadSpinnerOwnedFCategoryDataTL();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFLocation.isChecked()) {
                        loadSpinnerOwnedFCategoryDataTL();
                        loadSpinnerOwnedFCategoryDataTL();
                    } else {
                        loadSpinnerOwnedFCategoryData();
                        loadSpinnerOwnedFCategoryData();
                    }

                    spinnerOwnedFCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    spinnerOwnedFCategory = (Spinner) findViewById(R.id.spinnerOwnedFCategory);
                    spinnerOwnedFCategory.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getCategory = "";
                    Log.w("CATEGORY_RESET", "Category string has been set to: " + getCategory);
                }

            }
        });

    }


    public void addListenerOnCheckBoxOwnedFLocation() {

        checkBoxOwnedFLocation = (CheckBox) findViewById(R.id.checkBoxOwnedFLocation);

        checkBoxOwnedFLocation.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {

                    spinnerOwnedFLocation = (Spinner) findViewById(R.id.spinnerOwnedFLocation);
                    if (checkBoxOwnedFTitle.isChecked()){
                        loadSpinnerOwnedFLocationDataT();
                        loadSpinnerOwnedFLocationDataT();
                    } else if (checkBoxOwnedFSeries.isChecked()){
                        loadSpinnerOwnedFLocationDataS();
                        loadSpinnerOwnedFLocationDataS();
                    } else if (checkBoxOwnedFCategory.isChecked()) {
                        loadSpinnerOwnedFLocationDataC();
                        loadSpinnerOwnedFLocationDataC();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked()) {
                        loadSpinnerOwnedFLocationDataTS();
                        loadSpinnerOwnedFLocationDataTS();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFCategory.isChecked()) {
                        loadSpinnerOwnedFLocationDataTC();
                        loadSpinnerOwnedFLocationDataTC();
                    } else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFCategory.isChecked()) {
                        loadSpinnerOwnedFLocationDataTSC();
                        loadSpinnerOwnedFLocationDataTSC();
                    } else {
                        loadSpinnerOwnedFLocationData();
                        loadSpinnerOwnedFLocationData();
                    }

                    spinnerOwnedFLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    spinnerOwnedFLocation = (Spinner) findViewById(R.id.spinnerOwnedFLocation);
                    spinnerOwnedFLocation.setAdapter(null);
                    adapter.notifyDataSetChanged();
                    getLocation = "";
                    Log.w("LOCATION_RESET", "Location string has been set to: " + getLocation);
                }

            }
        });

    }


    // Methods to reset Spinners and Filters
    public void resetSpinnerTitleData(View view) {
        loadSpinnerOwnedFTitleData();
    }

    public void resetSpinnerSeriesData(View view) {
        loadSpinnerOwnedFSeriesData();
    }

    public void resetSpinnerCategoryData(View view) {
        loadSpinnerOwnedFCategoryData();
    }

    public void resetSpinnerLocationData(View view) {
        loadSpinnerOwnedFLocationData();
    }

    public void resetFilters() {
        checkBoxOwnedFTitle.setChecked(false);
        spinnerOwnedFTitle = (Spinner) findViewById(R.id.spinnerOwnedFTitle);
        spinnerOwnedFTitle.setAdapter(null);
        adapter.notifyDataSetChanged();
        getTitle = "";
        Log.w("TITLE_RESET", "Title string has been set to: " + getTitle);

        checkBoxOwnedFSeries.setChecked(false);
        spinnerOwnedFSeries = (Spinner) findViewById(R.id.spinnerOwnedFSeries);
        spinnerOwnedFSeries.setAdapter(null);
        adapter.notifyDataSetChanged();
        getSeries = "";
        Log.w("SERIES_RESET", "Series string has been set to: " + getSeries);

        checkBoxOwnedFCategory.setChecked(false);
        spinnerOwnedFCategory = (Spinner) findViewById(R.id.spinnerOwnedFCategory);
        spinnerOwnedFCategory.setAdapter(null);
        adapter.notifyDataSetChanged();
        getCategory = "";
        Log.w("CATEGORY_RESET", "Category string has been set to: " + getCategory);

        checkBoxOwnedFLocation.setChecked(false);
        spinnerOwnedFLocation = (Spinner) findViewById(R.id.spinnerOwnedFLocation);
        spinnerOwnedFLocation.setAdapter(null);
        adapter.notifyDataSetChanged();
        getLocation = "";
        Log.w("LOCATION_RESET", "Location string has been set to: " + getLocation);
    }


    // Method to display the books list prior to filtering
    public void RefreshListViewForAllRecords() {

        allOwnedFictionBooks = new ArrayList<String>();

        allOwnedFictionBooks.addAll(sqh.populateOwnedFictionResults());

        // Construct an ArrayAdapter,
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allOwnedFictionBooks);

        // Link the ArrayAdapter to the listview
        listViewOwnedFiction.setAdapter(adapter);

    }


    // Method to display the filtered books list
    public void FilteredListViewForAllRecords() {

        filteredOwnedFictionBooks = new ArrayList<String>();

        // Check if all Checkboxes are ticked:
        if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFCategory.isChecked() && checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsAll(sqdb, getTitle, getSeries, getCategory, getLocation));
        } // Check if Title, Series and Category are ticked:
        else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFCategory.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsTSC(sqdb, getTitle, getSeries, getCategory));

        } // Check if Title, Series and Location are ticked:
        else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsTSC(sqdb, getTitle, getSeries, getLocation));

        } // Check if Title, Category and Location are ticked:
        else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFCategory.isChecked() && checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsTCL(sqdb, getTitle, getCategory, getLocation));

        } // Check if Title and Series are ticked:
        else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFSeries.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsTS(sqdb, getTitle, getSeries));

        } // Check if Title and Category are ticked:
        else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFCategory.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsTC(sqdb, getTitle, getCategory));

        } // Check if Title and Location are ticked:
        else if (checkBoxOwnedFTitle.isChecked() && checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsTL(sqdb, getTitle, getLocation));

        } // Check if Series, Category and Location are ticked:
        else if (checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFCategory.isChecked() && checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsSCL(sqdb, getSeries, getCategory, getLocation));

        } // Check if Series and Category are ticked:
        else if (checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFCategory.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsSC(sqdb, getSeries, getCategory));

        } // Check if Series and Location are ticked:
        else if (checkBoxOwnedFSeries.isChecked() && checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsSL(sqdb, getSeries, getLocation));

        } // Check if Category and Location are ticked:
        else if (checkBoxOwnedFCategory.isChecked() && checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsCL(sqdb, getCategory, getLocation));

        } // Check if Title only is ticked:
        else if (checkBoxOwnedFTitle.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsT(sqdb, getTitle));

        } // Check if Series only is ticked:
        else if (checkBoxOwnedFSeries.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsS(sqdb, getSeries));

        } // Check if Category only is ticked:
        else if (checkBoxOwnedFCategory.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsC(sqdb, getCategory));

        } // Check if Location only is ticked:
        else if (checkBoxOwnedFLocation.isChecked()) {

            filteredOwnedFictionBooks.addAll(sqh.filterOwnedFictionResultsT(sqdb, getLocation));

        }

        // Construct an ArrayAdapter,
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredOwnedFictionBooks);

        // Link the ArrayAdapter to the listview
        listViewOwnedFiction.setAdapter(adapter);

    }


    /**
     * Intents for changing Activities.
     */

    // Intents to Return Home
    public void intentHome() {
        Intent intentHome = new Intent(this, MainActivity.class);
        startActivity(intentHome);
    }


    // Intents to Activities displaying Fiction Books
    public void intentOwnedFiction() {
        Intent intentOwnedFiction = new Intent(this, AddNewBookActivity.class);
        startActivity(intentOwnedFiction);
    }

    public void intentNeedFiction() {
        Intent intentNeedFiction = new Intent(this, NeedFictionActivity.class);
        startActivity(intentNeedFiction);
    }

    public void intentWishListFiction() {
        Intent intentWishListFiction = new Intent(this, WishListFictionActivity.class);
        startActivity(intentWishListFiction);
    }

    public void intentBorrowedFiction() {
        Intent intentBorrowedFiction = new Intent(this, BorrowedFictionActivity.class);
        startActivity(intentBorrowedFiction);
    }

    public void intentSellFiction() {
        Intent intentSellFiction = new Intent(this, SellFictionActivity.class);
        startActivity(intentSellFiction);
    }


    // Intents to Activities displaying Non-Fiction Books
    public void intentOwnedNonFiction() {
        Intent intentOwnedNonFiction = new Intent(this, OwnedNonFictionActivity.class);
        startActivity(intentOwnedNonFiction);
    }

    public void intentNeedNonFiction() {
        Intent intentNeedNonFiction = new Intent(this, NeedNonFictionActivity.class);
        startActivity(intentNeedNonFiction);
    }

    public void intentWishListNonFiction() {
        Intent intentWishListNonFiction = new Intent(this, WishListNonFictionActivity.class);
        startActivity(intentWishListNonFiction);
    }

    public void intentBorrowedNonFiction() {
        Intent intentBorrowedNonFiction = new Intent(this, BorrowedNonFictionActivity.class);
        startActivity(intentBorrowedNonFiction);
    }

    public void intentSellNonFiction() {
        Intent intentSellNonFiction = new Intent(this, SellNonFictionActivity.class);
        startActivity(intentSellNonFiction);
    }


    //Intents to activities displaying Borrowed Books
    public void intentBorrowedWGU() {
        Intent intentBorrowedWGU = new Intent(this, BorrowedWGUActivity.class);
        startActivity(intentBorrowedWGU);
    }

    public void intentBorrowedWCBC() {
        Intent intentBorrowedWCBC = new Intent(this, BorrowedWCBCActivity.class);
        startActivity(intentBorrowedWCBC);
    }

    public void intentBorrowedCC() {
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
        getMenuInflater().inflate(R.menu.owned_fiction, menu);
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
            case R.id.nav_go_home:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentHome();
                return true;
            case R.id.nav_owned_fiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentOwnedFiction();
                return true;
            case R.id.nav_need_fiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentNeedFiction();
                return true;
            case R.id.nav_wishlist_fiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentWishListFiction();
                return true;
            case R.id.nav_borrowed_fiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentBorrowedFiction();
                return true;
            case R.id.nav_sell_fiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentSellFiction();
                return true;
            case R.id.nav_owned_nonfiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentOwnedNonFiction();
                return true;
            case R.id.nav_need_nonfiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentNeedNonFiction();
                return true;
            case R.id.nav_wishlist_nonfiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentWishListNonFiction();
                return true;
            case R.id.nav_borrowed_nonfiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentBorrowedNonFiction();
                return true;
            case R.id.nav_sell_nonfiction:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentSellNonFiction();
                return true;
            case R.id.nav_borrowed_from_wgu:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentBorrowedWGU();
                return true;
            case R.id.nav_borrowed_from_wcbc:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentBorrowedWCBC();
                return true;
            case R.id.nav_borrowed_from_cc:
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                intentBorrowedCC();
                return true;

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
