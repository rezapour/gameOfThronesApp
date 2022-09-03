package me.rezapour.gameofthrones.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterNetworkEntity(
    @Expose @SerializedName("url") var url: String,
    @Expose @SerializedName("name") var name: String,
    @Expose @SerializedName("gender") var gender: String,
    @Expose @SerializedName("culture") var culture: String,
    @Expose @SerializedName("born") var born: String,
    @Expose @SerializedName("died") var died: String,
    @Expose @SerializedName("titles") var titles: ArrayList<String>,
    @Expose @SerializedName("aliases") var aliases: ArrayList<String>,
    @Expose @SerializedName("father") var father: String,
    @Expose @SerializedName("mother") var mother: String,
    @Expose @SerializedName("spouse") var spouse: String,
    @Expose @SerializedName("allegiances") var allegiances: ArrayList<String>,
    @Expose @SerializedName("books") var books: ArrayList<String>,
    @Expose @SerializedName("povBooks") var povBooks: ArrayList<String>,
    @Expose @SerializedName("tvSeries") var tvSeries: ArrayList<String>,
    @Expose @SerializedName("playedBy") var playedBy: ArrayList<String>
)