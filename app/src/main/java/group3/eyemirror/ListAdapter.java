package group3.eyemirror;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter <ListAdapter.MyViewHolder> {
    ArrayList schedule;
    ArrayList timesList;
    ArrayList<Event> events = new ArrayList<Event>();
    Context context;

    public ListAdapter(Context context, ArrayList times, ArrayList schedule, ArrayList<Event> events) {
        this.context = context;
        this.schedule = schedule;
        this.events = events;
        this.timesList = times;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MyViewHolder mv = new MyViewHolder(v);
        return mv;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // set the data in items
        holder.time.setText(timesList.get(position).toString());
        holder.event.setText(schedule.get(position).toString());
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast
                Toast.makeText(context, timesList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return timesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView event;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            time = (TextView) itemView.findViewById(R.id.timeView);
            event = (TextView) itemView.findViewById(R.id.eventView);
        }
    }
}

