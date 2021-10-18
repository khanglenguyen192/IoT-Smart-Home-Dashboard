package com.example.smarthomedashboard.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.CaseMap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.smarthomedashboard.R;

public class CameraFragment extends Fragment {

    // Declare
    private String cam1Url = "https://www.google.com.vn/?hl=vi";

    private WebView cam1;
    private ProgressBar progressBar;

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

        cam1 = (WebView)view.findViewById(R.id.cam1);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        cam1.setWebViewClient(new WebViewClient());
        cam1.loadUrl(cam1Url);
        //enable JavaScript
        WebSettings webSettings = cam1.getSettings();
        webSettings.setJavaScriptEnabled(true);

        cam1.setWebViewClient(new WebViewClient(){
            //Method control page start + page finish functionality..
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //set progressBar when page loading is start...
                progressBar.setVisibility(View.VISIBLE);
                getActivity().setTitle("Loading");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                getActivity().setTitle(view.getTitle());
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getActivity(), "Your Internet Connection may not be active Or " + error.getDescription(), Toast.LENGTH_LONG).show();
                super.onReceivedError(view, request, error);
            }
        });

        //cameraSetup();

        refreshButton = view.findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cam1.loadUrl(cam1Url);
                connectionStatus();
            }
        });

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
        cam1.loadUrl(cam1Url);
    }

    protected void connectionStatus() {
        boolean check = checkConnection();

        if(check==true) {
            Toast.makeText(getActivity(), "Internet is Connected", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getActivity(), "Failed to connect to internet.", Toast.LENGTH_LONG).show();
        }
    }

    protected boolean checkConnection(){
        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = conMan.getActiveNetworkInfo();

        final boolean connected = networkInfo != null
                && networkInfo.isAvailable()
                && networkInfo.isConnected();

        if (!connected) {
            return false;
        }
        return true;
    }

}