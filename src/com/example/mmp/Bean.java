package com.example.mmp;

import android.net.Uri;

public class Bean {
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

	// get-----------------------------------------------
	public long getId() {
		return id;
	}

	public long getAlbumId() {
		return albumId;
	}

	public long getArtistId() {
		return artistId;
	}

	public String getPath() {
		return path;
	}

	public String getTitle() {
		return title;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtist() {
		return artist;
	}

	public Uri getUri() {
		return uri;
	}

	public long getDuration() {
		return duration;
	}

	public int getTrackNo() {
		return trackNo;
	}

	// set-------------------------------------------------
	public void setId(long id) {
		this.id = id;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public void setTrackNo(int trackNo) {
		this.trackNo = trackNo;
	}

}
