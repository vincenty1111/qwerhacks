package com.example.qwerhacksapplication;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.qwerhacksapplication.R;

import java.util.ArrayList;




public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mPlatform = new ArrayList<>();
    private RecyclerViewClickListener listener;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls,
                               ArrayList<String> platform, RecyclerViewClickListener listen) {
        mTitles = names;
        mImageUrls = imageUrls;
        mPlatform = platform;
        mContext = context;
        listener = listen;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        System.out.println(mImageUrls.get(position));
        Glide.with(mContext).load(mImageUrls.get(position))
                    .apply(new RequestOptions().override(200, 200)).into(holder.image);

        holder.name.setText(mTitles.get(position));
        if (mPlatform.get(position).equals("spotify")) {
            Glide.with(mContext).load(mImageUrls.get(position))
                    .apply(new RequestOptions().override(200, 200)).into(holder.platform);
        } else {
            Glide.with(mContext).load(mImageUrls.get(position))
                    .apply(new RequestOptions().override(200, 200)).into(holder.platform);
        }
//        holder.id.setText(""+mIds.get(position));

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView name;
        ImageView platform;
        ConstraintLayout parent;
        TextView id;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.show_image);
            name = itemView.findViewById(R.id.show_title);
            platform = itemView.findViewById(R.id.platform_icon);
            parent = itemView.findViewById(R.id.parent);
//            id = itemView.findViewById(R.id.explore_bus_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener {
        void onClick(View v, int pos);
    }
}