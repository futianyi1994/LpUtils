package com.leapmotor.lpradio.model.bean;

import com.leapmotor.lpradio.model.LpRadioResult;

import java.util.List;

/**
 * good programmer.
 *
 * @date : 2022-09-08 17:18
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class RecommendListBean extends LpRadioResult<List<RecommendListBean.DataBean>> {
    public static class DataBean {

        private String id;
        private String name;
        private String type;
        private String logo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", logo='" + logo + '\'' +
                    '}';
        }
    }
}