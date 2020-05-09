package com.josh.trackcovid19v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendFeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendFeedbackFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SendFeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendFeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendFeedbackFragment newInstance(String param1, String param2) {
        SendFeedbackFragment fragment = new SendFeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_feedback, container, false);
        final TextView to = (TextView) view.findViewById(R.id.sendTo);
        final EditText message = (EditText) view.findViewById(R.id.EmailText);
        final EditText subject = (EditText) view.findViewById(R.id.subject);

        Button sendE = (Button) view.findViewById(R.id.sendEmail);
        sendE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Data");
                builder.setMessage("We will need to take some data from your device to fix the report. Is this okay with you?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        String system = System.getProperty("os.version");
                        Integer API = Build.VERSION.SDK_INT;
                        String device = Build.DEVICE;
                        String model = Build.MODEL;
                        String product = Build.PRODUCT;
                        String display = Build.DISPLAY;
                        String type = Build.TYPE;
                        String user = Build.USER;
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        ((Activity) getContext()).getWindowManager()
                                .getDefaultDisplay()
                                .getMetrics(displayMetrics);
                        int height = displayMetrics.heightPixels + getNavigationBarHeight();
                        int width = displayMetrics.widthPixels;
                        String toS = "joshpatra12@protonmail.com";
                        String subS = subject.getText().toString();
                        String mesS = message.getText().toString() + "\n\n\n\n\n\n" + "\nAPI: " + API + "\nDevice: "
                                + device + "\nModel: "+ model +"\nType:" + type +"\nUser:" + user +
                                "\nDisplay height: " + height + "\nDisplay width: " + width;



                        /*
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, toS);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subS);
                        emailIntent.putExtra(Intent.EXTRA_TEXT, mesS + "\n\n\n\n\n\n" + "\nAPI: " + API + "\nDevice: "
                                + device + "\nModel: " + model + "\nType:" + type + "\nUser:" + user +
                                "\nDisplay height: " + height + "\nDisplay width: " + width);
                        startActivity(Intent.createChooser(emailIntent, "Choose an app to sdend the email with ⬇️"));

                         */
                        /*
                        Intent email = new Intent(Intent.ACTION_SEND);

                        email.putExtra(Intent.EXTRA_EMAIL , new String [] {toS});

                        email.putExtra(Intent.EXTRA_SUBJECT , subS);
                        email.putExtra(Intent.EXTRA_TEXT , mesS);
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Choose an app to send the email with ⬇"));
                        *

                         */
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, new String [] {toS});
                        intent.putExtra(Intent.EXTRA_SUBJECT, subS);
                        intent.putExtra(Intent.EXTRA_TEXT, mesS);
                        startActivity(Intent.createChooser(intent, "Choose an app to send the email with ⬇"));
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return view;
    }

    private int getNavigationBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            ((Activity) getContext()).getWindowManager()
                    .getDefaultDisplay()
                    .getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }
}
