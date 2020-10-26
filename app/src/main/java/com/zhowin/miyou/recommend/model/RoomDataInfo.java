package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/10/24
 * desc ：房间的基本信息
 */
public class RoomDataInfo {


    /**
     * room : {"roomId":89,"title":"测试4","coverPictureKey":"http://qfah2px93.hn-bkt.clouddn.com//LuckyStar/uploads/image/20201026/3348391603673096","decoratePicture":null,"backgroundPictureKey":"http://qfah2px93.hn-bkt.clouddn.com/image.room.background.1.jpg","typeId":1,"typeName":"娱乐房","allowMicFree":1,"enableCount":0,"closeScreen":0,"existPwd":false,"owner":33,"heatValue":790}
     * owner : {"userId":33,"username":"92714635","avatar":"123","gender":"男","status":"","age":39,"profilePictureKey":"http://qfah2px93.hn-bkt.clouddn.com//LuckyStar/uploads/image/20200928/3329401601283870","levelObj":{"level":3,"startExpValue":301,"endExpValue":700,"color":"#ffffff"},"rank":{"userId":33,"rankId":1,"rankName":"子爵","rankPictureKey":"http://qfah2px93.hn-bkt.clouddn.com/2341234tr34r.jpg","level":2,"expireTime":1613269225000,"status":1},"usingTxk":{"id":"2","name":"兔耳朵","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","expireTime":1603878561000,"expireDays":3,"sort":1,"using":true},"usingCar":{"id":"1","name":"兰博基尼","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","expireTime":1611222783000,"expireDays":88,"sort":1,"using":true},"position":1,"condition":0}
     */

    private RoomBean room;
    private OwnerBean owner;

    public RoomBean getRoom() {
        return room;
    }

