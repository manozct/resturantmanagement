package rms.manozct.resturantmanagement.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.adapter.BadgeDrawable;
import rms.manozct.resturantmanagement.adapter.OnListFragmentInteractionListener;
import rms.manozct.resturantmanagement.fragment.CartFragment;
import rms.manozct.resturantmanagement.fragment.CashierFragment;
import rms.manozct.resturantmanagement.fragment.EmployeeFragment;
import rms.manozct.resturantmanagement.fragment.EmployeeFunctionsFragment;
import rms.manozct.resturantmanagement.fragment.EmployeeListFragment;
import rms.manozct.resturantmanagement.fragment.LoginFragment;
import rms.manozct.resturantmanagement.fragment.MainFragment;
import rms.manozct.resturantmanagement.fragment.MenuFragment;
import rms.manozct.resturantmanagement.fragment.MenuListFragment;
import rms.manozct.resturantmanagement.fragment.OrderFragment;
import rms.manozct.resturantmanagement.fragment.PlaceOrderFragment;
import rms.manozct.resturantmanagement.fragment.SelectSubmenuFragment;
import rms.manozct.resturantmanagement.fragment.SubMenuFragment;
import rms.manozct.resturantmanagement.fragment.SubMenuListFragment;
import rms.manozct.resturantmanagement.fragment.TableFragment;
import rms.manozct.resturantmanagement.model.Employee;
import rms.manozct.resturantmanagement.model.SubMenu;

