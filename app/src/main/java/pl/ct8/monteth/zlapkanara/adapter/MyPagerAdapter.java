package pl.ct8.monteth.zlapkanara.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import pl.ct8.monteth.zlapkanara.fragment.MainFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    public MyPagerAdapter(FragmentManager manager){
        super(manager);
    }
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return MainFragment.newInstance();
            case 1:
                return MainFragment.newInstance();
            default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
