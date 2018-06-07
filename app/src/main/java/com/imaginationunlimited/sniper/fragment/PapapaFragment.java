package com.imaginationunlimited.sniper.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.entity.HomeSlidingItemEntity;
import com.imaginationunlimited.sniper.utils.DataService;
import com.imaginationunlimited.sniper.utils.DeviceUtils;
import com.imaginationunlimited.sniper.utils.JsonUtils;
import com.imaginationunlimited.sniper.utils.PixelUtils;
import com.imaginationunlimited.sniper.utils.RESTfulFactory;
import com.imaginationunlimited.sniper.utils.picasso.PicassoUtils;
import com.imaginationunlimited.sniper.widget.SwipeCardView.SwipeFlingAdapterView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jiaxu on 2018-06-05
 */

public class PapapaFragment extends Fragment implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener, View.OnClickListener {

    int[] headerIcons = {
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
            R.drawable.i5,
            R.drawable.i6
    };

    String[] names = {"张三", "李四", "王五", "小明", "小红", "小花"};

    String[] citys = {"北京", "上海", "广州", "深圳"};

    String[] edus = {"大专", "本科", "硕士", "博士"};

    String[] years = {"1年", "2年", "3年", "4年", "5年"};

    Random ran = new Random();

    private int cardWidth;
    private int cardHeight;

    private SwipeFlingAdapterView swipeView;
    private InnerAdapter adapter;
    private View mRootView;
    private View mNopeView, mLikeView;
    private int screenWidth, screenHeight;
    private final int DEFAULT_TRANS_X = PixelUtils.getPxByDp(160);
    private final int DEFAULT_DIFF_TRANS_Y = PixelUtils.getPxByDp(100);

    public static PapapaFragment instance() {
        PapapaFragment fragment = new PapapaFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenWidth = DeviceUtils.getScreenWidth();
        screenHeight = DeviceUtils.getScreenHeight();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_papapa, null, false);

