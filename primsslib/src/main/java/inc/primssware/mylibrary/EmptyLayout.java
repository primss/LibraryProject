package inc.primssware.mylibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.joanzapata.iconify.IconDrawable;

import inc.primssware.mylibrary.iconify.Icons;
import inc.primssware.mylibrary.widgets.EmptyRecyclerView;

public class EmptyLayout {

	private Context mContext;
	private ViewGroup mView;
	private EmptyRecyclerView mRecyclerView;
	private LayoutInflater mInflater;
	private View.OnClickListener mButtonClickListener;
    private TextView textViewMessage;
    private Button buttonRefresh;
    private ImageView imageViewIcon;

	// ---------------------------
	// static variables 
	// ---------------------------
	/**
	 * The empty state
	 */
	public final static int TYPE_EMPTY = 0;
	/**
	 * The loading state
	 */
	public final static int TYPE_LOADING = 1;

	// ---------------------------
	// default values
	// ---------------------------
	private int mEmptyType = TYPE_LOADING;
	private String[] mMessage = new String[2];
    private String mButtonText;
    private boolean mViewsAdded;
	private boolean mShowButton = true;
    private boolean mShowImage = true;

	// ---------------------------
	// getters and setters
	// ---------------------------
	/**
	 * Gets the loading layout
	 * @return the loading layout
	 */
	public ViewGroup getView() {
		return mView;
	}

	/**
	 * Gets the list view for which this library is being used
	 * @return the list view
	 */
	public EmptyRecyclerView getRecyclerView() {
		return mRecyclerView;
	}
	
	/**
	 * Sets the list view for which this library is being used
	 * @param RecyclerView
	 */
	public void setRecyclerView(EmptyRecyclerView RecyclerView) {
		this.mRecyclerView = RecyclerView;
	}
	
	/**
	 * Gets the last set state of the list view
	 * @return loading or empty or error
	 */
	public int getEmptyType() {
		return mEmptyType;
	}
	
	/**
	 * Sets the state of the empty view of the list view
	 * @param emptyType loading or empty or error
	 */
	public void setEmptyType(int emptyType) {
		this.mEmptyType = emptyType;
		changeEmptyType();
	}
	
	/**
	 * Gets the message which is shown when the list could not be loaded due to some error
	 * @return the error message 
	 */
	public String getMessage() {
		return mEmptyType == TYPE_EMPTY ? mMessage[0] : mMessage[1];
	}

	/**
	 * Sets the message to be shown when the list could not be loaded due to some error
	 * @param message the error message
	 */
	public EmptyLayout setEmptyMessage(String message) {
		this.mMessage[TYPE_EMPTY] = message;
		return this;
	}

	/**
	 * Sets the message to be shown when the list could not be loaded due to some error
	 * @param messageResId the error message
	 */
	public EmptyLayout setEmptyMessage(int messageResId) {
		this.mMessage[TYPE_EMPTY] = mContext.getString(messageResId);
		return this;
	}

	/**
	 * Sets the message to be shown when the list is loading
	 * @param message the error message
	 */
	public EmptyLayout setLoadingMessage(String message) {
		this.mMessage[TYPE_LOADING] = message;
		return this;
	}

	/**
	 * Sets the message to be shown when the list is loading
	 * @param messageResId the error message
	 */
	public EmptyLayout setLoadingMessage(int messageResId) {
		this.mMessage[TYPE_LOADING] = mContext.getString(messageResId);
		return this;
	}

    /**
     * Gets the OnClickListener which perform when LoadingView was click
     * @return
     */
    public View.OnClickListener getButtonClickListener() {
        return mButtonClickListener;
    }

    /**
     * Sets the OnClickListener to EmptyView
     * @param buttonClickListener OnClickListener Object
     */
    public EmptyLayout setButtonClickListener(View.OnClickListener buttonClickListener) {
        this.mButtonClickListener = buttonClickListener;
		return this;
    }

    /**
     * Gets if a button is shown in the empty view
     * @return if a button is shown in the empty view
     */
    public boolean isButtonVisible() {
		return mShowButton;
	}

    public String getButtonText() {
        return mButtonText;
    }

    public EmptyLayout setButtonText(String buttonText) {
        this.mButtonText = buttonText;
		return this;
    }

    public EmptyLayout setButtonText(int buttonTextResId) {
        this.mButtonText = mContext.getString(buttonTextResId);
		return this;
    }

