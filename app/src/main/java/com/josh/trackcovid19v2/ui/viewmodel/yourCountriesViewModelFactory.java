package com.josh.trackcovid19v2.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.josh.trackcovid19v2.data.CountriesRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link CountriesRepository}
 */
public class yourCountriesViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final CountriesRepository countriesRepository;

    public yourCountriesViewModelFactory(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new YourcountriesViewModel(countriesRepository);
    }
}
