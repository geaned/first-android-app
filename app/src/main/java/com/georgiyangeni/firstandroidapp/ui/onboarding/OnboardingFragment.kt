package com.georgiyangeni.firstandroidapp.ui.onboarding

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentOnboardingBinding
import com.georgiyangeni.firstandroidapp.onboardingTextAdapterDelegate
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val viewBinding by viewBinding(FragmentOnboardingBinding::bind)
    private var player: ExoPlayer? = null

    private var isSoundOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = SimpleExoPlayer.Builder(requireContext()).build().apply {
            addMediaItem(MediaItem.fromUri("asset:///onboarding.mp4"))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.playerView.player = player
        player?.volume = 0f

        viewBinding.viewPager.setTextPages()
        viewBinding.viewPager.attachDots(viewBinding.onboaringTextTabLayout)
        viewBinding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_signInFragment)
        }

        viewBinding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_signUpFragment)
        }

        viewBinding.volumeControlButton.setOnClickListener {
            changeVolumeStatus()
        }
    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    private fun ViewPager2.setTextPages() {
        adapter =
            ListDelegationAdapter(onboardingTextAdapterDelegate()).apply {
                items =
                    listOf(
                        getString(R.string.onboarding_view_pager_text_1),
                        getString(R.string.onboarding_view_pager_text_2),
                        getString(R.string.onboarding_view_pager_text_3)
                    )
            }
    }

    private fun ViewPager2.attachDots(tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, this) { _, _ -> }.attach()
    }

    private fun changeVolumeStatus() {
        isSoundOn = !isSoundOn

        if (isSoundOn) {
            player?.volume = 1f
            viewBinding.volumeControlButton.setImageResource(R.drawable.ic_volume_up_white_24dp)
        } else {
            player?.volume = 0f
            viewBinding.volumeControlButton.setImageResource(R.drawable.ic_volume_down_white_24dp)
        }
    }
}