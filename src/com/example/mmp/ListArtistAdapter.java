package com.example.mmp;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListArtistAdapter extends ArrayAdapter<Album> {
	LayoutInflater mInflater;
	static Context Mcontext;

	public ListArtistAdapter(Context context, List<Album> item) {
		super(context, 0, item);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Mcontext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Album item = getItem(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.testa, null);
			holder = new ViewHolder();
			holder.albumTextView = (TextView) convertView
					.findViewById(R.id.taito);
			holder.artistTextView = (TextView) convertView
					.findViewById(R.id.aru);
			holder.tracksTextView = (TextView) convertView
					.findViewById(R.id.taimu);
			holder.artworkImageView = (ImageView) convertView
					.findViewById(R.id.al);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.albumTextView.setText(item.album);
		holder.artistTextView.setText(item.artist);
		holder.tracksTextView.setText(String.valueOf(item.tracks) + "tracks");

		String path = item.albumArt;
		holder.artworkImageView.setImageResource(R.drawable.ic_launcher);
		if (path == null) {
			path = String.valueOf(R.drawable.ic_launcher);
			Bitmap bitmap = ImageCache.getImage(path);
			if (bitmap == null) {
				bitmap = BitmapFactory.decodeResource(Mcontext.getResources(),
						R.drawable.ic_launcher);
				ImageCache.setImage(path, bitmap);
			}
		}
		holder.artworkImageView.setTag(path);
		ImageGetTask task = new ImageGetTask(holder.artworkImageView);
		task.execute(path);

		return convertView;
	}

	static class ViewHolder {
		TextView albumTextView;
		TextView artistTextView;
		TextView tracksTextView;
		ImageView artworkImageView;
	}

}
