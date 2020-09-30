package com.zhowin.miyou.db.manager;


import com.zhowin.base_library.base.BaseApplication;
import com.zhowin.miyou.DaoMaster;
import com.zhowin.miyou.DaoSession;
import com.zhowin.miyou.SearchUserHistoryDao;

import org.greenrobot.greendao.query.QueryBuilder;

public class DBManager {

    private static final String DB_NAME = "MiYou.db";
    private static DBManager instance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static void initDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance(), DB_NAME, null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    /**
     * 设置debug模式开启或关闭，默认关闭
     *
     * @param flag
     */
    public void setDebug(boolean flag) {
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    /**
     * 关闭数据库
     */
    public synchronized void closeDataBase() {
        closeDaoSession();
        closeDaoMaster();
    }


    private void closeDaoSession() {
        if (null != daoSession) {
            daoSession.clear();
            daoSession = null;
        }
    }


    private void closeDaoMaster() {
        if (daoMaster != null) {
            daoMaster = null;
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SearchUserHistoryDao getHistoryDao() {
        return getDaoSession().getSearchUserHistoryDao();
    }

}
