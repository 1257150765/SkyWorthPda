package ruiduoyi.com.skyworthpda.util;


import android.content.Context;

import com.seuic.scanner.DecodeInfo;
import com.seuic.scanner.DecodeInfoCallBack;
import com.seuic.scanner.Scanner;
import com.seuic.scanner.ScannerFactory;
import com.seuic.scanner.ScannerKey;

/**
 * Created by Chen on 2018/5/4.
 */
@Deprecated
public class ScanUtil implements DecodeInfoCallBack {
    private  Scanner scanner;
    OnScanListener onScanListener;

    public void setOnScanListener(OnScanListener onScanListener) {
        this.onScanListener = onScanListener;
    }

    private  Context context;

    public ScanUtil(Context context) {
        this.context = context;
        scanner = ScannerFactory.getScanner(context);
        scanner.open();
        scanner.setDecodeInfoCallBack(this);
        new Thread(runnable).start();
    }

    private boolean flag = true;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int ret1 = ScannerKey.open();
            if (ret1 > -1) {
                while (flag) {
                    int ret = ScannerKey.getKeyEvent();
                    if (ret > -1) {
                        switch (ret) {
                            case ScannerKey.KEY_DOWN:
                                scanner.startScan();
                                break;
                            case ScannerKey.KEY_UP:
                                scanner.stopScan();
                                break;
                        }
                    }
                }
            }
        }
    };
    public void close() {
        scanner.stopScan();
        scanner.setDecodeInfoCallBack(null);
        scanner.close();
        setOnScanListener(null);
        context = null;
    }

    @Override
    public void onDecodeComplete(DecodeInfo decodeInfo) {
        if (null != onScanListener){
            onScanListener.onScanSucceed(decodeInfo.barcode);
        }
        scanner.stopScan();
    }

    public interface OnScanListener{
        void onScanSucceed(String code);
    }
}
