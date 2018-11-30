package pl.ct8.monteth.zlapkanara;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class MainActivity  extends AppCompatActivity {
    RecyclerView busLanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busLanes = findViewById(R.id.rv_bus_lanes);

    }
}
