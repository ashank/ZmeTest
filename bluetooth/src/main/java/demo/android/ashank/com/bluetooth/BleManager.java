//package demo.android.ashank.com.bluetooth;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import com.facebook.react.bridge.Arguments;
//import com.facebook.react.bridge.Callback;
//import com.facebook.react.bridge.ReactApplicationContext;
//import com.facebook.react.bridge.ReactContextBaseJavaModule;
//import com.facebook.react.bridge.ReactMethod;
//import com.facebook.react.bridge.ReadableMap;
//import com.facebook.react.bridge.WritableMap;
//import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
//
///**
// * FileName    : BleManager
// *
// * @author : Zhiyahan
// * Date        : 2019/4/30 17:39
// * Description : 蓝牙的实现，开放方法 提供给react native 调用，兼容5.0以下的机型
// */
//public class BleManager extends ReactContextBaseJavaModule {
//
//    private static final String TAG = "BleManager";
//
//    private Context context;
//    private ReactApplicationContext reactContext;
//    BluetoohHelper mBluetoohHelper;
//
//    public BleManager(ReactApplicationContext reactContext) {
//        super(reactContext);
//        this.reactContext = reactContext;
//        context = reactContext;
//        mBluetoohHelper = BluetoohHelper.getInstance(context);
//    }
//
//    @Override
//    public String getName() {
//        return "OldBleManager";
//    }
//
//
//    @ReactMethod
//    public  void enableBlutooth(Callback callback){
//
//        if(!mBluetoohHelper.isSupportBluetooth()){
//            callback.invoke("ble is not support");
//            return;
//        }
//
//        if (mBluetoohHelper.openBluetooth()){
//            callback.invoke();
//        }else {
//            callback.invoke("BluetoothAdapter is null");
//        }
//    }
//
//
//    @ReactMethod
//    public void start(Callback callback){
//
//    }
//
//
//    @ReactMethod
//    public void start(ReadableMap options, Callback callback) {
//
//        if(!mBluetoohHelper.isSupportBluetooth()){
//            callback.invoke("ble is not support");
//            return;
//        }
//
//        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
//        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//        context.registerReceiver(mReceiver, filter);
//        callback.invoke();
//        Log.d(TAG, "BleManager initialized");
//    }
//
//    @ReactMethod
//    public void  scan (Callback callback){
//
//    }
//
//
//    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d(TAG, "onReceive");
//            final String action = intent.getAction();
//            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
//                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
//                String stringState = "";
//
//                switch (state) {
//                    case BluetoothAdapter.STATE_OFF:
//                        stringState = "off";
//                        //清理掉
//                        break;
//                    case BluetoothAdapter.STATE_TURNING_OFF:
//                        stringState = "turning_off";
//                        break;
//                    case BluetoothAdapter.STATE_ON:
//                        stringState = "on";
//                        break;
//                    case BluetoothAdapter.STATE_TURNING_ON:
//                        stringState = "turning_on";
//                        break;
//                    default:
//                }
//
//                WritableMap map = Arguments.createMap();
//                map.putString("state", stringState);
//                Log.d(TAG, "state: " + stringState);
//                sendEvent("BleManagerDidUpdateState", map);
//
//            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
//                final int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
//                final int prevState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE,
//                        BluetoothDevice.ERROR);
//                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//
//                String bondStateStr = "UNKNOWN";
//                switch (bondState) {
//                    case BluetoothDevice.BOND_BONDED:
//                        bondStateStr = "BOND_BONDED";
//                        break;
//                    case BluetoothDevice.BOND_BONDING:
//                        bondStateStr = "BOND_BONDING";
//                        break;
//                    case BluetoothDevice.BOND_NONE:
//                        bondStateStr = "BOND_NONE";
//                        break;
//                    default:
//                }
//                Log.d(TAG, "bond state: " + bondStateStr);
//            }
//
//        }
//    };
//
//
//    public void sendEvent(String eventName, @Nullable WritableMap params) {
//        getReactApplicationContext().getJSModule(RCTNativeAppEventEmitter.class).emit(eventName, params);
//    }
//
//
//
//
//
//}
