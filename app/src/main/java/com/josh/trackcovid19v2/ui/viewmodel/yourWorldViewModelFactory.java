package com.josh.trackcovid19v2.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.josh.trackcovid19v2.data.WorldRepository;
import com.josh.trackcovid19v2.data.StateRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link StateRepository}
 */
public class yourWorldViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final WorldRepository worldRepository;

    public yourWorldViewModelFactory(WorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new YourworldViewModel(worldRepository);
    }
}
