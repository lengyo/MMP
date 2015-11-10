package com.example.mmp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class arubamulist extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test4);
		final ArrayList<Album> track = new ArrayList<Album>();

		track.addAll(Album.getItems(this));
		Intent data = getIntent();

		Bundle extras = data.getExtras();
		String item = extras.getString("param1");
		int ct = Integer.parseInt(item);
		Album ui = track.get(ct);
		String aas = String.valueOf(ui.albumId);
		Log.d("log", aas);
		List tracks = Track.getItemsByAlbum(this, ui.albumId);

		ListView trackList = (ListView) findViewById(R.id.lista);
		ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
		trackList.setAdapter(adapter);

		trackList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent it = new Intent();
				Intent data = getIntent();

				Bundle extras = data.getExtras();
				String item = extras.getString("param1");
				int ct = Integer.parseInt(item);
				Album ui = track.get(ct);
				String aas = String.valueOf(ui.albumId);
				// 呼び出し元と、呼び出し先のアクティビティを設定
				String s = Integer.toString(position);

				Log.d("log", s);
				it.putExtra("param1", s);
				it.putExtra("param2", aas);
				// 呼び出し元と、呼び出し先のアクティビティを設定
				it.setClass(arubamulist.this, saisei2.class);
				// アクティビティの起動
				startActivity(it);
			}
		});
	}

}
