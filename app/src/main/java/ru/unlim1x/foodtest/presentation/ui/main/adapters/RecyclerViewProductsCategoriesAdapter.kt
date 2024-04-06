package ru.unlim1x.shelf.presentation.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.foodtest.R
import ru.unlim1x.foodtest.presentation.ui.main.CategoriesListener


class RecyclerViewProductsCategoriesAdapter(val listener: CategoriesListener) :
    RecyclerView.Adapter<RecyclerViewProductsCategoriesAdapter.ViewHolder>() {

    private var categories: List<Category?>? = null


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.category_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(card)
    }

    var selectedPosition = 0
    var lastSelectedPosition = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.e("RVCA", "position: $position")
        if (categories != null) {
            holder.textView.text = categories!![position]?.strCategory

        } else {
            Log.e("RVIA", "empty list onBindViewHolder")
        }
        holder.itemView.setOnClickListener {
            lastSelectedPosition = selectedPosition;
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastSelectedPosition);
            notifyItemChanged(selectedPosition);

            categories!![position]?.strCategory?.let { it1 -> listener.onCategoryPressed(it1) }
        }
        if (selectedPosition == holder.adapterPosition) {
            holder.itemView.findViewById<TextView>(R.id.category_text)
                .setTextColor(Color.parseColor("#FD3A69"))
        } else {
            holder.itemView.findViewById<TextView>(R.id.category_text)
                .setTextColor(Color.parseColor("#808080"))
        }

    }

    fun setList(categories: List<Category?>?) {
        this.categories = categories
        Log.e("RVIA", "Size of list: ${this.categories?.size}")
    }


    override fun getItemCount(): Int {
        return if (categories != null) {
            //Log.e("RVA", "item count called")
            categories!!.size
        } else
            0
    }

    private fun resetColors() {

    }

}