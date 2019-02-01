package com.koteswara.gsonexamplewithvolley.pojowithgson;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koteswara.gsonexamplewithvolley.R;

import java.util.ArrayList;
import java.util.List;

public class ReccyclerAdapter extends RecyclerView.Adapter<ReccyclerAdapter.MYViewHolder> {
    private String TAG=getClass().getSimpleName();
    private List<Android> list=new ArrayList<>();
    ReccyclerAdapter(List<Android> list){
        this.list=list;

    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_row,viewGroup,false);
        return new MYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder myViewHolder, int i) {

        myViewHolder.api_level.setText(list.get(i).getApi());
        myViewHolder.name.setText(list.get(i).getName());
        myViewHolder.version.setText(list.get(i).getVer());
//        Log.i(TAG,list.get(i).getApi());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MYViewHolder extends RecyclerView.ViewHolder {
        TextView version,name,api_level;
        public MYViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            version=(TextView)itemView.findViewById(R.id.vesrion);
            api_level=(TextView)itemView.findViewById(R.id.api_level);

        }
    }
}
