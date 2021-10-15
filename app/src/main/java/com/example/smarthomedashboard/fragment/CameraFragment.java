package com.example.smarthomedashboard.fragment;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.smarthomedashboard.R;

public class CameraFragment extends Fragment {

    // Declare
    private WebView cam1;
    private ProgressDialog progressDialog;

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
                connectionStatus();
            }
        });

        cameraSetup();



        return view;
    }

    private void cameraSetup() {
        String userAgent = System.getProperty( "http.agent" );
        Log.e("User agent",userAgent);

        //cam1.setWebViewClient(new WebViewClient());
        cam1.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            public void onLoadResource(WebView view, String url) {
                // Check to see if there is a progress dialog
                if (progressDialog == null) {
                    // If no progress dialog, make one and set message
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Loading please wait...");
                    progressDialog.show();

                    // Hide the webview while loading
                    cam1.setEnabled(false);
                }
            }
            public void onPageFinished(WebView view, String url) {
                // Page is done loading;
                // hide the progress dialog and show the webview
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                    cam1.setEnabled(true);
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getActivity(), "Your Internet Connection May not be active Or " + error.getDescription(), Toast.LENGTH_LONG).show();
            }
        });

        cam1.getSettings().setUserAgentString(userAgent);
        cam1.getSettings().setJavaScriptEnabled(true);
        cam1.getSettings().setAppCacheEnabled(true);
        cam1.getSettings().setDomStorageEnabled(true);
        cam1.loadUrl("http://smarthomecamera.ddns.net:8081/");
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

        if ( !connected) {
            Toast.makeText(getActivity(), "Failed to connect to Internet.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}