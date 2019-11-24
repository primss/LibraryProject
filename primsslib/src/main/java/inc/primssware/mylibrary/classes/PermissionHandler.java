package inc.primssware.mylibrary.classes;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdallah on 02/04/2016
 */
public class PermissionHandler {

    public final static int COARSE_LOCATION_RESULT = 100;
    public final static int FINE_LOCATION_RESULT = 101;
    public final static int CALL_PHONE_RESULT = 102;
    public final static int CAMERA_RESULT = 103;
    public final static int READ_EXTERNAL_RESULT = 104;
    public final static int WRITE_EXTERNAL_RESULT = 105;
    public final static int RECEIVE_SMS_RESULT = 106;
    public final static int READ_SMS_RESULT = 107;
    public final static int ALL_PERMISSIONS_RESULT = 108;
    public final static int ADD_PHOTO_RESULT = 109; // READ_EXTERNAL for gallery and CAMERA for Camera
    public final static int ALL_EXTERNAL_RESULT = 110;
    public final static int ALL_SMS_RESULT = 111; // RECEIVE_SMS and RECEIVE_SMS

    private Context mContext;

    public PermissionHandler(Context context){
        mContext = context;
    }

    public boolean shouldRequestPermission(){
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public boolean hasPermission(String permission) {
        if(shouldRequestPermission()){
            return(mContext.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    /**
     * method to determine whether we have asked
     * for this permission before.. if we have, we do not want to ask again.
     * They either rejected us or later removed the permission.
     * @param permission
     * @return
     */
    public boolean shouldWeAsk(String permission) {
        return(SecurePrefs.getBoolean(permission, true));
    }

    /**
     * we will save that we have already asked the user
     * @param permission
     */
    public void markAsAsked(String permission) {
        SecurePrefs.putBoolean(permission, false);
    }

    /**
     * We may want to ask the user again at their request.. Let's clear the
     * marked as seen preference for that permission.
     * @param permission
     */
    public void clearMarkAsAsked(String permission) {
        SecurePrefs.putBoolean(permission, true);
    }

    /**
     * This method is used to determine the permissions we do not have accepted yet and ones that we have not already
     * bugged the user about.  This comes in handle when you are asking for multiple permissions at once.
     * @param wanted
     * @return
     */
    public List<String> findUnAskedPermissions(List<String> wanted) {
        List<String> result = new ArrayList<>();

        for (String perm : wanted) {
            if (!hasPermission(perm) && shouldWeAsk(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    /**
     * this will return us all the permissions we have previously asked for but
     * currently do not have permission to use. This may be because they declined us
     * or later revoked our permission. This becomes useful when you want to tell the user
     * what permissions they declined and why they cannot use a feature.
     * @param wanted
     * @return
     */
    public List<String> findRejectedPermissions(List<String> wanted) {
        List<String> result = new ArrayList<>();

        for (String perm : wanted) {
            if (!hasPermission(perm) && !shouldWeAsk(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

}
