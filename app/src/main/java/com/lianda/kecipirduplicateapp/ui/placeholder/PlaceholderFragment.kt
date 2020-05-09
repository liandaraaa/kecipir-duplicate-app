package com.lianda.kecipirduplicateapp.ui.placeholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lianda.kecipirduplicateapp.R

/**
 * A simple [Fragment] subclass.
 */
class PlaceholderFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceholderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placeholder, container, false)
    }

}
