package pl.ct8.monteth.zlapkanara.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Toast;
import pl.ct8.monteth.zlapkanara.fragment.MainFragment;
import pl.ct8.monteth.zlapkanara.fragment.WeekFragment;

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
                Log.i("fragment2", "fragment2");
                return WeekFragment.newInstance();
            default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
