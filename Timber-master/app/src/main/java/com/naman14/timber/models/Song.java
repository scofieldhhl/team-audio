/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.timber.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Song {
    @Id
    public long index;

    public long albumId;
    public String albumName;
    public long artistId;
    public String artistName;
    public int duration;
    public long id;
    public String title;
    public int trackNumber;

    //add by hhl
    public String path;
    public String indexBegin;//开始时间
    public String indexEnd;//结束时间

    public String format;//格式信息
    public long seekPostion;

    public String sampleRate;//采样频率 HZ
    public String bit;// bits
    public String bitRate;//码率Kbps

    public String categoryType;//列表所属类别

    public Song() {
        this.id = -1;
        this.albumId = -1;
        this.artistId = -1;
        this.title = "";
        this.artistName = "";
        this.albumName = "";
        this.duration = -1;
        this.trackNumber = -1;
    }

    public Song(long _id, long _albumId, long _artistId, String _title, String _artistName, String _albumName, int _duration, int _trackNumber) {
        this.id = _id;
        this.albumId = _albumId;
        this.artistId = _artistId;
        this.title = _title;
        this.artistName = _artistName;
        this.albumName = _albumName;
        this.duration = _duration;
        this.trackNumber = _trackNumber;
    }

    public String getCategoryType() {
        return this.categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getBitRate() {
        return this.bitRate;
    }

    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    public String getBit() {
        return this.bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }

    public String getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(String sampleRate) {
        this.sampleRate = sampleRate;
    }

    public long getSeekPostion() {
        return this.seekPostion;
    }

    public void setSeekPostion(long seekPostion) {
        this.seekPostion = seekPostion;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getIndexEnd() {
        return this.indexEnd;
    }

    public void setIndexEnd(String indexEnd) {
        this.indexEnd = indexEnd;
    }

    public String getIndexBegin() {
        return this.indexBegin;
    }

    public void setIndexBegin(String indexBegin) {
        this.indexBegin = indexBegin;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTrackNumber() {
        return this.trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public long getArtistId() {
        return this.artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getIndex() {
        return this.index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    @Generated(hash = 1553552365)
    public Song(long index, long albumId, String albumName, long artistId, String artistName, int duration, long id, String title, int trackNumber,
            String path, String indexBegin, String indexEnd, String format, long seekPostion, String sampleRate, String bit, String bitRate,
            String categoryType) {
        this.index = index;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.duration = duration;
        this.id = id;
        this.title = title;
        this.trackNumber = trackNumber;
        this.path = path;
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.format = format;
        this.seekPostion = seekPostion;
        this.sampleRate = sampleRate;
        this.bit = bit;
        this.bitRate = bitRate;
        this.categoryType = categoryType;
    }

    
}
