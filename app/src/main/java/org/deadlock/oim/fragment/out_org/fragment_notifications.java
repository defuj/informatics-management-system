package org.deadlock.oim.fragment.out_org;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import org.deadlock.oim.R;
import org.deadlock.oim.adapter.out_org.adapter_list_notif;
import org.deadlock.oim.model.model_list_notif;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragment_notifications extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<model_list_notif> notifs;
    private LayoutAnimationController animationController;
    private adapter_list_notif adapterListNotif;
    private model_list_notif modelListNotif;

    public fragment_notifications() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notifs = new ArrayList<>();
        notifs.clear();
        int restID = R.anim.layout_animation_from_bottom;
        animationController = AnimationUtils.loadLayoutAnimation(getContext(), restID);

        recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.list_notif);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutAnimation(animationController);
        recyclerView.scheduleLayoutAnimation();

        retrieving_items();
    }

    private void retrieving_items() {
        /*helper_data DBHelper = new helper_data(getContext());
        SQLiteDatabase db = DBHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM orgs", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            modelListOrg = new model_list_org();
            modelListOrg.setId(String.valueOf(cursor.getColumnIndex("id")));
            modelListOrg.setOrg(String.valueOf(cursor.getColumnIndex("organization")));
            modelListOrg.setDesc(String.valueOf(cursor.getColumnIndex("organization_categories")));
            modelListOrg.setNama(String.valueOf(cursor.getColumnIndex("descriptions")));
            orgs.add(modelListOrg);
        }**/

        for(int a = 1;a < 5;a++){
            modelListNotif = new model_list_notif();
            notifs.add(modelListNotif);
        }

        adapterListNotif = new adapter_list_notif(getContext(), notifs);
        recyclerView.setAdapter(adapterListNotif);

        recyclerView.setLayoutAnimation(animationController);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }
}
