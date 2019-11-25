package inc.primssware.mylibrary.adapters;

import android.util.Log;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static String TAG = SimpleRecyclerAdapter.class.getSimpleName();

    public interface RecyclerAdapterMethods {
        void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i);

        RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i);

        int getItemCount();
    }

    private List<T> mData = new ArrayList<>();
    private RecyclerAdapterMethods mRecyclerAdapterMethods;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (mRecyclerAdapterMethods == null)
            throw new NullPointerException("You must call implementRecyclerAdapterMethods");

        mRecyclerAdapterMethods.onBindViewHolder(viewHolder, i);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (mRecyclerAdapterMethods == null)
            throw new NullPointerException("You must call implementRecyclerAdapterMethods");

        return mRecyclerAdapterMethods.onCreateViewHolder(viewGroup, i);
    }

    public SimpleRecyclerAdapter(List<T> data) {
        mData = data;
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        if (data == null) {
            if (mData != null)
                mData.clear();
        } else
            mData = data;
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        if (items == null)
            return;
        if (mData == null) {
            mData = items;
        } else if (mData.size() == 0){
            mData.addAll(items);
        } else {
            mData.addAll(0, items);
        }
        notifyDataSetChanged();
    }

    public void addItem(T item, int position) {
        Log.d(TAG, "mData: " + mData);
        Log.d(TAG, "Item: " + item);
        if (mData == null)
            mData = new ArrayList<>();
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(T item) {
        int position = mData.indexOf(item);
        if (position < 0)
            return;
        mData.remove(item);
        notifyItemRemoved(position);
    }

    public void updateItem(int position, T item) {
        if (position < 0)
            return;
        mData.remove(position);
        mData.add(position, item);
        notifyItemChanged(position);
    }

    public int getItemCount() {
        if (mRecyclerAdapterMethods == null)
            throw new NullPointerException("You must call implementRecyclerAdapterMethods");

        return mRecyclerAdapterMethods.getItemCount();
    }

    /**
     * You must call this method to set your normal adapter methods
     *
     * @param callbacks
     */
    public void implementRecyclerAdapterMethods(RecyclerAdapterMethods callbacks) {
        mRecyclerAdapterMethods = callbacks;
    }

}
