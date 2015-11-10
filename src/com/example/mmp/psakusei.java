package com.example.mmp;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class psakusei extends Activity {
	Track ui;
	Track id;
	SparseBooleanArray checked;
	String[] DAYS;
	ListView listView;
	EditText ed;
	int ct = 0;

	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.sakusei);
	//
	// List tracks = Track.getItems(this);
	// ListView trackList = (ListView)findViewById(R.id.pureilist);
	// ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
	// trackList.setAdapter(adapter);
	// }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sakusei);
		ed = (EditText) findViewById(R.id.editText1);
		final ArrayList<Track> track = new ArrayList<Track>();

		track.addAll(Track.getItems(this));
		DAYS = new String[track.size()];
		for (int bb = 0; bb < track.size(); bb++) {

			ui = track.get(bb);
			DAYS[bb] = ui.title;
		}
		listView = (ListView) findViewById(R.id.ListView);

		// アダプタの作成
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, DAYS));

		// フォーカスが当たらないよう設定
		listView.setItemsCanFocus(false);

		// 選択の方式の設定
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		for (int i = 1; i <= track.size() - 1; i++) {
			// 指定したアイテムがチェックされているかを設定
			listView.setItemChecked(i, false);
		}

		// アイテムがクリックされたときに呼び出されるコールバックを登録
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});

		// 現在チェックされているアイテムを取得
		// チェックされてないアイテムは含まれない模様
		checked = listView.getCheckedItemPositions();
		for (int i = 0; i < checked.size(); i++) {
			// チェックされているアイテムの key の取得
			int key = checked.keyAt(i);
			Log.v(getClass().getSimpleName(), "values: " + DAYS[key]);
		}
		Button b1 = (Button) findViewById(R.id.kanryou);
		OnClickListener a = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.kanryou:
					SharedPreferences pref = getSharedPreferences("プレイリスト2",
							MODE_WORLD_READABLE);
					int ii = 0;
					while (ii == 0) {
						String use = pref.getString("タイトル" + ct, "1");
						if (use.equals("1")) {
							ii++;
							ct--;
						}
						ct++;

					}
					String name = ed.getText().toString();
					Log.d("name", name);

					SharedPreferences prefer;
					if (name.equals("名前を入力してください")) {
						prefer = getSharedPreferences("プレイリスト" + ct,
								MODE_WORLD_READABLE);
						name = "プレイリスト" + ct;
					} else {
						prefer = getSharedPreferences(name, MODE_WORLD_READABLE);
					}
					Editor edito = pref.edit();
					edito.putString("タイトル" + ct, name);
					edito.commit();
					checked = listView.getCheckedItemPositions();
					Log.d("checkd", checked.toString());
					int i = 0;
					int ct = 0;
					while (checked.size() >= ct) {
						boolean key = checked.get(ct);

						Log.d("log", key + "");

						if (key) {
							Editor editor = prefer.edit();
							editor.putString("タイトル" + i, DAYS[ct]);
							id = track.get(ct);
							editor.putString("tid" + i, id.path);
							editor.putLong("taim" + i, id.duration);
							editor.commit();
							String useId = prefer
									.getString("タイトル" + i, "10001");
							Log.d("lo", useId);
							i++;
						}
						ct++;
					}
					Intent it = new Intent();
					it.setClass(psakusei.this, purei.class);
					// アクティビティの起動
					startActivity(it);
					break;
				}
			}
		};
		b1.setOnClickListener(a);
	}

	// ListView に表示させる文字列

}