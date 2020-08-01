package net.snatchdreams.hiltdaggerplayground.model

//This is the DomainModel which will never change
data class Blog(
    var id: Int,
    var title: String,
    var body: String,
    var image: String,
    var category: String
)