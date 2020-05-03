package com.josh.trackcovid19v2.ui.yourcountries;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.evrencoskun.tableview.TableView;
import com.josh.trackcovid19v2.R;
import com.josh.trackcovid19v2.model.ServiceRequest;
import com.josh.trackcovid19v2.ui.tableview.CountriesTableAdapter;
import com.josh.trackcovid19v2.ui.tableview.MyTableViewListener;
import com.josh.trackcovid19v2.ui.viewmodel.YourcountriesViewModel;
import com.josh.trackcovid19v2.ui.viewmodel.yourCountriesViewModelFactory;
import com.josh.trackcovid19v2.utility.InjectorUtils;

public class YourcountriesFragment extends Fragment {


    private YourcountriesViewModel yourcountryViewModel;
    private TableView mTableView;
    private CountriesTableAdapter mTableAdapter;
    private ProgressBar mProgressBar;
    private YourcountriesViewModel vYourcountriesViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_yourworld, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

         */
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yourcountry, container, false);

        mProgressBar = view.findViewById(R.id.countries_progressBar);

        mTableView = view.findViewById(R.id.countries_TableView);

        initializeTableView(mTableView);


        // initialize ViewModel
        yourCountriesViewModelFactory factory = InjectorUtils.getCountriesViewModelFactory(getActivity().getApplicationContext());
        vYourcountriesViewModel = ViewModelProviders.of(this, factory).get(YourcountriesViewModel.class);

        vYourcountriesViewModel.getCountriesList().observe(this, countries -> {

            if(countries != null && countries.size()>0){
                // set the list on TableViewModel
                mTableAdapter.setCountriesList(countries);

                hideProgressBar();
            }
        });

        // Let's post a request to get the User data from a web server.
        postRequest();

        return view;

    }

    private void initializeTableView(TableView tableView){

        // Create TableView Adapter
        mTableAdapter = new CountriesTableAdapter(getContext());
        tableView.setAdapter(mTableAdapter);

        // Create listener
        tableView.setTableViewListener(new MyTableViewListener(tableView));
    }


    private void postRequest(){
        int size = 100; // this is the count of the data items.
        int page = 1; // Which page do we want to get from the server.
        ServiceRequest serviceRequest = new ServiceRequest();
        vYourcountriesViewModel.postRequest(serviceRequest);

        showProgressBar();
    }


    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTableView.setVisibility(View.INVISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mTableView.setVisibility(View.VISIBLE);
    }
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        if(html == null){
            // return an empty spannable if the html is null
            return new SpannableString("");
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
}






