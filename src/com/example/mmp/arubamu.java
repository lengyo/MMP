package com.example.mmp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class arubamu extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test4);
		List<Album> albums = Album.getItems(this);
		ListView trackList = (ListView) findViewById(R.id.lista);
		ListAlbumAdapter adapter = new ListAlbumAdapter(this, albums);
		trackList.setAdapter(adapter);
		trackList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent it = new Intent();

				// �Ăяo�����ƁA�Ăяo����̃A�N�e�B�r�e�B��ݒ�
				String s = Integer.toString(position);
				Log.d("log", s);
				it.putExtra("param1", s);
				// �Ăяo�����ƁA�Ăяo����̃A�N�e�B�r�e�B��ݒ�
				it.setClass(arubamu.this, arubamulist.class);

				// �A�N�e�B�r�e�B�̋N��
				startActivity(it);
			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		// List<Album> albums = Album.getItems(this);
		// ListView trackList = (ListView)findViewById(R.id.lista);
		// ListAlbumAdapter adapter = new ListAlbumAdapter(this,albums );
		// trackList.setAdapter(adapter);

		return true;
	}
}
