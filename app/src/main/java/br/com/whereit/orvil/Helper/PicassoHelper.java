package br.com.whereit.orvil.Helper;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import br.com.whereit.orvil.R;

public class PicassoHelper {
    public static void downloadImage(Context context, ImageView imageView, String url){
        Picasso.with(context).load(url).placeholder(R.drawable.no_cover).networkPolicy(NetworkPolicy.NO_CACHE).into(imageView);
    }
}
