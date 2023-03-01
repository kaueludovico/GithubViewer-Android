package com.zallpy.githubviewer.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zallpy.githubviewer.databinding.FragmentSearchBinding
import com.zallpy.githubviewer.viewModel.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setButtonClick()
    }

    private fun setViewModel() {
        binding?.let {
            viewModel = ViewModelProvider(
                this,
                SearchViewModel.SearchViewModelProvider(
                    this,
                    it
                )
            )[SearchViewModel::class.java]
        }
    }

    private fun setButtonClick() {
        binding?.buttonSearch?.setOnClickListener {
            viewModel.getUser()
            viewModel.liveDataUser.observe(viewLifecycleOwner, Observer {
                val action = SearchFragmentDirections.actionSearchFragmentToProfileFragment(
                    it
                )
                findNavController().navigate(action)
            })
        }
    }
}