package com.example.shopapp.presentation.screens.itemScreen

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentItemBinding
import com.example.shopapp.domain.model.Jeans
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemFragment:Fragment(R.layout.fragment_item) {
    private lateinit var snackbar: Snackbar
    private val viewBinding: FragmentItemBinding by viewBinding(FragmentItemBinding::bind)
    private val favouriteViewModel: FavouriteProductViewModel by viewModels()
    private val args by navArgs<ItemFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackbar = Snackbar.make(requireView(), "", 3000)
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(Jeans::class.java)
        val jean = adapter.fromJson(args.product)
        Glide.with(requireContext()).load(jean!!.image).into(viewBinding.ivJean)
        viewBinding.tvPrice.text = "${jean.price?.toInt()} P"
        viewBinding.tvTitle.text = jean.title
        viewBinding.cbFavourite.isChecked = jean.favourite!!
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            snackbar.view.visibility = View.GONE
            parentFragmentManager.setFragmentResult(
                "product",
                bundleOf("favourite" to viewBinding.cbFavourite.isChecked)
            )
            findNavController().popBackStack()
        }

        viewBinding.cbFavourite.setOnCheckedChangeListener { _, isChecked ->
            var textSnackBar = ""
            if (isChecked) {
                textSnackBar = "Товар добавлен в избранное"
                favouriteViewModel.favourite(jean.id!!)
            } else {
                textSnackBar = "Товар удалён из избранного"
                favouriteViewModel.unFavourite(jean.id!!)
            }
            snackbar =
                Snackbar.make(requireView(), textSnackBar, 3000)
            snackbar.anchorView = viewBinding.clNotification
            snackbar.show()
        }
    }

    override fun onDestroyView() {
        snackbar.dismiss()
        super.onDestroyView()
    }

}
