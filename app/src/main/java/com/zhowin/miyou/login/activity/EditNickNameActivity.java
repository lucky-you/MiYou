package com.zhowin.miyou.login.activity;


import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.GridLayoutManager;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.pickerview.OnSelectTimeClickListener;
import com.zhowin.base_library.pickerview.PickerViewTimeUtils;
import com.zhowin.base_library.utils.ZodiacUtil;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityEditNickNameBinding;
import com.zhowin.miyou.login.adapter.SelectInterestAdapter;
import com.zhowin.miyou.login.adapter.SelectUserAvatarAdapter;
import com.zhowin.miyou.login.model.LabelList;
import com.zhowin.miyou.main.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 设置昵称
 */
public class EditNickNameActivity extends BaseBindActivity<ActivityEditNickNameBinding> implements RadioGroup.OnCheckedChangeListener {


    public static final int LAYOUT_TYPE_NICKNAME = 1; //昵称
    public static final int LAYOUT_TYPE_SEX = 2; //性别
    public static final int LAYOUT_TYPE_BIRTHDAY = 3; //生日
    public static final int LAYOUT_TYPE_AVATAR = 4; //头像
    public static final int LAYOUT_TYPE_INTEREST = 5; //兴趣
    private int layoutType = LAYOUT_TYPE_NICKNAME;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_nick_name;
    }

    @Override
    public void initView() {

        setOnClick(R.id.tvNextStep);
    }

    @Override
    public void initData() {
        mBinding.rgUserGenderLayout.setOnCheckedChangeListener(this::onCheckedChanged);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvNextStep) {
            switch (layoutType) {
                case LAYOUT_TYPE_NICKNAME:

                    layoutType = LAYOUT_TYPE_SEX;
                    break;


                case LAYOUT_TYPE_SEX:

                    layoutType = LAYOUT_TYPE_BIRTHDAY;

                    break;

                case LAYOUT_TYPE_BIRTHDAY:

                    layoutType = LAYOUT_TYPE_AVATAR;
                    break;
                case LAYOUT_TYPE_AVATAR:

                    layoutType = LAYOUT_TYPE_INTEREST;
                    break;

                case LAYOUT_TYPE_INTEREST:

//                    startActivity(MainActivity.class );

                    break;

            }
            changeUiFromLayoutType(layoutType);
        }
    }


    private void changeUiFromLayoutType(int layoutType) {
        switch (layoutType) {
            case LAYOUT_TYPE_NICKNAME:
                mBinding.tvTitle.setText("对了,你的昵称是?");
                mBinding.tvContent.setVisibility(View.GONE);
                mBinding.llNickNameLayout.setVisibility(View.VISIBLE);
                break;

            case LAYOUT_TYPE_SEX:
                mBinding.tvTitle.setText("Hi，你的性别是?");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("只有一次选择性别的机会，我们会根据选择推荐你喜欢的内容,性别选择后不可更改");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.VISIBLE);
                break;

            case LAYOUT_TYPE_BIRTHDAY:
                mBinding.tvTitle.setText("Hi，你的生日是哪天呢?");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("选择正确的生辰日期，会受到来自朋友们的祝福哟");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.GONE);
                mBinding.rlBirthdayLayout.setVisibility(View.VISIBLE);
                setUserBirthday(ZodiacUtil.getCurrentDate());
                showBirthdayDialog();
                break;

            case LAYOUT_TYPE_AVATAR:
                mBinding.tvTitle.setText("真棒！选择你喜欢的头像吧?");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("头像可选择下面你喜欢的，也可以自定义上传哟");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rlBirthdayLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.GONE);
                mBinding.selectItemRecyclerView.setVisibility(View.VISIBLE);
                List<LabelList> UserLists = getUserAvatarList();
                SelectUserAvatarAdapter userAvatarAdapterAdapter = new SelectUserAvatarAdapter(UserLists);
                mBinding.selectItemRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                mBinding.selectItemRecyclerView.setAdapter(userAvatarAdapterAdapter);

                break;

            case LAYOUT_TYPE_INTEREST:
                mBinding.tvTitle.setText("最后一步啦，\n" + "选择属于你的定制标签吧");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("兴趣标签选择后也是不可更改的，选择最符合你的三种标签吧");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rlBirthdayLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.GONE);
                mBinding.selectItemRecyclerView.setVisibility(View.VISIBLE);
                List<LabelList> labelLists = getInterestList();
                SelectInterestAdapter selectInterestAdapter = new SelectInterestAdapter(labelLists);
                mBinding.selectItemRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                mBinding.selectItemRecyclerView.setAdapter(selectInterestAdapter);
                break;

        }
    }

    private void showBirthdayDialog() {
        PickerViewTimeUtils.selectTimePickerViewNoDialog(mContext, mBinding.llSelectBirthday, new OnSelectTimeClickListener() {
            @Override
            public void onDateTime(String dateTime) {
                setUserBirthday(dateTime);
            }
        });
    }

    private void setUserBirthday(String birthday) {
        String xinZuo = ZodiacUtil.date2Constellation(birthday);
        mBinding.tvSelectBirthday.setText(birthday + " " + xinZuo);
    }


    private List<LabelList> getUserAvatarList() {

        List<LabelList> interestList = new ArrayList<>();
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        interestList.add(new LabelList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        return interestList;
    }

    private List<LabelList> getInterestList() {
        List<LabelList> interestList = new ArrayList<>();
        interestList.add(new LabelList("英雄联盟"));
        interestList.add(new LabelList("王者荣耀"));
        interestList.add(new LabelList("英雄联盟"));
        interestList.add(new LabelList("王者荣耀"));
        interestList.add(new LabelList("英雄联盟"));
        interestList.add(new LabelList("王者荣耀"));
        interestList.add(new LabelList("英雄联盟"));
        interestList.add(new LabelList("王者荣耀"));
        interestList.add(new LabelList("王者荣耀"));
        return interestList;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbManOfGender:
                break;
            case R.id.rbWomanOfGender:
                break;
        }
    }
}
