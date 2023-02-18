package com.example.shopapp.presentation.screens.firstScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentFirstBinding
import com.example.shopapp.domain.model.Jeans
import com.example.shopapp.presentation.adapter.JeansAdapter
import com.example.shopapp.presentation.decoration.GridItemDecoration
import com.example.shopapp.presentation.screens.itemScreen.FavouriteProductViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment: Fragment(R.layout.fragment_first), JeansAdapter.ListenerItem {
    private lateinit var snackbar: Snackbar
    private val viewBinding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)
    private val viewModel: JeansViewModel by viewModels()
    private var productPosition: Int = -1
    private lateinit var productJean: Jeans
    private val favouriteViewModel: FavouriteProductViewModel by viewModels()
    private val jeans = arrayListOf<Jeans>()
    private lateinit var adapter: JeansAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackbar = Snackbar.make(requireView(), "", 100)
        viewBinding.rvJeans.layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.rvJeans.addItemDecoration(GridItemDecoration(85, 22))
        viewModel.getJeans()

        viewModel.jeans.observe(viewLifecycleOwner, {
            if (jeans.size == 0) {
                jeans.addAll(it)
            }
            if (viewBinding.rvJeans.adapter == null) {
                adapter = JeansAdapter(jeans, this)
                viewBinding.rvJeans.adapter = adapter
            }

        })
        setFragmentResultListener("product") { key, bundle ->
            val result = bundle.getBoolean("favourite")
            productJean.favourite = result
            jeans[productPosition] = productJean
            adapter.notifyItemChanged(productPosition)
        }
    }


    override fun onDestroyView() {
        snackbar.dismiss()
        super.onDestroyView()
    }

    override fun onLikeClickItem(id: Int, add: Boolean) {
        var textSnackBar = ""
        if (add) {
            textSnackBar = "Товар добавлен в избранное"
            favouriteViewModel.favourite(id)
        } else {
            textSnackBar = "Товар удалён из избранного"
            favouriteViewModel.unFavourite(id)
        }
        snackbar =
            Snackbar.make(requireView(), textSnackBar, Snackbar.LENGTH_INDEFINITE)
        snackbar.anchorView = null
        snackbar.show()

    }

    override fun onItemClick(jean: Jeans, position: Int) {
        productPosition = position
        productJean = jean
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(Jeans::class.java)
        val action =
            FirstFragmentDirections.actionFirstFragmentToItemFragment(adapter.toJson(jean))
        findNavController().navigate(action)
    }





















}