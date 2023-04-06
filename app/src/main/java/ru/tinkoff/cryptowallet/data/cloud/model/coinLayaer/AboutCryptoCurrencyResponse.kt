package ru.tinkoff.cryptowallet.data.cloud.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class AboutCryptoCurrencyResponse(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("crypto")
    val crypto: Map<String, AboutCrypto>,
)

data class AboutCrypto(
    @SerializedName("icon_url")
    val iconUrl: String?,
    @SerializedName("max_supply")
    val maxSupply: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_full")
    val nameFull: String?,
    @SerializedName("symbol")
    val symbol: String?,
)

class AboutCryptoCurrencyResponseDeserializer : JsonDeserializer<AboutCryptoCurrencyResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): AboutCryptoCurrencyResponse {
        if (json == null || context == null) {
            // handle error here
            throw Exception("Error")
        }
        val obj = json.asJsonObject

        val success = context.deserialize<Boolean?>(obj.get("success"), Boolean::class.java)

        val aboutCryptoSet = obj.get("crypto").asJsonObject.entrySet()

        val aboutCryptoMap = aboutCryptoSet.associate {
            val bodyAbout = it.value.asJsonObject
            val iconUrl =
                context.deserialize<String?>(bodyAbout.get("icon_url"), String::class.java)
            val maxSupply = context.deserialize<Int?>(bodyAbout.get("max_supply"), Int::class.java)
            val name = context.deserialize<String?>(bodyAbout.get("name"), String::class.java)
            val nameFull =
                context.deserialize<String?>(bodyAbout.get("name_full"), String::class.java)
            val symbol = context.deserialize<String?>(bodyAbout.get("symbol"), String::class.java)

            Pair(it.key, AboutCrypto(
                iconUrl, maxSupply, name, nameFull, symbol
            ))
        }
        return AboutCryptoCurrencyResponse(success, aboutCryptoMap)
    }
}


/*

class CurrencyDeserializer : JsonDeserializer<Currency> {
override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Currency {
if (json == null || context == null) {
    // handle error here
    throw Exception("Error")
}
val obj = json.asJsonObject

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
}
}
* */