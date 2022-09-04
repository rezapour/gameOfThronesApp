package me.rezapour.gameofthrones.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HouseNetworkEntity(
    @Expose @SerializedName("url") var url: String,
    @Expose @SerializedName("name") var name: String,
    @Expose @SerializedName("region") var region: String,
    @Expose @SerializedName("coatOfArms") var coatOfArms: String,
    @Expose @SerializedName("words") var words: String,
    @Expose @SerializedName("titles") var titles: ArrayList<String>,
    @Expose @SerializedName("seats") var seats: ArrayList<String>,
    @Expose @SerializedName("currentLord") var currentLord: String,
    @Expose @SerializedName("heir") var heir: String,
    @Expose @SerializedName("overlord") var overlord: String,
    @Expose @SerializedName("founded") var founded: String,
    @Expose @SerializedName("founder") var founder: String,
    @Expose @SerializedName("diedOut") var diedOut: String,
    @Expose @SerializedName("ancestralWeapons") var ancestralWeapons: ArrayList<String>,
    @Expose @SerializedName("cadetBranches") var cadetBranches: ArrayList<String>,
    @Expose @SerializedName("swornMembers") var swornMembers: ArrayList<String>
)