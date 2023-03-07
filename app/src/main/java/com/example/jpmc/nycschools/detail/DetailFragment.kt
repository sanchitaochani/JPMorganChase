package com.example.jpmc.nycschools.detail

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.jpmc.nycschools.MainApplication
import com.example.jpmc.nycschools.R
import com.example.jpmc.nycschools.data.School
import com.example.jpmc.nycschools.viewmodel.SchoolsViewModel
import com.example.jpmc.nycschools.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Displays the detail screen of a school.
 * Right now this fragment doesn't have proper
 * loading and error states.
 * Would love to build a better UI for this.
 */
class DetailFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SchoolsViewModel>
    private val viewModel by lazy {
        viewModelFactory.get<SchoolsViewModel>(requireActivity())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainApplication.getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragments_school_detail, container, false)
    }

    /**
     * Given more time, I would like to handle this
     * for versions below 33.
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val school = arguments?.getParcelable(KEY, School::class.java)
        school?.let {
            viewModel.getSatScores(it)
        }
        setObservers(view)
    }

    private fun setObservers(view: View) {
        viewModel.detailScreenUiModel.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.schoolName).text = it.name
            view.findViewById<TextView>(R.id.schoolDescription).text = it.description
            view.findViewById<TextView>(R.id.numOfTestTakers).text = it.numOfTestTakers
            view.findViewById<TextView>(R.id.readingScore).text = it.readingScore
            view.findViewById<TextView>(R.id.mathScore).text = it.mathScore
            view.findViewById<TextView>(R.id.writingScore).text = it.writingScore
            setSchoolDetails(view, it.location, it.website, it.activities, it.grades)
        }
    }

    private fun setSchoolDetails(
        view: View,
        location: String?,
        website: String?,
        activities: String?,
        grades: String?
    ) {
        if (!location.isNullOrBlank()) {
            val formattedLocation = location.replace("\\(.*?\\)".toRegex(),"")
            view.findViewById<TextView>(R.id.schoolLocation).text = getString(R.string.location, formattedLocation)
        } else {
            view.findViewById<TextView>(R.id.schoolLocation).visibility = View.GONE
        }
        if (!grades.isNullOrBlank()) {
            view.findViewById<TextView>(R.id.schoolGrades).text = grades
        } else {
            view.findViewById<TextView>(R.id.schoolGrades).visibility = View.GONE
            view.findViewById<TextView>(R.id.schoolGradesIcon).visibility = View.GONE
        }
        if (!website.isNullOrBlank()) {
            view.findViewById<TextView>(R.id.schoolWebsite).text = getString(R.string.website, website)
        } else {
            view.findViewById<TextView>(R.id.schoolWebsite).visibility = View.GONE
        }
        if (!activities.isNullOrBlank()) {
            view.findViewById<TextView>(R.id.schoolActivities).text = getString(R.string.extra_curr_activities, activities)
        } else {
            view.findViewById<TextView>(R.id.schoolActivities).visibility = View.GONE
            view.findViewById<TextView>(R.id.extraCurrActivities).visibility = View.GONE
        }
    }


    companion object {
        const val KEY = "Detail Fragment object key"
    }
}