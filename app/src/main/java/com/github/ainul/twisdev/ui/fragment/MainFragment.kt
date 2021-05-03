package com.github.ainul.twisdev.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.ainul.twisdev.R
import com.github.ainul.twisdev.adapter.ListItemAdapter
import com.github.ainul.twisdev.databinding.FragmentMainBinding
import com.github.ainul.twisdev.ui.viewmodel.MainViewModel
import com.google.android.material.transition.MaterialSharedAxis

class MainFragment : Fragment() {

    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        setupRecyclerView()

        val defaultTransitionDuration =
            resources.getInteger(R.integer.default_transition_duration).toLong()

        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = defaultTransitionDuration
        }

        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = defaultTransitionDuration
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        // setup sample data
        val sampleData = ArrayList<Int>()
        for (i in 0..10) {
            sampleData.add(i)
        }

        // setup adapter & assign to the view
        val listItemAdapter = ListItemAdapter(requireContext())
        val listView: RecyclerView = binding.listView

        listItemAdapter.data = sampleData
        listView.adapter = listItemAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuCart -> {
                navigateToCart()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun navigateToCart() {
        this.findNavController().navigate(R.id.action_mainFragment_to_shoppingCartFragment)
        viewmodel.hideActionBar()
    }
}