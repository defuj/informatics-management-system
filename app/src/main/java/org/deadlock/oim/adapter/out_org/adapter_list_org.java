package org.deadlock.oim.adapter.out_org;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.deadlock.oim.R;
import org.deadlock.oim.activity.in_org.activity_organization;
import org.deadlock.oim.helper.helper_data;
import org.deadlock.oim.model.model_list_org;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class adapter_list_org extends RecyclerView.Adapter<adapter_list_org.ViewHolder> {
    private ArrayList<model_list_org> orgs;
    private Context context;

    public adapter_list_org(Context context, ArrayList<model_list_org> org) {
        this.orgs = org;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_list_org.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_org, null);
        return new adapter_list_org.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final adapter_list_org.ViewHolder holder, final int position) {
        helper_data DBHelper = new helper_data(context);
        final SQLiteDatabase db = DBHelper.getWritableDatabase();

        holder.org.setText(orgs.get(position).getOrg());
        holder.desc.setText(orgs.get(position).getDesc());
        holder.nama.setText(orgs.get(position).getNama());

        holder.toolbar.getMenu().clear();
        holder.toolbar.inflateMenu(R.menu.menu_content_list_org);
        holder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.action_out){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(holder.itemView.getContext());
                    View view = View.inflate(context,R.layout.content_alert_dialog,null);
                    TextView title = view.findViewById(R.id.txtAlertTitle);
                    TextView msg = view.findViewById(R.id.txtAlertMsg);
                    title.setText("Unenroll from "+orgs.get(position).getOrg()+"?");
                    msg.setText("You won't be able to see or participate in the organization anymore.");

                    dialog.setView(view);
                    final AlertDialog alertDialog = dialog.create();
                    Button cancel = view.findViewById(R.id.btnAlertNegative);
                    cancel.setText("Cancel");
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });

                    Button ok = view.findViewById(R.id.btnAlertPositive);
                    ok.setText("Unenroll");
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //db.execSQL("DELETE FROM orgs where id = "+Integer.valueOf(orgs.get(position).getId()));
                            alertDialog.dismiss();
                            orgs.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,orgs.size());
                        }
                    });

                    alertDialog.show();
                }
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, activity_organization.class),
                       ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context).toBundle());

            }
        });

    }


    @Override
    public int getItemCount() {
        return orgs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView org,desc,nama;
        Toolbar toolbar;
        SimpleDraweeView bg;

        ViewHolder(View itemView){
            super(itemView);
            org = itemView.findViewById(R.id.txtnamaorg);
            desc = itemView.findViewById(R.id.txtdescorg);
            nama = itemView.findViewById(R.id.txtleaderorg);
            toolbar = itemView.findViewById(R.id.toolbar_list_org);
            bg = itemView.findViewById(R.id.bg_content_org);
        }
    }
}
