package com.beetrack.appbarexample

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.beetrack.appbarexample.databinding.FragmentSecondBinding
import com.beetrack.appbarexample.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_third, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_rowing_third -> {
                Log.d("Second", "action search second")
                findNavController().navigate(R.id.action_thirdFragment_to_fullscreenFragment)
                true
            }
            R.id.action_refresh_third -> {
                Toast.makeText(requireContext(), "Third Refreshing breeze", Toast.LENGTH_LONG).show()
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

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonThird.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}