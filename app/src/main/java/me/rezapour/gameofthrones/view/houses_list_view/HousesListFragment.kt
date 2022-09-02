package me.rezapour.gameofthrones.view.houses_list_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.rezapour.gameofthrones.R
import me.rezapour.gameofthrones.databinding.FragmentHousesListBinding
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.utils.DataState
import me.rezapour.gameofthrones.viewmodel.HousesListViewModel

@AndroidEntryPoint
class HousesListFragment : Fragment() {

    private var _binding: FragmentHousesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HousesListViewModel by viewModels()
    private lateinit var swiper: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HousesListAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHousesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupFragment()
    }

    private fun setupFragment() {
        setupUi()
        subscribeToViewModel()
        getData()
    }

    private fun setupUi() {
        recyclerView = binding.houseListRecyclerView
        adapter = HousesListAdapter(ArrayList()) { house ->
            gotoDetailFragment(house)
        }
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)



        swiper = binding.swiperLayout
        swiper.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        viewModel.getHouses()
    }

    private fun subscribeToViewModel() {
        viewModel.housesDataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                DataState.DefaultError -> onError(R.string.error_default_message)
                is DataState.Error -> onError(dataState.messageId)
                DataState.Loading -> loading(true)
                is DataState.Success -> onSuccess(dataState.data)
            }
        }
    }

    private fun onSuccess(items: List<HouseDomain>) {
        loading(false)
        adapter.addItem(items)
        adapter.notifyDataSetChanged()
    }

    private fun onError(messageId: Int) {
        loading(false)
        showSnackBar(messageId = messageId) {
            getData()
        }
    }

    private fun loading(isShowing: Boolean) {
        showSwiper(isShowing)
    }

    private fun showSwiper(isRefreshing: Boolean) {
        swiper.isRefreshing = isRefreshing
    }

    private fun showSnackBar(messageId: Int, retry: () -> Unit) {
        Snackbar.make(binding.coordinatorLayout, messageId, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                retry()
            }
            .show();
    }

    private fun gotoDetailFragment(house: HouseDomain) {
        val directions =
            HousesListFragmentDirections.actionHousesListFragmentToHouseDetailFragment(house)
        navController.navigate(directions)
    }
}