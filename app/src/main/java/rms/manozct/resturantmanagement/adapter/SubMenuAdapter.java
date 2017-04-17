package rms.manozct.resturantmanagement.adapter;

import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import rms.manozct.resturantmanagement.R;
import rms.manozct.resturantmanagement.activity.EmployeeActivity;
import rms.manozct.resturantmanagement.fragment.SubMenuFragment;
import rms.manozct.resturantmanagement.fragment.SubMenuListFragment;
import rms.manozct.resturantmanagement.model.SubMenu;
import rms.manozct.resturantmanagement.util.Util;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.ViewHolder>{
    private final List<SubMenu> mValues;
    private final SubMenuListFragment.OnFragmentInteractionListener mListener;
    private EmployeeActivity employeeActivity;

    public SubMenuAdapter(List<SubMenu> items, SubMenuListFragment.OnFragmentInteractionListener listener, EmployeeActivity employeeActivity) {
        mValues = items;
        mListener = listener;
        this.employeeActivity = employeeActivity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item1, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.descriptionText.setText(holder.mItem.getSubMenuName());
        holder.priceText.setText("$"+holder.mItem.getPrice());

        String url = holder.mItem.getImageUrl();

        if (url!=null){
            Uri uri = Uri.parse(url);

            url = Util.getImagePath(uri, employeeActivity);
        }

        Glide.with(employeeActivity)
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.appetizer)
                .crossFade()
                .into(holder.image);

        holder.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeActivity.addToCart(holder.mItem);
            }
        });

        holder.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubMenuFragment subMenuFragment = new SubMenuFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("SubMenu", holder.mItem);
                subMenuFragment.setArguments(bundle);
                EmployeeActivity.replaceFragment(subMenuFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void add(int position, SubMenu item) {
        mValues.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mValues.indexOf(item);
        mValues.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView descriptionText;
        public final TextView priceText;
        public final ImageView image;
        public final Button cartBtn;
        public final Button favBtn;
        public final Button detailBtn;
        public SubMenu mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            descriptionText = (TextView) view.findViewById(R.id.description);
            priceText = (TextView) view.findViewById(R.id.cost);
            image = (ImageView) view.findViewById(R.id.photo);

            cartBtn = (Button) view.findViewById(R.id.btn_cart);
            favBtn = (Button) view.findViewById(R.id.btn_fav);
            detailBtn = (Button) view.findViewById(R.id.btn_detail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + priceText.getText() + "'";
        }


    }

    public void setSubMenu(List<SubMenu> data) {
        mValues.clear();
        mValues.addAll(data);
        notifyDataSetChanged();
    }
}
