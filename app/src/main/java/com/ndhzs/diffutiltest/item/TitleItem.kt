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
 * @time 22:17
 */
class TitleItem(
    map: Map<Int, String>
) : SimpleRvAdapter2.VHItem<TitleItem.TitleVH, String>(
    map,
    R.layout.item_title
) {
    class TitleVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun getNewViewHolder(itemView: View): TitleVH {
        return TitleVH(itemView)
    }

    override fun onCreate(holder: TitleVH, map: Map<Int, String>) {
    }

    override fun onRefactor(holder: TitleVH, position: Int, value: String) {
        holder.tv.text = value
    }
}