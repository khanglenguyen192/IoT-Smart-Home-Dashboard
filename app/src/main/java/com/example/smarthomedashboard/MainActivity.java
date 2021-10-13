package com.example.smarthomedashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.smarthomedashboard.adapter.MainViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.smarthomedashboard.mqtt.MQTTHelper;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;



public class MainActivity extends AppCompatActivity {

    // Declare
    private BottomNavigationView main_bottom_navigation;
    private ViewPager main_view_pager;
    ProgressBar temperature;
    ProgressBar humidity;
    ProgressBar amountOfGas;
    MQTTHelper mqttHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get view
        main_bottom_navigation = findViewById(R.id.main_bottom_navigation);
        main_view_pager = findViewById(R.id.main_view_pager);


        // Set up
        setUpBottomNavigation();
        setUpViewPager();

        sendMQTT();
    }

    private void setUpBottomNavigation() {
        main_bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        main_view_pager.setCurrentItem(0);
                        break;
                    case R.id.action_chart:
                        main_view_pager.setCurrentItem(1);
                        break;
                    case R.id.action_setting:
                        main_view_pager.setCurrentItem(2);
                        break;
                    case R.id.action_camera:
                        main_view_pager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        main_view_pager.setAdapter(mainViewPagerAdapter);
        main_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        main_bottom_navigation.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        main_bottom_navigation.getMenu().findItem(R.id.action_chart).setChecked(true);
                        break;
                    case 2:
                        main_bottom_navigation.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void sendMQTT() {
        mqttHelper = new MQTTHelper(getApplicationContext(), "123");
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                Log.d("Mqtt", "Connect successfully");
            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d("msg", "messageArrived: " + message.toString());
                JSONObject jsonObject = new JSONObject(message.toString());
                if (topic.contains("homeinfo")) {
                    int temp = jsonObject.getInt("temp");
                    int humid = jsonObject.getInt("humidity");
                    int gas = jsonObject.getInt("gas");

                    temperature = findViewById(R.id.tempProgressBar);
                    humidity = findViewById(R.id.humidProgressBar);
                    amountOfGas = findViewById(R.id.gasProgressBar);

                    temperature.setProgress(temp);
                    humidity.setProgress(humid);
                    amountOfGas.setProgress(gas);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    public void sendDataMQTT(String data, String topic) {
        MqttMessage message = new MqttMessage();
        message.setId(123);
        message.setQos(0);
        message.setRetained(true);
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        message.setPayload(bytes);
        Log.d("ABC","Publish:"+ message);
        try {
            mqttHelper.mqttAndroidClient.publish(topic,message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}