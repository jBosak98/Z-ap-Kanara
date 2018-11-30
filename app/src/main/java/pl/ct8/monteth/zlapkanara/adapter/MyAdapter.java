package pl.ct8.monteth.zlapkanara.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.ct8.monteth.zlapkanara.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    List<String> busLines = new ArrayList<>();
    public MyAdapter(List<String> lanes){
        busLines=lanes;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bus_line, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mLine.setText("Linia testowa");
    }


    @Override
    public int getItemCount() {
        return busLines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView mLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLine = itemView.findViewById(R.id.tv_line);
        }
    }
}
