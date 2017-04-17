package rms.manozct.resturantmanagement.adapter;

import android.net.Uri;
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
import rms.manozct.resturantmanagement.model.SubMenu;
import rms.manozct.resturantmanagement.util.Util;

/**
 * Created by Crawlers on 1/11/2017.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>{

    private final List<SubMenu> mValues;
    private final OnListFragmentInteractionListener mListener;

    EmployeeActivity mainActivity;
    public CartListAdapter(List<SubMenu> items, OnListFragmentInteractionListener listener, EmployeeActivity mainActivity) {
        mValues = items;
        mListener = listener;
        this.mainActivity = mainActivity;
    }

    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new CartListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartListAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.descriptionText.setText(holder.mItem.getSubMenuName());
        holder.priceText.setText("$."+holder.mItem.getPrice());
        holder.quantityText.setText(Integer.toString(1));

        String url = holder.mItem.getImageUrl();

        Uri uri = Uri.parse(url);

        url = Util.getImagePath(uri, mainActivity);

        Glide.with(mainActivity.getApplicationContext())
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.appetizer)
                .crossFade()
                .into(holder.image);

        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.mItem);
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = Integer.parseInt(holder.quantityText.getText().toString());
                quantity++;
                holder.quantityText.setText(Integer.toString(quantity));
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer quantity = Integer.parseInt(holder.quantityText.getText().toString());
                if (quantity!=0)
                    quantity--;
                holder.quantityText.setText(Integer.toString(quantity));
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

    public void remove(SubMenu item) {
        int position = mValues.indexOf(item);
        mValues.remove(position);
        mainActivity.removeFromCart(item);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView descriptionText;
        public final TextView priceText;
        public final TextView quantityText;
        public final ImageView image;
        public final Button cross;
        public final Button add;
        public final Button minus;
        public SubMenu mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            descriptionText = (TextView) view.findViewById(R.id.product_name);
            priceText = (TextView) view.findViewById(R.id.product_cost);
            quantityText = (TextView) view.findViewById(R.id.product_quantity);
            image = (ImageView) view.findViewById(R.id.product_img);
            cross = (Button) view.findViewById(R.id.btn_cross);
            add = (Button) view.findViewById(R.id.btn_add);
            minus = (Button) view.findViewById(R.id.btn_minus);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + priceText.getText() + "'";
        }
    }

    public void setProducts(List<SubMenu> data) {
        System.out.println("adding cart products:"+data);
        mValues.addAll(data);
        notifyDataSetChanged();
    }

}
