package com.example.fragmenttransition.adapter;

import static com.example.fragmenttransition.adapter.ImageData.IMAGE_DRAWABLES;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.fragmenttransition.fragment.ImageFragment;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    public ImagePagerAdapter(Fragment fragment) {
        // Note: Initialize with the child fragment manager.
        super(fragment.getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public int getCount() {
        return IMAGE_DRAWABLES.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(IMAGE_DRAWABLES[position]);
    }
}