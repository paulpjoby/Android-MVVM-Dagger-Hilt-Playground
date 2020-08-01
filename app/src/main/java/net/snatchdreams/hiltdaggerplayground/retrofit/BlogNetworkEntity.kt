package net.snatchdreams.hiltdaggerplayground.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//EntityModel Specific Purpose for Retrofit
class BlogNetworkEntity(
    @SerializedName(value = "pk")
    @Expose
    var id: Int,

    @SerializedName(value = "title")
    @Expose
    var title: String,

    @SerializedName(value = "body")
    @Expose
    var body: String,

    @SerializedName(value = "category")
    @Expose
    var category: String,

    @SerializedName(value = "image")
    @Expose
    var image: String
){
}