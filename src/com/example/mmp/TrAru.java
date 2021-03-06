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
	public long id; // コンテントプロバイダに登録されたID
	public long albumId; // 同じくトラックのアルバムのID
	public long artistId; // 同じくトラックのアーティストのID
	public String path; // 実データのPATH
	public String title; // トラックタイトル
	public String album; // アルバムタイトル
	public String artist; // アーティスト名
	public Uri uri; // URI
	public long duration; // 再生時間(ミリ秒)
	public int trackNo; // アルバムのトラックナンバ
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
