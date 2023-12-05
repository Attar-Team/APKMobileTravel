package com.example.rahmatantravel.api.customerResponse

import com.google.gson.annotations.SerializedName

class APICustomer (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<DataCustomerResponse>,
)
