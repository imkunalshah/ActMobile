package com.kunal.actmobile.data.repositories

import com.kunal.actmobile.data.network.SafeApiRequest
import com.kunal.actmobile.data.network.apis.ImageApi

class ImageRepository(
    private val api: ImageApi
) : SafeApiRequest() {

}