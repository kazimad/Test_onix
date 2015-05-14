package com.example.kazimad.test_onix.navigationDrawerSections;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kazimad.test_onix.R;
import com.example.kazimad.test_onix.adapters.ItemAdapter;
import com.example.kazimad.test_onix.models.Item;
import com.example.kazimad.test_onix.network.response.ItemResponse;
import com.example.kazimad.test_onix.util.App;
import java.util.ArrayList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Kazimad on 29.04.2015.
 */
public class Fragment_2 extends Fragment {

    private ListView listView;
    private TextView emptyText;
    private FrameLayout frameLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_2_list_view);
        emptyText = (TextView) view.findViewById(R.id.textViewfragment2);
        frameLayout = (FrameLayout) view.findViewById(R.id.progress_bar_layout_2);
        frameLayout.setVisibility(View.VISIBLE);
        App.getRestClient().getFromApi().getItems_2(new Callback<ItemResponse>() {
            @Override
            public void success(ItemResponse itemResponse, Response response) {
                frameLayout.setVisibility(View.GONE);
                ArrayList<Item> arrayList = itemResponse.getItemArrayList();
                ItemAdapter adapter = new ItemAdapter(getActivity(), arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                frameLayout.setVisibility(View.GONE);
                emptyText.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}
