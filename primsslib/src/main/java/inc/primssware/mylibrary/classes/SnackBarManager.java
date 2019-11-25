package inc.primssware.mylibrary.classes;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import inc.primssware.mylibrary.R;

/**
 * Handle snackbar usage all over the application
 *
 * @author Primss Abdallah
 */
public class SnackBarManager {

    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public static final int INFORMATION = 2;
    public static final int WARNING = 3;
    public static final int QUESTION = 4;

    @IntDef({ERROR, SUCCESS, INFORMATION, WARNING, QUESTION})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Purpose{}

    @Purpose
    private static int mPurpose = INFORMATION;

    private static Context mContext;

    /**
     * Singleton instance.
     */
    private final static SnackBarManager instance = new SnackBarManager();

    /**
     * get the singleton instance
     * @return singleton instance
     */
    public static SnackBarManager getInstance() {
        return instance;
    }

    /**
     * Initialize the factory. Must be called before any primary key is generated
     * - preferably from application class.
     */
    public synchronized void initialize(final Context context) {
        mContext = context;
    }

    public static Snackbar buildShortSnackBar(View view, @StringRes int messageResId) {
        return build(view, mContext.getString(messageResId), Snackbar.LENGTH_SHORT);
    }
    public static void showShortSnackBar(View view, @StringRes int messageResId, @Purpose int purpose) {
        if (view == null)
            return;
        mPurpose = purpose;
        build(view, mContext.getString(messageResId), Snackbar.LENGTH_SHORT).show();
    }

    public static Snackbar buildShortSnackBar(View view, String message) {
        return build(view, message, Snackbar.LENGTH_SHORT);
    }
    public static void showShortSnackBar(View view, String message, @Purpose int purpose) {
        mPurpose = purpose;
        build(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static Snackbar buildLongSnackBar(View view, @StringRes int messageResId) {
        return build(view, mContext.getString(messageResId), Snackbar.LENGTH_LONG);
    }
    /**
     * Show snack bar with long duration
     * @param view View to attach to
     * @param messageResId Message to show resource id
     * @param purpose Purpose of the snack bar
     */
    public static void showLongSnackBar(final View view, @StringRes final int messageResId, @Purpose int purpose) {
        if (view == null)
            return;
        mPurpose = purpose;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                build(view, mContext.getString(messageResId), Snackbar.LENGTH_LONG).show();
            }
        }, 1000);
    }

    public static Snackbar buildLongSnackBar(View view, String message) {
        return build(view, message, Snackbar.LENGTH_LONG);
    }
    /**
     * Show snack bar with long duration
     * @param view View to attach to
     * @param message Message to show
     * @param purpose Purpose of the snack bar
     */
    public static void showLongSnackBar(View view, String message, @Purpose int purpose) {
        if (view == null)
            return;
        mPurpose = purpose;
        build(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static Snackbar buildSnackBar(View view, @StringRes int messageResId, @Snackbar.Duration int duration) {
        return build(view, mContext.getString(messageResId), duration);
    }
    public static void showSnackBar(View view, @StringRes int messageResId, @Snackbar.Duration int duration, @Purpose int purpose) {
        if (view == null)
            return;
        mPurpose = purpose;
        build(view, mContext.getString(messageResId), duration).show();
    }

    public static Snackbar buildSnackBar(View view, String message, @Snackbar.Duration int duration) {
        return build(view, message, duration);
    }
    public static void showSnackBar(View view, String message, @Snackbar.Duration int duration, @Purpose int purpose) {
        if (view == null)
            return;
        mPurpose = purpose;
        build(view, message, duration).show();
    }

    public static Snackbar buildFullSnackBar(View view, @StringRes int messageResId, int duration,
                                             @StringRes int actionResId, View.OnClickListener onClickListener) {
        return build(view, mContext.getString(messageResId), duration, mContext.getString(messageResId), onClickListener);
    }
    public static void showFullSnackBar(View view, @StringRes int messageResId, int duration,
                                        @StringRes int actionResId, @Purpose int purpose, View.OnClickListener onClickListener) {
        if (view == null)
            return;
        mPurpose = purpose;
        build(view, mContext.getString(messageResId), duration, mContext.getString(actionResId), onClickListener).show();
    }

    private static Snackbar build(@NonNull View view, @NonNull String message, int duration) {
        Snackbar snackbar = Snackbar
                .make(view, message, duration);
        int backColor = ContextCompat.getColor(mContext, R.color.grey_800);
        int textColor = ContextCompat.getColor(mContext, android.R.color.white);
        switch (mPurpose) {
            case ERROR:
                backColor = ContextCompat.getColor(mContext, R.color.red_500);
                textColor = ContextCompat.getColor(mContext, android.R.color.white);
                break;
            case INFORMATION:
                backColor = ContextCompat.getColor(mContext, R.color.grey_800);
                textColor = ContextCompat.getColor(mContext, android.R.color.white);
                break;
            case SUCCESS:
                backColor = ContextCompat.getColor(mContext, R.color.green_500);
                textColor = ContextCompat.getColor(mContext, android.R.color.white);
                break;
            case WARNING:
                backColor = ContextCompat.getColor(mContext, R.color.yellow_A700);
                textColor = ContextCompat.getColor(mContext, R.color.grey_900);
                break;
        }
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(backColor);
        TextView textView = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(textColor);
        return snackbar;
    }

    private static Snackbar build(@NonNull View view, @NonNull String message, @Snackbar.Duration int duration,
                                  @NonNull String action, @NonNull View.OnClickListener mOnClickListener) {
        Snackbar snackbar = Snackbar
                .make(view, message, duration)
                .setAction(action, mOnClickListener);
        int backColor = ContextCompat.getColor(mContext, R.color.grey_800);
        int textColor = ContextCompat.getColor(mContext, android.R.color.white);
        int actionColor = ContextCompat.getColor(mContext, android.R.color.white);
        switch (mPurpose) {
            case ERROR:
                backColor = ContextCompat.getColor(mContext, R.color.red_500);
                textColor = ContextCompat.getColor(mContext, android.R.color.white);
                actionColor = textColor;
                break;
            case INFORMATION:
                backColor = ContextCompat.getColor(mContext, R.color.grey_800);
                textColor = ContextCompat.getColor(mContext, android.R.color.white);
                actionColor = ContextCompat.getColor(mContext, R.color.green_400);
                break;
            case SUCCESS:
                backColor = ContextCompat.getColor(mContext, R.color.green_500);
                textColor = ContextCompat.getColor(mContext, android.R.color.white);
                actionColor = ContextCompat.getColor(mContext, R.color.red_400);
                break;
            case WARNING:
                backColor = ContextCompat.getColor(mContext, R.color.yellow_A700);
                textColor = ContextCompat.getColor(mContext, R.color.grey_900);
                actionColor = ContextCompat.getColor(mContext, R.color.red_600);
                break;
            case QUESTION:
                backColor = ContextCompat.getColor(mContext, android.R.color.white);
                textColor = ContextCompat.getColor(mContext, R.color.indigo_700);
                actionColor = ContextCompat.getColor(mContext, R.color.green_500);
                break;
        }
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(backColor);
        TextView textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(textColor);
        snackbar.setActionTextColor(actionColor);
        return snackbar;
    }

}