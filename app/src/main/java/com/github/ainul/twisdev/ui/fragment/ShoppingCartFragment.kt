package com.github.ainul.twisdev.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.ainul.twisdev.R
import com.github.ainul.twisdev.adapter.ListItemAdapter
import com.github.ainul.twisdev.databinding.FragmentShoppingCartBinding
import com.github.ainul.twisdev.ui.viewmodel.MainViewModel
import com.google.android.material.transition.MaterialSharedAxis

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding
    private val viewmodel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)

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

        val adapter = ListItemAdapter(requireContext())
        adapter.data = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        binding.listView.adapter = adapter
    }

    override fun onDestroy() {
        // show actionBar after leaving the fragment
        viewmodel.hideActionBar()
        super.onDestroy()
    }
}