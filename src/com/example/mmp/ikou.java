package com.example.mmp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ListView;

public class ikou extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test4);

		List tracks = Track.getItems(this);
		ListView trackList = (ListView) findViewById(R.id.lista);
		ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
		trackList.setAdapter(adapter);
		trackList.setOnItemClickListener(

				new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

						Intent it = new Intent();

						// 呼び出し元と、呼び出し先のアクティビティを設定
						String s = Integer.toString(position);
						Log.d("log", s);
						it.putExtra("param1", s);
						// 呼び出し元と、呼び出し先のアクティビティを設定
						it.setClass(ikou.this, saisei.class);
						// アクティビティの起動
						startActivity(it);
					}
				});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		// List tracks = Track.getItems(this);
		// ListView trackList = (ListView)findViewById(R.id.lista);
		// ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
		// trackList.setAdapter(adapter);
		// List<Album> albums = Album.getItems(this);
		// ListView trackList = (ListView)findViewById(R.id.list);
		// ListAlbumAdapter adapter = new ListAlbumAdapter(this,albums );
		// trackList.setAdapter(adapter);

		return true;
	}
}
