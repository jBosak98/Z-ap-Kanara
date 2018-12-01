package pl.ct8.monteth.zlapkanara.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.ct8.monteth.zlapkanara.R;
import pl.ct8.monteth.zlapkanara.adapter.MyAdapter;
import pl.ct8.monteth.zlapkanara.adapter.MyWeekAdapter;
import pl.ct8.monteth.zlapkanara.data.Day;
import pl.ct8.monteth.zlapkanara.services.TicketInsService;

import java.util.ArrayList;
import java.util.List;


public class WeekFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RecyclerView mBusLanes;
    TextView tvData;
    TextView tvStreet;
    TextView tvRoutes;



    public WeekFragment() {
        // Required empty public constructor
    }

    public static WeekFragment newInstance() {
        WeekFragment fragment = new WeekFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        mBusLanes = view.findViewById(R.id.rv_bus_lines_week_fragment);
        tvData = view.findViewById(R.id.tv_date);
        tvStreet = view.findViewById(R.id.tv_street_week_fragment);
        tvRoutes = view.findViewById(R.id.tv_routes);
        mBusLanes.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        List<String> routes = new ArrayList<>();
        routes.add("123");
        routes.add("1");
        routes.add("K");
        routes.add("910P");
        List<Day> days = TicketInsService.INSTANCE.getWeekData();

        MyWeekAdapter adapter = new MyWeekAdapter(days);
        mBusLanes.setAdapter(adapter);
        mBusLanes.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }




}
