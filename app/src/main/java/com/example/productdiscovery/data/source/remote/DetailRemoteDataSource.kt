package com.example.productdiscovery.data.source.remote

import com.example.productdiscovery.data.source.remote.service.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRemoteDataSource @Inject constructor(val api: Api) : DetailDataSource.RemoteDataSource {

}