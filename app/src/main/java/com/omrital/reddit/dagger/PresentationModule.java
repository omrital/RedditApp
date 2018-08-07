package com.omrital.reddit.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.omrital.reddit.core.CustomViewModelFactory;
import com.omrital.reddit.screens.food.RecentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PresentationModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(CustomViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(RecentViewModel.class)
    abstract ViewModel bindCampaignViewModel(RecentViewModel recentViewModel);
}

