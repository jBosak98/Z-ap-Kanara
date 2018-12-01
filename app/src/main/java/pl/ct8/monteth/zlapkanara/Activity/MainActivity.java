package pl.ct8.monteth.zlapkanara.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import pl.ct8.monteth.zlapkanara.R;
import pl.ct8.monteth.zlapkanara.adapter.MyPagerAdapter;
import pl.ct8.monteth.zlapkanara.services.StreetLocationService;

public class MainActivity extends AppCompatActivity {
    ViewPager vp;
    private String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSION_ALL = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.vp_main_view_pager, new MainFragment());
//        ft.commit();
        vp = findViewById(R.id.vp_main_view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);
        StreetLocationService.INSTANCE.getLocation(this, "Szybka 7, Wrocław");


    }

}
