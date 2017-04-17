package rms.manozct.resturantmanagement.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.adapter.SubMenuAdapter;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.model.Menu;
import rms.manozct.resturantmanagement.model.Role;
import rms.manozct.resturantmanagement.model.SubMenu;

public class SubMenuListFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private SubMenuAdapter subMenuAdapter;

    private List<SubMenu> subMenus = new ArrayList<>();
    private int page = 1;

    private int mColumnCount = 2;

    private EmployeeActivity employeeActivity;

    private Spinner spinner;

    HashMap<Integer, String> menuMap;
    HashMap<Integer, List<SubMenu>> menuSubMenuMap;

    public SubMenuListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employeeActivity = (EmployeeActivity) getActivity();
        employeeActivity.setTitle("Sub Menus");


        initMaps();
        subMenus = new ArrayList<>();

        subMenuAdapter = new SubMenuAdapter(subMenus, mListener, employeeActivity);
        if (menuMap.keySet() !=null && menuMap.keySet().toArray().length>0){
            setAdapter((Integer)menuMap.keySet().toArray()[0]);
        }
    }

    private void setAdapter(Integer mainMenuId){
        subMenus = menuSubMenuMap.get(mainMenuId);
        subMenuAdapter.setSubMenu(subMenus);
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void initMaps(){

        //Db Call
        DbHelper dbHelper = new DbHelper(employeeActivity);
        dbHelper.read();
        List<Menu> menus = dbHelper.getMenuList(null);
        List<SubMenu> subMenusAll = dbHelper.getSubMenu(null);
        dbHelper.close();

        menuMap = new HashMap();
        menuSubMenuMap = new HashMap();

        for (Menu menu:
                menus) {
            menuMap.put(menu.getMenuId(), menu.getMenuName());
        }

        for (Integer key : menuMap.keySet()) {
            List<SubMenu> subs = new ArrayList<>();
            for (SubMenu subMenu : subMenusAll){
                if (subMenu.getMainMenuId()==key){
                    subs.add(subMenu);
                }
            }
            menuSubMenuMap.put(key, subs);
        }
        System.out.println("Submenu map:"+menuSubMenuMap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_menu_list, container, false);
        // Set the adapter
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.product_list);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(subMenuAdapter);
            System.out.println("Get Adapter:"+recyclerView.getAdapter());

        spinner=(Spinner)view.findViewById(R.id.spinner);


        // Creating adapter for spinner
        Object[] arr =  menuMap.values().toArray();
        System.out.println("Arrays:"+arr);
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item);
        dataAdapter.addAll(arr);
        dataAdapter.add("Select Menu");

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setSelection(dataAdapter.getCount()-1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String selected = adapterView.getItemAtPosition(i).toString();
                String selected = spinner.getSelectedItem().toString();
                System.out.println("Selected:"+selected);
                Integer key = getKeyByValue(menuMap, selected);
                System.out.println("Key:"+key);
                if (key!=null){
                    setAdapter(key);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
