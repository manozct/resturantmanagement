package rms.manozct.resturantmanagement.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.model.Employee;
import rms.manozct.resturantmanagement.model.Menu;


public class MenuListFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private TableLayout stk;
    private Button addMenuButton;
    View view;

    public MenuListFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmployeeActivity.setTitle("Menu");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu_list, container, false);
        addMenuButton = (Button) view.findViewById(R.id.addMenuBtn);
        addMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeActivity.replaceFragment(new MenuFragment());
            }
        });
        loadMenuList();
        return view ;
    }
    public void loadMenuList() {
        System.out.println("loadMenuList method");

        stk = (TableLayout) view.findViewById(R.id.table_menu);
        TableRow tbrow0 = new TableRow(getActivity());
        TextView tv0 = new TextView(getActivity());
        tv0.setText(" S.N. ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(getActivity());
        tv1.setText(" Menu Name ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        stk.addView(tbrow0);
        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();

        final List<Menu> menus = dbHelper.getMenuList(null);

        for (int i = 0; i < menus.size(); i++) {
           // System.out.println("employee id:"+employees.get(i).getEmpId());
            TableRow tbrow = new TableRow(getActivity());
            if(i%2==0){
                tbrow.setBackgroundColor(Color.DKGRAY);
            }else{
                tbrow.setBackgroundColor(getResources().getColor(R.color.itti_blue));
            }
            TextView t1v = new TextView(getActivity());
            t1v.setText(Integer.toString(i+1));
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(getActivity());
            t2v.setText(menus.get(i).getMenuName());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            stk.addView(tbrow);
            t1v.setTextSize( 15f);
            t2v.setTextSize( 15f);
            final Menu menu = menus.get(i);
           tbrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Clicked on:"+menu.getMenuId(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("menuKey", menu);
                    MenuFragment menuFragment = new MenuFragment();
                    menuFragment.setArguments(bundle);
                    EmployeeActivity.replaceFragment(menuFragment);


                }
            });
        }

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
