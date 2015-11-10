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

		// �A�_�v�^�̍쐬
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, DAYS));

		// �t�H�[�J�X��������Ȃ��悤�ݒ�
		listView.setItemsCanFocus(false);

		// �I���̕����̐ݒ�
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		for (int i = 1; i <= track.size() - 1; i++) {
			// �w�肵���A�C�e�����`�F�b�N����Ă��邩��ݒ�
			listView.setItemChecked(i, false);
		}

		// �A�C�e�����N���b�N���ꂽ�Ƃ��ɌĂяo�����R�[���o�b�N��o�^
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});

		// ���݃`�F�b�N����Ă���A�C�e�����擾
		// �`�F�b�N����ĂȂ��A�C�e���͊܂܂�Ȃ��͗l
		checked = listView.getCheckedItemPositions();
		for (int i = 0; i < checked.size(); i++) {
			// �`�F�b�N����Ă���A�C�e���� key �̎擾
			int key = checked.keyAt(i);
			Log.v(getClass().getSimpleName(), "values: " + DAYS[key]);
		}
		Button b1 = (Button) findViewById(R.id.kanryou);
		OnClickListener a = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.kanryou:
					SharedPreferences pref = getSharedPreferences("�v���C���X�g2",
							MODE_WORLD_READABLE);
					int ii = 0;
					while (ii == 0) {
						String use = pref.getString("�^�C�g��" + ct, "1");
						if (use.equals("1")) {
							ii++;
							ct--;
						}
						ct++;

					}
					String name = ed.getText().toString();
					Log.d("name", name);

					SharedPreferences prefer;
					if (name.equals("���O����͂��Ă�������")) {
						prefer = getSharedPreferences("�v���C���X�g" + ct,
								MODE_WORLD_READABLE);
						name = "�v���C���X�g" + ct;
					} else {
						prefer = getSharedPreferences(name, MODE_WORLD_READABLE);
					}
					Editor edito = pref.edit();
					edito.putString("�^�C�g��" + ct, name);
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
							editor.putString("�^�C�g��" + i, DAYS[ct]);
							id = track.get(ct);
							editor.putString("tid" + i, id.path);
							editor.putLong("taim" + i, id.duration);
							editor.commit();
							String useId = prefer
									.getString("�^�C�g��" + i, "10001");
							Log.d("lo", useId);
							i++;
						}
						ct++;
					}
					Intent it = new Intent();
					it.setClass(psakusei.this, purei.class);
					// �A�N�e�B�r�e�B�̋N��
					startActivity(it);
					break;
				}
			}
		};
		b1.setOnClickListener(a);
	}

	// ListView �ɕ\�������镶����

}