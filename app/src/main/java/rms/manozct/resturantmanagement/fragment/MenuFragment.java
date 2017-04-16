package rms.manozct.resturantmanagement.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.model.Employee;
import rms.manozct.resturantmanagement.model.Menu;
import rms.manozct.resturantmanagement.model.Role;
import rms.manozct.resturantmanagement.util.Util;


public class MenuFragment extends Fragment {
  private EditText menuText;
    private Button submitBtn;
    private Button deleteBtn;
    private Menu menu;
    private boolean isUpdate;



    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundleMenu = getArguments();
        if (bundleMenu!=null){
           // System.out.println("Inside onCreate of MenuFragment");
            menu = (Menu) bundleMenu.getSerializable("menuKey");
            //System.out.println("Menu id:"+menu.getMenuId());
            if (menu!=null){
                isUpdate = true;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_menu, container, false);
        menuText=(EditText) view.findViewById(R.id.menuTxt);
        submitBtn=(Button) view.findViewById(R.id.submitBtn);
        deleteBtn=(Button) view.findViewById(R.id.deleteBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menuName=menuText.getText().toString();
               // Toast.makeText(MenuFragment.this.getActivity(),"Menu:"+menuName,Toast.LENGTH_SHORT).show();
                addMenu();
                EmployeeActivity.replaceFragment(new MenuListFragment());

            }
        });
        if (isUpdate){
            setUpdateMenu();
        }
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMenu();
            }
        });
        return view;
    }


    public void addMenu(){
        Menu menuItem = new Menu();
        //Getting input from user and setting to employee model
        menuItem.setMenuName(menuText.getText().toString());
        //TODO other setter
        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();
        try {
           if (isUpdate){

                dbHelper.updateMenu(menu.getMenuId(), menuItem);
                Toast.makeText(getActivity(), "Menu updated successfully", Toast.LENGTH_SHORT).show();
            }else {
               dbHelper.insertMenu(menuItem);
               Toast.makeText(MenuFragment.this.getActivity(),"Menu added Successfully",Toast.LENGTH_SHORT).show();
            }




        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            dbHelper.close();
        }
    }
    void setUpdateMenu(){
        //System.out.println("Enter inside Update Method");
        menuText.setText(menu.getMenuName());

        //TODO
    }
     void deleteMenu(){
       // System.out.println("delete method");
       // System.out.println("delete emp ID:"+employee.getEmpId());

        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();
        dbHelper.deleteMenu(menu.getMenuId());
        Toast.makeText(getActivity(), "Menu deleted successfully", Toast.LENGTH_SHORT).show();
         EmployeeActivity.replaceFragment(new MenuListFragment());



    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
