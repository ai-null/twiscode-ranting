package com.github.ainul.twisdev.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.ainul.twisdev.R
import com.github.ainul.twisdev.adapter.ListItemAdapter
import com.github.ainul.twisdev.adapter.listener.ListItemListener
import com.github.ainul.twisdev.databinding.FragmentShoppingCartBinding
import com.github.ainul.twisdev.ui.viewmodel.MainViewModel
import com.google.android.material.transition.MaterialSharedAxis

class ShoppingCartFragment : Fragment(), ListItemListener {

    // Viewmodel, dataBinding, viewComponents, reference, etc...
    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var adapter: ListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel

        binding.lifecycleOwner = this

        setAnimationTransition()
        return binding.root
    }

    private fun setAnimationTransition() {
        val transitionDuration =
            resources.getInteger(R.integer.default_transition_duration).toLong()
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = transitionDuration
        }

        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = transitionDuration
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListItemAdapter(requireContext(), this)
        adapter.submitList(viewmodel.listOfItems)
        binding.listView.adapter = adapter

        updateLiveData()
    }

    private fun updateLiveData() {
        viewmodel.itemsOnCart.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    /**
     * called on navigateUp to showActionBar again
     */
    override fun onDestroy() {
        // show actionBar after leaving the fragment
        viewmodel.hideActionBar(false)
        _binding = null
        super.onDestroy()
    }

    override fun onListItemAction(data: MainViewModel.Companion.CartItems, increase: Boolean) {
        super.onListItemAction(data, increase)
        viewmodel.updateData(data, increase)
    }
}