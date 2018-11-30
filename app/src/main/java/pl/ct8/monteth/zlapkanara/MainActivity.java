package pl.ct8.monteth.zlapkanara;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import pl.ct8.monteth.zlapkanara.adapter.MyAdapter;
import pl.ct8.monteth.zlapkanara.adapter.MyPagerAdapter;
import pl.ct8.monteth.zlapkanara.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity {
    ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.vp_main_view_pager, new MainFragment());
//        ft.commit();
        vp=findViewById(R.id.vp_main_view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);
    }
}
