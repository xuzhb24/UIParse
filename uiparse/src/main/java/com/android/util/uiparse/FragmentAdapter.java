package com.android.util.uiparse;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhb on 2020/12/13
 */
public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.ViewHolder> {

    private Context mContext;
    private List<FragmentStructure> mList;
    private boolean isResumed;

    public FragmentAdapter(Context context, List<FragmentStructure> list) {
        this.mContext = context;
        this.mList = list;
        List<FragmentStructure> userVisibleHintList = new ArrayList<>();
        List<FragmentStructure> isResumedList = new ArrayList<>();
        for (FragmentStructure f : mList) {
            if (f.isUserVisibleHint()) {
                userVisibleHintList.add(f);
            }
            if (f.isResumed()) {
                isResumedList.add(f);
            }
        }
        isResumed = isResumedList.size() <= userVisibleHintList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.item_ui_parse, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FragmentStructure structure = mList.get(position);
        holder.nameTv.setText(structure.getName());
        holder.visibleTv.setText(String.valueOf(isResumed ? structure.isResumed() : structure.isUserVisibleHint()));
        if (isResumed ? structure.isResumed() : structure.isUserVisibleHint()) {
            holder.visibleTv.setTextColor(Color.parseColor("#db4b3c"));
        } else {
            holder.visibleTv.setTextColor(Color.BLACK);
        }
        if (structure.isChildFragment()) {
            holder.rootLl.setBackgroundColor(Color.parseColor("#f3f3f3"));
            holder.nameTv.setPadding((int) Util.dp2px(mContext, 10), 0, 0, 0);
        } else {
            holder.rootLl.setBackgroundColor(Color.WHITE);
            holder.nameTv.setPadding(0, 0, 0, 0);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rootLl;
        TextView nameTv;
        TextView visibleTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootLl = itemView.findViewById(R.id.root_ll);
            nameTv = itemView.findViewById(R.id.name_tv);
            visibleTv = itemView.findViewById(R.id.visible_tv);
        }
    }

}
