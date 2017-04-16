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
import android.widget.ImageButton;
import android.widget.Toast;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
// * Use the {@link SubMenuFragment} factory method to
 * create an instance of this fragment.
 */
public class
SubMenuFragment extends Fragment {
   private ImageButton subMenubtn;
   private Button selectMenuBtn;



    private OnFragmentInteractionListener mListener;

    public SubMenuFragment() {
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
        View view=inflater.inflate(R.layout.fragment_sub_menu, container, false);
        subMenubtn=(ImageButton) view.findViewById(R.id.menuBtn);
        selectMenuBtn=(Button)view.findViewById(R.id.selectMenuBtn);
        subMenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subMenu=subMenubtn.getContext().toString();
//                EmployeeActivity.replaceFragment(new SubMenuFragment());
                Toast.makeText(SubMenuFragment.this.getActivity(), "Sub Menu:", Toast.LENGTH_SHORT).show();
            }
        });
        selectMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subMenu=selectMenuBtn.getContext().toString();
//                EmployeeActivity.replaceFragment(new SubMenuFragment());
                Toast.makeText(SubMenuFragment.this.getActivity(), "Sub Menu:", Toast.LENGTH_SHORT).show();
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
