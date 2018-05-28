package com.funhotel.hmvp.model.entity;

import java.util.List;

/**
 * Description ：NewEntity1
 * Author：ZME
 * Create Time ：2018/5/27 16:51
 * Modify Time：2018/5/27 16:51
 * Version：1.0
 */
public class NewEntity1 {


    /**
     * status : 0
     * msg : ok
     * result : {"channel":"头条","num":"10","list":[{"title":"中国开闸放水27天解救越南旱灾","time":"2016-03-16 07:23","src":"中国网",
     * "category":"mil","pic":"http://api.jisuapi.com/news/upload/20160316/105123_31442.jpg","content":"原标题：防总：应越南请求
     * 中方启动澜沧江水电站水量应急调度　记者从国家防总获悉，应越南社会主义共和国请求，我方启动澜沧江梯级水电站水量应急调度，缓解湄公河流域严重旱情。3月15日8时，澜沧江景洪水电站下泄流量已加大至2190
     * 立方米每秒，标志着应越方请求，由我方实施的澜沧江梯级水电站水量应急调度正式启动。","url":"http://mil.sina.cn/zgjq/2016-03-16/detail-ifxqhmve9235380.d
     * .html?vt=4&pos=108","weburl":"http://mil.news.sina.com.cn/china/2016-03-16/doc-ifxqhmve9235380.shtml"}]}
     */

    private String status;
    private String msg;
    private ResultEntity result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {

        /**
         * channel : 头条
         * num : 10
         * list : [{"title":"中国开闸放水27天解救越南旱灾","time":"2016-03-16 07:23","src":"中国网","category":"mil",
         * "pic":"http://api.jisuapi.com/news/upload/20160316/105123_31442.jpg","content":"原标题：防总：应越南请求
         * 中方启动澜沧江水电站水量应急调度　记者从国家防总获悉，应越南社会主义共和国请求，我方启动澜沧江梯级水电站水量应急调度，缓解湄公河流域严重旱情。3月15日8时，澜沧江景洪水电站下泄流量已加大至2190
         * 立方米每秒，标志着应越方请求，由我方实施的澜沧江梯级水电站水量应急调度正式启动。","url":"http://mil.sina
         * .cn/zgjq/2016-03-16/detail-ifxqhmve9235380.d.html?vt=4&pos=108","weburl":"http://mil.news.sina.com
         * .cn/china/2016-03-16/doc-ifxqhmve9235380.shtml"}]
         */

        private String channel;
        private String num;
        private List<ListEntity> list;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {

            /**
             * title : 中国开闸放水27天解救越南旱灾
             * time : 2016-03-16 07:23
             * src : 中国网
             * category : mil
             * pic : http://api.jisuapi.com/news/upload/20160316/105123_31442.jpg
             * content : 原标题：防总：应越南请求 中方启动澜沧江水电站水量应急调度　记者从国家防总获悉，应越南社会主义共和国请求，我方启动澜沧江梯级水电站水量应急调度，缓解湄公河流域严重旱情。3月15日8
             * 时，澜沧江景洪水电站下泄流量已加大至2190立方米每秒，标志着应越方请求，由我方实施的澜沧江梯级水电站水量应急调度正式启动。
             * url : http://mil.sina.cn/zgjq/2016-03-16/detail-ifxqhmve9235380.d.html?vt=4&pos=108
             * weburl : http://mil.news.sina.com.cn/china/2016-03-16/doc-ifxqhmve9235380.shtml
             */

            private String title;
            private String time;
            private String src;
            private String category;
            private String pic;
            private String content;
            private String url;
            private String weburl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWeburl() {
                return weburl;
            }

            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
        }
    }
}
