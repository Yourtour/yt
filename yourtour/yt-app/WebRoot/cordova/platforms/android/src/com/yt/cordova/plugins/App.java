package com.yt.cordova.plugins;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.content.pm.PackageManager;

public class App extends CordovaPlugin {
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {
        Activity activity = this.cordova.getActivity();
        if (action.equals("getVersion")) {
            version(callbackContext);

            return true;
        }

        if (action.equals("install")) {
            install(args.getString(0), callbackContext);

            return true;
        }

        return false;
    }

    private void version(CallbackContext callbackContext) {
        try {
            PackageInfo packInfo = this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getActivity().getPackageName(),0);
            String version = packInfo.versionName;
            callbackContext.success(version);
        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error("unknown");
        }
    }

    private void install(String apkName, CallbackContext callbackContext) {
        try {
            //String fileName = Environment.getExternalStorageDirectory() + apkName;
            String fileName = apkName;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
            this.cordova.getActivity().startActivity(intent);
            callbackContext.success(fileName);
        }catch(Exception e){
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            e.printStackTrace(writer);
            StringBuffer buffer = stringWriter.getBuffer();

            callbackContext.error(buffer.toString());
        }
    }
}