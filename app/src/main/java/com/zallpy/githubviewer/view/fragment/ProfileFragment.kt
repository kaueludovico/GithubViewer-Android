package com.zallpy.githubviewer.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.zallpy.githubviewer.R
import com.zallpy.githubviewer.databinding.FragmentProfileBinding
import com.zallpy.githubviewer.model.Repository
import com.zallpy.githubviewer.view.adapter.RepositoryAdapter
import com.zallpy.githubviewer.viewModel.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var adapterRepository: RepositoryAdapter
    val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()
        setViewModel()
        callRepos()
    }

    private fun setUserData() {
        Glide.with(this)
            .load(args.user.avatarUrl)
            .into(binding!!.imageViewProfile)
        binding!!.textViewName.text = args.user.name
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this,
            ProfileViewModel.ProfileViewModelProvider(this)
        )[ProfileViewModel::class.java]
    }

    private fun callRepos() {
        viewModel.getRepositories()

        viewModel.liveDataRepository.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })
    }

    private fun setAdapter(list: List<Repository>) {
        adapterRepository = RepositoryAdapter(
            list
        ) {
            openGithub(it)
        }
        binding?.let {
            it.recyclerView.apply {
                adapter = adapterRepository
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun openGithub(repository: Repository) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlUrl))
        startActivity(intent)
    }
}