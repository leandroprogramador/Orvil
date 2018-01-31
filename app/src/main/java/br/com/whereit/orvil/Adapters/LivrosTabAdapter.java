package br.com.whereit.orvil.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.whereit.orvil.Fragments.LivrosLendoFragment;

/**
 * Created by leandro.araujo on 16/01/2018.
 */

public class LivrosTabAdapter extends FragmentStatePagerAdapter {

    int mTabCount;
    public LivrosTabAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position){
            case 0:
                fragment = new LivrosLendoFragment();
                break;
            case 1:
                fragment = new LivrosLendoFragment();
                break;
            case 2:
                fragment = new LivrosLendoFragment();
                break;
            default:
                fragment = null;
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
