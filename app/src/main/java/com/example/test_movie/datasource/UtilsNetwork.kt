package com.example.test_movie.datasource

class UtilsNetwork {
    companion object {
        const val SUCCESS = "SUCCESS"
        const val RUNNING = "RUNNING"
        const val FAILED = "FAILED"
    }
}

data class NetworkState private constructor(
    val status: String
) {
    companion object {
        val LOADED = NetworkState(UtilsNetwork.SUCCESS)
        val LOADING = NetworkState(UtilsNetwork.RUNNING)
        val ERROR = NetworkState(UtilsNetwork.FAILED)
    }
}