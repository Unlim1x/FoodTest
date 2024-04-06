package ru.unlim1x.shelf.presentation.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.foodtest.R
import java.nio.file.DirectoryStream
import java.util.Locale


class RecyclerViewProductsAdapter() :
    RecyclerView.Adapter<RecyclerViewProductsAdapter.ViewHolder>(), Filterable {

    private var productsList: MutableList<Product?>? = mutableListOf()
    private var productsListFiltered: MutableList<Product?>? = mutableListOf()

    override fun getFilter(): Filter {
        return filter
    }

    private val filter : Filter = object:Filter(){
        override fun performFiltering(query: CharSequence?): FilterResults {
            Log.e("query", query.toString())

            if(query.isNullOrEmpty()){
                productsListFiltered = productsList
            }
            else{
                if(query.equals("All")){
                    productsListFiltered = productsList
                }
                else {
                    val string = query.toString()
                    productsListFiltered = mutableListOf()
                    productsList?.forEach {
                        if (it?.strCategory?.equals(query) == true) {
                            productsListFiltered?.add(it)
                        }
                    }
                }
            }
            val filterResults = FilterResults()

            filterResults.values = productsListFiltered
            filterResults.count = productsListFiltered?.size!!
            return filterResults
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            productsList = p1?.values as MutableList<Product?>?
            notifyDataSetChanged()
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: String? = null
        var product_name: TextView = itemView.findViewById(R.id.product_name)
        var product_description: TextView = itemView.findViewById(R.id.product_description)
        var product_image: ImageView = itemView.findViewById(R.id.product_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ViewHolder(card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.e("RVA", "position: $position")
        if (productsList != null) {
            holder.id = productsList!![position]?.idMeal
            holder.product_name.text = productsList!![position]?.strMeal
            Log.e("catInRVA", "${productsList!![position]?.strCategory}")
            Log.e("RVA product, position", "${productsList!![position]?.strMeal}, $position")

            holder.product_description.text = productsList!![position]?.ingredientsList?.joinToString()

            holder.product_image.load(
                productsList!![position]?.strMealThumb
            ) {
                crossfade(true)
                transformations(RoundedCornersTransformation(20f))
            }


        } else {
            Log.e("RVA", "empty list onBindViewHolder")
        }
    }

    fun setList(productsList: List<Product?>?) {
        this.productsList = productsList as MutableList<Product?>?
        Log.e("RVA", "Size of list: ${this.productsList?.size}")
    }


    override fun getItemCount(): Int {
        return if (productsList != null) {
            productsList!!.size
        } else
            0
    }


}