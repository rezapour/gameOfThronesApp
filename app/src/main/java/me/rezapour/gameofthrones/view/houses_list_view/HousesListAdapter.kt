package me.rezapour.gameofthrones.view.houses_list_view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import me.rezapour.gameofthrones.databinding.RowHouseDetailBinding
import me.rezapour.gameofthrones.model.house.HouseDomain

class HousesListAdapter(
    val houseList: ArrayList<HouseDomain>,
    val onClick: (house: HouseDomain) -> Unit,
    val onButtomRiched: () -> Unit
) :
    RecyclerView.Adapter<HousesListAdapter.HouseDetailViewHolder>() {

    inner class HouseDetailViewHolder(binding: RowHouseDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val houseName: TextView = binding.txtHouseName
        private val region: TextView = binding.txtRegion
        private val coatOfArms: TextView = binding.txtCoatOfArms
        private val row: ConstraintLayout = binding.rowLayout
        fun onBind(house: HouseDomain) {
            houseName.text = house.name
            region.text = house.region
            coatOfArms.text = house.coatOfArms

            row.setOnClickListener {
                onClick(house)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseDetailViewHolder {
        val binding =
            RowHouseDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HouseDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseDetailViewHolder, position: Int) {
        holder.onBind(houseList[position])
        if (position == houseList.size - 1)
            onButtomRiched()
    }

    override fun getItemCount() = houseList.size

    fun addItem(items: List<HouseDomain>) {
        houseList.clear()
        houseList.addAll(items)
    }
}