        mLikeView = mRootView.findViewById(R.id.layout_like);
        mNopeView = mRootView.findViewById(R.id.layout_nope);
        initSlidingView();
        initView();
        loadData();
        return mRootView;
    }

    private void initSlidingView() {
        mLikeView.setTranslationY(screenHeight / 2);
        mLikeView.setTranslationX(DEFAULT_TRANS_X);
        mLikeView.setAlpha(1);
        mNopeView.setTranslationY(screenHeight / 2);
        mNopeView.setTranslationX(-DEFAULT_TRANS_X);
        mNopeView.setAlpha(1);
    }

    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (280 * density));


        swipeView = (SwipeFlingAdapterView) mRootView.findViewById(R.id.swipe_view);
        if (swipeView != null) {
            swipeView.setIsNeedSwipe(true);
            swipeView.setFlingListener(this);
            swipeView.setOnItemClickListener(this);

            adapter = new InnerAdapter();
            swipeView.setAdapter(adapter);
        }

        View v = mRootView.findViewById(R.id.swipeLeft);
        if (v != null) {
            v.setOnClickListener(this);
        }
        v = mRootView.findViewById(R.id.swipeRight);
        if (v != null) {
            v.setOnClickListener(this);
        }

    }


    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {
    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);

        mNopeView.animate().alpha(0).setDuration(100).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mNopeView.setTranslationY(screenHeight / 2);
                mNopeView.setTranslationX(-DEFAULT_TRANS_X);
                mNopeView.setAlpha(1);
            }
        }).start();
        mLikeView.animate().alpha(0).setDuration(100).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLikeView.setTranslationY(screenHeight / 2);
                mLikeView.setTranslationX(DEFAULT_TRANS_X);
                mLikeView.setAlpha(1);
            }
        }).start();
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
    }

    @Override
    public void onRightCardExit(Object dataObject) {
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {
        mLikeView.setTranslationY(screenHeight / 2 - Math.abs(scrollXProgress * DEFAULT_DIFF_TRANS_Y));
        mLikeView.setTranslationX(DEFAULT_TRANS_X - (screenWidth / 2 + DEFAULT_TRANS_X / 2) * scrollXProgress);

        mNopeView.setTranslationY(screenHeight / 2 - Math.abs(scrollXProgress * DEFAULT_DIFF_TRANS_Y));
        mNopeView.setTranslationX(-DEFAULT_TRANS_X - (screenWidth / 2 + DEFAULT_TRANS_X / 2) * scrollXProgress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.swipeLeft:
                swipeView.swipeLeft();
                //swipeView.swipeLeft(250);
                break;
            case R.id.swipeRight:
                swipeView.swipeRight();
                //swipeView.swipeRight(250);
        }
    }

    private void loadData() {
//        new AsyncTask<Void, Void, List<HomeSlidingItemEntity>>() {
//            @Override
//            protected List<HomeSlidingItemEntity> doInBackground(Void... params) {
//                ArrayList<HomeSlidingItemEntity> list = new ArrayList<>(10);
//                HomeSlidingItemEntity talent;
//                for (int i = 0; i < 10; i++) {
//                    talent = new HomeSlidingItemEntity();
//                    talent.headerIcon = headerIcons[i % headerIcons.length];
//                    talent.nickname = names[ran.nextInt(names.length - 1)];
//                    talent.cityName = citys[ran.nextInt(citys.length - 1)];
//                    talent.educationName = edus[ran.nextInt(edus.length - 1)];
//                    talent.workYearName = years[ran.nextInt(years.length - 1)];
//                    list.add(talent);
//                }
//                return list;
//            }
//
//            @Override
//            protected void onPostExecute(List<HomeSlidingItemEntity> list) {
//                super.onPostExecute(list);
//                adapter.addAll(list);
//            }
//        }.execute();

        RESTfulFactory.getInstance().createJson(DataService.class)
                .getHomeItemList("c70b005c9a42f764a3d05ed898bceb35")// FIXME
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(JSONObject stringHttpResponse) {
                        Type listType = new TypeToken<ArrayList<HomeSlidingItemEntity>>() {
                        }.getType();
                        List<HomeSlidingItemEntity> subTypeList = JsonUtils.getTypeListFromData(listType, stringHttpResponse);
//                        for (int i = 0; i < subTypeList.size(); i++) {
//                            Log.i(TAG, "---log---subTypeList-getName>" + subTypeList.get(i).getName());
//                        }
                        adapter.addAll(subTypeList);
                    }

                });
    }

    private final String TAG = "papa";

    private class InnerAdapter extends BaseAdapter {

        ArrayList<HomeSlidingItemEntity> objs;

        public InnerAdapter() {
            objs = new ArrayList<>();
        }

        public void addAll(List<HomeSlidingItemEntity> list) {
            if (isEmpty()) {
                objs.addAll(list);
                notifyDataSetChanged();
            } else {
                objs.addAll(list);
            }
        }

        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return objs.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return objs.size();
        }

        @Override
        public HomeSlidingItemEntity getItem(int position) {
            if (objs == null || objs.size() == 0) return null;
            return objs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // TODO: getView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            HomeSlidingItemEntity entity = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_card_item, parent, false);
                holder = new ViewHolder();
                convertView.setTag(holder);
                convertView.getLayoutParams().width = cardWidth;
                holder.portraitView = (ImageView) convertView.findViewById(R.id.portrait);
                //holder.portraitView.getLayoutParams().width = cardWidth;
                holder.portraitView.getLayoutParams().height = cardHeight;
                holder.nameView = (TextView) convertView.findViewById(R.id.name);
                //parentView.getLayoutParams().width = cardWidth;
                //holder.jobView = (TextView) convertView.findViewById(R.id.job);
                //holder.companyView = (TextView) convertView.findViewById(R.id.company);
                holder.cityView = (TextView) convertView.findViewById(R.id.city);
                holder.eduView = (TextView) convertView.findViewById(R.id.education);
                holder.workView = (TextView) convertView.findViewById(R.id.work_year);
                holder.recyclerView = convertView.findViewById(R.id.recycler);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            if (!TextUtils.isEmpty(entity.getUserPhoto())) {
                PicassoUtils.with(getActivity()).load(entity.getUserPhoto()).into(holder.portraitView);
            }

            holder.nameView.setText(String.format("%s", entity.getName()));
            //holder.jobView.setText(entity.jobName);

            final CharSequence no = "暂无";

            holder.cityView.setHint(no);
            holder.cityView.setText(entity.getCity());

            holder.eduView.setHint(no);
            holder.eduView.setText(entity.getDesc());

            holder.workView.setHint(no);
            holder.workView.setText(entity.getBirthYear());

            holder.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            ImagesAdapter imagesAdapter = new ImagesAdapter();
            holder.recyclerView.setAdapter(imagesAdapter);


            return convertView;
        }

        private class ImagesAdapter extends RecyclerView.Adapter<ImagesHolder> {

            @Override
            public ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(ImagesHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        }

        private class ImagesHolder extends RecyclerView.ViewHolder {

            public ImagesHolder(View itemView) {
                super(itemView);
            }
        }

    }

    private static class ViewHolder {
        ImageView portraitView;
        TextView nameView;
        TextView cityView;
        TextView eduView;
        TextView workView;
        CheckedTextView collectView;
        RecyclerView recyclerView;
    }

//    public static class HomeSlidingItemEntity {
//        public int headerIcon;
//        public String nickname;
//        public String cityName;
//        public String educationName;
//        public String workYearName;
//    }
}
