package com.leapmotor.lpradio.model.bean;

import com.leapmotor.lpradio.model.LpRadioResult;

import java.util.List;

/**
 * good programmer.
 *
 * @date : 2022-08-31 10:55
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AlbumInfoBean extends LpRadioResult<AlbumInfoBean.DataBean> {
    public static class DataBean {
        private String albumId;
        private String albumName;
        private String albumLogo;
        private List<SongBean> songList;

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public String getAlbumLogo() {
            return albumLogo;
        }

        public void setAlbumLogo(String albumLogo) {
            this.albumLogo = albumLogo;
        }

        public List<SongBean> getSongList() {
            return songList;
        }

        public void setSongList(List<SongBean> songList) {
            this.songList = songList;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "albumId='" + albumId + '\'' +
                    ", albumName='" + albumName + '\'' +
                    ", albumLogo='" + albumLogo + '\'' +
                    ", songList=" + songList +
                    '}';
        }
    }
}