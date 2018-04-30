package br.com.whereit.orvil.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import br.com.whereit.orvil.Activities.LivrosActivity;
import br.com.whereit.orvil.Activities.NewBookSteps.ChooseMethodActivity;
import br.com.whereit.orvil.Adapters.LivrosTabAdapter;
import br.com.whereit.orvil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivrosFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    LivrosTabAdapter livrosTabAdapter;
    FloatingActionButton fabLido, fabQueroLer, fabLendo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_livros,container,false);
        fabLido = view.findViewById(R.id.lidos_fab);
        fabQueroLer = view.findViewById(R.id.quero_fab);
        fabLendo = view.findViewById(R.id.lendo_fab);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);


        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.lendo)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.quero_ler)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.lido)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        livrosTabAdapter = new LivrosTabAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(livrosTabAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);

        fabLido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewBookActivity(getString(R.string.lido));
            }
        });

        fabQueroLer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewBookActivity(getString(R.string.quero_ler));
            }
        });
        

        fabLendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewBookActivity(getString(R.string.lendo));
            }
        });


        return view;
    }

    private void openNewBookActivity(String status) {
        Intent intent = new Intent(getActivity(), ChooseMethodActivity.class);
        intent.putExtra(getString(R.string.status), status);
        startActivity(intent);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
