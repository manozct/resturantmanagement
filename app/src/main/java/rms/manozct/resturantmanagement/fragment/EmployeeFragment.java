package rms.manozct.resturantmanagement.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.model.Employee;
import rms.manozct.resturantmanagement.model.Role;
import rms.manozct.resturantmanagement.util.Util;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmployeeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * * create an instance of this fragment.
 */
public class EmployeeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    private boolean isUpdate;
    private Employee employee;

    private EditText nameText;
    private EditText userName;
    private EditText password;
    private EditText addressText;
    private EditText contactNoText;
    private EditText ssnText;
    private EditText dobDate;
    private EditText hireDate;
    private Spinner spinnerPosition;
    ArrayAdapter<Role> dataAdapter;
    private EditText salary;

    private Button submitBtn;
    private Button deleteBtn;

    public EmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            employee = (Employee) bundle.getSerializable("employee");
            System.out.println("Emp id:" + employee.getEmpId());
            if (employee != null) {
                isUpdate = true;
            }
        }
        EmployeeActivity.setTitle("Employee");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee, container, false);
        EmployeeActivity.setTitle("Add new Employee");
        nameText = (EditText) view.findViewById(R.id.nameTxt);
        userName = (EditText) view.findViewById(R.id.userNameTxt);
        password = (EditText) view.findViewById(R.id.passwordTxt);
        addressText = (EditText) view.findViewById(R.id.addressTxt);
        contactNoText = (EditText) view.findViewById(R.id.contactTxt);
        ssnText = (EditText) view.findViewById(R.id.snnTxt);
        salary = (EditText) view.findViewById(R.id.salary);
        dobDate = (EditText) view.findViewById(R.id.dob);
        spinnerPosition = (Spinner) view.findViewById(R.id.positionSpinner);
        hireDate = (EditText) view.findViewById(R.id.hireDate);

        submitBtn = (Button) view.findViewById(R.id.submitBtn);
        deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
        spinnerPosition = (Spinner) view.findViewById(R.id.positionSpinner);

        List<String> positions = new ArrayList<String>();

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<Role>(getActivity(), android.R.layout.simple_spinner_item, Role.values());

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerPosition.setAdapter(dataAdapter);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(submitData()) // try to submit data to data base if success change the activity to EmployeeListFragment
                replaceFragment(new EmployeeListFragment());

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();

            }
        });


        if (isUpdate) {
            setUpdateData();
        }else {
            deleteBtn.setVisibility(View.GONE);
        }
        return view;
    }

    boolean validateEmployee() {

        boolean validationStatus = true;

        if ( nameText.getText() == null ||nameText.getText().toString().length() == 0 ) {
            nameText.setError("Name is required!");
            validationStatus = false;

        }
        if ( userName.getText() == null ||userName.getText().toString().length() == 0 ) {
            userName.setError("userName is required!");
            validationStatus = false;


        }
        if (password.getText().toString().length() < 3 || password.getText() == null) {
            password.setError("Minimun Password length is 3 !");
            validationStatus = false;

        }

        if ( addressText.getText() == null||addressText.getText().toString().length() == 0) {
            addressText.setError("Address is required!");
            validationStatus = false;

        }

        if ( dobDate.getText() == null ||dobDate.getText().toString().length() == 0 ) {
            dobDate.setError("DOB is required!");
            validationStatus = false;

        } else if(!Util.isCorrectDate(dobDate.getText().toString()))
        {
            dobDate.setError("Invalid date format !!");
            validationStatus = false;

        }
        if ( contactNoText.getText() == null||contactNoText.getText().toString().length() == 0) {
            contactNoText.setError("Contact No. is required!");
            validationStatus = false;

        }

        if ( ssnText.getText() == null||ssnText.getText().toString().length() !=9 ) {
            ssnText.setError("Invalid SSN !!");
            validationStatus = false;

        }

        if ( hireDate.getText() == null||hireDate.getText().toString().length() == 0 )
        {
            hireDate.setError("Hire Date is required!");
            validationStatus = false;
        }
        // check if valid date :
        else if(!Util.isCorrectDate(hireDate.getText().toString()))
        {
            hireDate.setError("Invalid date format !!");
            validationStatus = false;

        }

        return validationStatus;


    }


    void setUpdateData() {
        System.out.println("Enter inside Update Method");
        nameText.setText(employee.getName());
        userName.setText(employee.getEmpUserName());
        password.setText(employee.getEmpPassword());
        addressText.setText(employee.getAddress());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        // Date today = Calendar.getInstance().getTime();
        System.out.println(" Birth:" + employee.getDob());
       /*String dob = df.format(employee.getDob());
       System.out.println("Date of Birth:"+dob);
       dobDate.setText(dob);*/
        contactNoText.setText(employee.getcNo());
        ssnText.setText(employee.getSsn());
        // hireDate.setText(employee.getHireDay().toString());
        int position = (dataAdapter.getPosition(employee.getRole()));
        spinnerPosition.setSelection(position);
    }

    public void replaceFragment(Fragment newFragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, newFragment);
        String backStateName = newFragment.getClass().getName();
        System.out.println("Fragment tag:" + backStateName);
        fragmentTransaction.addToBackStack(backStateName);    //Add previous state to backstack
        fragmentTransaction.commit();
    }

    public boolean submitData() {
        if (!validateEmployee())
        {
           Toast.makeText(getActivity(), "Invalid input detected !!", Toast.LENGTH_LONG).show();
            return false; // ie submit data failed
        }
        Employee emp = new Employee();
        //Getting input from user and setting to employee model
        emp.setEmpName(nameText.getText().toString());
        emp.setEmpUserName(userName.getText().toString());
        emp.setEmpPassword(password.getText().toString());
        emp.setcNo(contactNoText.getText().toString());
        emp.setAddress(addressText.getText().toString());
        emp.setSsn(ssnText.getText().toString());
        //employee.setDob(Util.convertStringToDate(dobDate.getText().toString()));
        emp.setDob(Util.convertStringToDate(dobDate.getText().toString()));

        emp.setHireDay(Util.convertStringToDate(hireDate.getText().toString()));
        emp.setRole((Role) spinnerPosition.getSelectedItem());

        //TODO other setter
        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();
        try {

                if (isUpdate) {

                    dbHelper.updateEmployee(employee.getEmpId(), emp);
                    Toast.makeText(getActivity(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                } else {

                    dbHelper.insertEmployee(emp);
                    Toast.makeText(getActivity(), "Data saved Successfully", Toast.LENGTH_SHORT).show();

                }


        } catch (Exception e) {
            e.printStackTrace();

        } finally
        {
            dbHelper.close();
        }
        return true; // submit data success
    }

    public void deleteData() {
        // System.out.println("delete method");
        // System.out.println("delete emp ID:"+employee.getEmpId());

        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();
        dbHelper.deleteEmployee(employee.getEmpId());
        Toast.makeText(getActivity(), "Data deleted successfully", Toast.LENGTH_SHORT).show();


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
