package com.example.smarthomedashboard.fragment.camera;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomedashboard.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.w3c.dom.Text;

public class Camera1Fragment extends Fragment {

    // Declare
    private boolean camAvailable = true;

    private final String camUrl = "http://smarthomecamera.ddns.net:8081";
    private String camName = "Cam 1";
    private String camInfo = "Out door";

    private WebView cam;
    private ProgressBar progressBar;

    private ImageButton captureButton;
    private ImageButton infoButton;
    private ImageButton refreshButton;
    private ImageButton shareButton;
    private ImageButton settingButton;

    private Dialog myDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera1, container, false);

        cam = (WebView)view.findViewById(R.id.cam1);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        cam.setWebViewClient(new WebViewClient());
        cam.loadUrl(camUrl);
        //enable JavaScript
        WebSettings webSettings = cam.getSettings();
        webSettings.setJavaScriptEnabled(true);

        cam.setWebViewClient(new WebViewClient(){
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

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getActivity(), "Your Internet Connection may not be active Or " + error.getDescription(), Toast.LENGTH_LONG).show();
                super.onReceivedError(view, request, error);
            }
        });

        infoButton = view.findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        refreshButton = view.findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cam.loadUrl(camUrl);
                connectionStatus();
            }
        });

        myDialog = new Dialog(getContext());
        shareButton = view.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup(view);
            }
        });

        return  view;
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

    public void ShowPopup(View v) {
        ImageView popup_qrCode;
        TextView txtclose;
        TextView txtCamName;
        TextView txtCamInfo;
        TextView txtCamUrl;
        Button btnExternalShare;

        myDialog.setContentView(R.layout.fragment_camera_popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        txtCamName =(TextView) myDialog.findViewById(R.id.popup_camName);
        txtCamName.setText(camName);
        txtCamInfo =(TextView) myDialog.findViewById(R.id.popup_camInfo);
        txtCamInfo.setText(camInfo);
        txtCamUrl =(TextView) myDialog.findViewById(R.id.popup_camUrl);
        txtCamUrl.setText(camUrl);

        popup_qrCode = myDialog.findViewById(R.id.popup_qrCode);
        generateQrCode(popup_qrCode);

        //btnExternalShare = (Button) myDialog.findViewById(R.id.btnExternalShare);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void generateQrCode(ImageView barcode) {
        String data_in_code="Hello Bar Code Data";
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try{
            BitMatrix bitMatrix=multiFormatWriter.encode(data_in_code, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            barcode.setImageBitmap(bitmap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}