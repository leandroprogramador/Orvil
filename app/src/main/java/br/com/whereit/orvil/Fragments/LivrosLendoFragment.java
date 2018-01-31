package br.com.whereit.orvil.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.whereit.orvil.Adapters.LivrosCardAdapter;
import br.com.whereit.orvil.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LivrosLendoFragment extends Fragment {


    public LivrosLendoFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    LivrosCardAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_livros_lendo, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        int images[] = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img6,R.drawable.img7,R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new LivrosCardAdapter(images);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