//import rms.manozct.resturantmanagement.fragment.EmployeeListFragment;

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
        CashierFragment.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener,
        EmployeeListFragment.OnFragmentInteractionListener,
        SubMenuListFragment.OnFragmentInteractionListener,
        CartFragment.OnFragmentInteractionListener,
        OnListFragmentInteractionListener,
        TableFragment.OnFragmentInteractionListener,
        MenuListFragment.OnFragmentInteractionListener
{

    public static final int SELECT_PHOTO = 100;

    //Cart Variables
    MenuItem itemCart;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private static FragmentManager fragmentManager;
    public static Employee loginEmployee;
    private boolean doubleBackToExitPressedOnce = false;


    private static Toolbar toolbar;

    MenuItem navAccnt;
    MenuItem navUser;
    MenuItem navPlaceOrder;
    MenuItem navMenu;
    MenuItem navInventory;
    MenuItem navSubMenu;
    MenuItem navReport;

    TextView navLogout;
    TextView navUsername;

    public List<SubMenu> cartList;

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

        //navigationView.setCheckedItem(R.id.nav_login);

        cartList = new ArrayList<>();

        Menu menuNav = navigationView.getMenu();

        navAccnt = menuNav.findItem(R.id.nav_account);
        navInventory = menuNav.findItem(R.id.nav_inventory);
        navUser = menuNav.findItem(R.id.nav_user);
        navMenu = menuNav.findItem(R.id.nav_menu);
        navSubMenu = menuNav.findItem(R.id.nav_sub_menu);
        navReport = menuNav.findItem(R.id.nav_report);
        navPlaceOrder = menuNav.findItem(R.id.nav_place_order);

        View hView =  navigationView.getHeaderView(0);

        navLogout = (TextView) hView.findViewById(R.id.nav_logout);
        navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        navUsername = (TextView) hView.findViewById(R.id.nav_email);

        initDrawerBeforeLogin();
        if (loginEmployee!=null){
            hideLoginNav();
        }else {
            showLoginNav();
        }
        replaceFragment(new LoginFragment());
    }

    public void logout(){
        showLoginNav();
        loginEmployee = null;
        initDrawerBeforeLogin();
        cartList.clear();

        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        replaceFragment(new LoginFragment());
        drawer.closeDrawer(GravityCompat.START);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int backCount = fragmentManager.getBackStackEntryCount();
            System.out.println("back count:" + backCount);
            if (backCount > 0) {
                //getCurrentFragment(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName());
                super.onBackPressed();
            } else {
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
        itemCart = menu.findItem(R.id.action_cart);
        setBadgeCount(null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_cart:
                showCartFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        changeLayout(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeLayout(int id) {
        switch (id) {
            //my Account
            case R.id.nav_account:
                //if (Role.MANAGER==loginEmployee.getRole()){
                //replaceFragment(new EmployeeListFragment());
                /*}else {
                    Toast.makeText(this, "You are not authorized to Add user", Toast.LENGTH_SHORT).show();
                }*/
                break;
            //login
            /*case R.id.nav_login:
                replaceFragment(new LoginFragment());
                break;*/
            //for home menu
            case R.id.nav_place_order:
                replaceFragment(new SubMenuListFragment());
                break;
            //for user menu
            case R.id.nav_user:
                //replaceFragment(new SubMenuListFragment());
                replaceFragment(new EmployeeListFragment());
                break;

            //inventory menu
            case R.id.nav_inventory:
                replaceFragment(new CashierFragment());
                break;
            //menu
            case R.id.nav_menu:
                replaceFragment(new MenuListFragment());
                break;
            //table
            case R.id.nav_sub_menu:
                replaceFragment(new SubMenuFragment());
                break;
            //
            case R.id.nav_report:
                //replaceFragment(new EmployeeListFragment());
                break;


        }
    }

    public static void replaceFragment(Fragment newFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, newFragment);
        String backStateName = newFragment.getClass().getName();
        System.out.println("Fragment tag:" + backStateName);
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

    public boolean checkIfAddedToCart(SubMenu subMenu) {
        return cartList.contains(subMenu);
    }

    public void addToCart(SubMenu subMenu) {
        if (!checkIfAddedToCart(subMenu)) {
            cartList.add(subMenu);
            System.out.println("Add Cart list size:" + cartList.size());
            setBadgeCount(Integer.toString(cartList.size()));
        }
    }

    public void removeFromCart(SubMenu subMenu){
        cartList.remove(subMenu);
        System.out.println("Remove Cart list size:"+cartList.size());
        setBadgeCount(Integer.toString(cartList.size()));
    }

    public void setBadgeCount(String count) {
        BadgeDrawable badge;
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(this);
        }

        if (count!=null){
            badge.setCount(count);
        }
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    public void showCartFragment(){
        if (cartList.size()<1){
            Toast.makeText(this, "No item added in cart!!!", Toast.LENGTH_SHORT).show();
        }else {
            replaceFragment(new CartFragment());
        }
    }

    private void exitApp() {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static void setTitle(String title){
        toolbar.setTitle(title);
        System.out.println("TItle movie:"+toolbar.getTitle());
    }

    public void hideLoginNav(){
        navLogout.setVisibility(View.VISIBLE);
        navAccnt.setVisible(true);
        navUsername.setText(loginEmployee.getEmpUserName());
    }

    public void showLoginNav(){
        navAccnt.setVisible(false);
        navLogout.setVisibility(View.GONE);
        navUsername.setText(" ");
    }


    public void customizeNavDrawer(){
        switch (loginEmployee.getRole()){
            case MANAGER:
                roleManager();
                break;
            case CASHIER:
                roleCashier();
                break;
            case WAITER:
                roleWaiter();
                break;
        }
    }
    public void initDrawerBeforeLogin(){
        navMenu.setVisible(false);
        navSubMenu.setVisible(false);
        navAccnt.setVisible(false);
        navUser.setVisible(false);
        navReport.setVisible(false);
        navInventory.setVisible(false);
        navPlaceOrder.setVisible(false);
    }

    public void roleWaiter(){
        initDrawerBeforeLogin();
        navAccnt.setVisible(true);
        navPlaceOrder.setVisible(true);
    }

    public void roleManager(){
        navMenu.setVisible(true);
        navSubMenu.setVisible(true);
        navAccnt.setVisible(true);
        navUser.setVisible(true);
        navReport.setVisible(true);
        navInventory.setVisible(true);
        navPlaceOrder.setVisible(true);
    }

    public void roleCashier(){
        initDrawerBeforeLogin();
        navAccnt.setVisible(true);
    }

    @Override
    public void onListFragmentInteraction(Object item) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    System.out.println("Selected Image URl"+selectedImage);
                    Toast.makeText(this, "Got Image:"+selectedImage.toString(), Toast.LENGTH_LONG);
                    /*InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);*/
                }
        }
    }
}
