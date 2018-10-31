package com.example.erick.demo

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*


class MainActivityFragment : Fragment(), DemoView {

    private val presenter = DemoPresenter(this)
    lateinit var recyclerViewAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onResume() {
        super.onResume()
        setupUi()
    }

    private fun setupUi() {
        recyclerViewAdapter = EventAdapter()
        recyclerEvent.adapter = recyclerViewAdapter

        textPickDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val year = currentDate.get(Calendar.YEAR)
            val month = currentDate.get(Calendar.MONTH)
            val day = currentDate.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                activity!!,
                DatePickerDialog.OnDateSetListener { _, y, m, d ->
                    presenter.fetchNasaApiData(y, m, d)
                },
                year, month, day
            ).show()
        }
    }

    override fun showError() {
        Toast.makeText(context, "Error fetching data :(", Toast.LENGTH_SHORT).show()
    }

    override fun hideText() {
        textDate.visibility = View.GONE
    }

    override fun showDate(currentDate: String) {
        textDate.visibility = View.VISIBLE
        textDate.text = currentDate

        textPickDate.setText(currentDate)
    }

    override fun showCard(event: Event) {
        recyclerViewAdapter.updateEvent(event)
    }

    override fun showLoadingView() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        progressBar.visibility = View.GONE
    }
}
