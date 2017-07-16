package rainvisitor.accesscontrol.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import rainvisitor.accesscontrol.R;
import rainvisitor.accesscontrol.models.Building;

/**
 * Created by Ray on 2017/7/16.
 *
 */

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Building item);
    }

    private final List<Building> contactList;
    private final OnItemClickListener listener;
    private Context context;

    public BuildingAdapter(Context context, List<Building> contactList, OnItemClickListener listener) {
        this.contactList = contactList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_building, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView_title.setText(contactList.get(position).getTitle());
        if (contactList.get(position).isClicked()) {
            int color_activeID = ContextCompat.getColor(context, R.color.item_active);
            //Picasso.with(context).load(contactList.get(position).image_activeID).into(holder.imageView);
            holder.textView_title.setTextColor(color_activeID);
            holder.frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.item_background_active));
        } else {
            int color_inActiveID = ContextCompat.getColor(context, R.color.item_in_active);
            holder.textView_title.setTextColor(color_inActiveID);
            holder.frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.item_background_in_active));
        }
        //holder.bind(contactList.get(position), listener);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                //Toast.makeText(context,Integer.toString(position),Toast.LENGTH_SHORT).show();
                listener.onItemClick(contactList.get(position));
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameLayout;
        TextView textView_title;
        View mView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            frameLayout = (FrameLayout) itemLayoutView.findViewById(R.id.layout_root);
            textView_title = (TextView) itemLayoutView.findViewById(R.id.textView_title);
            mView = itemLayoutView;
        }
    }
}
