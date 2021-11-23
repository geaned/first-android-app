package com.georgiyangeni.firstandroidapp.ui.userlist

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment
import com.georgiyangeni.firstandroidapp.ui.MainViewModel
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentUserlistBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListFragment : BaseFragment(R.layout.fragment_userlist) {

    private lateinit var viewModel: UserListViewModel

    private val viewBinding by viewBinding(FragmentUserlistBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    renderViewState(viewState)
                }
            }
        }
    }

    private fun renderViewState(viewState: UserListViewModel.ViewState) {
        when (viewState) {
            is UserListViewModel.ViewState.Loading -> {
                viewBinding.usersRecyclerView.isVisible = false
                viewBinding.progressBar.isVisible = true
            }
            is UserListViewModel.ViewState.Data -> {
                viewBinding.usersRecyclerView.isVisible = true
                (viewBinding.usersRecyclerView.adapter as UserAdapter).apply {
                    userList = viewState.userList
                    notifyDataSetChanged()
                }
                viewBinding.progressBar.isVisible = false
            }
        }
    }

    private fun setupRecyclerView(): UserAdapter {
//        val recyclerView = viewBinding.usersRecyclerView
//        val adapter = UserAdapter()
//        recyclerView.adapter = adapter
        val adapter = UserAdapter().also {
            viewBinding.usersRecyclerView.adapter = it
        }
        ContextCompat.getDrawable(requireContext(), R.drawable.userlist_gradient_separator)?.let { drawable ->
            UserListItemSeparator(drawable)
        }?.let { itemDecoration ->
            viewBinding.usersRecyclerView.addItemDecoration(itemDecoration)
        }
        return adapter
    }
}