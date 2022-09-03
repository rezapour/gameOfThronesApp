package me.rezapour.gameofthrones.model.charecter

data class CharacterDomain(
    var url: String,
    var name: String,
    var gender: String,
    var culture: String,
    var born: String,
    var died: String,
    var titles: ArrayList<String>,
    var aliases: ArrayList<String>,
    var father: String,
    var mother: String,
    var spouse: String,
    var allegiances: ArrayList<String>,
    var books: ArrayList<String>,
    var povBooks: ArrayList<String>,
    var tvSeries: ArrayList<String>,
    var playedBy: ArrayList<String>
)