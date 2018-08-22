/**
 * src/android: OpenSchemeUrl.java
**/

package no.amphibian.openschemeurl;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.PackageManager;

public class OpenSchemeUrl extends CordovaPlugin {
    @Override public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("open")) {
            String url = args.getString(0);
            this.open(url, callbackContext);
            return true;
        } else if (action.equals("isInstalled")) {
            String packageId = args.getString(0);
            this.isInstalled(packageId, callbackContext);
            return true;
        }

        return false;
    }

    private void isInstalled(String packageId, CallbackContext callbackContext) {
	    PackageManager pm = this.cordova.getActivity().getPackageManager();
	    try {
	        pm.getPackageInfo(packageId, PackageManager.GET_ACTIVITIES);
            callbackContext.success();
	    } catch (PackageManager.NameNotFoundException error) {
            callbackContext.error(error.getMessage());
	    }
	}


    private void open(String url, CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            callbackContext.success();
        } catch (android.content.ActivityNotFoundException error) {
            callbackContext.error(error.getMessage());
        }
    }
}
