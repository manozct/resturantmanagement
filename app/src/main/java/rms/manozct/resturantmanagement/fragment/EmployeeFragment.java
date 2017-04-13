package rms.manozct.resturantmanagement.fragment ;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rms.manozct.resturantmanagement.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmployeeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * * create an instance of this fragment.
 */
public class EmployeeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private EditText nameText;
    private EditText addressText;
    private EditText contactNoText;
    private EditText ssnText;
    private EditText dobDate;
    private EditText hireDate;
    private Spinner spinnerPosition;


    private Button submitBtn;
    public EmployeeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        nameText = (EditText) view.findViewById(R.id.nameTxt);
        addressText = (EditText) view.findViewById(R.id.addressTxt);
        contactNoText = (EditText) view.findViewById(R.id.contactTxt);
        ssnText = (EditText) view.findViewById(R.id.snnTxt);

        dobDate = (EditText) view.findViewById(R.id.dob);

        submitBtn = (Button) view.findViewById(R.id.submitBtn);
        spinnerPosition=(Spinner)view.findViewById(R.id.positionSpinner);

        List<String>positions=new ArrayList<String>();
        positions.add("Manager");
        positions.add("Cashier");
        positions.add("Waiter");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,positions);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerPosition.setAdapter(dataAdapter);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                Toast.makeText(EmployeeFragment.this.getActivity(), "Hello "+name, Toast.LENGTH_SHORT).show();
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
