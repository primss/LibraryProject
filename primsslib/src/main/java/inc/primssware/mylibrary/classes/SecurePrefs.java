package inc.primssware.mylibrary.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.prashantsolanki.secureprefmanager.SecurePrefManager;
import com.prashantsolanki.secureprefmanager.SecurePrefManagerInit;

import java.util.List;

import static com.prashantsolanki.secureprefmanager.SecurePrefManager.with;

@SuppressWarnings("unused")
public final class SecurePrefs {

    private static final String DEFAULT_SUFFIX = "_preferences";
    private static final String LENGTH = "#LENGTH";
    private static SharedPreferences mPrefs;
    private static Context mContext;

    public static void initPrefs(Context context) {
        mContext = context.getApplicationContext();
        new SecurePrefManagerInit.Initializer(mContext)
                .useEncryption(true)
                .initialize();
    }

    /**
     * Retrieves a stored int value.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException if there is a preference with this name that is not
     *                            an int.
     * @see SharedPreferences#getInt(String, int)
     */
    public static int getInt(final String key, final int defValue) {
        return with(mContext).get(key).defaultValue(defValue).go();
    }

    /**
     * Retrieves a stored boolean value.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException if there is a preference with this name that is not a boolean.
     * @see SharedPreferences#getBoolean(String, boolean)
     */
    public static boolean getBoolean(final String key, final boolean defValue) {
        return with(mContext).get(key).defaultValue(defValue).go();
    }

    /**
     * Retrieves a stored long value.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException if there is a preference with this name that is not a long.
     * @see SharedPreferences#getLong(String, long)
     */
    public static long getLong(final String key, final long defValue) {
        return with(mContext).get(key).defaultValue(defValue).go();
    }

    /**
     * Returns the double that has been saved as a long raw bits value in the long preferences.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue the double Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException if there is a preference with this name that is not a long.
     * @see SharedPreferences#getLong(String, long)
     */
    public static double getDouble(final String key, final double defValue) {
        return Double.longBitsToDouble(with(mContext).get(key).defaultValue(Double.doubleToLongBits(defValue)).go());
    }

    /**
     * Retrieves a stored float value.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException if there is a preference with this name that is not a float.
     * @see SharedPreferences#getFloat(String, float)
     */
    public static float getFloat(final String key, final float defValue) {
        return with(mContext).get(key).defaultValue(defValue).go();
    }

    /**
     * Retrieves a stored String value.
     *
     * @param key      The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     * @throws ClassCastException if there is a preference with this name that is not a String.
     * @see SharedPreferences#getString(String, String)
     */
    public static String getString(final String key, final String defValue) {
        return with(mContext).get(key).defaultValue(defValue).go();
    }

    /**
     * Get an array of integer from preferences
     * @return Array of integer
     */
    public static int[] getIntegerArray(String countName, String intPrefix){
        int[] ret;
        int count = mPrefs.getInt(countName, 0);
        ret = new int[count];
        for (int i = 0; i < count; i++){
            ret[i] = SecurePrefManager.with(mContext).get(intPrefix).defaultValue(i).go();
        }
        return ret;
    }

    /**
     * Stores a long value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor#putLong(String, long)
     */
    public static void putLong(final String key, final long value) {
        SecurePrefManager.with(mContext).set(key).value(value).go();
    }

    /**
     * Stores an integer value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor#putInt(String, int)
     */
    public static void putInt(final String key, final int value) {
        SecurePrefManager.with(mContext).set(key).value(value).go();
    }

    /**
     * Stores a double value as a long raw bits value.
     *
     * @param key   The name of the preference to modify.
     * @param value The double value to be save in the preferences.
     * @see Editor#putLong(String, long)
     */
    public static void putDouble(final String key, final double value) {
        SecurePrefManager.with(mContext).set(key).value(Double.doubleToRawLongBits(value)).go();
    }

    /**
     * Stores a float value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor#putFloat(String, float)
     */
    public static void putFloat(final String key, final float value) {
        SecurePrefManager.with(mContext).set(key).value(value).go();
    }

    /**
     * Stores a boolean value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor#putBoolean(String, boolean)
     */
    public static void putBoolean(final String key, final boolean value) {
        SecurePrefManager.with(mContext).set(key).value(value).go();
    }

    /**
     * Stores a String value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor#putString(String, String)
     */
    public static void putString(final String key, final String value) {
        SecurePrefManager.with(mContext).set(key).value(value).go();
    }

    /**
     * Put an array of int in preferences
     * @param array The array to put
     */
    public static void putIntegerArray(int[] array, String countName, String intPrefix){
        SecurePrefManager.with(mContext).set(countName).value(array.length).go();
        int count = 0;
        for (int i: array){
            SecurePrefManager.with(mContext).set(intPrefix + count++).value(i).go();
        }
    }

    /**
     * Removes a preference value.
     *
     * @param key The name of the preference to remove.
     * @see Editor#remove(String)
     */
    public static void remove(final String key) {
        SecurePrefManager.with(mContext).remove(key).confirm();
    }

    /**
     * Removes a preference value.
     *
     * @see Editor#remove(String)
     */
    public static void removeAll() {
        SecurePrefManager.with(mContext).clear().confirm();
    }

    /**
     * Removes a preference value.
     *
     * @param keys List of names preference to remove.
     * @see Editor#remove(String)
     */
    public static void removeList(final List<String> keys) {
        for (String key : keys) {
            SecurePrefManager.with(mContext).remove(key).confirm();
        }
    }

}