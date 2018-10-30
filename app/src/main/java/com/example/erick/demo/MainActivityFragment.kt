package com.example.erick.demo

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), DemoView {
    val presenter = DemoPresenter(this)
    lateinit var recyclerViewAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onResume() {
        super.onResume()
        recyclerViewAdapter = EventAdapter()
        recyclerEvent.adapter = recyclerViewAdapter

        btnPickDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val year = currentDate.get(Calendar.YEAR)
            val month = currentDate.get(Calendar.MONTH)
            val day = currentDate.get(Calendar.DAY_OF_MONTH)

            val mDatePicker = DatePickerDialog(
                activity!!,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    presenter.fetchNasaApiData(
                        year,
                        month,
                        day
                    )
                },
                year,
                month,
                day
            )
            mDatePicker.show()
        }
    }

    override fun showError() {
        Toast.makeText(context, "error :(", Toast.LENGTH_SHORT).show()
    }

    override fun hideText() {
        textDate.visibility = View.GONE
    }

    override fun showText(currentDate: String) {
        textDate.visibility = View.VISIBLE
        textDate.text = currentDate
    }

    override fun showCard(event: Event) {
        recyclerViewAdapter.updateEvent(event)
    }
}
