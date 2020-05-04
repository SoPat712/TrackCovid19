package com.josh.trackcovid19v2.ui.yourworld;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.josh.trackcovid19v2.R;
import com.josh.trackcovid19v2.model.ServiceRequest;
import com.josh.trackcovid19v2.ui.viewmodel.yourWorldViewModelFactory;
import com.josh.trackcovid19v2.utility.InjectorUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class YourworldFragment extends Fragment {

    private com.josh.trackcovid19v2.ui.viewmodel.YourworldViewModel vYourworldViewModel;
    private static DecimalFormat df = new DecimalFormat("0.00");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yourworld, container, false);
        SwipeRefreshLayout mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
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
        yourWorldViewModelFactory factory = InjectorUtils.getWorldViewModelFactory(getActivity().getApplicationContext());
        vYourworldViewModel = ViewModelProviders.of(this, factory).get(com.josh.trackcovid19v2.ui.viewmodel.YourworldViewModel.class);

        final int[] cases = {0};
        final int[] ycases = {0};
        final int[] yACases = {0};
        final int[] ACases = {0};
        final int[] yRecovered = {0};
        final int[] recovered = {0};
        final int[] yDeaths = {0};
        final int[] Toddeaths = {0};
        vYourworldViewModel.getWorld().observe(this, world -> {

            if (world != null) {

                Log.d("foooo", String.valueOf(world.cases));


                cases[0] = world.cases;
                ACases[0] = world.active;
                recovered[0] = world.recovered;
                Toddeaths[0] = world.deaths;

                Log.d("foooo", "I am here");
                NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                //setData(vYourworldViewModel,world);
                TextView textView = view.findViewById(R.id.text_totalCasesData);
                TextView textViewa = view.findViewById(R.id.text_totalCasesName);
                TextView textView2 = view.findViewById(R.id.text_deathsData);
                TextView textView3 = view.findViewById(R.id.text_recoveredData);
                //unneeded for now
                TextView textView4 = view.findViewById(R.id.text_updated);
                TextView textView5 = view.findViewById(R.id.text_activeCasesData);
                TextView textView6 = view.findViewById(R.id.text_affectedCountriesData);
                TextView textView7 = view.findViewById(R.id.textView2);
                TextView textView123 = view.findViewById(R.id.textView4);

                //commas to large numbers
                String totalCasesWithCommas = numberFormat.format(world.cases);
                String activeCasesWithCommas = numberFormat.format(world.active);
                String recoveredWithCommas = numberFormat.format(world.recovered);
                String deathWithCommas = numberFormat.format(world.deaths);
                //yesworld data goes above
                String affectedCountriesData = Integer.toString(world.affectedCountries);
                Date date = new Date(world.updated);
                textView.setText(Html.fromHtml("<center><b>" + totalCasesWithCommas + "</b></center>"));
                textViewa.setText(Html.fromHtml("Total Confirmed Cases"));
                textView7.setText(Html.fromHtml("<b>COVID-19  Statistics</b>"));
                textView2.setText(Html.fromHtml("<b>" + deathWithCommas + "</b>"));
                textView3.setText(Html.fromHtml("<b>" + recoveredWithCommas + "</b>"));
                textView5.setText(Html.fromHtml("<b>" + activeCasesWithCommas + "</b>"));
                textView6.setText(Html.fromHtml("<b><sup>" + affectedCountriesData + "</sup>" + "/<sub>251</sub></b>"));
                textView4.setText(Html.fromHtml("Updated on " + date));
                ImageView img1 = view.findViewById(R.id.image_level);
                double recov = world.recovered;
                double tcases = world.cases;
                double percent = (recov / tcases) * 100;
                String percentlegible = df.format(percent);
                textView123.setText(Html.fromHtml("<b><i>" + percentlegible + "%</i></b>"));
                Log.d("dfdF", String.valueOf(world.recovered));
                Log.d("dfdF", String.valueOf(world.cases));
                Log.d("dfdF", String.valueOf(percent));
                int x = (int) percent;
                ClipDrawable drawable = (ClipDrawable) img1.getDrawable();
                drawable.setLevel(100 * x);
            }
            vYourworldViewModel.getYesWorld().observe(this, yesworld -> {

                if (yesworld != null) {

                    Log.d("foooo", String.valueOf(yesworld.cases));
                    ycases[0] = yesworld.cases;
                    yACases[0] = yesworld.active;
                    yRecovered[0] = yesworld.recovered;
                    yDeaths[0] = yesworld.deaths;

                    Log.d("foooo", "I am in yesworld here");
                    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                    TextView textView8 = view.findViewById(R.id.text_dailyIncriment);
                    TextView textView9 = view.findViewById(R.id.text_activeCasesIncriment);
                    TextView textView10 = view.findViewById(R.id.text_recoveredDataIncriment);
                    TextView textView11 = view.findViewById(R.id.text_deathsDataIncriment);
                    String dailyIncWithCommas = numberFormat.format(cases[0] - ycases[0]);
                    String dailyActWithCommas = numberFormat.format(ACases[0] - yACases[0]);
                    String dailyDeathWithCommas = numberFormat.format(Toddeaths[0] - yDeaths[0]);
                    String dailyRecoveredWithCommas = numberFormat.format(recovered[0] - yRecovered[0]);
                    Log.d("foo", "I got here");

                    if ((cases[0] - ycases[0]) >= 0) {
                        Spanned text = Html.fromHtml("<i>Daily Increment: " + "<b>" + "+" + dailyIncWithCommas + "</i></b>");
                        SpannableString ss = new SpannableString(text);
                        ForegroundColorSpan fcsWhite = new ForegroundColorSpan(Color.WHITE);
                        @SuppressLint("ResourceAsColor") ForegroundColorSpan fcsGreen = new ForegroundColorSpan(R.color.just_cuz);
                        ss.setSpan(fcsWhite, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        textView8.setText(ss);
                    } else {
                        Spanned text = Html.fromHtml("<i>Daily Increment: " + "<b>" + "" + dailyIncWithCommas + "</i></b>");
                        SpannableString ss = new SpannableString(text);
                        ForegroundColorSpan fcsWhite = new ForegroundColorSpan(Color.WHITE);
                        @SuppressLint("ResourceAsColor") ForegroundColorSpan fcsGreen = new ForegroundColorSpan(R.color.just_cuz);
                        ss.setSpan(fcsWhite, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        textView8.setText(ss);
                    }
                    if ((ACases[0] - yACases[0]) >= 0) {
                        textView9.setText(Html.fromHtml("<b><i>" + "+" + dailyActWithCommas + "</i></b>"));
                    } else {
                        textView9.setText(Html.fromHtml("<b><i>" + "" + dailyActWithCommas + "</i></b>"));
                    }
                    if ((recovered[0] - yRecovered[0]) >= 0) {
                        textView10.setText(Html.fromHtml("<b><i>" + "+" + dailyRecoveredWithCommas + "</i></b>"));
                    } else {
                        textView10.setText(Html.fromHtml("<b><i>" + "" + dailyRecoveredWithCommas + "</i></b>"));
                    }
                    if ((Toddeaths[0] - yDeaths[0]) >= 0) {
                        textView11.setText(Html.fromHtml("<b><i>" + "+" + dailyDeathWithCommas + "</i></b>"));
                    } else {
                        textView11.setText(Html.fromHtml("<b><i>" + "" + dailyDeathWithCommas + "</i></b>"));
                    }

                    //blahahahahahahahah
                    //semicolon has been added
                    Log.d("test","test");
                    //setData(vYourworldViewModel,world);

                    //final TextView textView5 = view.findViewById(R.id.text_activeCases);

                    //String totalCases = Integer.toString(yesworld.cases);

                    //textView5.setText(Html.fromHtml("<p><u>Yerterday cases</u></p>"+totalCases));

                }
            });
        });


        postRequest();

        return view;
    }


    private void postRequest() {
        int size = 100; // this is the count of the data items.
        int page = 1; // Which page do we want to get from the server.
        ServiceRequest serviceRequest = new ServiceRequest();
        vYourworldViewModel.postRequest(serviceRequest);

    }

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
}