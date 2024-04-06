package ru.unlim1x.shelf.presentation.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.foodtest.R



class RecyclerViewBannerAdapter() :
    RecyclerView.Adapter<RecyclerViewBannerAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var product_image: ImageView = itemView.findViewById(R.id.banner_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.banner_item, parent, false)
        return ViewHolder(card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.product_image.load(R.drawable.rectangle1
            ) {
                crossfade(true)
                transformations(RoundedCornersTransformation(20f))
            }

    }



    override fun getItemCount(): Int {
        return 3
    }


}