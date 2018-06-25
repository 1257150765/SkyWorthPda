package ruiduoyi.com.skyworthpda.util;

import android.content.Context;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.ScannerUnavailableException;
import com.honeywell.aidc.UnsupportedPropertyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2018-06-25.
 */

public class EDAScanUtil implements BarcodeReader.BarcodeListener {
    private static final String TAG = EDAScanUtil.class.getSimpleName();
    private Handler handler;
    private Context mContext;
    private AidcManager manager;
    private BarcodeReader barcodeReader;
    private EDAScanListener mEDAScanListener;
    public EDAScanUtil(Context mContext) {
        this.mContext = mContext;
        handler = new Handler(mContext.getMainLooper());
        AidcManager.create(mContext, new AidcManager.CreatedCallback() {

            @Override
            public void onCreated(AidcManager aidcManager) {
                manager = aidcManager;
                barcodeReader = manager.createBarcodeReader();
                barcodeReader.addBarcodeListener(EDAScanUtil.this);
                // set the trigger mode to client control
                try {
                    barcodeReader.setProperty(BarcodeReader.PROPERTY_TRIGGER_CONTROL_MODE,
                            BarcodeReader.TRIGGER_CONTROL_MODE_AUTO_CONTROL);
                } catch (UnsupportedPropertyException e) {
                    e.printStackTrace();
                    //Toast.makeText(this, "Failed to apply properties", Toast.LENGTH_SHORT).show();
                }
                // register trigger state change listener
                //barcodeReader.addTriggerListener(this);

                Map<String, Object> properties = new HashMap<String, Object>();
                // Set Symbologies On/Off
                properties.put(BarcodeReader.PROPERTY_CODE_128_ENABLED, true);
                properties.put(BarcodeReader.PROPERTY_GS1_128_ENABLED, true);
                properties.put(BarcodeReader.PROPERTY_QR_CODE_ENABLED, true);
                properties.put(BarcodeReader.PROPERTY_CODE_39_ENABLED, true);
                properties.put(BarcodeReader.PROPERTY_DATAMATRIX_ENABLED, true);
                properties.put(BarcodeReader.PROPERTY_UPC_A_ENABLE, true);
                properties.put(BarcodeReader.PROPERTY_EAN_13_ENABLED, false);
                properties.put(BarcodeReader.PROPERTY_AZTEC_ENABLED, false);
                properties.put(BarcodeReader.PROPERTY_CODABAR_ENABLED, false);
                properties.put(BarcodeReader.PROPERTY_INTERLEAVED_25_ENABLED, false);
                properties.put(BarcodeReader.PROPERTY_PDF_417_ENABLED, false);
                // Set Max Code 39 barcode length
                properties.put(BarcodeReader.PROPERTY_CODE_39_MAXIMUM_LENGTH, 10);
                // Turn on center decoding
                properties.put(BarcodeReader.PROPERTY_CENTER_DECODE, true);
                // Enable bad read response
                properties.put(BarcodeReader.PROPERTY_NOTIFICATION_BAD_READ_ENABLED, true);
                // Apply the settings
                barcodeReader.setProperties(properties);
                LogWraper.d(TAG,"初始化EDA60K扫描");
                //初始化后就开始等待扫描
                start(null);
            }
        });

    }
    //每个需要接受二维码的Activity都需要使用这个方法来启动扫描，并且传入一个接口，接受扫描到的二维码
    public void start(EDAScanListener mEDAScanListener) {
        if (mEDAScanListener != null) {
            this.mEDAScanListener = mEDAScanListener;
        }
        //有可能程序跑到这里，扫描还没准备好，没有扫描结果
        if (barcodeReader != null) {
            try {
                LogWraper.d(TAG,"EDA60K扫描");
                barcodeReader.claim();
            } catch (ScannerUnavailableException e) {
                e.printStackTrace();
            }
        }
    }
    //不接收二维码时，一定要调用此方法，否则会造成内存泄露
    public void stop(){
        mEDAScanListener = null;
        if (barcodeReader != null) {
            barcodeReader.release();
        }

    }
    //调用此方法将关闭扫描
    public void onDestroy() {
        if (barcodeReader != null) {
            // unregister barcode event listener
            barcodeReader.removeBarcodeListener(this);
        }
        if (barcodeReader != null) {
            // close BarcodeReader to clean up resources.
            barcodeReader.close();
            barcodeReader = null;
        }
        if (manager != null) {
            // close AidcManager to disconnect from the scanner service.
            // once closed, the object can no longer be used.
            manager.close();
        }
    }
    @Override
    public void onBarcodeEvent(final BarcodeReadEvent event) {
        if (mEDAScanListener != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // update UI to reflect the data
                    /*List<String> list = new ArrayList<String>();
                    list.add("Barcode data: " + event.getBarcodeData());
                    list.add("Character Set: " + event.getCharset());
                    list.add("Code ID: " + event.getCodeId());
                    list.add("AIM ID: " + event.getAimId());
                    list.add("Timestamp: " + event.getTimestamp());*/
                    LogWraper.d(TAG,"EDA60K扫描到了");
                    mEDAScanListener.onScanSucceed(event.getBarcodeData());
                }
            });
        }

    }

    @Override
    public void onFailureEvent(BarcodeFailureEvent barcodeFailureEvent) {

    }
    public interface EDAScanListener{
        void onScanSucceed(String code);
    }
}
