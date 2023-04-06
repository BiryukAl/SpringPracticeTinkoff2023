package ru.tinkoff.cryptowallet.data.cloud.model


import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class CryptoCurrencyResponse(
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("rates")
    val rates: Map<String, Double?>,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("target")
    val target: String?,
    @SerializedName("terms")
    val terms: String?,
    @SerializedName("timestamp")
    val timestamp: Int?,
)

class RatesDeserializer : JsonDeserializer<CryptoCurrencyResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): CryptoCurrencyResponse {
        if (json == null || context == null) {
            // handle error here
            throw Exception("Error")
        }
        val obj = json.asJsonObject

        val privacy = context.deserialize<String?>(obj.get("privacy"), String::class.java)
        val success = context.deserialize<Boolean?>(obj.get("success"), Boolean::class.java)
        val target = context.deserialize<String?>(obj.get("target"), String::class.java)
        val terms = context.deserialize<String?>(obj.get("terms"), String::class.java)
        val timestamp = context.deserialize<Int?>(obj.get("timestamp"), Int::class.java)

        val ratesSet = obj.get("rates").asJsonObject.entrySet()
        val ratesMap = ratesSet.associate {
            Pair(it.key, it.value.asDouble)
        }
        return CryptoCurrencyResponse(
            privacy, ratesMap, success, target, terms, timestamp
        )
    }

}

/*
        // let Gson handle the other 3 properties
        val success = context.deserialize<Boolean?>(obj.get("success"), Boolean::class.java)
        val base = context.deserialize<String?>(obj.get("base"), String::class.java)
        val date = context.deserialize<Date?>(obj.get("date"), Date::class.java)

        // create List<Rate> from the rates JsonObject
        val ratesSet = obj.get("rates").asJsonObject.entrySet()
        val ratesList = ratesSet.map {
            val code = it.key
            val value = it.value.asFloat
            Rate(code, value)
        }

        return Currency(success, base, date, ratesList)
*/