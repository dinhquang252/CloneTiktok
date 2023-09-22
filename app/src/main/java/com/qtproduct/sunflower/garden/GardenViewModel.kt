package com.qtproduct.sunflower.garden

import androidx.lifecycle.ViewModel
import com.qtproduct.sunflower.usecase.GetListPlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author quangtran
 * @since 9/22/23
 */

@HiltViewModel
class GardenViewModel @Inject constructor(private val getListPlantUseCase: GetListPlantUseCase) : ViewModel(){
    val plants = getListPlantUseCase.plants
}