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
import com.github.ainul.twisdev.ui.viewmodel.MainViewModel.Companion.CartItems
import com.google.android.material.transition.MaterialSharedAxis

class ShoppingCartFragment : Fragment(), ListItemListener {

    // Viewmodel, dataBinding, viewComponents, reference, etc...
    private lateinit var binding: FragmentShoppingCartBinding
    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var adapter: ListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setAnimationTransition()
        updateLiveData()
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

    private fun updateLiveData() {
        viewmodel.itemsOnCart.observe(viewLifecycleOwner, {
            adapter.submitList(it.toList())
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListItemAdapter(requireContext(), this)
        adapter.submitList(viewmodel.listOfItems)
        binding.listView.adapter = adapter
    }

    /**
     * called on navigateUp to showActionBar again
     */
    override fun onDestroy() {
        // show actionBar after leaving the fragment
        viewmodel.hideActionBar(false)
        super.onDestroy()
    }

    override fun onListItemAction(data: CartItems) {
        viewmodel.updateData(data)
    }
}