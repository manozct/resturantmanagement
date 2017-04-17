package rms.manozct.resturantmanagement.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.adapter.CartListAdapter;
import rms.manozct.resturantmanagement.adapter.OnListFragmentInteractionListener;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.model.Order;
import rms.manozct.resturantmanagement.model.SubMenu;

public class CartFragment extends Fragment {
    private EmployeeActivity activity;

    private OnListFragmentInteractionListener mListener;

    private CartListAdapter cartListAdapter;
    private List<SubMenu> cartList = new ArrayList<>();

    Button orderButton;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (EmployeeActivity) getActivity();

        cartListAdapter = new CartListAdapter(cartList, mListener, activity);
        cartListAdapter.setProducts(activity.cartList);
        activity.setTitle("Finalize Order");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        // Set the adapter
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCart);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            recyclerView.setAdapter(cartListAdapter);

        orderButton = (Button) view.findViewById(R.id.orderBtn);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = DbHelper.getDbHelper(getActivity());
                dbHelper.read();
                Order order;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String dateStr = dateFormat.format(date);
                System.out.println(dateFormat.format(date));
                long id=0;
                for (SubMenu subMenu : cartList){
                    order = new Order(1, EmployeeActivity.loginEmployee.getEmpId(), subMenu.getSubMenuId(), 1, subMenu.getPrice(), dateStr);
                    id = dbHelper.insertOrder(order);
                }
                dbHelper.close();
                if (id>0){
                    Toast.makeText(getActivity(), "Orders placed Successfully", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(), "Error in placing order", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
