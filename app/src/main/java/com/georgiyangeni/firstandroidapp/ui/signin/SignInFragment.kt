package com.georgiyangeni.firstandroidapp.ui.signin

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewBinding by viewBinding(FragmentSignInBinding::bind)

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backButton.setOnClickListener {
            val email = viewBinding.emailEditText.text?.toString()
            val password = viewBinding.passwordEditText.text?.toString()

            if (email.isNullOrBlank() && password.isNullOrBlank()) {
                findNavController().popBackStack()
                return@setOnClickListener
            }
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.sign_in_back_alert_dialog_text)
                .setNegativeButton(R.string.sign_in_back_alert_dialog_no_button_text) { dialog, _ ->
                    dialog?.dismiss()
                }
                .setPositiveButton(R.string.sign_in_back_alert_dialog_ok_button_text) { _, _ ->
                    findNavController().popBackStack()
                }
                .show()
        }
        viewBinding.signInButton.setOnClickListener {
            viewModel.signIn(
                viewBinding.emailEditText.text?.toString() ?: "",
                viewBinding.passwordEditText.text?.toString() ?: "",
            )
        }
        subscribeToFormFields()
    }

    private fun subscribeToFormFields() {
        decideSignInButtonEnabledState(
            email = viewBinding.emailEditText.text?.toString() ?: "",
            password = viewBinding.passwordEditText.text?.toString() ?: "",
        )
        viewBinding.emailEditText.doAfterTextChanged { email ->
            decideSignInButtonEnabledState(
                email = email?.toString(),
                password = viewBinding.passwordEditText.text?.toString(),
            )
        }
        viewBinding.passwordEditText.doAfterTextChanged { password ->
            decideSignInButtonEnabledState(
                email = viewBinding.passwordEditText.text?.toString(),
                password = password?.toString(),
            )
        }
    }

    private fun decideSignInButtonEnabledState(email: String?, password: String?) {
        viewBinding.signInButton.isEnabled = !(email.isNullOrBlank() || password.isNullOrBlank())
    }
}