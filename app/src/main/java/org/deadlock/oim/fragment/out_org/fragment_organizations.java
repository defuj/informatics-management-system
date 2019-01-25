package org.deadlock.oim.fragment.out_org;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.deadlock.oim.R;
import org.deadlock.oim.adapter.adapter_list_org;
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
    private model_list_org modelListOrg;
    private ArrayList<model_list_org> orgs;
    private adapter_list_org adapterListOrg;

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

        recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.list_org);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        retrieving_items();

    }

    private void retrieving_items() {
        for(int a =1;a<=17;a++){
            modelListOrg = new model_list_org();
            modelListOrg.setOrg("Organization Name "+a);
            modelListOrg.setDesc("Type of organizations");
            modelListOrg.setNama("Name of organization leader");
            orgs.add(modelListOrg);
        }

        adapterListOrg = new adapter_list_org(getContext(), orgs);
        recyclerView.setAdapter(adapterListOrg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organizations, container, false);
    }
}
