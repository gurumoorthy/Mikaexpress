

package com.gurumoorthy.mikaexpress.view.customview;



public interface ItemTouchHelperAdapter {


    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
