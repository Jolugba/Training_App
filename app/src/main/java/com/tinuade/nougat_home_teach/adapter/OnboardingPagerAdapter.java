package com.tinuade.nougat_home_teach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.tinuade.nougat_home_teach.R;
import com.tinuade.nougat_home_teach.util.OnboardingListItem;

import java.util.List;

public class OnboardingPagerAdapter extends PagerAdapter {

    private Context context;
    private List<OnboardingListItem> onboardingItemList;

    public OnboardingPagerAdapter(Context context, List<OnboardingListItem> onboardingItemList) {
        this.context = context;
        this.onboardingItemList = onboardingItemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        //initialize views
        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.into_description);

        title.setText(onboardingItemList.get(position).getTitle());
        description.setText(onboardingItemList.get(position).getDescription());
        imgSlide.setImageResource(onboardingItemList.get(position).getImageResourceId());

        container.addView(layoutScreen);

        return layoutScreen;

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return onboardingItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
