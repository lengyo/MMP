package com.example.mmp;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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

public class saisei2 extends Activity implements OnCompletionListener {
	private Button b5;
	private SeekBar seekBar;
	private Button b2;
	private Runnable updatet;
	private int cc = 0;
	private long tuki;
	private int ctt = 0;
	private final int REPEAT_INTERVAL = 100;
	private int c2 = 0;
	private boolean isRepeat = true;
	private final int MESSAGE_WHAT = 100;
	private Message message;
	private long taim;
	private int taim12;
	private int st = 0;
	private long taime;
	private long item;
	private String ite;
	String item2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		message = new Message();
		message.what = MESSAGE_WHAT;

		setContentView(R.layout.saisei1);

		final ArrayList<Track> track = new ArrayList<Track>();

		Intent data = getIntent();
		Bundle extras = data.getExtras();
		item2 = extras.getString("param2");
		item = Long.parseLong(item2);
		ite = extras.getString("param1");

		track.addAll(Track.getItemsByAlbum(this, item));

		Button b1 = (Button) findViewById(R.id.saisei);
		b2 = (Button) findViewById(R.id.sukiltupu);
		Button b3 = (Button) findViewById(R.id.stop);
		Button b4 = (Button) findViewById(R.id.modoru);
		b5 = (Button) findViewById(R.id.modoru12);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		tuki = 1000;

		OnClickListener a = new OnClickListener() {

			MediaPlayer mp = new MediaPlayer();

			int ct = Integer.parseInt(ite);

			Track uii = track.get(ct);
			Track ui = track.get(ct);

			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Log.d("log", "bean");
				String i;

				TextView editText = (TextView) findViewById(R.id.textView1);

				taime = ui.duration;
				String saa = "" + taime;
				Log.d("log", saa);

				switch (v.getId()) {

				case R.id.saisei:
					if (c2 == 0) {
						seekBar.setProgress(0);
						taim = ui.duration;
						taim12 = (int) taime;
						seekBar.setMax(taim12);
						i = ui.path.toString();

						Log.d("log", i);
						try {
							mp.setDataSource(i);
							mp.prepare();

						} catch (IllegalArgumentException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} catch (IllegalStateException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} catch (IOException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
						c2++;
					}

					editText.setText(ui.title);
					mp.start();
					mp.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							// TODO 自動生成されたメソッド・スタブ
							Log.v("MediaPlayer", "onCompletion");

							b2.performClick();

						}
					});
					if (cc == 0) {

						handler.sendMessageDelayed(message, REPEAT_INTERVAL);
					}
					isRepeat = true;

					st = 0;
					break;
				case R.id.sukiltupu:

					mp.pause();

					mp.reset();
					ct = ct + 1;
					String qqq = "" + ct;
					Log.d("log", qqq);
					if (ct == track.size()) {
						ct = 0;
					}
					ui = track.get(ct);
					String ccc = "" + ct;
					i = ui.path.toString();
					Log.d("log", i);
					Log.d("log", ccc);
					try {
						mp.setDataSource(i);
						mp.prepare();
					} catch (IllegalArgumentException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					mp.start();
					editText.setText(ui.title);
					int cx = ct;
					uii = track.get(cx);
					taime = uii.duration;

					taim12 = (int) taime;
					String q = "" + taime;
					Log.d("log", q);
					seekBar.setProgress(0);
					seekBar.setMax(taim12);
					isRepeat = true;
					break;

				case R.id.stop:
					cc = 2;
					mp.pause();
					isRepeat = false;
					st = 1;
					break;

				case R.id.modoru:
					mp.pause();

					mp.reset();
					ct = ct - 1;
					if (ct < 0) {
						ct = track.size() - 1;
					}
					ui = track.get(ct);
					i = ui.path.toString();

					Log.d("log", i);
					try {
						mp.setDataSource(i);
						mp.prepare();
					} catch (IllegalArgumentException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					editText.setText(ui.title);
					mp.start();
					uii = track.get(ct);
					taime = uii.duration;

					taim12 = (int) taime;
					seekBar.setProgress(0);
					seekBar.setMax(taim12);
					isRepeat = true;
					break;
				case R.id.modoru12:
					mp.stop();
					mp.reset();
					mp.release();

					break;

				default:
					editText.setText(ui.title);
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// ==== キーコード判定 ====//

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// -==- Backキー -==-//

			b5.performClick();

		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO 自動生成されたメソッド・スタブ
		Log.v("MediaPlayer", "onCompletion");
		b2.performClick();
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
