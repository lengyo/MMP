package com.example.mmp;

import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class pureidel extends Activity {
	Track ui;
	Track id;
	SparseBooleanArray checked;

	ListView listView;
	EditText ed;
	int ct = 0;
	SharedPreferences prefer;
	ArrayList<String> DAYS = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sakusei);
		ed = (EditText) findViewById(R.id.editText1);

		prefer = getSharedPreferences("�v���C���X�g2", MODE_WORLD_READABLE);
		int i = 0;
		int ii = 0;

		while (ii == 0) {

			String useId = prefer.getString("�^�C�g��" + i, "1");
			if (useId.equals("1")) {
				ii++;
			} else {
				Log.d("log", useId);
				DAYS.add(useId);
				i++;
			}
		}
		listView = (ListView) findViewById(R.id.ListView);

		// �A�_�v�^�̍쐬
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, DAYS));

		// �t�H�[�J�X��������Ȃ��悤�ݒ�
		listView.setItemsCanFocus(false);

		// �I���̕����̐ݒ�
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		for (int io = 1; io <= ii; i++) {
			// �w�肵���A�C�e�����`�F�b�N����Ă��邩��ݒ�
			listView.setItemChecked(io, false);
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
		for (int a = 0; a < checked.size(); i++) {
			// �`�F�b�N����Ă���A�C�e���� key �̎擾
			int key = checked.keyAt(a);

		}
		Button b1 = (Button) findViewById(R.id.kanryou);
		OnClickListener a = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.kanryou:

					String name = ed.getText().toString();
					int is = 1;
					int ct = 0;
					while (is == 1) {
						boolean key = checked.get(ct);
						if (key) {
							prefer.edit().remove("�^�C�g��" + ct);
						}
						ct++;
					}
					Intent it = new Intent();
					it.setClass(pureidel.this, purei.class);
					// �A�N�e�B�r�e�B�̋N��
					startActivity(it);
					break;
				}
			}
		};
		b1.setOnClickListener(a);
	}

}
