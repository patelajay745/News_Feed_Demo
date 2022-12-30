package com.ajay.example.newsappdemo.adapter

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ItemClickListner"

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerClickListner
) : RecyclerView.SimpleOnItemTouchListener() {

    interface OnRecyclerClickListner {
        fun onItemClick(view: View, position: Int)

        //for long press
        //fun onItemLongClick(view: View, Poistion: Int)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val result=gestureDetector.onTouchEvent(e)
        return result
    }

    private val gestureDetector=GestureDetectorCompat(context,object:GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val childView=recyclerView.findChildViewUnder(e.x,e.y)
            if (childView != null) {
                listener.onItemClick(childView,recyclerView.getChildAdapterPosition(childView))
            }

            return true
        }

/*
        override fun onLongPress(e: MotionEvent) {
            val childView=recyclerView.findChildViewUnder(e.x,e.y)
            if (childView != null) {
                listener.onItemLongClick(childView,recyclerView.getChildAdapterPosition(childView))
            }

        }

 */
    })


}