	public EmptyLayout withButton(){
		mShowButton = true;
		return this;
	}

	public boolean isImageVisible(){
        return this.mShowImage;
    }

    public void showImage(boolean showImage){
        this.mShowImage = showImage;
    }

    // ---------------------------
	// private methods
	// ---------------------------	

	private void changeEmptyType() {
		
		setDefaultValues();
		refreshMessages();

		// insert views in the root view
		if (!mViewsAdded) {
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			RelativeLayout rl = new RelativeLayout(mContext);
			rl.setLayoutParams(lp);
			if (mView!=null) rl.addView(mView);
			mViewsAdded = true;			

			if (!mRecyclerView.hasEmptyView()) {
                ViewGroup parent = (ViewGroup) mRecyclerView.getParent();
                if (parent instanceof SwipeRefreshLayout)
                    parent = (ViewGroup) parent.getParent();
                parent.addView(rl);
                mRecyclerView.setEmptyView(rl);
            }
		}
		
		// change empty type
		if (mRecyclerView != null && mView != null) {
            imageViewIcon.setVisibility(mShowImage? View.VISIBLE : View.GONE);
            Drawable mLoadingIcon = (new IconDrawable(mContext, Icons.ic_coffee)
                    .colorRes(R.color.green_500)
                    .sizeDp(50));
            imageViewIcon.setImageDrawable(mEmptyType != TYPE_EMPTY ? mLoadingIcon : null);
        }
	}
	
	private void refreshMessages() {
		textViewMessage.setText(mEmptyType == TYPE_EMPTY ? mMessage[0] : mMessage[1]);
	}

	private void setDefaultValues() {
		if (mView == null) {
            mView = (ViewGroup) mInflater.inflate(R.layout.empty_layout, null);
            textViewMessage = (TextView) mView.findViewById(R.id.textview_empty);
            buttonRefresh = (Button) mView.findViewById(R.id.button_empty);
            imageViewIcon = (ImageView) mView.findViewById(R.id.imageview_empty);
        }
        if (mShowButton && mButtonClickListener != null) {
            buttonRefresh.setText(mButtonText);
            buttonRefresh.setOnClickListener(mButtonClickListener);
            buttonRefresh.setVisibility(View.VISIBLE);
        } else if (buttonRefresh != null) {
            buttonRefresh.setText(mButtonText);
            buttonRefresh.setVisibility(View.GONE);
        }
        mShowButton = false;
	}

	// ---------------------------
	// public methods
	// ---------------------------
	
	/**
	 * Constructor
	 * @param context the context (preferred context is any activity)
	 */
	public EmptyLayout(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/**
	 * Constructor
	 * @param context the context (preferred context is any activity)
	 * @param RecyclerView the list view for which this library is being used
	 */
	public EmptyLayout(Context context, EmptyRecyclerView RecyclerView) {
		mContext = context;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRecyclerView = RecyclerView;
	}
	
	/**
	 * Shows the empty layout if the list is empty
	 */
	public void showEmpty() {
		this.mEmptyType = TYPE_EMPTY;
		changeEmptyType();
	}

	/**
	 * Shows the empty layout if the list is empty
	 */
	public void showEmpty(String message) {
		this.mEmptyType = TYPE_EMPTY;
		setEmptyMessage(message);
		changeEmptyType();
	}

    /**
     * Shows the empty layout if the list is empty
     */
    public void showEmpty(int messageResId) {
        this.mEmptyType = TYPE_EMPTY;
        setEmptyMessage(mContext.getString(messageResId));
        changeEmptyType();
    }

	/**
	 * Shows loading layout if the list is empty
	 */
	public void showLoading() {
		this.mEmptyType = TYPE_LOADING;
		changeEmptyType();
	}

	/**
	 * Shows loading layout if the list is empty
	 */
	public void showLoading(String message) {
		this.mEmptyType = TYPE_LOADING;
		setLoadingMessage(message);
		changeEmptyType();
	}

    /**
     * Shows loading layout if the list is empty
     */
    public void showLoading(int messageResId) {
        this.mEmptyType = TYPE_LOADING;
        setLoadingMessage(mContext.getString(messageResId));
        changeEmptyType();
    }

	public void forceHide(){
		if (mView != null)
			mView.setVisibility(View.GONE);
	}
}
