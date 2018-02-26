package com.udacity.gradle.builditbigger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by brockrice on 2/26/18.
 */

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        //set up spinner and set to GONE
        final ProgressBar spinner = root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);


        final Button jokeButton = root.findViewById(R.id.btn_joke);
        jokeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getJokes();

                spinner.setVisibility(View.GONE);

            }
        });

        return root;
    }

    public void getJokes() {
        //initiate the EndpointsAsyncTask here and get the Activity tied to fragment
        new EndpointsAsyncTask().execute(getActivity());
    }
}
