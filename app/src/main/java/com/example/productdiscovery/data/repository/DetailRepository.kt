package com.example.productdiscovery.data.repository

import com.example.productdiscovery.data.source.remote.DetailDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(val remote: DetailDataSource.RemoteDataSource)
    : DetailDataSource.RemoteDataSource {
}
