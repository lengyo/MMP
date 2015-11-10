package com.example.mmp;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Psaisei extends Activity implements OnCompletionListener {
	private Button b5;
	private SeekBar seekBar;
	private Button b2;
	private final int REPEAT_INTERVAL = 100;
	private boolean isRepeat = true;
	private final int MESSAGE_WHAT = 100;
	private Message message;
	int ct = 0;
	SharedPreferences pref;
	SharedPreferences pre;
	MediaPlayer mp;
	String item2;
	String item;
	String id;
	int a = 0;
	private int no;
	TextView editText;
	int cc = 0;
	int aa = 1;
	int saisei = 0;
	long timeLong;
	int timeInt;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saisei1);
		message = new Message();
		message.what = MESSAGE_WHAT;
		Intent data = getIntent();
		Bundle extras = data.getExtras();
		item = extras.getString("param2");
		item2 = extras.getString("param1");
		Button b1 = (Button) findViewById(R.id.saisei);
		b2 = (Button) findViewById(R.id.sukiltupu);
		Button b3 = (Button) findViewById(R.id.stop);
		Button b4 = (Button) findViewById(R.id.modoru);
		b5 = (Button) findViewById(R.id.modoru12);
		editText = (TextView) findViewById(R.id.textView1);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		pref = getSharedPreferences("プレイリスト2", MODE_WORLD_READABLE);
		String useId = pref.getString("タイトル" + item, "1");

		pre = getSharedPreferences(useId, MODE_WORLD_READABLE);
		no = Integer.parseInt(item2);
		mp = new MediaPlayer();

		OnClickListener a = new OnClickListener() {

			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ

				switch (v.getId()) {
				case R.id.saisei:
					saisei(no);
					break;
				case R.id.sukiltupu:
					sukip();
					break;
				case R.id.stop:
					stop();
					break;
				case R.id.modoru:
					modoru();
					break;
				case R.id.modoru12:
					stopex();
					break;
				}
				seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					// トグルがドラッグされると呼ばれる
					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {

					}

					// トグルがタッチされた時に呼ばれる
					public void onStartTrackingTouch(SeekBar seekBar) {
						mp.pause();
					}

					// トグルがリリースされた時に呼ばれる
					public void onStopTrackingTouch(SeekBar seekBar) {
						int tumami = seekBar.getProgress();

						mp.seekTo(tumami);
						mp.start();

					}

				});
			}

		};
		b1.setOnClickListener(a);
		b2.setOnClickListener(a);
		b3.setOnClickListener(a);
		b4.setOnClickListener(a);
		b5.setOnClickListener(a);
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		Log.v("MediaPlayer", "onCompletion");

		b2.performClick();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// ==== キーコード判定 ====//

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// -==- Backキー -==-//

			b5.performClick();

		}

		return super.onKeyDown(keyCode, event);
	}

	public void saisei(int no) {

		timeLong = pre.getLong("taim" + no, 1);
		timeInt = (int) timeLong;
		seekBar.setMax(timeInt);
		if (ct == 0) {
			id = pre.getString("tid" + no, "1");
			try {
				seekBar.setProgress(0);
				mp.setDataSource(id);
				mp.prepare();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ct++;
		}
		if (saisei == 0) {
			mp.start();
			saisei++;
		}

		editText.setText(pre.getString("タイトル" + no, "1"));
		if (cc == 0) {

			handler.sendMessageDelayed(message, REPEAT_INTERVAL);
		}
		isRepeat = true;
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO 自動生成されたメソッド・スタブ
				Log.v("MediaPlayer", "onCompletion");

				b2.performClick();

			}
		});

	}

	public void sukip() {
		mp.pause();

		mp.reset();
		no++;
		id = pre.getString("tid" + no, "1");
		if (id.equals("1")) {
			no = 0;
			id = pre.getString("tid" + no, "1");
		}
		try {
			mp.setDataSource(id);
			mp.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mp.start();
		editText.setText(pre.getString("タイトル" + no, "1"));
		isRepeat = true;
		timeLong = pre.getLong("taim" + no, 1);
		timeInt = (int) timeLong;
		seekBar.setProgress(0);
		seekBar.setMax(timeInt);

	}

	public void stop() {
		cc = 2;
		mp.pause();
		isRepeat = false;
		saisei = 0;
	}

	public void modoru() {
		mp.pause();

		mp.reset();
		no--;

		if (no < 0) {
			while (a == 0) {
				id = pre.getString("tid" + no, "1");
				if (id.equals("1")) {
					a++;

				} else {
					no++;
				}
			}
		}
		id = pre.getString("tid" + no, "1");
		try {
			mp.setDataSource(id);
			mp.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mp.start();
		editText.setText(pre.getString("タイトル" + no, "1"));
		timeLong = pre.getLong("taim" + no, 1);
		timeInt = (int) timeLong;
		seekBar.setProgress(0);
		seekBar.setMax(timeInt);
	}

	public void stopex() {
		cc = 2;
		mp.stop();
		mp.reset();
		mp.release();
		isRepeat = false;
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (isRepeat) {
				int a = seekBar.getProgress() + 100;

				seekBar.setProgress(a);

				handler.sendMessageDelayed(obtainMessage(), REPEAT_INTERVAL);
			} else {
				handler.sendMessageDelayed(obtainMessage(), REPEAT_INTERVAL);
			}
		}

	};

}
