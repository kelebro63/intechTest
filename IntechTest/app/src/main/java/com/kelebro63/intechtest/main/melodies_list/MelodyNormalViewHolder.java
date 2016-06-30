package com.kelebro63.intechtest.main.melodies_list;

import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelebro63.intechtest.App;
import com.kelebro63.intechtest.R;
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
    public void bind(MediaMetadataCompat item) {
        if (item != null) {
            MediaDescriptionCompat description = item.getDescription();
            if (description.getTitle() != null) {
                titleMusic.setText(description.getTitle());
            }
            if (description.getSubtitle() != null) {
                artist.setText(description.getSubtitle());
            }
            Picasso.with(getContext()).load(description.getIconUri()).into(cover);
        }
    }


}
