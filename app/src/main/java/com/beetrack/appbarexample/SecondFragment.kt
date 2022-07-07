package com.beetrack.appbarexample

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.beetrack.appbarexample.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    val inputListSecond = (1..20).map { Item("second text $it") }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val stringAdapter = StringAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_second, menu)

        val searchView = menu.findItem(R.id.action_search_second)?.actionView as SearchView
        searchView.setOnQueryTextListener (object : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    stringAdapter.submitList(inputListSecond.filter { item -> item.text.contains(newText) })
                }

                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_search_second -> {
                Log.d("Second", "action search second")
                true
            }
            R.id.action_search_second -> {
                Toast.makeText(requireContext(), "Second Refreshing breeze", Toast.LENGTH_LONG).show()
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonFirst2.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
        }

        stringAdapter.submitList(inputListSecond)

        with(binding.recyclerViewSecond) {
            setHasFixedSize(true)
            adapter = stringAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}