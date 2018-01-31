package br.com.whereit.orvil.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.whereit.orvil.R;

/**
 * Created by leandro.araujo on 16/01/2018.
 */

public class LivrosCardAdapter extends RecyclerView.Adapter<LivrosCardAdapter.MyViewolder> {

    public LivrosCardAdapter(int[] images) {
        this.images = images;
    }

    int[] images;
    @Override
    public MyViewolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_row,parent, false);

        return new MyViewolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
