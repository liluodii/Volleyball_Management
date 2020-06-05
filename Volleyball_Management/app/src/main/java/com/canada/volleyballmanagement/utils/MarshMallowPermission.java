package com.canada.volleyballmanagement.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale;

public class MarshMallowPermission {

    public static final int READ_CALENDAR_CODE = 1;
    public static final int WRITE_CALENDAR_CODE = 2;
    public static final int CAMERA_CODE = 3;
    public static final int READ_CONTACTS_CODE = 4;
    public static final int WRITE_CONTACTS_CODE = 5;
    public static final int GET_ACCOUNT_CODE = 6;
    public static final int ACCESS_FINE_LOCATION_CODE = 7;
    public static final int ACCESS_COARSE_LOCATION_CODE = 8;
    public static final int RECORD_AUDIO_CODE = 9;
    public static final int READ_PHONE_STATE_CODE = 10;
    public static final int CALL_PHONE__CODE = 11;
    public static final int READ_CALL_LOG_CODE = 12;
    public static final int WRITE_CALL_LOG_CODE = 13;
    public static final int ADD_VOICE_MAIL_CODE = 14;
    public static final int USE_SIP_CODE = 15;
    public static final int PROCESS_OUTGOING_CALL_CODE = 16;
    public static final int BODY_SENSOR_CODE = 17;
    public static final int SEND_SMS_CODE = 18;
    public static final int RECEIVE_SMS_CODE = 19;
    public static final int READ_SMS_CODE = 20;
    public static final int RECEIVE_WAP_PUSH_CODE = 21;
    public static final int RECEIVE_MMS_CODE = 22;
    public static final int READ_EXTERNAL_STORAGE_CODE = 23;
    public static final int WRITE_EXTERNAL_STORAGE_CODE = 24;

    Activity activity;

    public MarshMallowPermission(Activity activity) {
        this.activity = activity;
    }

    public boolean checkPermissionForReadCalender() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CALENDAR);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWriteCalender() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForCamera() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReadContacts() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWriteContacts() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForGetAccount() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForAccessFineLocation()
    {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForAccessCoarseLocation() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForRecordAudio() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReadPhoneState() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForCallPhone() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReadCallLog() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWriteCallLog() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALL_LOG);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForAddVoiceMail() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ADD_VOICEMAIL);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForUseSip() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.USE_SIP);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForProcessOutGoingCall() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.PROCESS_OUTGOING_CALLS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForBodySensor() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.BODY_SENSORS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForSendSms() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReceiveSms() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReadSms() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReceiveWapPush() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_WAP_PUSH);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReceiveMms() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_MMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReadexternal() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWriteexternal() {
        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    public void requestPermissionForReadCalender() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALENDAR)) {
            requestPermissions(activity, new String[]{Manifest.permission.READ_CALENDAR}, READ_CALENDAR_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.READ_CALENDAR}, READ_CALENDAR_CODE);
        }
    }

    public void requestPermissionForWriteCalender() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALENDAR)) {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, WRITE_CALENDAR_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, WRITE_CALENDAR_CODE);
        }
    }

    public void requestPermissionForCamera() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
        }
    }

    public void requestPermissionForReadContacts() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
            requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_CODE);
        }
    }

    public void requestPermissionForWriteContacts() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CONTACTS)) {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACTS_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACTS_CODE);
        }
    }

    public void requestPermissionForGetAccount() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.GET_ACCOUNTS)) {
            requestPermissions(activity, new String[]{Manifest.permission.GET_ACCOUNTS}, GET_ACCOUNT_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.GET_ACCOUNTS}, GET_ACCOUNT_CODE);
        }
    }

    public void requestPermissionForAccessFineLocation() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE);
        }
    }

    public void requestPermissionForAccessCoarseLocatio() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_LOCATION_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_LOCATION_CODE);
        }
    }

    public void requestPermissionForRecordAudio() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_CODE);
        }
    }

    public void requestPermissionForReadPhoneState() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
            requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_CODE);
        }
    }

    public void requestPermissionForCallPhone() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
            requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE__CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE__CODE);
        }
    }

    public void requestPermissionForReadCallLog() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALL_LOG)) {
            requestPermissions(activity, new String[]{Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG_CODE);
        }
    }

    public void requestPermissionForWriteCallLog() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALL_LOG)) {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALL_LOG}, WRITE_CALL_LOG_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALL_LOG}, WRITE_CALL_LOG_CODE);
        }
    }

    public void requestPermissionForAddVoiceMail() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.ADD_VOICEMAIL)) {
            requestPermissions(activity, new String[]{Manifest.permission.ADD_VOICEMAIL}, ADD_VOICE_MAIL_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.ADD_VOICEMAIL}, ADD_VOICE_MAIL_CODE);
        }
    }

    public void requestPermissionForUseSip() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.USE_SIP)) {
            requestPermissions(activity, new String[]{Manifest.permission.USE_SIP}, USE_SIP_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.USE_SIP}, USE_SIP_CODE);
        }
    }

    public void requestPermissionForProcessOutGoingCall() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.PROCESS_OUTGOING_CALLS)) {
            requestPermissions(activity, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS}, PROCESS_OUTGOING_CALL_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS}, PROCESS_OUTGOING_CALL_CODE);
        }
    }

    public void requestPermissionForBodySensor() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.BODY_SENSORS)) {
            requestPermissions(activity, new String[]{Manifest.permission.BODY_SENSORS}, BODY_SENSOR_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.BODY_SENSORS}, BODY_SENSOR_CODE);
        }
    }

    public void requestPermissionForSendSms() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {
            requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_CODE);
        }
    }

    public void requestPermissionForReceiveSms() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)) {
            requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_SMS}, RECEIVE_SMS_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_SMS}, RECEIVE_SMS_CODE);
        }
    }

    public void requestPermissionForReadSms() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)) {
            requestPermissions(activity, new String[]{Manifest.permission.READ_SMS}, READ_SMS_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.READ_SMS}, READ_SMS_CODE);
        }
    }

    public void requestPermissionForReceiveWapPush() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_WAP_PUSH)) {
            requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_WAP_PUSH}, RECEIVE_WAP_PUSH_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_WAP_PUSH}, RECEIVE_WAP_PUSH_CODE);
        }
    }

    public void requestPermissionForReceiveMms() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_MMS)) {
            requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_MMS}, RECEIVE_MMS_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_MMS}, RECEIVE_MMS_CODE);
        }
    }

    public void requestPermissionForWriteexternal() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_CODE);
        }
    }

    public void requestPermissionForReadexternal() {
        if (shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_CODE);
        } else {
            requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_CODE);
        }
    }

}