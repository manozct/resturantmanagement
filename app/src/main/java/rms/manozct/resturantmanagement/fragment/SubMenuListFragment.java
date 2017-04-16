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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.adapter.SubMenuAdapter;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.model.SubMenu;

public class SubMenuListFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private SubMenuAdapter subMenuAdapter;

    private List<SubMenu> subMenus = new ArrayList<>();
    private int page = 1;

    private int mColumnCount = 2;

    EmployeeActivity employeeActivity;
    public SubMenuListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employeeActivity = (EmployeeActivity) getActivity();
        employeeActivity.setTitle("Products");
        subMenuAdapter = new SubMenuAdapter(subMenus, mListener, employeeActivity);

        for (int i = 0; i < 10; i++) {
            subMenus.add(new SubMenu("Lunch", "Tea", 100D));
        }
        //Db Call
        DbHelper dbHelper = new DbHelper(employeeActivity);
        dbHelper.read();

        subMenuAdapter.setSubMenu(subMenus);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_menu_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(subMenuAdapter);
        }
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
