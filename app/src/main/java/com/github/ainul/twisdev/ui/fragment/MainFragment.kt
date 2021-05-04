package com.github.ainul.twisdev.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.ainul.twisdev.R
import com.github.ainul.twisdev.adapter.GridItemAdapter
import com.github.ainul.twisdev.databinding.FragmentMainBinding
import com.github.ainul.twisdev.ui.viewmodel.MainViewModel
import com.google.android.material.transition.MaterialSharedAxis

class MainFragment : Fragment() {

    // Viewmodel, dataBinding, viewComponents, reference, etc...
    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var gridItemAdapter: GridItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        // set option to begin work with menu & set transition to this screen
        setHasOptionsMenu(true)
        setTransitionAnimation()

        setupRecyclerView()

        // liveData watcher
        updateLiveData()

        return binding.root
    }

    private fun setTransitionAnimation() {
        val transitionDuration = resources.getInteger(R.integer.default_transition_duration).toLong()
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = transitionDuration
        }

        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = transitionDuration
        }
    }

    private fun updateLiveData() {
        viewmodel.fetchedListItemData.observe(viewLifecycleOwner, Observer {
            it?.let {
                gridItemAdapter.data = it
            }
        })
    }

    /** Setup GridItemRecyclerView */
    private fun setupRecyclerView() {
        // setup adapter & assign to the view
        gridItemAdapter = GridItemAdapter(requireContext())
        binding.listView.adapter = gridItemAdapter
    }

    private fun navigateToCart() {
        this.findNavController().navigate(R.id.action_mainFragment_to_shoppingCartFragment)
        viewmodel.hideActionBar()
    }

    /** Options menu creator & eventHandler **/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuCart -> navigateToCart()
        }

        return super.onOptionsItemSelected(item)
    }
}