package germangirod.databindingexample;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by germangirod on 3/14/16.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mViewDataBinding;

    public CustomViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());

        mViewDataBinding = viewDataBinding;
        mViewDataBinding.executePendingBindings();
    }

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }
}
