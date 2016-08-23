package com.bhargavmogra.selectablelistdecoration_sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhargavmogra.selectablelistdecoration.SelectedItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The adapter for the list in the sample.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
        implements SelectedItemDecoration.SelectableAdapter {
    private List<String> mItems;
    private Map<Integer, Boolean> isStringSelectedMap;

    public ListAdapter(List<String> texts) {
        mItems = texts;
        isStringSelectedMap = new HashMap<>(texts.size());
        for (int i = 0; i < texts.size(); i++) {
            isStringSelectedMap.put(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mItems.get(position);
        holder.text.setText(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public boolean isSelected(int position) {
        return isStringSelectedMap.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.list_item_text);
            this.text.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.list_item_text:
                    int position = getAdapterPosition();
                    if (isStringSelectedMap.get(position)) {
                        isStringSelectedMap.put(position, false);
                    } else {
                        isStringSelectedMap.put(position, true);
                    }
                    notifyItemChanged(position);
                    break;
            }
        }
    }
}
