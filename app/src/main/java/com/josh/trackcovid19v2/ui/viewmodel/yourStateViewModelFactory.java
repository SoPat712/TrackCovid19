package com.josh.trackcovid19v2.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.josh.trackcovid19v2.data.StateRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link StateRepository}
 */
public class yourStateViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final StateRepository stateRepository;

    public yourStateViewModelFactory(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new YourstateViewModel(stateRepository);
    }
}
