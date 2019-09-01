package lrm.com.bluetoothdemo.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import lrm.com.bluetoothdemo.R;

/**
 * 创建日期：2019/9/1
 * 作者:baiyang
 * 参考https://www.jianshu.com/p/3a372af38103
 */
public class TestActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    private Button btn_start;
    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 218;
    private Button btn_scan;
    private BluetoothAdapter.LeScanCallback mCallback;
    private BluetoothDevice mDevice;//要链接的设备

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "初探测试";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_scan = (Button) findViewById(R.id.btn_scan);
    }

    @Override
    protected void initVariables() {
        // Initializes Bluetooth adapter.只有API18以上会得到
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override
    protected void initListenter() {
        btn_start.setOnClickListener(this);
        btn_scan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                statrBluetooth();
                break;
            case R.id.btn_scan:
                startLeScan();
                break;
        }
    }

    /**
     * 开启蓝牙
     */
    private void statrBluetooth() {
        // Ensures Bluetooth is available on the device and it is enabled. If not,
        // displays a dialog requesting user permission to enable Bluetooth.
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    /**
     * 开始扫描
     */
    private void startLeScan() {
        mCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                //bluetoothDeviceArrayList.add(device);
                Log.e(TAG, "onLeScan: run: scanning...");
                //这块要过滤出相关蓝牙信息
                //之后链接蓝牙
            }
        };

        mBluetoothAdapter.startLeScan(mCallback);
    }

    /**
     * 停止蓝牙扫描
     */
    private void stopLeScan() {
        mBluetoothAdapter.stopLeScan(mCallback);
    }

    /**
     * 开始链接蓝牙设备
     */
    private void startConnectionDevice() {
        if (mDevice != null) {
            //第二个参数表示是否需要自动连接。如果设置为 true, 表示如果设备断开了，会不断的尝试自动连接。设置为 false 表示只进行一次连接尝试
            mDevice.connectGatt(mContext, true, new BluetoothGattCallback() {
                @Override
                public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                    super.onPhyUpdate(gatt, txPhy, rxPhy, status);
                }

                @Override
                public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                    super.onPhyRead(gatt, txPhy, rxPhy, status);
                }

                /**
                 * 如果连接成功会回调 BluetoothGattCalbackl#onConnectionStateChange 方法。这个方法运行的线程是一个 Binder 线程，
                 * 所以不建议直接在这个线程处理耗时的任务，因为这可能导致蓝牙相关的线程被阻塞
                 */
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                    super.onConnectionStateChange(gatt, status, newState);
                }

                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    super.onServicesDiscovered(gatt, status);
                }

                @Override
                public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    super.onCharacteristicRead(gatt, characteristic, status);
                }

                @Override
                public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    super.onCharacteristicWrite(gatt, characteristic, status);
                }

                @Override
                public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                    super.onCharacteristicChanged(gatt, characteristic);
                }

                @Override
                public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                    super.onDescriptorRead(gatt, descriptor, status);
                }

                @Override
                public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                    super.onDescriptorWrite(gatt, descriptor, status);
                }

                @Override
                public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
                    super.onReliableWriteCompleted(gatt, status);
                }

                @Override
                public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                    super.onReadRemoteRssi(gatt, rssi, status);
                }

                @Override
                public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                    super.onMtuChanged(gatt, mtu, status);
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_ENABLE_BT) {
            //蓝牙开启是否回调
            Log.e(TAG, "onActivityResult: -------");
        }
    }
}