    public void setRoom(RoomBean room) {
        this.room = room;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public static class RoomBean {
        /**
         * roomId : 89
         * title : 测试4
         * coverPictureKey : http://qfah2px93.hn-bkt.clouddn.com//LuckyStar/uploads/image/20201026/3348391603673096
         * decoratePicture : null
         * backgroundPictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.room.background.1.jpg
         * typeId : 1
         * typeName : 娱乐房
         * allowMicFree : 1
         * enableCount : 0
         * closeScreen : 0
         * existPwd : false
         * owner : 33
         * heatValue : 790
         */

        private int roomId;
        private String title;
        private String coverPictureKey;
        private Object decoratePicture;
        private String backgroundPictureKey;
        private int typeId;
        private String typeName;
        private int allowMicFree;
        private int enableCount;
        private int closeScreen;
        private boolean existPwd;
        private int owner;
        private int heatValue;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverPictureKey() {
            return coverPictureKey;
        }

        public void setCoverPictureKey(String coverPictureKey) {
            this.coverPictureKey = coverPictureKey;
        }

        public Object getDecoratePicture() {
            return decoratePicture;
        }

        public void setDecoratePicture(Object decoratePicture) {
            this.decoratePicture = decoratePicture;
        }

        public String getBackgroundPictureKey() {
            return backgroundPictureKey;
        }

        public void setBackgroundPictureKey(String backgroundPictureKey) {
            this.backgroundPictureKey = backgroundPictureKey;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getAllowMicFree() {
            return allowMicFree;
        }

        public void setAllowMicFree(int allowMicFree) {
            this.allowMicFree = allowMicFree;
        }

        public int getEnableCount() {
            return enableCount;
        }

        public void setEnableCount(int enableCount) {
            this.enableCount = enableCount;
        }

        public int getCloseScreen() {
            return closeScreen;
        }

        public void setCloseScreen(int closeScreen) {
            this.closeScreen = closeScreen;
        }

        public boolean isExistPwd() {
            return existPwd;
        }

        public void setExistPwd(boolean existPwd) {
            this.existPwd = existPwd;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }

        public int getHeatValue() {
            return heatValue;
        }

        public void setHeatValue(int heatValue) {
            this.heatValue = heatValue;
        }
    }

    public static class OwnerBean {
        /**
         * userId : 33
         * username : 92714635
         * avatar : 123
         * gender : 男
         * status :
         * age : 39
         * profilePictureKey : http://qfah2px93.hn-bkt.clouddn.com//LuckyStar/uploads/image/20200928/3329401601283870
         * levelObj : {"level":3,"startExpValue":301,"endExpValue":700,"color":"#ffffff"}
         * rank : {"userId":33,"rankId":1,"rankName":"子爵","rankPictureKey":"http://qfah2px93.hn-bkt.clouddn.com/2341234tr34r.jpg","level":2,"expireTime":1613269225000,"status":1}
         * usingTxk : {"id":"2","name":"兔耳朵","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","expireTime":1603878561000,"expireDays":3,"sort":1,"using":true}
         * usingCar : {"id":"1","name":"兰博基尼","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg","expireTime":1611222783000,"expireDays":88,"sort":1,"using":true}
         * position : 1
         * condition : 0
         */

        private int userId;
        private String username;
        private String avatar;
        private String gender;
        private String status;
        private int age;
        private String profilePictureKey;
        private LevelObjBean levelObj;
        private RankBean rank;
        private UsingTxkBean usingTxk;
        private UsingCarBean usingCar;
        private int position;
        private int condition;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getProfilePictureKey() {
            return profilePictureKey;
        }

        public void setProfilePictureKey(String profilePictureKey) {
            this.profilePictureKey = profilePictureKey;
        }

        public LevelObjBean getLevelObj() {
            return levelObj;
        }

        public void setLevelObj(LevelObjBean levelObj) {
            this.levelObj = levelObj;
        }

        public RankBean getRank() {
            return rank;
        }

        public void setRank(RankBean rank) {
            this.rank = rank;
        }

        public UsingTxkBean getUsingTxk() {
            return usingTxk;
        }

        public void setUsingTxk(UsingTxkBean usingTxk) {
            this.usingTxk = usingTxk;
        }

        public UsingCarBean getUsingCar() {
            return usingCar;
        }

        public void setUsingCar(UsingCarBean usingCar) {
            this.usingCar = usingCar;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getCondition() {
            return condition;
        }

        public void setCondition(int condition) {
            this.condition = condition;
        }

        public static class LevelObjBean {
            /**
             * level : 3
             * startExpValue : 301
             * endExpValue : 700
             * color : #ffffff
             */

            private int level;
            private int startExpValue;
            private int endExpValue;
            private String color;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getStartExpValue() {
                return startExpValue;
            }

            public void setStartExpValue(int startExpValue) {
                this.startExpValue = startExpValue;
            }

            public int getEndExpValue() {
                return endExpValue;
            }

            public void setEndExpValue(int endExpValue) {
                this.endExpValue = endExpValue;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class RankBean {
            /**
             * userId : 33
             * rankId : 1
             * rankName : 子爵
             * rankPictureKey : http://qfah2px93.hn-bkt.clouddn.com/2341234tr34r.jpg
             * level : 2
             * expireTime : 1613269225000
             * status : 1
             */

            private int userId;
            private int rankId;
            private String rankName;
            private String rankPictureKey;
            private int level;
            private long expireTime;
            private int status;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getRankId() {
                return rankId;
            }

            public void setRankId(int rankId) {
                this.rankId = rankId;
            }

            public String getRankName() {
                return rankName;
            }

            public void setRankName(String rankName) {
                this.rankName = rankName;
            }

            public String getRankPictureKey() {
                return rankPictureKey;
            }

            public void setRankPictureKey(String rankPictureKey) {
                this.rankPictureKey = rankPictureKey;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public long getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(long expireTime) {
                this.expireTime = expireTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class UsingTxkBean {
            /**
             * id : 2
             * name : 兔耳朵
             * heightPicture : http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg
             * greyPicture : http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg
             * expireTime : 1603878561000
             * expireDays : 3
             * sort : 1
             * using : true
             */

            private String id;
            private String name;
            private String heightPicture;
            private String greyPicture;
            private long expireTime;
            private int expireDays;
            private int sort;
            private boolean using;

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

            public String getHeightPicture() {
                return heightPicture;
            }

            public void setHeightPicture(String heightPicture) {
                this.heightPicture = heightPicture;
            }

            public String getGreyPicture() {
                return greyPicture;
            }

            public void setGreyPicture(String greyPicture) {
                this.greyPicture = greyPicture;
            }

            public long getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(long expireTime) {
                this.expireTime = expireTime;
            }

            public int getExpireDays() {
                return expireDays;
            }

            public void setExpireDays(int expireDays) {
                this.expireDays = expireDays;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public boolean isUsing() {
                return using;
            }

            public void setUsing(boolean using) {
                this.using = using;
            }
        }

        public static class UsingCarBean {
            /**
             * id : 1
             * name : 兰博基尼
             * heightPicture : http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg
             * greyPicture : http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg
             * expireTime : 1611222783000
             * expireDays : 88
             * sort : 1
             * using : true
             */

            private String id;
            private String name;
            private String heightPicture;
            private String greyPicture;
            private long expireTime;
            private int expireDays;
            private int sort;
            private boolean using;

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

            public String getHeightPicture() {
                return heightPicture;
            }

            public void setHeightPicture(String heightPicture) {
                this.heightPicture = heightPicture;
            }

            public String getGreyPicture() {
                return greyPicture;
            }

            public void setGreyPicture(String greyPicture) {
                this.greyPicture = greyPicture;
            }

            public long getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(long expireTime) {
                this.expireTime = expireTime;
            }

            public int getExpireDays() {
                return expireDays;
            }

            public void setExpireDays(int expireDays) {
                this.expireDays = expireDays;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public boolean isUsing() {
                return using;
            }

            public void setUsing(boolean using) {
                this.using = using;
            }
        }
    }
}
