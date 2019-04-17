package org.tampubolon.indonews.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.tampubolon.indonews.R;
import org.tampubolon.indonews.model.News;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<News> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView published_at;
        public TextView content;
        public TextView author;
        public ImageView thumbnail;
        public LinearLayout panel;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_news);
            content = (TextView) view.findViewById(R.id.content);
            published_at = (TextView) view.findViewById(R.id.published_at);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            author = (TextView) view.findViewById(R.id.author);
            panel = view.findViewById(R.id.panel);
        }
    }

    public NewsAdapter(Context mContext, List<News> coll) {
        this.mContext = mContext;
        this.list = coll;
    }

    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new NewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.MyViewHolder holder, final int position) {
        final News item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.published_at.setText(item.getPublishedAt());
        holder.content.setText(item.getDescription() + "...");
        holder.author.setText("#" + item.getAuthor());
        // loading album cover using Glide library
        Glide.with(mContext).load(item.getUrlToImage()).into(holder.thumbnail);
        holder.panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Goto Detail News*/
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""+item.getUrl()));
                mContext.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<News> _data) {
        this.list = _data;
    }
}
