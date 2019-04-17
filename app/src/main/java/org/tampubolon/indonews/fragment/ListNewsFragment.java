package org.tampubolon.indonews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tampubolon.indonews.R;
import org.tampubolon.indonews.adapter.NewsAdapter;
import org.tampubolon.indonews.model.News;
import org.tampubolon.indonews.repository.NewsRepository;
import org.tampubolon.indonews.viewmodel.NewsModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListNewsFragment extends Fragment {

    View view;
    String category = "technology";
    NewsModel viewModel;
    RecyclerView rc_list;
    NewsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.list_news_fragment, container, false);
        this.getPassedData();
        this.initComponent();
        this.initList();
        return view;
    }
    public void getPassedData(){
        Bundle bundle = this.getArguments();
        try {
            category = bundle.getString("category");
        }catch (NullPointerException ex){

        }

    }
    public void initComponent(){
        rc_list = view.findViewById(R.id.rc_list);
        // Assign View Model from News View Model
        viewModel = ViewModelProviders.of(this).get(NewsModel.class);
        ((NewsModel) viewModel).init(new NewsRepository());
        //instantiate news adapter
        adapter = new NewsAdapter(getContext(),new ArrayList<News>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,
                false);
        rc_list.setLayoutManager(layoutManager);
        rc_list.setAdapter(adapter);
    }

    public void initList(){
        // put observer when news data is downloaded
        viewModel.getNewsByCategory(this.category).observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                adapter.setData(news);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
