package com.example.acer.pixabayfragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter
{
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                return new ChoclateFragment();
            case 1:
                return new TeddyBearFragment();

        }
        return null;
    }

    @Override
    public int getCount()
    {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Choclates";
            case 1:
                return  "TeddyBears";

        }
        return super.getPageTitle(position);

    }
}
