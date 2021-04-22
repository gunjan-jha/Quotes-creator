package com.example.quotescreator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder> {
    private List<Quotes> quotes;
    private String Author_name;
    private Context context;
    public MyAdapter(Context context,List<Quotes> q) {


        this.context=context;
        this.quotes=q;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.quotes_list,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        final Quotes temp=quotes.get(position);
        Quotes quotes1=quotes.get(position);
        holder.textView.setText(quotes1.getQuotes_text());
        holder.textView1.setText(quotes1.getAuthor_name());

        /*on clicking the textView in application it will take you to the next
        Activity for Adding Image to the Quotes we have implemented this with the help of
        intents.*/

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Features.class);
                intent.putExtra("quote text",temp.getQuotes_text());
                intent.putExtra("Author_name",temp.getAuthor_name());
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView1;
        public holder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.txtView);
            textView1=(TextView)itemView.findViewById(R.id.textView2);

        }
    }
}
