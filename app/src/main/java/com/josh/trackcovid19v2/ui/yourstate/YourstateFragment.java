package com.josh.trackcovid19v2.ui.yourstate;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.josh.trackcovid19v2.R;
import com.josh.trackcovid19v2.model.ServiceRequest;
import com.josh.trackcovid19v2.ui.viewmodel.YourstateViewModel;
import com.josh.trackcovid19v2.ui.viewmodel.yourStateViewModelFactory;
import com.josh.trackcovid19v2.utility.InjectorUtils;

import java.text.NumberFormat;
import java.util.Locale;


public class YourstateFragment extends Fragment {

    private ProgressBar mProgressBar;
    private YourstateViewModel vYourstateViewModel;

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        if (html == null) {
            // return an empty spannable if the html is null
            return new SpannableString("");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yourstate, container, false);
        SwipeRefreshLayout mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        final int[] rememberLocation = {-1};
        final int[] j = {0};

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.v("REFRESHING", "************** APP - SWIPE REFRESH EVENT TRIGGERED!!!!!");

                postRequest();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });

        yourStateViewModelFactory factory = InjectorUtils.getStateViewModelFactory(getActivity().getApplicationContext());
        vYourstateViewModel = ViewModelProviders.of(this, factory).get(YourstateViewModel.class);

        vYourstateViewModel.getStatesList().observe(this, states -> {

            if (states != null && states.size() > 0) {
                // set the list on TableViewModel

                String[] state_list = new String[states.size()];

                for (int i = 0; i < states.size(); i++) {
                    state_list[i] = states.get(i).state;
                    if (states.get(i).state.equalsIgnoreCase("New York")) {
                        j[0] = i;
                    }
                }
                final int[] cases = {0};
                final int[] ycases = {0};
                final int[] yACases = {0};
                final int[] ACases = {0};
                final int[] yDeaths = {0};
                final int[] Toddeaths = {0};
                final int[] yTests = {0};
                final int[] tests = {0};


                vYourstateViewModel.getYesStatesList().observe(this, yesstates -> {
                    if (yesstates != null && yesstates.size() > 0) {
                        String[] yesstate_list = new String[yesstates.size()];

                        //String[] users = { "Suresh Dasari", "Trishika Dasari", "Rohini Alavala", "Praveen Kumar", "Madhav Sai" };
                        Spinner spin = view.findViewById(R.id.spinner1);
                        final TextView textView = view.findViewById(R.id.text_totalCasesData);
                        final TextView textView1 = view.findViewById(R.id.text_activeCasesData);
                        final TextView textView2 = view.findViewById(R.id.text_RecoveredData);
                        final TextView textView3 = view.findViewById(R.id.text_deathsData);
                        final TextView textView4 = view.findViewById(R.id.text_CriticalData);
                        final TextView textView5 = view.findViewById(R.id.text_CriticalDataIncriment);
                        final TextView textView6 = view.findViewById(R.id.text_deathsDataIncriment);
                        final TextView textView7 = view.findViewById(R.id.text_RealRecoveredDataIncriment);
                        final TextView textView8 = view.findViewById(R.id.text_dailyIncriment);
                        final TextView textView9 = view.findViewById(R.id.text_activeCasesIncriment);
                        final TextView textView10 = view.findViewById(R.id.textView2);
                        final TextView textView11 = view.findViewById(R.id.text_totalTestsData);
                        final TextView textView12 = view.findViewById(R.id.text_testsPerOneMillionData);
                        final TextView textView13 = view.findViewById(R.id.text_updated);
                        final ImageView imageView = view.findViewById(R.id.htmlImageGetter);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner_style, state_list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spin.setAdapter(adapter);
                        if (rememberLocation[0] == -1)
                            spin.setSelection(j[0]);
                        else
                            spin.setSelection(rememberLocation[0]);
                        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                rememberLocation[0] = position;
                                j[0] = 0;
                                Log.d("foo", state_list[position]);
                                cases[0] = states.get(position).cases;
                                ycases[0] = yesstates.get(position).cases;
                                ACases[0] = states.get(position).active;
                                yACases[0] = yesstates.get(position).active;
                                Toddeaths[0] = states.get(position).deaths;
                                yDeaths[0] = yesstates.get(position).deaths;
                                tests[0] = states.get(position).tests;
                                yTests[0] = yesstates.get(position).tests;
                                NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                                //setData(vYourworldViewModel,world);
                                textView10.setText(Html.fromHtml("<b>Pick a state from the dropdown below</b>"));
                                String totalCasesWithCommas = numberFormat.format(states.get(position).cases);
                                String activeCasesWithCommas = numberFormat.format(states.get(position).active);
                                String deathWithCommas = numberFormat.format(states.get(position).deaths);
                                String ToddeathsWithCommas = numberFormat.format(states.get(position).todayDeaths);
                                String TodCasesWithCommas = numberFormat.format(states.get(position).todayCases);
                                String testsWithCommas = numberFormat.format(states.get(position).tests);
                                String testsMilWithcommas = numberFormat.format(states.get(position).testsPerMillion);
                                textView.setText(Html.fromHtml("<center><b>" + totalCasesWithCommas + "</b></center>"));
                                textView3.setText(Html.fromHtml("<b>" + deathWithCommas + "</b>"));
                                textView1.setText(Html.fromHtml("<b>" + activeCasesWithCommas + "</b>"));
                                textView6.setText(Html.fromHtml("<b>" + ToddeathsWithCommas + "</b>"));
                                textView8.setText(Html.fromHtml("<b>" + TodCasesWithCommas + "</b>"));
                                textView11.setText(Html.fromHtml("<b>" + testsWithCommas + "</b>"));
                                textView12.setText(Html.fromHtml("<b>" + testsMilWithcommas + "</b>"));
                                String dailyActWithCommas = numberFormat.format(ACases[0] - yACases[0]);
                                String dailyTestWithCommas = numberFormat.format(tests[0] - yTests[0]);
                                if ((cases[0] - ycases[0]) >= 0) {
                                    Spanned text = Html.fromHtml("<i>Daily Increment: " + "<b>" + "+" + TodCasesWithCommas + "</i></b>");
                                    SpannableString ss = new SpannableString(text);
                                    ForegroundColorSpan fcsWhite = new ForegroundColorSpan(Color.WHITE);
                                    @SuppressLint("ResourceAsColor") ForegroundColorSpan fcsGreen = new ForegroundColorSpan(R.color.just_cuz);
                                    ss.setSpan(fcsWhite, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    textView8.setText(ss);
                                } else {
                                    Spanned text = Html.fromHtml("<i>Daily Increment: " + "<b>" + "-" + TodCasesWithCommas + "</i></b>");
                                    SpannableString ss = new SpannableString(text);
                                    ForegroundColorSpan fcsWhite = new ForegroundColorSpan(Color.WHITE);
                                    @SuppressLint("ResourceAsColor") ForegroundColorSpan fcsGreen = new ForegroundColorSpan(R.color.just_cuz);
                                    ss.setSpan(fcsWhite, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    textView8.setText(ss);
                                }
                                if ((ACases[0] - yACases[0]) >= 0) {
                                    textView9.setText(Html.fromHtml("<b><i>" + "+" + dailyActWithCommas + "</i></b>"));
                                } else {
                                    textView9.setText(Html.fromHtml("<b><i>" + dailyActWithCommas + "</i></b>"));
                                }
                                if ((Toddeaths[0] - yDeaths[0]) >= 0) {
                                    textView6.setText(Html.fromHtml("<b><i>" + "+" + ToddeathsWithCommas + "</i></b>"));
                                } else {
                                    textView6.setText(Html.fromHtml("<b><i>" + ToddeathsWithCommas + "</i></b>"));
                                }
                            /*
                            if((Toddeaths[0] - yDeaths[0]) >= 0){
                                textView6.setText(Html.fromHtml("<b><i>"  + "+" + ToddeathsWithCommas + "</i></b>"));
                            }
                            else {
                                textView6.setText(Html.fromHtml("<b><i>"  + "-" + ToddeathsWithCommas + "</i></b>"));
                            }
                            */
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    else{
                        Log.d("lol", "didn't work something broken inside one." + ",      name: "+"not found at all");
                    }

                });

            }
            else{
                Log.d("lol", "didn't work something broken outside one." + ",      name: "+"not found at all");
            }
        });
        postRequest();
        return view;
    }

    private void postRequest() {
        int size = 100; // this is the count of the data items.
        int page = 1; // Which page do we want to get from the server.
        ServiceRequest serviceRequest = new ServiceRequest();
        vYourstateViewModel.postRequest(serviceRequest);

    }
}
