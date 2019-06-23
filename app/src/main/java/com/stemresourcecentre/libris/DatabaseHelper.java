package com.stemresourcecentre.libris;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "libri.db";

    // TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
    private static final int DATABASE_VERSION = 3;

    // AVAILABLE TABLES IN THE DATABASE:
    private static final String TABLE_AUTHORS = "authors";
    private static final String TABLE_BINDING = "binding";
    private static final String TABLE_LOCATION = "book_locations";
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_BOOKS_AUTHORS = "books_authors";
    private static final String TABLE_BOOKS_EDITORS = "books_editors";
    private static final String TABLE_BOOKS_STATUS = "books_status";
    private static final String TABLE_BOOKS_TYPE = "books_type";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PUBLISHERS = "publishers";
    private static final String TABLE_SERIES = "series";

    Cursor cursor;
    Cursor c;

    DatabaseHelper(Context context)
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }


    // Queries for AddNewBookActivity
    /**
     * Query to populate the Types spinner in AddNewBookActivity
     */
    public List<String> populateBookType() {
        List<String> allBookTypes = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_type_desc " +
                "FROM books_type bt ORDER BY book_type_id";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookTypes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allBookTypes;
    }


    /**
     * Query to populate the Series spinner in AddNewBookActivity
     */
    public List<String> populateBookSeriesF() {
        List<String> allBookSeriesF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT series_name " +
                "FROM series WHERE series_book_type = 1 ORDER BY series_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookSeriesF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all non-fiction series names
        return allBookSeriesF;
    }


    public List<String> populateBookSeriesNF() {
        List<String> allBookSeriesNF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT series_name " +
                "FROM series WHERE series_book_type = 2 ORDER BY series_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookSeriesNF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all non-fiction series names
        return allBookSeriesNF;
    }

    /**
     * Query to populate the Series spinner in AddNewBookActivity
     */
    public List<String> populateBookCategoriesF() {
        List<String> allBookCategoriesF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT category_name " +
                "FROM categories WHERE category_book_type = 1 ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookCategoriesF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all non-fiction series names
        return allBookCategoriesF;
    }


    public List<String> populateBookCategoriesNF() {
        List<String> allBookCategoriesNF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT category_name " +
                "FROM categories WHERE category_book_type = 2 ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookCategoriesNF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all non-fiction series names
        return allBookCategoriesNF;
    }

    /**
     * Query to populate the Publishers spinner in AddNewBookActivity
     */
    public List<String> populateBookPublishers() {
        List<String> allBookPublishers = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT publisher_name " +
                "FROM publishers p ORDER BY publisher_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookPublishers.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allBookPublishers;
    }


    /**
     * Query to populate the Publishers spinner in AddNewBookActivity
     */
    public List<String> populateBookLocations() {
        List<String> allBookLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT publisher_name " +
                "FROM book_locations bl ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allBookLocations;
    }


    /**
     * Query to populate the Publishers spinner in AddNewBookActivity
     */
    public List<String> populateBookBindings() {
        List<String> allBookBindings = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT binding_type " +
                "FROM binding bi ORDER BY binding_type";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookBindings.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allBookBindings;
    }



    /**
     * Query to populate the Status spinner in AddNewBookActivity
     */
    public List<String> populateBookStatus() {
        List<String> allBookStatus = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_status_desc " +
                "FROM books_status s ORDER BY book_status_id";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allBookStatus.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allBookStatus;
    }






    // Queries for OwnedFictionActivity

    /**
     * Query to populate the Titles Spinner in OwnedFictionActivity
     * @return
     */
    public List<String> populateOwnedFictionTitles() {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    public List<String> populateOwnedFictionTitlesS(SQLiteDatabase sqdb, String getSeries) {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "' " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    public List<String> populateOwnedFictionTitlesC(SQLiteDatabase sqdb, String getCategory) {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    public List<String> populateOwnedFictionTitlesL(SQLiteDatabase sqdb, String getLocation) {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    public List<String> populateOwnedFictionTitlesSC(SQLiteDatabase sqdb, String getSeries, String getCategory) {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "'" +
                " AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    public List<String> populateOwnedFictionTitlesSL(SQLiteDatabase sqdb, String getSeries, String getLocation) {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "'" +
                " AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    public List<String> populateOwnedFictionTitlesCL(SQLiteDatabase sqdb, String getCategories, String getLocation) {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND c.category_name = " + "'" + getCategories + "'" +
                " AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    public List<String> populateOwnedFictionTitlesSCL(SQLiteDatabase sqdb, String getSeries, String getCategories, String getLocation) {
        List<String> allOwnedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "'" +
                " AND c.category_name = " + "'" + getCategories + "'" +
                " AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionTitles;
    }


    /**
     * Query to populate the Series Spinner in OwnedFictionActivity
     * @return
     */
    public List<String> populateOwnedFictionSeries() {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    public List<String> populateOwnedFictionSeriesT(SQLiteDatabase sqdb, String getTitle) {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    public List<String> populateOwnedFictionSeriesC(SQLiteDatabase sqdb, String getCategory) {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    public List<String> populateOwnedFictionSeriesL(SQLiteDatabase sqdb, String getLocation) {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    public List<String> populateOwnedFictionSeriesTC(SQLiteDatabase sqdb, String getTitle, String getCategory) {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    public List<String> populateOwnedFictionSeriesTL(SQLiteDatabase sqdb, String getTitle, String getLocation) {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    public List<String> populateOwnedFictionSeriesCL(SQLiteDatabase sqdb, String getCategory, String getLocation) {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND c.category_name = " + "'" + getCategory + "' " +
                "AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    public List<String> populateOwnedFictionSeriesTCL(SQLiteDatabase sqdb, String getTitle, String getCategory, String getLocation) {
        List<String> allOwnedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND c.category_name = " + "'" + getCategory + "' " +
                "AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY series_name";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allOwnedFictionSeries;
    }


    /**
     * Query to populate the Categories Spinner in OwnedFictionActivity
     * @return
     */
    public List<String> populateOwnedFictionCategories() {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    public List<String> populateOwnedFictionCategoriesT(SQLiteDatabase sqdb, String getTitle) {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    public List<String> populateOwnedFictionCategoriesS(SQLiteDatabase sqdb, String getSeries) {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "' " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    public List<String> populateOwnedFictionCategoriesL(SQLiteDatabase sqdb, String getLocation) {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    public List<String> populateOwnedFictionCategoriesTS(SQLiteDatabase sqdb,String getTitle, String getSeries) {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND s.series_name = " + "'" + getSeries + "' " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    public List<String> populateOwnedFictionCategoriesTL(SQLiteDatabase sqdb,String getTitle, String getLocation) {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    public List<String> populateOwnedFictionCategoriesSL(SQLiteDatabase sqdb,String getSeries, String getLocation) {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "' " +
                "AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    public List<String> populateOwnedFictionCategoriesTSL(SQLiteDatabase sqdb, String getTitle, String getSeries, String getLocation) {
        List<String> allOwnedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND s.series_name = " + "'" + getSeries + "' " +
                "AND bl.book_location = " + "'" + getLocation + "' " +
                "ORDER BY category_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionCategories;
    }


    /**
     * Query to populate the Locations Spinner in OwnedFictionActivity
     * @return
     */
    public List<String> populateOwnedFictionLocations() {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location" +
                "   FROM books b" +
                "   LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id)" +
                "   LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id)" +
                "   LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id)" +
                "   LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "   WHERE b.book_type = 1 AND b.book_status = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction locations
        return allOwnedFictionLocations;
    }


    public List<String> populateOwnedFictionLocationsT(SQLiteDatabase sqdb, String getTitle) {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionLocations;
    }



        public List<String> populateOwnedFictionLocationsS(SQLiteDatabase sqdb, String getSeries) {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "' " +
                "ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionLocations;
    }



            public List<String> populateOwnedFictionLocationsC(SQLiteDatabase sqdb, String getCategory) {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionLocations;
    }



    public List<String> populateOwnedFictionLocationsTS(SQLiteDatabase sqdb, String getTitle, String getSeries) {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND s.series_name = " + "'" + getSeries + "' " +
                "ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionLocations;
    }


    public List<String> populateOwnedFictionLocationsTC(SQLiteDatabase sqdb, String getTitle, String getCategory) {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionLocations;
    }


    public List<String> populateOwnedFictionLocationsSC(SQLiteDatabase sqdb, String getSeries, String getCategory) {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND s.series_name = " + "'" + getSeries + "' " +
                "AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionLocations;
    }


    public List<String> populateOwnedFictionLocationsTSC(SQLiteDatabase sqdb, String getTitle, String getSeries, String getCategory) {
        List<String> allOwnedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND b.book_title = " + "'" + getTitle + "' " +
                "AND s.series_name = " + "'" + getSeries + "' " +
                "AND c.category_name = " + "'" + getCategory + "' " +
                "ORDER BY book_location";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allOwnedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allOwnedFictionLocations;
    }





    /**
     * Query to populate the ListView in OwnedFictionActivity with all books
     * @return
     */
    public ArrayList<String> populateOwnedFictionResults( )
    {
        ArrayList<String> allOwnedFictionBooks;
        String ownedFiction = "";

        allOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
        "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    allOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return allOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title&Series&Category&Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsAll( SQLiteDatabase sqdb, String getTitle,
                                                        String getSeries, String getCategory, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
        "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsT( SQLiteDatabase sqdb, String getTitle )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
        "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title&Series Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsTS( SQLiteDatabase sqdb, String getTitle, String getSeries )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title&Series&Category Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsTSC( SQLiteDatabase sqdb, String getTitle,
                                                           String getSeries, String getCategory )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title&Series&Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsTSL( SQLiteDatabase sqdb, String getTitle,
                                                           String getSeries, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title&Category&Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsTCL( SQLiteDatabase sqdb, String getTitle,
                                                           String getCategory, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title&Category Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsTC( SQLiteDatabase sqdb, String getTitle, String getCategory )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Title&Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsTL( SQLiteDatabase sqdb, String getTitle, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Series&Category&Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsSCL( SQLiteDatabase sqdb, String getSeries, String getCategory, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Series&Category Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsSC( SQLiteDatabase sqdb, String getSeries, String getCategory )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Series&Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsSL( SQLiteDatabase sqdb, String getSeries, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Series Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsS( SQLiteDatabase sqdb, String getSeries )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Category Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsC( SQLiteDatabase sqdb, String getCategory )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsL( SQLiteDatabase sqdb, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * Query to populate the ListView in OwnedFictionActivity with all books based on Category&Location Spinner
     * @return
     */
    public ArrayList<String> filterOwnedFictionResultsCL( SQLiteDatabase sqdb, String getCategory, String getLocation )
    {
        ArrayList<String> filteredOwnedFictionBooks;
        String ownedFiction = "";

        filteredOwnedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 1 AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    ownedFiction = "";

                    String bookID = c.getString(0);
                    ownedFiction = ownedFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    ownedFiction = ownedFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    ownedFiction = ownedFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    ownedFiction = ownedFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    ownedFiction = ownedFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    ownedFiction = ownedFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    ownedFiction = ownedFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    ownedFiction = ownedFiction + authorFirst;
                    String authorMid = c.getString(7);
                    ownedFiction = ownedFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    ownedFiction = ownedFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    ownedFiction = ownedFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    ownedFiction = ownedFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    ownedFiction = ownedFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    ownedFiction = ownedFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    ownedFiction = ownedFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    ownedFiction = ownedFiction + "Book Location: " + bookLocation + "\n";



                    filteredOwnedFictionBooks.add( ownedFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredOwnedFictionBooks;

    }


    /**
     * End of OwnedFictionActivity Queries
     *
     */



    // Queries for NeedFictionActivity

    /**
     * Query to populate the Titles Spinner in NeedFictionActivity
     * @return
     */
    public List<String> populateNeedFictionTitles() {
        List<String> allNeedFictionTitles = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_title " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 " +
                "ORDER BY book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allNeedFictionTitles.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allNeedFictionTitles;
    }


    /**
     * Query to populate the Series Spinner in NeedFictionActivity
     * @return
     */
    public List<String> populateNeedFictionSeries() {
        List<String> allNeedFictionSeries = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT series_name " +
                "FROM series " +
                "WHERE series_book_type = 1 " +
                "ORDER BY series_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allNeedFictionSeries.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction titles
        return allNeedFictionSeries;
    }


    /**
     * Query to populate the Categories Spinner in NeedFictionActivity
     * @return
     */
    public List<String> populateNeedFictionCategories() {
        List<String> allNeedFictionCategories = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT category_name" +
                "   FROM books b" +
                "   LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id)" +
                "   LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id)" +
                "   LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id)" +
                "   LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "   WHERE b.book_type = 1 AND b.book_status = 2";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allNeedFictionCategories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction categories
        return allNeedFictionCategories;
    }


    /**
     * Query to populate the Locations Spinner in NeedFictionActivity
     * @return
     */
    /* Not currently required
    public List<String> populateNeedFictionLocations() {
        List<String> allNeedFictionLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location" +
                "   FROM books b" +
                "   LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id)" +
                "   LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id)" +
                "   LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id)" +
                "   LEFT JOIN categories c ON (b.book_category = c.category_id)" +
                "   WHERE b.book_type = 1 AND b.book_status = 2";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allNeedFictionLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning results of all owned fiction locations
        return allNeedFictionLocations;
    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books
     * @return
     */
    public ArrayList<String> populateNeedFictionResults( )
    {
        ArrayList<String> allNeedFictionBooks;
        String needFiction = "";

        allNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    allNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return allNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title&Series&Category&Location Spinner
     * @return
     */
    /* Not currently required
    public ArrayList<String> filterNeedFictionResultsAll( SQLiteDatabase sqdb, String getTitle,
                                                           String getSeries, String getCategory, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title Spinner
     * @return
     */
    public ArrayList<String> filterNeedFictionResultsT( SQLiteDatabase sqdb, String getTitle )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title&Series Spinner
     * @return
     */
    public ArrayList<String> filterNeedFictionResultsTS( SQLiteDatabase sqdb, String getTitle, String getSeries )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title&Series&Category Spinner
     * @return
     */
    public ArrayList<String> filterNeedFictionResultsTSC( SQLiteDatabase sqdb, String getTitle,
                                                           String getSeries, String getCategory )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title&Series&Location Spinner
     * @return
     */
    /* Not currently required:
    public ArrayList<String> filterNeedFictionResultsTSL( SQLiteDatabase sqdb, String getTitle,
                                                           String getSeries, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND s.series_name = " + "'" + getSeries + "'" + " AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title&Category&Location Spinner
     * @return
     */
    /* Not currently required:
    public ArrayList<String> filterNeedFictionResultsTCL( SQLiteDatabase sqdb, String getTitle,
                                                           String getCategory, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title&Category Spinner
     * @return
     */
    public ArrayList<String> filterNeedFictionResultsTC( SQLiteDatabase sqdb, String getTitle, String getCategory )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Title&Location Spinner
     * @return
     */
    /* Not currently required:
    public ArrayList<String> filterNeedFictionResultsTL( SQLiteDatabase sqdb, String getTitle, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "b.book_title = " + "'" + getTitle + "'" + " AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Series&Category&Location Spinner
     * @return
     */
    /* Not currently required.
    public ArrayList<String> filterNeedFictionResultsSCL( SQLiteDatabase sqdb, String getSeries, String getCategory, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Series&Category Spinner
     * @return
     */
    public ArrayList<String> filterNeedFictionResultsSC( SQLiteDatabase sqdb, String getSeries, String getCategory )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Series&Location Spinner
     * @return
     */
    /* Not currently required.
    public ArrayList<String> filterNeedFictionResultsSL( SQLiteDatabase sqdb, String getSeries, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Series Spinner
     * @return
     */
    public ArrayList<String> filterNeedFictionResultsS( SQLiteDatabase sqdb, String getSeries )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "s.series_name = " + "'" + getSeries + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Category Spinner
     * @return
     */
    public ArrayList<String> filterNeedFictionResultsC( SQLiteDatabase sqdb, String getCategory )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "c.category_name = " + "'" + getCategory + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Location Spinner
     * @return
     */
    /* Not currently required.
    public ArrayList<String> filterNeedFictionResultsL( SQLiteDatabase sqdb, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * Query to populate the ListView in NeedFictionActivity with all books based on Category&Location Spinner
     * @return
     */
    /* Not currently required.
    public ArrayList<String> filterNeedFictionResultsCL( SQLiteDatabase sqdb, String getCategory, String getLocation )
    {
        ArrayList<String> filteredNeedFictionBooks;
        String needFiction = "";

        filteredNeedFictionBooks = new ArrayList<String>();

        String selectQuery = "SELECT book_id, book_type_desc, book_title, series_name, book_series_ordinal, " +
                "author_ordinal, author_first, author_mid_initials, author_last, book_isbn, category_name, " +
                "book_published_year, publisher_name, book_edition, book_location, binding_type, book_status_desc, " +
                "book_lent_to, book_lent_date, book_date_sold, book_sold_for, book_library_due_date " +
                "FROM books b " +
                "LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id) " +
                "LEFT JOIN books_authors ba ON (b.book_id = ba.books_authors_book_id) " +
                "LEFT JOIN authors a ON (ba.books_authors_author_id = a.author_id) " +
                "LEFT JOIN series s ON (b.book_series = s.series_id) " +
                "LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id) " +
                "LEFT JOIN categories c ON (b.book_category = c.category_id) " +
                "LEFT JOIN publishers p ON (b.book_publisher = p.publisher_id) " +
                "LEFT JOIN binding bg ON (b.book_binding = bg.binding_id) " +
                "LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id) " +
                "WHERE b.book_type = 1 AND b.book_status = 2 AND " +
                "c.category_name = " + "'" + getCategory + "'" + " AND bl.book_location = " + "'" + getLocation + "'" + " " +
                "ORDER BY book_type, series_name, book_series_ordinal, author_ordinal, author_last, author_first, " +
                "author_mid_initials, book_published_year, book_title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    needFiction = "";

                    String bookID = c.getString(0);
                    needFiction = needFiction + "\n" + bookID + " - ";
                    String bookTypeDesc = c.getString(1);
                    needFiction = needFiction + bookTypeDesc + ": ";
                    String bookCategory = c.getString(10);
                    needFiction = needFiction + bookCategory + "\n";
                    String bookTitle = c.getString(2);
                    needFiction = needFiction + bookTitle;
                    String bookSeries = c.getString(3);
                    needFiction = needFiction + " - " + bookSeries;
                    String bookSeriesOrdinal = c.getString(4);
                    needFiction = needFiction + " (" + bookSeriesOrdinal + ")\n";
                    String authorOrdinal = c.getString(5);
                    needFiction = needFiction + "(Author No: " + authorOrdinal + ") ";
                    String authorFirst = c.getString(6);
                    needFiction = needFiction + authorFirst;
                    String authorMid = c.getString(7);
                    needFiction = needFiction + " " + authorMid;
                    String authorLast = c.getString(8);
                    needFiction = needFiction + " " + authorLast + "\n";
                    String bookISBN = c.getString(9);
                    needFiction = needFiction + "ISBN: " + bookISBN + " - ";
                    String publishedYear = c.getString(11);
                    needFiction = needFiction + "Published " + publishedYear + " by ";
                    String publishedBy = c.getString(12);
                    needFiction = needFiction + publishedBy;
                    String bookEdition = c.getString(13);
                    needFiction = needFiction + " - Edition: " + bookEdition;
                    String bookBinding = c.getString(15);
                    needFiction = needFiction + " - " + bookBinding + "\n";
                    String bookLocation = c.getString(14);
                    needFiction = needFiction + "Book Location: " + bookLocation + "\n";



                    filteredNeedFictionBooks.add( needFiction );

                } while (c.moveToNext());
            }
        }
        c.close();

        return filteredNeedFictionBooks;

    }*/


    /**
     * End of NeedFictionActivity Queries
     *
     */



























































    public String populateFullAuthorName (SQLiteDatabase sqdb) {
        String fullAuthorName = "";
        Cursor c = sqdb.rawQuery("SELECT * FROM " + TABLE_AUTHORS, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String author_id = c.getString(0);
                    fullAuthorName = fullAuthorName + "ID: " + author_id;
                    String author_first = c.getString(1);
                    fullAuthorName = fullAuthorName + " - " + author_first;
                    String author_mid_initials = c.getString(2);
                    fullAuthorName = fullAuthorName + " " + author_mid_initials;
                    String author_last = c.getString(3);
                    fullAuthorName = fullAuthorName + " " + author_last;

                    fullAuthorName = fullAuthorName;
                } while (c.moveToNext());
            }
        }
        c.close();

        return fullAuthorName;

    }


    public void populateBinding (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT binding_type FROM " + TABLE_BINDING, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bindingType = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public void populateLocations (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT book_location FROM " + TABLE_LOCATION, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bookLocation = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public List<String> getAllLocations() {
        List<String> allLocations = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT book_location FROM " + TABLE_LOCATION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allLocations.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Storage Locations
        return allLocations;
    }


    public List<String> getAllLocationsOwnedF() {
        List<String> allLocationsOwnedF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT DISTINCT book_location" +
                "   FROM books b" +
                "   LEFT JOIN books_type bt ON (b.book_type = bt.book_type_id)" +
                "   LEFT JOIN book_locations bl ON (b.book_stored_location = bl.location_id)" +
                "   LEFT JOIN books_status bs ON (b.book_status = bs.book_status_id)" +
                "   WHERE b.book_type = 1 AND b.book_status = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allLocationsOwnedF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Storage Locations
        return allLocationsOwnedF;
    }


    public List<String> getAllLocationsNeedF() {
        List<String> allLocationsNeedF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT book_location FROM " + TABLE_LOCATION + " WHERE book_status = 2 AND book_type = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allLocationsNeedF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Storage Locations
        return allLocationsNeedF;
    }


    public List<String> getAllLocationsOwnedNF() {
        List<String> allLocationsOwnedNF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT book_location FROM " + TABLE_LOCATION + " WHERE book_status = 1 AND book_type = 2";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allLocationsOwnedNF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Storage Locations
        return allLocationsOwnedNF;
    }


    public List<String> getAllLocationsNeedNF() {
        List<String> allLocationsNeedNF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT book_location FROM " + TABLE_LOCATION + " WHERE book_status = 2 AND book_type = 2";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allLocationsNeedNF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Storage Locations
        return allLocationsNeedNF;
    }


    public void populateStatus (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT book_status FROM " + TABLE_BOOKS_STATUS, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bookStatus = c.getString(0);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public List<String> getAllStatuses() {
        List<String> allStatuses = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT book_status FROM " + TABLE_BOOKS_STATUS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allStatuses.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Statuses
        return allStatuses;
    }


    public void populateTypes (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT book_type_desc FROM " + TABLE_BOOKS_TYPE, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bookType = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public void populateCategoriesAll (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT category_name FROM " + TABLE_CATEGORIES, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bookCategory = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public void populateCategoriesF (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT category_name FROM " + TABLE_CATEGORIES + " WHERE category_book_type = 1", null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bookCategory = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public void populateCategoriesNF (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT category_name FROM " + TABLE_CATEGORIES + " WHERE category_book_type = 2", null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bookCategory = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public List<String> getAllCategoriesF() {
        List<String> allCategoriesF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT category_name FROM " + TABLE_CATEGORIES + " WHERE category_book_type = 1";
        //String selectQuery = "SELECT category_name FROM categories WHERE category_book_type = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Cursor c = sqdb.rawQuery("SELECT category_name FROM " + TABLE_CATEGORIES + " WHERE category_book_type = 1", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allCategoriesF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Fiction Categories
        return allCategoriesF;
    }


    public List<String> getAllCategoriesNF() {
        List<String> allCategoriesNF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT category_name FROM " + TABLE_CATEGORIES + " WHERE category_book_type = 2";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allCategoriesNF.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Non-Fiction Categories
        return allCategoriesNF;
    }


    public void populatePublishers (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT publisher_name FROM " + TABLE_PUBLISHERS, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String bookPublisher = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public void populateSeriesF (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT series_name FROM " + TABLE_SERIES + " WHERE series_book_type = 1", null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String seriesName = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }


    public void populateSeriesNF (SQLiteDatabase sqdb) {
        Cursor c = sqdb.rawQuery("SELECT series_name FROM " + TABLE_SERIES + " WHERE series_book_type = 2", null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String seriesName = c.getString(1);
                } while (c.moveToNext());
            }
        }
        c.close();

    }



    public List<String> getAllSeriesF() {
        List<String> allSeriesF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT series_name FROM " + TABLE_SERIES + " WHERE series_book_type = 1";
        //String selectQuery = "SELECT series_name FROM series WHERE series_book_type = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Cursor c = sqdb.rawQuery("SELECT series_name FROM " + TABLE_SERIES + " WHERE series_book_type = 1", null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allSeriesF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Fiction Series
        return allSeriesF;
    }


    public List<String> getAllSeriesNF() {
        List<String> allSeriesNF = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT series_name FROM " + TABLE_SERIES + " WHERE series_book_type = 2";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                allSeriesNF.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Non-Fiction Series
        return allSeriesNF;
    }





}
