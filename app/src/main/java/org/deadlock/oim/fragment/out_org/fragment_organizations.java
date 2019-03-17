package org.deadlock.oim.fragment.out_org;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import org.deadlock.oim.R;
import org.deadlock.oim.adapter.out_org.adapter_list_org;
import org.deadlock.oim.model.model_list_org;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragment_organizations extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<model_list_org> orgs;
    private LayoutAnimationController animationController;
    private adapter_list_org adapterListOrg;
    private model_list_org modelListOrg;

    public fragment_organizations() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orgs = new ArrayList<>();
        orgs.clear();
        int restID = R.anim.layout_animation_from_bottom;
        animationController = AnimationUtils.loadLayoutAnimation(getContext(), restID);

        recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.list_org);
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

        for(int a = 1;a < 4;a++){
            modelListOrg = new model_list_org();
            modelListOrg.setOrg("Organization Name "+a);
            modelListOrg.setDesc("Categories of organization "+a);
            modelListOrg.setNama("Some words as a simple description text of organization");
            orgs.add(modelListOrg);
        }

        adapterListOrg = new adapter_list_org(getContext(), orgs);
        recyclerView.setAdapter(adapterListOrg);

        recyclerView.setLayoutAnimation(animationController);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void addORG(){
        /**orgs = new ArrayList<>();
        modelListOrg = new model_list_org();
        modelListOrg.setOrg("Organization Name NEWS");
        modelListOrg.setDesc("Type of organizations");
        modelListOrg.setNama("Name of organization leader");
        orgs.add(modelListOrg);
        adapterListOrg.notifyItemInserted(orgs.size()-1);
        adapterListOrg.notifyDataSetChanged(); **/
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organizations, container, false);
    }
}
