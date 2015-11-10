package com.example.mmp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class purei extends Activity {
	private Intent it;

	ListView listView;

	// String[] DAYS;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.purei);
		listView = (ListView) findViewById(R.id.purei);
		Button b1 = (Button) findViewById(R.id.sakusei);
		Button b2 = (Button) findViewById(R.id.hensyuu);
		Button b3 = (Button) findViewById(R.id.del);
		ArrayList<String> DAYS = new ArrayList<String>();
		OnClickListener a = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				switch (v.getId()) {
				case R.id.sakusei:
					it = new Intent();
					// 呼び出し元と、呼び出し先のアクティビティを設定

					// 呼び出し元と、呼び出し先のアクティビティを設定
					it.setClass(purei.this, psakusei.class);

					// アクティビティの起動
					startActivity(it);
					break;
				case R.id.hensyuu:
					it = new Intent();
					// 呼び出し元と、呼び出し先のアクティビティを設定

					// 呼び出し元と、呼び出し先のアクティビティを設定
					it.setClass(purei.this, purei.class);

					// アクティビティの起動
					startActivity(it);

				default:
				case R.id.del:
					it = new Intent();
					// 呼び出し元と、呼び出し先のアクティビティを設定

					// 呼び出し元と、呼び出し先のアクティビティを設定
					it.setClass(purei.this, pureidel.class);

					// アクティビティの起動
					startActivity(it);
					break;
				}
			}

		};

		b1.setOnClickListener(a);
		b2.setOnClickListener(a);
		b3.setOnClickListener(a);
		ListView li = (ListView) findViewById(R.id.purei);
		SharedPreferences prefer = getSharedPreferences("プレイリスト2",
				MODE_WORLD_READABLE);
		int i = 0;
		int ii = 0;

		while (ii == 0) {

			String useId = prefer.getString("タイトル" + i, "1");
			if (useId.equals("1")) {
				ii++;
			} else {
				Log.d("log", useId);
				DAYS.add(useId);
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
				// 呼び出し元と、呼び出し先のアクティビティを設定
				it.setClass(purei.this, pureiK.class);
				// アクティビティの起動
				startActivity(it);
			}
		});
	}
}
