package rms.manozct.resturantmanagement.fragment ;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.database.RmsDb;
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
    private  Employee employee;

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
    public EmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle!=null){
            employee = (Employee) bundle.getSerializable("employee");
            System.out.println("Emp id:"+employee.getEmpId());
            if (employee!=null){
                isUpdate = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        nameText = (EditText) view.findViewById(R.id.nameTxt);
        userName=(EditText) view.findViewById(R.id.userNameTxt);
        password=(EditText)view.findViewById(R.id.passwordTxt);
        addressText = (EditText) view.findViewById(R.id.addressTxt);
        contactNoText = (EditText) view.findViewById(R.id.contactTxt);
        ssnText = (EditText) view.findViewById(R.id.snnTxt);
        salary=(EditText)view.findViewById(R.id.salary);
        dobDate = (EditText) view.findViewById(R.id.dob);
    spinnerPosition=(Spinner)view.findViewById(R.id.positionSpinner);
        hireDate = (EditText) view.findViewById(R.id.hireDate);

        submitBtn = (Button) view.findViewById(R.id.submitBtn);
        spinnerPosition=(Spinner)view.findViewById(R.id.positionSpinner);

        List<String>positions=new ArrayList<String>();

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<Role>(getActivity(),android.R.layout.simple_spinner_item,Role.values());

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerPosition.setAdapter(dataAdapter);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            submitData();
                Toast.makeText(getActivity(), "Data saved Successfully", Toast.LENGTH_SHORT).show();

//                replaceFragment(new EmployeeListFragment());

            }
        });

        if (isUpdate){
            setUpdateData();
        }
        return view;
    }

   void setUpdateData(){
       System.out.println("Enter inside Update Method");
        nameText.setText(employee.getName());
       userName.setText(employee.getEmpUserName());
       password.setText(employee.getEmpPassword());
       addressText.setText(employee.getAddress());
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
      // Date today = Calendar.getInstance().getTime();
       System.out.println(" Birth:"+employee.getDob());
       /*String dob = df.format(employee.getDob());
       System.out.println("Date of Birth:"+dob);
       dobDate.setText(dob);*/
       contactNoText.setText(employee.getcNo());
       ssnText.setText(employee.getSsn());
      // hireDate.setText(employee.getHireDay().toString());
       int position=(dataAdapter.getPosition(employee.getRole()));
       spinnerPosition.setSelection(position);


       //TODO
    }
    public void replaceFragment(Fragment newFragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, newFragment);
        String backStateName = newFragment.getClass().getName();
        System.out.println("Fragment tag:"+backStateName);
        fragmentTransaction.addToBackStack(backStateName);    //Add previous state to backstack
        fragmentTransaction.commit();
    }

    public void submitData(){
        Employee emp = new Employee();
        //Getting input from user and setting to employee model
        emp.setEmpName(nameText.getText().toString());
        emp.setEmpUserName(userName.getText().toString());
        emp.setEmpPassword(password.getText().toString());
        emp.setcNo(contactNoText.getText().toString());
        emp.setAddress(addressText.getText().toString());
        emp.setSsn(ssnText.getText().toString());
       // employee.setDob(dobDate.getText().toString());
        emp.setDob(Util.convertStringToDate(dobDate.getText().toString()));
       // employee.setHireDay(hireDate.getText().toString());
        emp.setHireDay(Util.convertStringToDate(hireDate.getText().toString()));
        emp.setRole((Role)spinnerPosition.getSelectedItem());

        //TODO other setter
        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();
        try {
            if (isUpdate){

                dbHelper.updateEmployee(employee.getEmpId(), emp);
                Toast.makeText(getActivity(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            }else {
                dbHelper.insertEmployee(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            dbHelper.close();
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
