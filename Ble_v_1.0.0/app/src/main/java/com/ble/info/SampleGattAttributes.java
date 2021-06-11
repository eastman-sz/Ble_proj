/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ble.info;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class SampleGattAttributes {
    private static HashMap<String, String> attributes = new HashMap<String, String>();
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
	/**
	 * 电量服务的UUID
	 */
	public final static UUID BATTERY_SERVICE_UUID = UUID.fromString("0000180f-0000-1000-8000-00805f9b34fb");

    static {
        // Sample Services.
    	attributes.put("00001800-0000-1000-8000-00805f9b34fb", "Generic Access");
    	attributes.put("00001801-0000-1000-8000-00805f9b34fb", "Generic Attribute");
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate Service");
        attributes.put("0000180f-0000-1000-8000-00805f9b34fb", "Battery Service");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information Service");
        
        // Sample Characteristics.
        attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
        attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name");
        attributes.put("00002a24-0000-1000-8000-00805f9b34fb", "Model Number");
        attributes.put("00002a25-0000-1000-8000-00805f9b34fb", "Serial Number");
        attributes.put("00002a27-0000-1000-8000-00805f9b34fb", "Hardware Revision");
        attributes.put("00002a26-0000-1000-8000-00805f9b34fb", "Firmware Revision");
        attributes.put("00002a28-0000-1000-8000-00805f9b34fb", "Software Revision");
        attributes.put("00002a23-0000-1000-8000-00805f9b34fb", "System ID");
        
        attributes.put("00002a19-0000-1000-8000-00805f9b34fb", "Battery Level");
        
        attributes.put("00002a38-0000-1000-8000-00805f9b34fb", "Body Sensor Location");

        //Generic Access Characteristics
        attributes.put("00002a00-0000-1000-8000-00805f9b34fb", "Device Name");
        attributes.put("00002a01-0000-1000-8000-00805f9b34fb", "Appearance");
        attributes.put("00002a04-0000-1000-8000-00805f9b34fb", "Peripheral Preferred Connection Parameters");
    }

    private static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
    
    public static String lookup(String uuid) {
        return lookup(uuid, "Custom Service");
    }
}
