package com.github.ainul.twisdev.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ainul.twisdev.adapter.ListItemAdapter
import com.github.ainul.twisdev.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        // setup recyclerView
        setupRecyclerView()

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
}