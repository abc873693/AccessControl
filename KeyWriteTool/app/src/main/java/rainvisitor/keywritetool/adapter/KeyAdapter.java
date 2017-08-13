package rainvisitor.keywritetool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import rainvisitor.keywritetool.R;
import rainvisitor.keywritetool.models.api.Key;

/**
 * Created by Ray on 2017/7/16.
 *
 */

public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Key item);
    }

    private final List<Key> contactList;
    private final OnItemClickListener listener;
    private Context context;

    public KeyAdapter(Context context, List<Key> contactList, OnItemClickListener listener) {
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
                inflate(R.layout.list_key, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView_key.setText(contactList.get(position).getKey());
        holder.textView_start_time.setText(contactList.get(position).getStartTime());
        holder.textView_end_time.setText(contactList.get(position).getEndTime());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView textView_key;
        TextView textView_start_time;
        TextView textView_end_time;
        View mView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            linearLayout = (LinearLayout) itemLayoutView.findViewById(R.id.layout_root);
            textView_key = (TextView) itemLayoutView.findViewById(R.id.text_key);
            textView_start_time = (TextView) itemLayoutView.findViewById(R.id.text_start_time);
            textView_end_time = (TextView) itemLayoutView.findViewById(R.id.text_end_time);
            mView = itemLayoutView;
        }
    }
}
