package com.example.mmp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class TrAru {
	public long id; // �R���e���g�v���o�C�_�ɓo�^���ꂽID
	public long albumId; // �������g���b�N�̃A���o����ID
	public long artistId; // �������g���b�N�̃A�[�e�B�X�g��ID
	public String path; // ���f�[�^��PATH
	public String title; // �g���b�N�^�C�g��
	public String album; // �A���o���^�C�g��
	public String artist; // �A�[�e�B�X�g��
	public Uri uri; // URI
	public long duration; // �Đ�����(�~���b)
	public int trackNo; // �A���o���̃g���b�N�i���o
	public static final String[] COLUMNS = { MediaStore.Audio.Media._ID,
			MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.TITLE,
			MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ARTIST_ID,
			MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.TRACK, };

	public static List getItems(Context activity) {

		List tracks = new ArrayList();
		ContentResolver resolver = activity.getContentResolver();
		Cursor cursor = resolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Track.COLUMNS,
				null, null, null);
		while (cursor.moveToNext()) {
			if (cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION)) < 3000) {
				continue;
			}
			tracks.add(new Track(cursor));
		}
		cursor.close();
		return tracks;
	}

	public static List getItemsByAlbum(Context activity, long albumID) {

		List tracks = new ArrayList();
		ContentResolver resolver = activity.getContentResolver();
		String[] SELECTION_ARG = { "" };
		SELECTION_ARG[0] = String.valueOf(albumID);
		Cursor cursor = resolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Track.COLUMNS,
				MediaStore.Audio.Media.ALBUM_ID + "= ?", SELECTION_ARG, null);
		while (cursor.moveToNext()) {
			if (cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION)) < 3000) {
				continue;
			}
			tracks.add(new Track(cursor));
		}
		cursor.close();
		return tracks;
	}

}
