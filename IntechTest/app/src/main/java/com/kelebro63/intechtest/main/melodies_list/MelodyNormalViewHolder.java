package com.kelebro63.intechtest.main.melodies_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelebro63.intechtest.App;
import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.models.Collection;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Created by kelebro63 on 02.06.20165.
 */
public class MelodyNormalViewHolder extends BaseMelodyViewHolder {

    @Bind(R.id.cover)
    ImageView cover;
    @Bind(R.id.titleMusic)
    TextView titleMusic;
    @Bind(R.id.artist)
    TextView artist;

    public MelodyNormalViewHolder(View itemView) {
        super(itemView);
        App.getAppComponent(getContext()).inject(this);
    }

    @Override
    public void bind(Collection item) {
        if (item != null) {
            if (item.getTitle() != null) {
                titleMusic.setText(item.getTitle());
            }
            if (item.getUser().getUsername() != null) {
                artist.setText(item.getUser().getUsername());
            }

            Picasso.with(getContext()).load(item.getUser().getAvatarUrl()).into(cover);
        }
    }


}
