package org.deadlock.oim.adapter.out_org;

import org.deadlock.oim.fragment.out_org.fragment_notifications;
import org.deadlock.oim.fragment.out_org.fragment_organizations;
import org.deadlock.oim.fragment.out_org.fragment_request_join;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class adapter_home extends FragmentStatePagerAdapter {
    public adapter_home(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new fragment_organizations();
            case 1 : return new fragment_notifications();
            case 2 : return new fragment_request_join();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
