package com.peter.ziska.kiwi.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.peter.ziska.kiwi.R
import com.peter.ziska.kiwi.ViewModelFactory
import com.peter.ziska.kiwi.network.Status
import com.peter.ziska.kiwi.prefs.FlightPreferences
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class FlightFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferences: FlightPreferences
    lateinit var viewModel: FlightViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private lateinit var flightAdapter: FlightAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        flightAdapter = FlightAdapter(this.context)
        viewModel =
            ViewModelProviders.of(this, context?.let { viewModelFactory })
                .get(FlightViewModel::class.java)

        loadData()
    }

    private fun loadData() {
        viewModel.flights.observe(viewLifecycleOwner, Observer { response ->
            when (response?.status) {
                Status.ERROR -> {
                    errorDialogShow(response.message)
                }
                Status.LOADING -> {
                    loadingProgressBar.show()
                }
                Status.SUCCESS -> {
                    initViewPager()
                    loadingProgressBar.hide()
                    if (preferences.shouldUpdateFlights()) {
                        preferences.updateFlag(false)
                        loadingProgressBar.show()
                        viewModel.refreshData()
                    }
                    response.data?.let { data -> flightAdapter.update(data) }
                }
            }
        })
    }

    private fun errorDialogShow(message: String?) {
        AlertDialog.Builder(context)
            .setTitle("Network error. Try again?")
            .setMessage(message)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                viewModel.refreshData()
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun initViewPager() {
        with(flight_view_pager) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            adapter = flightAdapter
        }
    }

    private fun ProgressBar.show() {
        this.visibility = View.VISIBLE
        flight_view_pager.visibility = View.GONE
    }

    private fun ProgressBar.hide() {
        this.visibility = View.GONE
        flight_view_pager.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = FlightFragment()
    }
}
