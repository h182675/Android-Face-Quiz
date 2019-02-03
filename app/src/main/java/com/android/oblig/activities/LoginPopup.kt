package com.android.oblig.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.oblig.R

class LoginPopup : Fragment() {

    companion object {
        fun newInstance() = LoginPopup()
    }

    private lateinit var viewModel: LoginPopupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_popup_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginPopupViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
