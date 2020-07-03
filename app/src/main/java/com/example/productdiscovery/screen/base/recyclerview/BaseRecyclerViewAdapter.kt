package com.example.productdiscovery.screen.base.recyclerview

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.productdiscovery.utils.define.notNull
import com.example.productdiscovery.utils.listener.BindableAdapter
import com.example.productdiscovery.utils.listener.OnItemClickListener

abstract class BaseRecyclerViewAdapter<T, V : RecyclerView.ViewHolder> constructor(
    protected val context: Context,
    protected var layoutInflater: LayoutInflater = LayoutInflater.from(
        context),
    protected var listData: MutableList<T> = mutableListOf()) :
    RecyclerView.Adapter<V>(), BindableAdapter<List<T>> {

    protected var onItemClickListener: OnItemClickListener<T>? = null

    companion object {
        const val DEFAULT_LAST_POSITION = -1
    }

    var lastPosition = DEFAULT_LAST_POSITION

    override fun onViewDetachedFromWindow(holder: V) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun getItemCount(): Int {
        listData?.let {
            return it.size
        }
    }

    fun getData(): List<T> {
        return listData
    }

    override fun refreshData(data: List<T>) {
        data.notNull {
            lastPosition = DEFAULT_LAST_POSITION
            listData.clear()
            listData.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun addData(data: List<T>) {
        data.notNull {
            listData.addAll(it)
            notifyItemRangeChanged(listData.size, it.size)
        }
    }

    fun registerOnItemClickListener(
        onItemClickListener: OnItemClickListener<T>
    ) {
        this.onItemClickListener = onItemClickListener
    }

}
