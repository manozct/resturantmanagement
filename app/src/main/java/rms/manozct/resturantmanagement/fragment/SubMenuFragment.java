package rms.manozct.resturantmanagement.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.database.DbHelper;
import rms.manozct.resturantmanagement.model.Employee;
import rms.manozct.resturantmanagement.model.Menu;
import rms.manozct.resturantmanagement.model.Role;
import rms.manozct.resturantmanagement.model.SubMenu;
import rms.manozct.resturantmanagement.util.Util;

import static android.app.Activity.RESULT_OK;

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
    private EditText subMenuText;
    private Spinner menuName;
    private EditText imageName;
    private EditText priceSubMenu;
    ArrayAdapter<String> dataAdapter;
    Uri selectedImage;
    private Button btnSubmitSubMenu;

    public static final int SELECT_PHOTO = 100;

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
        subMenuText = (EditText) view.findViewById(R.id.subMenuTxt);
        menuName=(Spinner)view.findViewById(R.id.mainMenuSpinner);
        priceSubMenu=(EditText)view.findViewById(R.id.subMenuPriceTxt);
        imageName=(EditText) view.findViewById(R.id.menuImageId);
        btnSubmitSubMenu=(Button)view.findViewById(R.id.submitSubMenu);
        LoadAllMenuList();
        imageName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, EmployeeActivity.SELECT_PHOTO);
            }
        });
        btnSubmitSubMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSubMenuData();
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    System.out.println("Selected Image URl"+selectedImage);
                    Toast.makeText(getActivity(), "Got Image:"+selectedImage.toString(), Toast.LENGTH_LONG);
                    imageName.setText(selectedImage.toString());
                    /*InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);*/
                }
        }
    }
    public void submitSubMenuData(){
        SubMenu sMenu = new SubMenu();
        //Getting input from user and setting to employee model
        sMenu.setMainMenuId(menuName.getSelectedItemPosition());
        sMenu.setSubMenuName(subMenuText.getText().toString());
        sMenu.setPrice(Double.parseDouble(priceSubMenu.getText().toString()));
        sMenu.setImageUrl(selectedImage.toString());


        //TODO other setter
        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();
        try {
            dbHelper.insertSubMenu(sMenu);
            Toast.makeText(getActivity(), "SubMenu saved successfully", Toast.LENGTH_SHORT).show();
           /* if (isUpdate){

                dbHelper.updateEmployee(employee.getEmpId(), emp);
                Toast.makeText(getActivity(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            }else {
                dbHelper.insertEmployee(emp);
            }*/

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            dbHelper.close();
        }
    }
    void LoadAllMenuList(){
        System.out.println("inside LoadAllMenuList");
        DbHelper dbHelper = new DbHelper(getActivity());
        dbHelper.write();
        final List<Menu> menus = dbHelper.getMenuList(null);
        List<String>menuList=new ArrayList<String>();

        for (int i = 0; i < menus.size(); i++){

            menuList.add(menus.get(i).getMenuName());

        }
        dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,menuList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        menuName.setAdapter(dataAdapter);

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
