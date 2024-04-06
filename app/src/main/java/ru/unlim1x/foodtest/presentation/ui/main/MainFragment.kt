package ru.unlim1x.foodtest.presentation.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.unlim1x.foodtest.R
import ru.unlim1x.foodtest.databinding.FragmentMenuBinding
import ru.unlim1x.shelf.presentation.adapters.RecyclerViewBannerAdapter
import ru.unlim1x.shelf.presentation.adapters.RecyclerViewProductsAdapter
import ru.unlim1x.shelf.presentation.adapters.RecyclerViewProductsCategoriesAdapter


class MainFragment : Fragment(), CategoriesListener {

    private var _binding: FragmentMenuBinding? = null
    private val productRecyclerAdapter = RecyclerViewProductsAdapter()

    private val binding get() = _binding!!
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.error.errorCard.visibility = View.INVISIBLE
        binding.loadingProgressBar.visibility = View.VISIBLE

        val productsRecyclerView = binding.productsRv
        productsRecyclerView.adapter = productRecyclerAdapter

        val categoriesRecyclerAdapter = RecyclerViewProductsCategoriesAdapter(this)
        val categoriesRecyclerView = binding.appbar.findViewById<RecyclerView>(R.id.categoreies_rv)
        categoriesRecyclerView.adapter = categoriesRecyclerAdapter

        val bannerRecyclerAdapter = RecyclerViewBannerAdapter()
        val bannerRecyclerView = binding.appbar.findViewById<RecyclerView>(R.id.banner_rv)
        bannerRecyclerView.adapter = bannerRecyclerAdapter
        bannerRecyclerAdapter.notifyItemRangeChanged(0, 2)





        viewModel.categoriesList.observe(viewLifecycleOwner){
            categoriesRecyclerAdapter.setList(it)
            categoriesRecyclerAdapter.notifyItemRangeChanged(0, it.size)

        }

        viewModel.productsList.observe(viewLifecycleOwner){
            productRecyclerAdapter.setList(it)
            productRecyclerAdapter.notifyItemRangeChanged(0, it.size)
            binding.loadingProgressBar.visibility = View.INVISIBLE
        }



        viewModel.errorWhileLoading.observe(viewLifecycleOwner){
            if(it == true) {
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.error.errorCard.visibility = View.VISIBLE
            }
        }


        viewModel.load()

        binding.appbar.addOnOffsetChangedListener(object:AppBarLayout.OnOffsetChangedListener{
            var scrollRange = -1
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                Log.e("scroll offset", "$p1")
                Log.e("scroll total", "${p0?.totalScrollRange!!}")
                binding.bannerRv.alpha = (p0?.totalScrollRange!!+p1)/p0?.totalScrollRange!!.toFloat()
            }
        })

        binding.error.repeatButton.setOnClickListener {
            binding.loadingProgressBar.visibility = View.VISIBLE
            binding.error.errorCard.visibility = View.GONE
            viewModel.load()
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryPressed(str: String) {
        Log.e("category:", str)
        productRecyclerAdapter.setList(viewModel.productsList.value)
        productRecyclerAdapter.filter.filter(str)
    }
}