package org.deadlock.oim.adapter.out_org;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.deadlock.oim.R;
import org.deadlock.oim.activity.in_org.activity_organizations;
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
        @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_org_type_02, null);
        return new adapter_list_org.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final adapter_list_org.ViewHolder holder, final int position) {
        //helper_data DBHelper = new helper_data(context);
        //final SQLiteDatabase db = DBHelper.getWritableDatabase();

        if(!orgs.get(position).getTheme().isEmpty()){
            //Change color
            holder.icon.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(orgs.get(position).getColor())));
            holder.iconMember.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(orgs.get(position).getColor())));
            holder.iconNotif.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(orgs.get(position).getColor())));
            holder.categories.setTextColor(Color.parseColor(orgs.get(position).getColor()));
            holder.org.setTextColor(Color.parseColor(orgs.get(position).getColor()));
            holder.countMember.setTextColor(Color.parseColor(orgs.get(position).getColor()));
            holder.countNotif.setTextColor(Color.parseColor(orgs.get(position).getColor()));
            holder.bgLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(orgs.get(position).getTheme())));
            holder.bgLayout.setAlpha((float) 0.7);
        }


        //Set Content
        holder.org.setText(orgs.get(position).getOrg());
        holder.desc.setText(orgs.get(position).getDesc());
        holder.categories.setText(orgs.get(position).getDesc());

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
                final ProgressDialog pd = new ProgressDialog(context,R.style.alertDialogTheme);
                pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
                pd.show();

                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        pd.dismiss();
                        context.startActivity(new Intent(context, activity_organizations.class),
                                ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context).toBundle());
                    }
                }.start();


            }
        });

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                View customView = View.inflate(context,R.layout.content_alert_dialog_detail_org,null);
                TextView orgName = customView.findViewById(R.id.txtOrgName);
                TextView orgDesc = customView.findViewById(R.id.txtOrgDesc);
                LinearLayout layoutColor = customView.findViewById(R.id.layoutBgColor);
                TextView txtCategories = customView.findViewById(R.id.txtCategories);
                ImageButton iconOrg = customView.findViewById(R.id.icon_org);

                if(!orgs.get(position).getTheme().isEmpty()){
                    layoutColor.setBackgroundColor(Color.parseColor(orgs.get(position).getTheme()));
                }else{
                    layoutColor.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    orgName.setTextColor(Color.parseColor("#747474"));
                    txtCategories.setTextColor(Color.parseColor("#747474"));
                }

                orgName.setText(orgs.get(position).getOrg());
                txtCategories.setText(orgs.get(position).getDesc());
                dialog.setView(customView);
                final AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return orgs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView org,desc,categories,countNotif,countMember;
        Toolbar toolbar;
        ImageButton icon,iconNotif,iconMember;
        RelativeLayout bgLayout;

        ViewHolder(View itemView){
            super(itemView);
            org = itemView.findViewById(R.id.txtnamaorg);
            desc = itemView.findViewById(R.id.txtdescorg);
            toolbar = itemView.findViewById(R.id.toolbar_list_org);
            icon = itemView.findViewById(R.id.icon_org);
            iconMember = itemView.findViewById(R.id.iconMember);
            iconNotif = itemView.findViewById(R.id.iconNotif);
            countMember = itemView.findViewById(R.id.showCountMember);
            countNotif = itemView.findViewById(R.id.showCountNotif);
            categories = itemView.findViewById(R.id.txtCategories);
            bgLayout = itemView.findViewById(R.id.bgLayoutOrg);
        }
    }
}
