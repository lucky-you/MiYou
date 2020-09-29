package com.zhowin.miyou.mine.model;

import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/29
 * desc ：爵位信息
 */
public class KnighthoodMessageInfo {


    /**
     * rank : null
     * rankInfos : [{"rank":{"rankId":2,"rankName":"男爵","rankPictureKey":"231.jpg","price":600,"bidPrice":588,"level":1,"enable":1,"sort":0,"createTime":1600956617000},"rankPerms":[{},{},{}]},{"rank":{"rankId":1,"rankName":"子爵","rankPictureKey":"2341234tr34r.jpg","price":1000,"bidPrice":888,"level":2,"enable":1,"sort":1,"createTime":1600927797000},"rankPerms":[{},{},{}]}]
     */

    private UserRankInfo rank;
    private List<RankInfosBean> rankInfos;

    public UserRankInfo getRank() {
        return rank;
    }

    public void setRank(UserRankInfo rank) {
        this.rank = rank;
    }

    public List<RankInfosBean> getRankInfos() {
        return rankInfos;
    }

    public void setRankInfos(List<RankInfosBean> rankInfos) {
        this.rankInfos = rankInfos;
    }

    public static class RankInfosBean {
        /**
         * rank : {"rankId":2,"rankName":"男爵","rankPictureKey":"231.jpg","price":600,"bidPrice":588,"level":1,"enable":1,"sort":0,"createTime":1600956617000}
         * rankPerms : [{},{},{}]
         */

        private UserRankInfo rank;
        private List<KnighthoodList> rankPerms;

        public UserRankInfo getRank() {
            return rank;
        }

        public void setRank(UserRankInfo rank) {
            this.rank = rank;
        }

        public List<KnighthoodList> getRankPerms() {
            return rankPerms;
        }

        public void setRankPerms(List<KnighthoodList> rankPerms) {
            this.rankPerms = rankPerms;
        }
    }
}
