package com.ineedyourcode.dictionary.ui.uils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ineedyourcode.dictionary.databinding.DialogFragmentNoConnectionBinding

class NoConnectionDialogFragment : DialogFragment() {
    private var _binding: DialogFragmentNoConnectionBinding? = null
    private val binding: DialogFragmentNoConnectionBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogFragmentNoConnectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.okButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}