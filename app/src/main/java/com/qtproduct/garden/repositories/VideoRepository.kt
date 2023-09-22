package com.qtproduct.garden.repositories

import com.nhatvm.toptop.R
import javax.inject.Inject

/**
 * @author quangtran
 * @since 9/15/23
 */
class VideoRepository @Inject constructor() {
    private val videos = listOf(
        R.raw.test,
        R.raw.test2,
        R.raw.test3,
        R.raw.test4,
    )

    fun getVideos() = videos
    fun getVideo() = videos.random()
}