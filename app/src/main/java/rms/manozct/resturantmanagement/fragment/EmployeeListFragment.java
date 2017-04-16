package rms.manozct.resturantmanagement.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import rms.manozct.resturantmanagement.model.Role;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmployeeListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class EmployeeListFragment extends Fragment {

    private TableLayout stk;

    private OnFragmentInteractionListener mListener;

    View view;
    private Button addButton;

    public EmployeeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_employee_list, container, false);
        addButton = (Button) view.findViewById(R.id.addBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EmployeeActivity.replaceFragment(new EmployeeFragment());
            }
        });
        init();
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

    public void init() {

         stk = (TableLayout) view.findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(getActivity());
        TextView tv0 = new TextView(getActivity());
        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(getActivity());
        tv1.setText(" Name ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(getActivity());
        tv2.setText(" UserName ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(getActivity());
        tv3.setText(" Position");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();

        List<Employee> employees = dbHelper.getEmployee(null);
        System.out.println(employees);
        for (int i = 0; i < employees.size(); i++) {
            System.out.println("employee id:"+employees.get(i).getEmpId());
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
            t2v.setText(employees.get(i).getName());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(getActivity());
            t3v.setText(employees.get(i).getEmpUserName());
            //System.out.println(employees.get(i).getEmpUserName());
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(getActivity());
           System.out.println("Position:"+employees.get(i).getRole());
            t4v.setText(employees.get(i).getRole().toString());
            t4v.setTextColor(Color.WHITE);

            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);

            t1v.setTextSize( 15f);
            t2v.setTextSize( 15f);
            t3v.setTextSize( 15f);
            t4v.setTextSize( 15f);
            final Employee emp = employees.get(i);
            tbrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Clicked on:"+emp.getEmpId(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("employee", emp);
                    EmployeeFragment employeeFragment = new EmployeeFragment();
                    employeeFragment.setArguments(bundle);
                    EmployeeActivity.replaceFragment(employeeFragment);
                    System.out.println("In EmloyeeListFragment DOB"+emp.getDob());
                   // Toast.makeText(getActivity(), "Clicked on:"+emp.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
