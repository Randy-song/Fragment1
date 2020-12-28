package cn.edu.fragment.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.edu.fragment.R
import cn.edu.fragment.city.City
import cn.edu.fragment.city.Main2Activity
import cn.edu.fragment.model.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main2.*

class WeacherFragment : Fragment() {

    companion object {
        fun newInstance() = WeacherFragment()
    }

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.city_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        viewModel.cities.observe(viewLifecycleOwner, Observer {
            val cities = it
            val adapter = ArrayAdapter<City>(requireActivity(), android.R.layout.simple_list_item_1, cities)
            listView.adapter = adapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val cityCode = cities[position].city_code
                val intent = Intent(requireActivity(), Main2Activity::class.java)
                intent.putExtra("city_code", cityCode)
                startActivity(intent)
            }
        })

    }

}