package tv.litui.bannerdemo;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudongjian on 2017/12/6 0006.
 */

public class Banner extends ViewPager {


    private Context mContext;

    //图片链接的集合
    private List<String> mImageUrlList;

    //发送定时器的Handler
    private Handler mHandler_task;

    //定时器
    private ViewPagerTask mViewPagerTask;

    //本控件
    private Banner mBanner;

    private int mNowPosition;

    public Banner(Context context) {
        super(context);
        init(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mBanner = this;
        mContext = context;
        mHandler_task = new Handler();
        mViewPagerTask = new ViewPagerTask();
        mHandler_task.postDelayed(mViewPagerTask, 1000);
    }

    /**
     * 设置图片链接集合,将自动加载图片
     *
     * @param imageUrlList 图片链接集合
     */
    public void setImageUrlList(List<String> imageUrlList) {
        mImageUrlList = imageUrlList;
        this.setAdapter(new BannerAdapter(mImageUrlList));
    }


    private class BannerAdapter extends PagerAdapter {

        private List<View> viewList;

        private List<String> imageUrlList;

        private BannerAdapter(List<String> list) {
            imageUrlList = list;
            viewList = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return 10000;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            mNowPosition=position;

            int more = position % 3;

            View view = LayoutInflater.from(mContext).inflate(R.layout.view_banner_item, container, false);

            TextView textView = view.findViewById(R.id.tv);

            textView.setText(imageUrlList.get(more));

            container.addView(view);

            viewList.add(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }


    /**
     * 定时器,用于自动轮播图片
     */
    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            mHandler_task.postDelayed(mViewPagerTask, 1000);
            mBanner.setCurrentItem(mNowPosition+1);
        }
    }


}
