package br.com.whereit.orvil.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.whereit.orvil.Adapters.LivrosTabAdapter;
import br.com.whereit.orvil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivrosFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    LivrosTabAdapter livrosTabAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_livros,container,false);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);


        tabLayout.addTab(tabLayout.newTab().setText("Lendo"));
        tabLayout.addTab(tabLayout.newTab().setText("Quero Ler"));
        tabLayout.addTab(tabLayout.newTab().setText("Lidos"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        livrosTabAdapter = new LivrosTabAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(livrosTabAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);

        return view;
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
