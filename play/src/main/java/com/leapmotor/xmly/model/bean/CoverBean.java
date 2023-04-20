package com.leapmotor.xmly.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * good programmer.
 *
 * @date : 2021-03-16 14:04
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class CoverBean implements Parcelable {
    public static final Creator<CoverBean> CREATOR = new Creator<CoverBean>() {
        @Override
        public CoverBean createFromParcel(Parcel source) {
            return new CoverBean(source);
        }

        @Override
        public CoverBean[] newArray(int size) {
            return new CoverBean[size];
        }
    };
    /**
     * small : {"height":86,"width":86,"url":"group12/M07/17/A4/wKgDW1VxNCrzTwWVAAAJnAUfyR8545.png"}
     * middle : {"height":140,"width":140,"url":"group12/M07/17/A1/wKgDXFVxNB6wGFwmAAAJnAUfyR8949.png"}
     * large : {"height":290,"width":290,"url":""}
     */
    private SmallBean small;
    private MiddleBean middle;
    private LargeBean large;

    public CoverBean() {
    }

    public CoverBean(Parcel in) {
        this.small = in.readParcelable(SmallBean.class.getClassLoader());
        this.middle = in.readParcelable(MiddleBean.class.getClassLoader());
        this.large = in.readParcelable(LargeBean.class.getClassLoader());
    }

    public SmallBean getSmall() {
        return small;
    }

    public void setSmall(SmallBean small) {
        this.small = small;
    }

    public MiddleBean getMiddle() {
        return middle;
    }

    public void setMiddle(MiddleBean middle) {
        this.middle = middle;
    }

    public LargeBean getLarge() {
        return large;
    }

    public void setLarge(LargeBean large) {
        this.large = large;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.small, flags);
        dest.writeParcelable(this.middle, flags);
        dest.writeParcelable(this.large, flags);
    }

    public static class SmallBean implements Parcelable {
        public static final Creator<SmallBean> CREATOR = new Creator<SmallBean>() {
            @Override
            public SmallBean createFromParcel(Parcel source) {
                return new SmallBean(source);
            }

            @Override
            public SmallBean[] newArray(int size) {
                return new SmallBean[size];
            }
        };
        /**
         * height : 86
         * width : 86
         * url : group12/M07/17/A4/wKgDW1VxNCrzTwWVAAAJnAUfyR8545.png
         */
        private int height;
        private int width;
        private String url;

        public SmallBean() {
        }

        public SmallBean(Parcel in) {
            this.height = in.readInt();
            this.width = in.readInt();
            this.url = in.readString();
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.height);
            dest.writeInt(this.width);
            dest.writeString(this.url);
        }
    }

    public static class MiddleBean implements Parcelable {
        public static final Creator<MiddleBean> CREATOR = new Creator<MiddleBean>() {
            @Override
            public MiddleBean createFromParcel(Parcel source) {
                return new MiddleBean(source);
            }

            @Override
            public MiddleBean[] newArray(int size) {
                return new MiddleBean[size];
            }
        };
        /**
         * height : 140
         * width : 140
         * url : group12/M07/17/A1/wKgDXFVxNB6wGFwmAAAJnAUfyR8949.png
         */
        private int height;
        private int width;
        private String url;

        public MiddleBean() {
        }

        public MiddleBean(Parcel in) {
            this.height = in.readInt();
            this.width = in.readInt();
            this.url = in.readString();
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.height);
            dest.writeInt(this.width);
            dest.writeString(this.url);
        }
    }

    public static class LargeBean implements Parcelable {
        public static final Creator<LargeBean> CREATOR = new Creator<LargeBean>() {
            @Override
            public LargeBean createFromParcel(Parcel source) {
                return new LargeBean(source);
            }

            @Override
            public LargeBean[] newArray(int size) {
                return new LargeBean[size];
            }
        };
        /**
         * height : 290
         * width : 290
         * url :
         */
        private int height;
        private int width;
        private String url;

        public LargeBean() {
        }

        public LargeBean(Parcel in) {
            this.height = in.readInt();
            this.width = in.readInt();
            this.url = in.readString();
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.height);
            dest.writeInt(this.width);
            dest.writeString(this.url);
        }
    }
}
