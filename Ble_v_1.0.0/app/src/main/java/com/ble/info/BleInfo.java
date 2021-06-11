package com.ble.info;

import java.util.List;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

public class BleInfo {

	private BluetoothGattService service = null;
	private List<BluetoothGattCharacteristic> characteristicList = null;
	
	public BluetoothGattService getService() {
		return service;
	}
	public void setService(BluetoothGattService service) {
		this.service = service;
	}
	public List<BluetoothGattCharacteristic> getCharacteristicList() {
		return characteristicList;
	}
	public void setCharacteristicList(
			List<BluetoothGattCharacteristic> characteristicList) {
		this.characteristicList = characteristicList;
	}
}
