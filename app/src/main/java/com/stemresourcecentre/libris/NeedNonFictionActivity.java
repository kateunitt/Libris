package com.stemresourcecentre.libris;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NeedNonFictionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_non_fiction);
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
        getMenuInflater().inflate(R.menu.need_non_fiction, menu);
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
