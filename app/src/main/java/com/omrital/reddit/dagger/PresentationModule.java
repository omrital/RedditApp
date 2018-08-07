package com.omrital.reddit.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.omrital.reddit.core.CustomViewModelFactory;
import com.omrital.reddit.screens.favorites.FavoritesViewModel;
import com.omrital.reddit.screens.food.FoodViewModel;
import com.omrital.reddit.screens.fullItem.FullItemViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PresentationModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(CustomViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(FoodViewModel.class)
    abstract ViewModel bindCampaignViewModel(FoodViewModel foodViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel.class)
    abstract ViewModel bindFavoritesViewModel(FavoritesViewModel favoritesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FullItemViewModel.class)
    abstract ViewModel bindFullItemViewModel(FullItemViewModel fullItemViewModel);
}

