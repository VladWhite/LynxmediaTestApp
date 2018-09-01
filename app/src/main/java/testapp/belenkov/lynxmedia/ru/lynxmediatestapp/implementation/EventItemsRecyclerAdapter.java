package testapp.belenkov.lynxmedia.ru.lynxmediatestapp.implementation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.ItemDetails;
import testapp.belenkov.lynxmedia.ru.lynxmediatestapp.model.EventsItem;
import testapp.belenkov.lynxmedia.ru.myapplication.R;

public class EventItemsRecyclerAdapter extends RecyclerView.Adapter<EventItemsRecyclerAdapter.EventItemsViewHolder> {
    private Context context;
    private List<EventsItem> items;

    public EventItemsRecyclerAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, viewGroup, false);

        return new EventItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventItemsViewHolder viewHolder, int i) {
        viewHolder.eventItemTitle.setText(items.get(i).getTitle());
        viewHolder.eventItemСoefficient.setText(items.get(i).getCoefficient());
        viewHolder.eventItemTime.setText(items.get(i).getTime());
        viewHolder.eventItemPlace.setText(items.get(i).getPlace());
        viewHolder.eventItemPreview.setText(items.get(i).getPreview());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setEventsItems(List<EventsItem> items) {
        if (items == null) {
            return;
        }
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    class EventItemsViewHolder extends RecyclerView.ViewHolder {
        TextView eventItemTitle;
        TextView eventItemСoefficient;
        TextView eventItemTime;
        TextView eventItemPlace;
        TextView eventItemPreview;

        @SuppressLint("CheckResult")
        EventItemsViewHolder(View view) {
            super(view);
            eventItemTitle = view.findViewById(R.id.eventItemTitle);
            eventItemСoefficient = view.findViewById(R.id.eventItemСoefficient);
            eventItemTime = view.findViewById(R.id.eventItemTime);
            eventItemPlace = view.findViewById(R.id.eventItemPlace);
            eventItemPreview = view.findViewById(R.id.preview);
            view.setOnClickListener(view1 -> {
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("article", items.get(getAdapterPosition()).getArticle());
                context.startActivity(intent);
            });
        }
    }
}
