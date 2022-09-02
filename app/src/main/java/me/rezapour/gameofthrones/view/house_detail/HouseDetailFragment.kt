package me.rezapour.gameofthrones.view.house_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.rezapour.gameofthrones.R
import me.rezapour.gameofthrones.databinding.FragmentHouseDetailBinding

@AndroidEntryPoint
class HouseDetailFragment : Fragment() {

    private var _binding: FragmentHouseDetailBinding? = null
    private val binding get() = _binding!!
    val args: HouseDetailFragmentArgs by navArgs()


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
        val name = args.house.name
        Toast.makeText(context, name, Toast.LENGTH_LONG).show()
    }

}