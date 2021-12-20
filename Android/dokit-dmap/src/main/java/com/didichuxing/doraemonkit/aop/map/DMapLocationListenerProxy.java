package com.didichuxing.doraemonkit.aop.map;

import android.location.Location;

import androidx.annotation.NonNull;

import com.didichuxing.bigdata.dp.locsdk.DIDILocation;
import com.didichuxing.bigdata.dp.locsdk.DIDILocationListener;
import com.didichuxing.bigdata.dp.locsdk.ErrInfo;
import com.didichuxing.bigdata.dp.locsdk.trace.data.ETraceSource;
import com.didichuxing.doraemonkit.kit.gpsmock.GpsMockProxyManager;

/**
 * Created by mmxb on 2021/9/13.
 */
public class DMapLocationListenerProxy implements DIDILocationListener, DMapLocationListener {
    public DIDILocationListener didiLocationListener;

    public DMapLocationListenerProxy(DIDILocationListener didiLocationListener) {
        this.didiLocationListener = didiLocationListener;
        GpsMockProxyManager.INSTANCE.addDMapLocationListenerProxy(this);
    }

    @Override
    public void onLocationChanged(DIDILocation didiLocation) {
        if (didiLocationListener != null) {
            didiLocationListener.onLocationChanged(didiLocation);
        }
    }

    @Override
    public void onLocationError(int i, ErrInfo errInfo) {
        if (didiLocationListener != null) {
            didiLocationListener.onLocationError(i, errInfo);
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        if (didiLocationListener != null) {
            didiLocationListener.onStatusUpdate(s, i, s1);
        }
    }

    @NonNull
    @Override
    public Object getDMapLocation() {
        return didiLocationListener;
    }


    @Override
    public void onLocationChange(Location location) {
        DIDILocation didiLocation = DIDILocation.loadFromSystemLoc(location, ETraceSource.cache, 0);
        if (didiLocationListener != null) {
            didiLocationListener.onLocationChanged(didiLocation);
        }
    }
}
