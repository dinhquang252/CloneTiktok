package com.qtproduct.sunflower.model

/**
 * @author quangtran
 * @since 9/22/23
 */
data class Plant(
    val id : String,
    val name : String?,
    val createAt : Long?,
    val updateAt : Long?,
    var imgUrl : String?
)