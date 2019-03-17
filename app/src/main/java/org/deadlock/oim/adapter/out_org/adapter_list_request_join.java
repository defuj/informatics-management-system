package org.deadlock.oim.adapter.out_org;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.deadlock.oim.R;
import org.deadlock.oim.model.model_list_request_join;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter_list_request_join extends RecyclerView.Adapter<adapter_list_request_join.ViewHolder> {
    private ArrayList<model_list_request_join> joins;
    private Context context;

    public adapter_list_request_join(Context context, ArrayList<model_list_request_join> join) {
        this.joins = join;
        this.context = context;
    }


    @NonNull
    @Override
    public adapter_list_request_join.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_request, null);
        return new adapter_list_request_join.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_list_request_join.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return joins.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //pointer

        ViewHolder(View itemView){
            super(itemView);

        }
    }
}
