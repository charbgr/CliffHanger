package com.github.charbgr.baseadapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseRvAdapter<T : BaseRvItem> :
  RecyclerView.Adapter<BaseRvAdapter<T>.BaseViewHolder>() {

  val itemList: MutableList<T> = mutableListOf()

  override fun getItemCount(): Int = itemList.size

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    val item = getItemAt(position)
    if (item != null) {
      holder.bind(item, position)
    } else {
      holder.clear()
    }
  }

  override fun onViewRecycled(holder: BaseViewHolder) {
    holder.clear()
    super.onViewRecycled(holder)
  }

  override fun getItemViewType(position: Int): Int = getItemAt(position)?.getItemViewType() ?: -1

  fun setItems(items: List<T>) {
    itemList.clear()
    itemList.addAll(items)
    notifyDataSetChanged()
  }

  fun addItem(item: T) {
    itemList.add(item)
    notifyItemRangeInserted(itemCount - 1, itemCount)
  }

  fun addItems(items: List<T>) {
    val prevSize = itemCount
    itemList.addAll(items)
    notifyItemRangeInserted(prevSize, itemCount)
  }

  fun getItemAt(position: Int): T? = itemList.getOrNull(position)

  fun clearItems() {
    itemList.clear()
    notifyDataSetChanged()
  }

  protected fun throwUnsupportedViewType(viewType: Int): BaseViewHolder {
    throw IllegalStateException(
        "Unsupported viewtype: $viewType. Did you forget to declare a ViewHolder??")
  }

  abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int)
    abstract fun clear()
  }
}
