package com.example.mmp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class pureiK extends Activity {
	String item;
	ListView listView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pureik);
		listView = (ListView) findViewById(R.id.pureik);
		Intent data = getIntent();
		Bundle extras = data.getExtras();
		item = extras.getString("param1");
		SharedPreferences pref = getSharedPreferences("プレイリスト2",
				MODE_WORLD_READABLE);
		String useId = pref.getString("タイトル" + item, "1");
		SharedPreferences pre = getSharedPreferences(useId, MODE_WORLD_READABLE);
		ArrayList<String> DAYS = new ArrayList<String>();
		int i = 0;
		int ii = 0;

		while (ii == 0) {

			String use = pre.getString("タイトル" + i, "1");
			if (use.equals("1")) {
				ii++;
			} else {
				Log.d("log", use);
				DAYS.add(use);
				Log.d("p", pre.getString("tid" + i, "1"));
				i++;
			}
		}
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, DAYS));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent it = new Intent();

				// 呼び出し元と、呼び出し先のアクティビティを設定
				String s = Integer.toString(position);
				Log.d("log", s);
				it.putExtra("param1", s);
				it.putExtra("param2", item);
				// 呼び出し元と、呼び出し先のアクティビティを設定
				it.setClass(pureiK.this, Psaisei.class);
				// アクティビティの起動
				startActivity(it);
			}
		});
	}

}
