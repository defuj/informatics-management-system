package org.deadlock.oim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.deadlock.oim.R;
import org.deadlock.oim.model.model_list_accounts;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter_list_accounts extends RecyclerView.Adapter<adapter_list_accounts.ViewHolder>{
    private ArrayList<model_list_accounts> accounts;
    private Context context;

    public adapter_list_accounts(ArrayList<model_list_accounts> accounts, Context context) {
        this.accounts = accounts;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_list_accounts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_accounts, null);
        return new adapter_list_accounts.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final adapter_list_accounts.ViewHolder holder, final int position) {
        holder.email.setText(accounts.get(position).getEmail());
        holder.img.setImageURI(Uri.parse(accounts.get(position).getFoto()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Switch to "+accounts.get(position).getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        SimpleDraweeView img;

        ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.img_accounts);
            email = itemView.findViewById(R.id.emailtext);
        }
    }
}
