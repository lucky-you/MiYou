package com.zhowin.miyou.db.manager;


import android.content.Context;

import com.zhowin.miyou.SearchUserHistoryDao;
import com.zhowin.miyou.db.model.SearchUserHistory;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * author      : Z_B
 * date       : 2018/9/6
 * function  : 操作数据库的帮助类
 */
public class DaoManagerUtils {

    /**
     * 删除
     */
    public static void deleteData(SearchUserHistory history) {
        DBManager.getInstance().getHistoryDao().delete(history);
    }

    /**
     * 根据id删除数据至数据库
     */
    public static void deleteByKeyData(Long id) {
        DBManager.getInstance().getHistoryDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     */
    public static void deleteAllData(Context context) {
        DBManager.getInstance().getHistoryDao().deleteAll();
    }

    /**
     * 查询所有数据
     */
    public static List<SearchUserHistory> queryAll() {
        QueryBuilder<SearchUserHistory> builder = DBManager.getInstance().getHistoryDao().queryBuilder();
        return builder.build().list();
    }

    /**
     * 根据ID查询
     */
    public static List<SearchUserHistory> getHistoryData() {
        return DBManager.getInstance().getHistoryDao().queryBuilder().orderDesc(SearchUserHistoryDao.Properties.Id).list();
    }

    /**
     * 存储
     */
    public static void insertHistoryDao(String key) {
        SearchUserHistory history = new SearchUserHistory(key.trim());
        SearchUserHistoryDao dao = DBManager.getInstance().getHistoryDao();
        List<SearchUserHistory> list = dao.queryBuilder().where(SearchUserHistoryDao.Properties.Title.eq(key)).list();
        if (list.size() > 0) {// 去重
            dao.delete(list.get(0));
        }
        long count = dao.queryBuilder().count();
        if (count == 3) { //只保留3个搜索历史
            List<SearchUserHistory> list1 = dao.queryBuilder().limit(1).list();
            dao.delete(list1.get(0));
        }
        dao.insert(history);
    }
}
