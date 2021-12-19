package com.georgiyangeni.firstandroidapp.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentNewsBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment
import dev.chrisbanes.insetter.applyInsetter

class NewsFragment : BaseFragment(R.layout.fragment_news) {

    private val viewBinding by viewBinding(FragmentNewsBinding::bind)

    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.applyInsetter {
            type(statusBars = true) { margin() }
        }
    }
}
