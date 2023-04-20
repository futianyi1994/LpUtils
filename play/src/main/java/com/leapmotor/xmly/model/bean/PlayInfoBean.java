package com.leapmotor.xmly.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * good programmer.
 *
 * @date : 2021-03-16 14:16
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class PlayInfoBean implements Parcelable {
    /**
     * play_32 : {"url":"http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL6zHTqEAATPrstpAvs630.mp3","size":315310}
     * play_64 : {"url":"http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2ACqArAAl3hAI3oHk004.mp3","size":620420}
     * play_24_aac : null
     * play_64_aac : null
     * play_128_aac : null
     * play_24_m4a : {"url":"http://audio.xmcdn.com/group66/M08/71/26/wKgMa11mNL6QTs5xAAOfnGwGu94953.m4a","size":237468}
     * play_64_m4a : {"url":"http://audio.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2Ruq_QAAlywS5lgPY531.m4a","size":619201}
     * play_amr : {"url":"http://fdfs.xmcdn.com/group65/M04/70/B7/wKgMal1mNLyw1Qj7AAEMIL5HPMg131.amr","size":68640}
     * download_url : {"url":"http://download.xmcdn.com/group66/M08/71/26/wKgMa11mNL6QTs5xAAOfnGwGu94953.m4a","size":237468}
     */
    public static final Creator<PlayInfoBean> CREATOR = new Creator<PlayInfoBean>() {
        @Override
        public PlayInfoBean createFromParcel(Parcel source) {
            return new PlayInfoBean(source);
        }

        @Override
        public PlayInfoBean[] newArray(int size) {
            return new PlayInfoBean[size];
        }
    };
    /**
     * 32位mp3音频地址(免费声音使用,但不保证有)
     */
    private Play32Bean play_32;
    /**
     * 64位mp3音频地址(免费声音使用,但不保证有)
     */
    private Play64Bean play_64;
    /**
     * 24位码率aac格式音频地址(付费声音使用)
     */
    private Play24AacBean play_24_aac;
    /**
     * 64位码率aac格式音频地址(付费声音使用)
     */
    private Play64AacBean play_64_aac;
    /**
     * 128位码率aac格式音频地址(免费声音使用,但不保证有)
     */
    private Play128AacBean play_128_aac;
    /**
     * 24位码率m4a格式音频(免费声音使用)
     */
    private Play24M4aBean play_24_m4a;
    /**
     * 64位码率m4a格式音频(免费声音使用)
     */
    private Play64M4aBean play_64_m4a;
    /**
     * amr格式音频(免费声音使用)
     */
    private PlayAmrBean play_amr;
    /**
     * 音频下载地址(免费声音使用)
     */
    private DownloadUrlBean download_url;

    public PlayInfoBean() {
    }

    protected PlayInfoBean(Parcel in) {
        this.play_32 = in.readParcelable(Play32Bean.class.getClassLoader());
        this.play_64 = in.readParcelable(Play64Bean.class.getClassLoader());
        this.play_24_aac = in.readParcelable(Play24AacBean.class.getClassLoader());
        this.play_64_aac = in.readParcelable(Play64AacBean.class.getClassLoader());
        this.play_128_aac = in.readParcelable(Play128AacBean.class.getClassLoader());
        this.play_24_m4a = in.readParcelable(Play24M4aBean.class.getClassLoader());
        this.play_64_m4a = in.readParcelable(Play64M4aBean.class.getClassLoader());
        this.play_amr = in.readParcelable(PlayAmrBean.class.getClassLoader());
        this.download_url = in.readParcelable(DownloadUrlBean.class.getClassLoader());
    }

    public Play32Bean getPlay_32() {
        return play_32;
    }

    public void setPlay_32(Play32Bean play_32) {
        this.play_32 = play_32;
    }

    public Play64Bean getPlay_64() {
        return play_64;
    }

    public void setPlay_64(Play64Bean play_64) {
        this.play_64 = play_64;
    }

    public Play24AacBean getPlay_24_aac() {
        return play_24_aac;
    }

    public void setPlay_24_aac(Play24AacBean play_24_aac) {
        this.play_24_aac = play_24_aac;
    }

    public Play64AacBean getPlay_64_aac() {
        return play_64_aac;
    }

    public void setPlay_64_aac(Play64AacBean play_64_aac) {
        this.play_64_aac = play_64_aac;
    }

    public Play128AacBean getPlay_128_aac() {
        return play_128_aac;
    }

    public void setPlay_128_aac(Play128AacBean play_128_aac) {
        this.play_128_aac = play_128_aac;
    }

    public Play24M4aBean getPlay_24_m4a() {
        return play_24_m4a;
    }

    public void setPlay_24_m4a(Play24M4aBean play_24_m4a) {
        this.play_24_m4a = play_24_m4a;
    }

    public Play64M4aBean getPlay_64_m4a() {
        return play_64_m4a;
    }

    public void setPlay_64_m4a(Play64M4aBean play_64_m4a) {
        this.play_64_m4a = play_64_m4a;
    }

    public PlayAmrBean getPlay_amr() {
        return play_amr;
    }

    public void setPlay_amr(PlayAmrBean play_amr) {
        this.play_amr = play_amr;
    }

    public DownloadUrlBean getDownload_url() {
        return download_url;
    }

    public void setDownload_url(DownloadUrlBean download_url) {
        this.download_url = download_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.play_32, flags);
        dest.writeParcelable(this.play_64, flags);
        dest.writeParcelable(this.play_24_aac, flags);
        dest.writeParcelable(this.play_64_aac, flags);
        dest.writeParcelable(this.play_128_aac, flags);
        dest.writeParcelable(this.play_24_m4a, flags);
        dest.writeParcelable(this.play_64_m4a, flags);
        dest.writeParcelable(this.play_amr, flags);
        dest.writeParcelable(this.download_url, flags);
    }

    public static class Play32Bean implements Parcelable {
        public static final Creator<Play32Bean> CREATOR = new Creator<Play32Bean>() {
            @Override
            public Play32Bean createFromParcel(Parcel source) {
                return new Play32Bean(source);
            }

            @Override
            public Play32Bean[] newArray(int size) {
                return new Play32Bean[size];
            }
        };
        /**
         * url : http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL6zHTqEAATPrstpAvs630.mp3
         * size : 315310
         */
        private String url;
        private int size;

        public Play32Bean() {
        }

        protected Play32Bean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class Play64Bean implements Parcelable {
        public static final Creator<Play64Bean> CREATOR = new Creator<Play64Bean>() {
            @Override
            public Play64Bean createFromParcel(Parcel source) {
                return new Play64Bean(source);
            }

            @Override
            public Play64Bean[] newArray(int size) {
                return new Play64Bean[size];
            }
        };
        /**
         * url : http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2ACqArAAl3hAI3oHk004.mp3
         * size : 620420
         */
        private String url;
        private int size;

        public Play64Bean() {
        }

        protected Play64Bean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class Play24AacBean implements Parcelable {
        public static final Creator<Play24AacBean> CREATOR = new Creator<Play24AacBean>() {
            @Override
            public Play24AacBean createFromParcel(Parcel source) {
                return new Play24AacBean(source);
            }

            @Override
            public Play24AacBean[] newArray(int size) {
                return new Play24AacBean[size];
            }
        };
        /**
         * url : http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2ACqArAAl3hAI3oHk004.mp3
         * size : 620420
         */
        private String url;
        private int size;

        public Play24AacBean() {
        }

        protected Play24AacBean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class Play64AacBean implements Parcelable {
        public static final Creator<Play64AacBean> CREATOR = new Creator<Play64AacBean>() {
            @Override
            public Play64AacBean createFromParcel(Parcel source) {
                return new Play64AacBean(source);
            }

            @Override
            public Play64AacBean[] newArray(int size) {
                return new Play64AacBean[size];
            }
        };
        /**
         * url : http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2ACqArAAl3hAI3oHk004.mp3
         * size : 620420
         */
        private String url;
        private int size;

        public Play64AacBean() {
        }

        protected Play64AacBean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class Play128AacBean implements Parcelable {
        public static final Creator<Play128AacBean> CREATOR = new Creator<Play128AacBean>() {
            @Override
            public Play128AacBean createFromParcel(Parcel source) {
                return new Play128AacBean(source);
            }

            @Override
            public Play128AacBean[] newArray(int size) {
                return new Play128AacBean[size];
            }
        };
        /**
         * url : http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2ACqArAAl3hAI3oHk004.mp3
         * size : 620420
         */
        private String url;
        private int size;

        public Play128AacBean() {
        }

        protected Play128AacBean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class Play24M4aBean implements Parcelable {
        public static final Creator<Play24M4aBean> CREATOR = new Creator<Play24M4aBean>() {
            @Override
            public Play24M4aBean createFromParcel(Parcel source) {
                return new Play24M4aBean(source);
            }

            @Override
            public Play24M4aBean[] newArray(int size) {
                return new Play24M4aBean[size];
            }
        };
        /**
         * url : http://audio.xmcdn.com/group66/M08/71/26/wKgMa11mNL6QTs5xAAOfnGwGu94953.m4a
         * size : 237468
         */
        private String url;
        private int size;

        public Play24M4aBean() {
        }

        protected Play24M4aBean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class Play64M4aBean implements Parcelable {
        public static final Creator<Play64M4aBean> CREATOR = new Creator<Play64M4aBean>() {
            @Override
            public Play64M4aBean createFromParcel(Parcel source) {
                return new Play64M4aBean(source);
            }

            @Override
            public Play64M4aBean[] newArray(int size) {
                return new Play64M4aBean[size];
            }
        };
        /**
         * url : http://audio.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2Ruq_QAAlywS5lgPY531.m4a
         * size : 619201
         */
        private String url;
        private int size;

        public Play64M4aBean() {
        }

        protected Play64M4aBean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class PlayAmrBean implements Parcelable {
        public static final Creator<PlayAmrBean> CREATOR = new Creator<PlayAmrBean>() {
            @Override
            public PlayAmrBean createFromParcel(Parcel source) {
                return new PlayAmrBean(source);
            }

            @Override
            public PlayAmrBean[] newArray(int size) {
                return new PlayAmrBean[size];
            }
        };
        /**
         * url : http://fdfs.xmcdn.com/group65/M04/70/B7/wKgMal1mNLyw1Qj7AAEMIL5HPMg131.amr
         * size : 68640
         */
        private String url;
        private int size;

        public PlayAmrBean() {
        }

        protected PlayAmrBean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }

    public static class DownloadUrlBean implements Parcelable {
        public static final Creator<DownloadUrlBean> CREATOR = new Creator<DownloadUrlBean>() {
            @Override
            public DownloadUrlBean createFromParcel(Parcel source) {
                return new DownloadUrlBean(source);
            }

            @Override
            public DownloadUrlBean[] newArray(int size) {
                return new DownloadUrlBean[size];
            }
        };
        /**
         * url : http://download.xmcdn.com/group66/M08/71/26/wKgMa11mNL6QTs5xAAOfnGwGu94953.m4a
         * size : 237468
         */
        private String url;
        private int size;

        public DownloadUrlBean() {
        }

        protected DownloadUrlBean(Parcel in) {
            this.url = in.readString();
            this.size = in.readInt();
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeInt(this.size);
        }
    }
}
