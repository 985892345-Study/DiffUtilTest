package com.ndhzs.diffutiltest.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.diffutiltest.R
import com.ndhzs.diffutiltest.SimpleRvAdapter2

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @data 2021/8/29
 * @time 22:18
 */
class ListItem(
    map: Map<Int, String>
) : SimpleRvAdapter2.VHItem<ListItem.ListVH, String>(
    map,
    R.layout.item_list
) {
    class ListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.tv_list)
    }

    override fun getNewViewHolder(itemView: View): ListVH {
        return ListVH(itemView)
    }

    override fun onCreate(holder: ListVH, map: Map<Int, String>) {
    }

    override fun onRefactor(holder: ListVH, position: Int, value: String) {
        holder.tv.text = value
    }
}