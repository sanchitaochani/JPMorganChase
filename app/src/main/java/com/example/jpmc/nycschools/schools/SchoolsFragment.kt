package com.example.jpmc.nycschools.schools

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jpmc.nycschools.MainApplication
import com.example.jpmc.nycschools.R
import com.example.jpmc.nycschools.data.ResponseWrapper
import com.example.jpmc.nycschools.data.School
import com.example.jpmc.nycschools.detail.DetailFragment
import com.example.jpmc.nycschools.viewmodel.SchoolsViewModel
import com.example.jpmc.nycschools.viewmodel.ViewModelFactory
import javax.inject.Inject

class SchoolsFragment : Fragment() {
    @Inject
   lateinit var viewModelFactory: ViewModelFactory<SchoolsViewModel>
   private val viewModel by lazy {
       viewModelFactory.get<SchoolsViewModel>(requireActivity())
   }

    private lateinit var adapter: SchoolsListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorLayout: View
    private lateinit var errorMsg: TextView
    private lateinit var loadingIndicator: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainApplication.getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragments_school_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
        setupRecyclerView()
        setObservers()
    }

    private fun setupRecyclerView() {
        adapter = SchoolsListAdapter() {
            handleCallback(it)
        }
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun handleCallback(school: School) {
        val action = R.id.action_schoolsFragment_to_detailFragment
        val bundle = Bundle()
        bundle.putParcelable(DetailFragment.KEY, school)
        findNavController().navigate(action, bundle)
    }

    private fun setObservers() {
        viewModel.schoolsListResult.observe(viewLifecycleOwner) {result ->
            when(result) {
                is ResponseWrapper.Error -> showErrorScreen(result.errorMsg)
                is ResponseWrapper.Success -> showData(result.schoolData)
                else -> showLoadingScreen()
            }
        }
    }

    private fun showErrorScreen(message: String?) {
        recyclerView.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        loadingIndicator.visibility = View.GONE
        errorMsg.text = message
    }

    private fun showLoadingScreen() {
        recyclerView.visibility = View.GONE
        errorLayout.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
    }

    private fun showData(data: List<School>?) {
        data?.let { adapter.setItems(it) }
        recyclerView.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
        loadingIndicator.visibility = View.GONE
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        errorLayout = view.findViewById(R.id.errorLayout)
        errorMsg = view.findViewById(R.id.errorDesc)
        loadingIndicator = view.findViewById(R.id.loadingIndicator)
        // Toast.makeText(context, R.string.click_to_see, Toast.LENGTH_LONG).show()
    }


}