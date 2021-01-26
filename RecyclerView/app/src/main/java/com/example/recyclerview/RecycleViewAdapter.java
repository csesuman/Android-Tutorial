package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    String nameList[], description[];
    int images[];
    Context context;

    public RecycleViewAdapter(MainActivity mainActivity, String[] nameList, String[] descriptions, int[] images) {
        this.context = mainActivity;
        this.nameList = nameList;
        this.description = descriptions;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.iconImage.setImageResource(images[position]);
        holder.nameText.setText(nameList[position]);
        holder.descriptionText.setText(description[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("name", nameList[position] );


                intent.putExtra("description", description[position] );
                intent.putExtra("myImage", images[position] );

//                Log.i("Suman ", "name is " + images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, descriptionText;
        ImageView iconImage;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.myImageView);
            nameText = itemView.findViewById(R.id.nameText);
            descriptionText = itemView.findViewById(R.id.nameDescription);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
