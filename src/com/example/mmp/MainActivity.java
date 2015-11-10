package com.example.mmp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		Button b1 = (Button) findViewById(R.id.kanryou);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent it = new Intent();
				// 呼び出し元と、呼び出し先のアクティビティを設定

				// 呼び出し元と、呼び出し先のアクティビティを設定
				it.setClass(MainActivity.this, ikou.class);
				// アクティビティの起動
				startActivity(it);
			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent it = new Intent();
				// 呼び出し元と、呼び出し先のアクティビティを設定

				// 呼び出し元と、呼び出し先のアクティビティを設定
				it.setClass(MainActivity.this, arubamu.class);
				// アクティビティの起動
				startActivity(it);
			}
		});
		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent it = new Intent();
				// 呼び出し元と、呼び出し先のアクティビティを設定

				// 呼び出し元と、呼び出し先のアクティビティを設定
				it.setClass(MainActivity.this, purei.class);

				// アクティビティの起動
				startActivity(it);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		// List tracks = Track.getItems(this);
		// ListView trackList = (ListView)findViewById(R.id.list);
		// ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
		// trackList.setAdapter(adapter);
		// List<Album> albums = Album.getItems(this);
		// ListView trackList = (ListView)findViewById(R.id.list);
		// ListAlbumAdapter adapter = new ListAlbumAdapter(this,albums );
		// trackList.setAdapter(adapter);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
