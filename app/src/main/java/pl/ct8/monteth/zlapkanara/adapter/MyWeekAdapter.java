package pl.ct8.monteth.zlapkanara.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.ct8.monteth.zlapkanara.R;
import pl.ct8.monteth.zlapkanara.data.Day;

import java.util.List;

public class MyWeekAdapter extends RecyclerView.Adapter<MyWeekAdapter.ViewHolder> {
    List<Day> days;
    public MyWeekAdapter(List<Day> days){
        this.days=days;
    }

    public List<Day> getDays() {
        return days;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_day, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvData.setText(days.get(i).getDate());
        viewHolder.tvStreet.setText(days.get(i).getStreet());
        StringBuilder s = new StringBuilder();
        for (String str:days.get(i).getRoutes())
            s.append(str+" ");
        viewHolder.tvRoutes.setText(s);

    }

    @Override
    public int getItemCount() {
        return days.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvData;
        TextView tvStreet;
        TextView tvRoutes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvData = itemView.findViewById(R.id.tv_date);
            tvStreet = itemView.findViewById(R.id.tv_street_week_fragment);
            tvRoutes = itemView.findViewById(R.id.tv_routes);
        }
    }
}
