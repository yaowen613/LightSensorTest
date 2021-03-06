package com.yaowen.lightsensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;//所有传感器的管理器
    private TextView lightLevel;//用来可以显示光照强度的控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView) findViewById(R.id.light_level);//初始化控件
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);//获取传感器的实例
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);//获取传感器的类型，这里是获取光传感器
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);//注册传感器的响应事件
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);//解除传感器的注册
        }
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // values数组中第1个下标的值就是当前的光照强度
            float value = event.values[0];
            lightLevel.setText("当前光线强度为：" + value + "lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
