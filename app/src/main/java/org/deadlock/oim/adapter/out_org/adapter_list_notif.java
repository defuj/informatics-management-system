package org.deadlock.oim.adapter.out_org;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.deadlock.oim.R;
import org.deadlock.oim.model.model_list_notif;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter_list_notif extends RecyclerView.Adapter<adapter_list_notif.ViewHolder>{
    private ArrayList<model_list_notif> notifs;
    private Context context;

    public adapter_list_notif(Context context, ArrayList<model_list_notif> notif) {
        this.notifs = notif;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_list_notif.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_notif, null);
        return new adapter_list_notif.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_list_notif.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //pointer

        ViewHolder(View itemView){
            super(itemView);

        }
    }
}
