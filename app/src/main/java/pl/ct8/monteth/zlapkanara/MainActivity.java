package pl.ct8.monteth.zlapkanara;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import pl.ct8.monteth.zlapkanara.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity {
    RecyclerView mBusLanes;
    TextView tvData;
    List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = new ArrayList();
        tvData = findViewById(R.id.tv_day);
        mList.add("linia1");
        mList.add("Linia2");
        mList.add("Linia3");
        mBusLanes = findViewById(R.id.rv_bus_lines);
        MyAdapter adapter = new MyAdapter(mList);
        mBusLanes.setAdapter(adapter);
        mBusLanes.setLayoutManager(new LinearLayoutManager(this));
    }
}
