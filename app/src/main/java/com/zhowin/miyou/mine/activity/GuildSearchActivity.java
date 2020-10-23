package com.zhowin.miyou.mine.activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.zhowin.base_library.model.UserInfo;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityGuildSearchBinding;
import com.zhowin.miyou.mine.adapter.GuildSearchUserAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 公会搜索
 */
public class GuildSearchActivity extends BaseBindActivity<ActivityGuildSearchBinding> {


    private GuildSearchUserAdapter guildSearchUserAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guild_search;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<UserInfo> userList = new ArrayList<>();
        userList.add(new UserInfo());
        userList.add(new UserInfo());
        userList.add(new UserInfo());
        userList.add(new UserInfo());
        userList.add(new UserInfo());
        guildSearchUserAdapter=new GuildSearchUserAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(guildSearchUserAdapter);
        guildSearchUserAdapter.setNewData(userList);
    }
}