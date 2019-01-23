package org.deadlock.oim.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.deadlock.oim.fragment.out_org.fragment_notifications;
import org.deadlock.oim.fragment.out_org.fragment_organizations;

public class adapter_home extends FragmentStatePagerAdapter {
    public adapter_home(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new fragment_organizations();
            case 1 : return new fragment_notifications();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
