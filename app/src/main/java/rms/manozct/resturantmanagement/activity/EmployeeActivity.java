package rms.manozct.resturantmanagement.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.fragment.CashierFragment;
import rms.manozct.resturantmanagement.fragment.EmployeeFragment;
import rms.manozct.resturantmanagement.fragment.EmployeeFunctionsFragment;
import rms.manozct.resturantmanagement.fragment.EmployeeListFragment;
import rms.manozct.resturantmanagement.fragment.LoginFragment;
import rms.manozct.resturantmanagement.fragment.MainFragment;
//import rms.manozct.resturantmanagement.fragment.EmployeeListFragment;
import rms.manozct.resturantmanagement.fragment.MenuFragment;
import rms.manozct.resturantmanagement.fragment.OrderFragment;
import rms.manozct.resturantmanagement.fragment.PlaceOrderFragment;
import rms.manozct.resturantmanagement.fragment.SelectSubmenuFragment;
import rms.manozct.resturantmanagement.fragment.SubMenuFragment;
import rms.manozct.resturantmanagement.fragment.TableFragment;
import rms.manozct.resturantmanagement.model.Employee;
import rms.manozct.resturantmanagement.model.Role;
//
public class EmployeeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    EmployeeFragment.OnFragmentInteractionListener,
                    MainFragment.OnFragmentInteractionListener,
        OrderFragment.OnFragmentInteractionListener,
        PlaceOrderFragment.OnFragmentInteractionListener,
        SubMenuFragment.OnFragmentInteractionListener,
        SelectSubmenuFragment.OnFragmentInteractionListener,
        EmployeeFunctionsFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener,
        CashierFragment.OnFragmentInteractionListener

                    {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private static FragmentManager fragmentManager;
    private boolean doubleBackToExitPressedOnce = false;

    private Toolbar toolbar;

    public static Employee loginEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_dashboard);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int backCount = fragmentManager.getBackStackEntryCount();
            System.out.println("back count:"+backCount);
            if (backCount>0){
                //getCurrentFragment(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName());
                super.onBackPressed();
            }else {
                //Check if backBtn is already pressed.
                if (doubleBackToExitPressedOnce) {
                    exitApp();
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press back again if you wish to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        changeLayout(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeLayout(int id){
        switch (id) {
          //my Account
            case R.id.nav_account:
                //if (Role.MANAGER==loginEmployee.getRole()){
                    replaceFragment(new EmployeeFragment());
                /*}else {
                    Toast.makeText(this, "You are not authorized to Add user", Toast.LENGTH_SHORT).show();
                }*/
                break;
            //login
            case R.id.nav_login:
                replaceFragment(new LoginFragment());
                break;
            //for home menu
            case R.id.nav_dashboard:
                replaceFragment(new MainFragment());
                break;
            //for user menu
            case R.id.nav_category:
                replaceFragment(new MainFragment());
                break;

            //inventory menu
            case R.id.nav_product:
                replaceFragment(new CashierFragment());
                break;
            //menu
            case R.id.nav_wishlist:
                replaceFragment(new MenuFragment());
                break;
            //table
            case R.id.nav_table:
                replaceFragment(new TableFragment());
                break;
            //
            case R.id.nav_report:
                replaceFragment(new MenuFragment());
                break;


        }
    }

    public static void replaceFragment(Fragment newFragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, newFragment);
        String backStateName = newFragment.getClass().getName();
        System.out.println("Fragment tag:"+backStateName);
        fragmentTransaction.addToBackStack(backStateName);    //Add previous state to backstack
        fragmentTransaction.commit();
    }

    /*@Override
    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawer.setDrawerLockMode(lockMode);
        toggle.setDrawerIndicatorEnabled(enabled);
    }*/

    private void exitApp(){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
