package me.rezapour.gameofthrones.view.house_detail

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.rezapour.gameofthrones.R
import me.rezapour.gameofthrones.databinding.FragmentHouseDetailBinding
import me.rezapour.gameofthrones.model.charecter.RequestData
import me.rezapour.gameofthrones.model.charecter.UiWidget
import me.rezapour.gameofthrones.model.charecter.UrlResponse
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.utils.DataState
import me.rezapour.gameofthrones.viewmodel.HouseDetailViewModel

@AndroidEntryPoint
class HouseDetailFragment : Fragment() {

    private var _binding: FragmentHouseDetailBinding? = null
    private val binding get() = _binding!!
    private val args: HouseDetailFragmentArgs by navArgs()
    private lateinit var house: HouseDomain
    private val viewModel: HouseDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentHouseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        house = args.house
        setupUi()
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.swornMembersDataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                DataState.DefaultError -> onError(R.string.error_default_message)
                is DataState.Error -> onError(dataState.messageId)
                DataState.Loading -> loading(true)
                is DataState.Success -> onSuccess(dataState.data)
            }
        }
    }

    private fun loading(isShow: Boolean) {
        binding.progressBar.visibility = if (isShow)
            View.VISIBLE
        else
            View.INVISIBLE
    }

    private fun setupUi() {
        binding.txtDetailHouseName.text = house.name
        binding.txtHouseDetailRegion.text = house.region
        binding.txtHouseDetailCoatOfArms.text = house.coatOfArms
        addList(binding.txtHouseDetailTitle, house.titles)
        addList(binding.txtHouseDetailSeats, house.seats)
        addList(binding.txtHouseDetailWeapons, house.ancestralWeapons)
        viewModel.loadUrls(makeRequest())
    }

    private fun addList(view: TextView, items: List<String>) {
        items.forEach { item ->
            view.append("$item \n")
        }
    }

    private fun addText(view: TextView, name: String) {
        view.append("$name\n")
    }

    private fun makeRequest(): List<RequestData> {
        val requests = ArrayList<RequestData>()
        requests.add(makeRequest(house.currentLord, UiWidget.CURRENT_LORD))

        requests.add(makeRequest(house.founder, UiWidget.FOUNDER))

        requests.addAll(house.swornMembers.map { url ->
            makeRequest(
                url,
                UiWidget.SWORN
            )
        })

        return requests
    }

    private fun makeRequest(url: String, uiWidget: UiWidget) = RequestData(url, uiWidget)

    private fun onSuccess(items: List<UrlResponse>) {
        loading(false)
        items.forEach { urlResponse -> setValues(urlResponse) }
    }


    private fun onError(messageId: Int) {
        loading(false)
        showSnackBar(messageId = messageId) {
            viewModel.loadUrls(makeRequest())
        }
    }

    private fun setValues(urlResponse: UrlResponse) {
        when (urlResponse.uiWidget) {
            UiWidget.CURRENT_LORD -> addText(binding.txtHouseDetailCurrentLord, urlResponse.name)
            UiWidget.FOUNDER -> addText(binding.txtHouseDetailFounder, urlResponse.name)
            UiWidget.SWORN -> addText(binding.txtHouseDetailswornMembers, urlResponse.name)
        }
    }


    private fun showSnackBar(messageId: Int, retry: () -> Unit) {
        Snackbar.make(binding.houseDetailLayout, messageId, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                retry()
            }
            .show();
    }


}