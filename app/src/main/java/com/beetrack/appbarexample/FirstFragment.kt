package com.beetrack.appbarexample

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beetrack.appbarexample.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    val inputList = (1..35).map { Item("text $it") }

    private val TAG: String = "FirstFragment"
    private var _binding: FragmentFirstBinding? = null


    private val stringAdapter = StringAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_first, menu)

        val searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener (object : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("first", newText!!)

                stringAdapter.submitList(inputList.filter { item -> item.text.contains(newText) })

                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_search -> {
                Log.d(TAG, "action search from first fragment")
                true
            }
            R.id.action_refresh_first -> {
                Toast.makeText(requireContext(), "Refreshing breeze", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_rowing -> {
                Toast.makeText(requireContext(), "Rowing Rowing Rowing", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_FirstFragment_to_fullscreenFragment)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        stringAdapter.submitList(inputList)

        with(binding.recyclerView) {
            setHasFixedSize(true)
            adapter = stringAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}