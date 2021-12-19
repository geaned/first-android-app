package com.georgiyangeni.firstandroidapp.ui.emailconfirmation

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentEmailConfirmationBinding
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.NonCancellable.cancel
import org.w3c.dom.Text

class EmailConfirmationFragment : BaseFragment(R.layout.fragment_email_confirmation) {

    private val viewBinding by viewBinding(FragmentEmailConfirmationBinding::bind)

    private val viewModel: EmailConfirmationViewModel by viewModels()

    private lateinit var resendTimer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.backButton.applyInsetter {
            type(statusBars = true) { margin() }
        }
        viewBinding.emailConfirmationButtons.applyInsetter {
            type(navigationBars = true) { margin() }
        }
        viewBinding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        resendTimer = object: CountDownTimer(
            resources.getInteger(R.integer.resend_confirmation_code_time_span).toLong() * 1000,
            1000) {

            override fun onTick(millisUntilFinished: Long) {
                resetTimerText((millisUntilFinished / 1000).toInt())
            }
            override fun onFinish() {
                viewBinding.resendConfirmationCodeLinkButton.isEnabled = true
                viewBinding.resendConfirmationCodeTimer.text =
                    resources.getString(R.string.resend_confirmation_code_message)
            }
        }

        viewBinding.resendConfirmationCodeLinkButton.setOnClickListener {
            viewBinding.resendConfirmationCodeLinkButton.isEnabled = false
            resendTimer.start()
        }
        resendTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        resendTimer.cancel()
    }

    private fun resetTimerText(seconds: Int) {
        viewBinding.resendConfirmationCodeTimer.text =
            resources.getString(R.string.confirmation_code_timer_template, seconds)
    }
}
