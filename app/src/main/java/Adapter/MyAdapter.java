package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.ListItem;
import us.nilesh.icare.ContactActivity;
import us.nilesh.icare.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
    private List<ListItem> listItems;
    public MyAdapter(Context context, List listItem){
        this.context=context;
        this.listItems=listItem;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item=listItems.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=(TextView)itemView.findViewById(R.id.tit);
            description=(TextView)itemView.findViewById(R.id.desc);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            ListItem item=listItems.get(position);
            Intent intent=new Intent(context, ContactActivity.class);
            intent.putExtra("name",item.getName());
            intent.putExtra("description",item.getDescription());
            context.startActivity(intent);
//            Toast.makeText(context,item.getName(),Toast.LENGTH_LONG).show();
        }
    }
}