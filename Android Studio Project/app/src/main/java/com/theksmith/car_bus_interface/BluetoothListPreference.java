package com.theksmith.car_bus_interface;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Set;


/**
 * a ListPreference for choosing a paired bluetooth device
 * example use within a pref.xml: <com.theksmith.car_bus_interface.BluetoothListPreference android:key="bluetooth_mac" android:title="Bluetooth Device" android:dialogTitle="Bluetooth Device" />
 *
 * @author Kristoffer Smith <kristoffer@theksmith.com>
 */
public class BluetoothListPreference extends ListPreference {
    private static final String TAG = "BluetoothListPreference";
    private static final boolean D = BuildConfig.SHOW_DEBUG_LOG;


    public BluetoothListPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        if (D) Log.d(TAG, "BluetoothListPreference()");

        CharSequence[] entries = new CharSequence[1];
        CharSequence[] values = new CharSequence[1];

        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> devices = adapter.getBondedDevices();

        if (devices.size() > 0) {
            entries = new CharSequence[devices.size()];
            values = new CharSequence[devices.size()];
            int i = 0;
            for (BluetoothDevice device : devices) {
                entries[i] = device.getName();
                values[i] = device.getAddress();
                i++;
            }
        } else {
            entries[0] = context.getResources().getString(R.string.msg_app_btlist_empty);
            values[0] = "";
        }

        setEntries(entries);
        setEntryValues(values);
    }

    public BluetoothListPreference(final Context context) {
        this(context, null);
    }
}