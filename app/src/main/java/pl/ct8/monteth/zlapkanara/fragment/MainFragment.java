package pl.ct8.monteth.zlapkanara.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import pl.ct8.monteth.zlapkanara.R;
import pl.ct8.monteth.zlapkanara.adapter.MyAdapter;
import pl.ct8.monteth.zlapkanara.services.TicketInsService;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RecyclerView mBusLanes;
    TextView tvData;
    TextView tvStreet;
    List<String> mList;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mList = TicketInsService.INSTANCE.getTodaysData().getRoutes();
        tvData = view.findViewById(R.id.tv_day);
        tvData.setText(TicketInsService.INSTANCE.getTodaysData().getDate());
        tvStreet = view.findViewById(R.id.tv_street);
        tvStreet.setText(TicketInsService.INSTANCE.getTodaysData().getStreet());
        mBusLanes = view.findViewById(R.id.rv_bus_lines);
        mBusLanes.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        MyAdapter adapter = new MyAdapter(mList);
        mBusLanes.setAdapter(adapter);
        mBusLanes.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }




}
