package com.example.smarthomedashboard.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.smarthomedashboard.R;

public class CameraFragment extends Fragment {

    // Declare
    private WebView cam1;
    private ImageButton captureButton;
    private ImageButton infoButton;
    private ImageButton refreshButton;
    private ImageButton shareButton;
    private ImageButton settingButton;

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        cam1 = view.findViewById(R.id.cam1);
        refreshButton = view.findViewById(R.id.refreshButton);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cam1.loadUrl("http://smarthomecamera.ddns.net:8081/");
            }
        });

        cameraSetup();

        /*String userAgent = System.getProperty( "http.agent" );
        Log.e("User agent",userAgent);

        cam1.setWebViewClient(new WebViewClient());
        cam1.getSettings().setUserAgentString(userAgent);
        cam1.getSettings().setJavaScriptEnabled(true);
        cam1.getSettings().setAppCacheEnabled(true);
        cam1.getSettings().setDomStorageEnabled(true);
        cam1.loadUrl("http://smarthomecamera.ddns.net:8081/");
         */
        return view;
    }

    private void cameraSetup() {
        String userAgent = System.getProperty( "http.agent" );
        Log.e("User agent",userAgent);

        cam1.setWebViewClient(new WebViewClient());
        cam1.getSettings().setUserAgentString(userAgent);
        cam1.getSettings().setJavaScriptEnabled(true);
        cam1.getSettings().setAppCacheEnabled(true);
        cam1.getSettings().setDomStorageEnabled(true);
        cam1.loadUrl("http://smarthomecamera.ddns.net:8081/");
    }
}