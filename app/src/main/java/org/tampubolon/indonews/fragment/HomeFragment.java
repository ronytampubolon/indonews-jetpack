package org.tampubolon.indonews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.tampubolon.indonews.R;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment implements View.OnClickListener {

    View view;
    CardView technology_panel;
    CardView business_panel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.category_fragment, container, false);
        initComponent();
        return view;
    }
    public void initComponent(){
        technology_panel = this.view.findViewById(R.id.categ_technology);
        business_panel = this.view.findViewById(R.id.categ_business);
        technology_panel.setOnClickListener(this);
        business_panel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.categ_technology:
                Bundle category_tech = new Bundle();
                category_tech.putString("category","technology");
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_listNewsFragment,category_tech);
                break;
            case R.id.categ_business:
                Bundle category_business = new Bundle();
                category_business.putString("category","business");
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_listNewsFragment,category_business);
                break;
        }
    }
}
