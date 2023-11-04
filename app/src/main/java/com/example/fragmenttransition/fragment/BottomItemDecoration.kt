package com.example.fragmenttransition.fragment

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomItemDecoration : RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if(parent.getChildAdapterPosition(view) == state.itemCount-1){
            outRect.bottom = 200
        }
    }

}