package com.alexkravtsov.vkpracticeone.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.alexkravtsov.vkpracticeone.domain.AppModel
import kotlinx.serialization.json.Json

val AppModelNavType = object : NavType<AppModel>(isNullableAllowed = false) {

    private val json = Json { ignoreUnknownKeys = true }

    override fun put(bundle: Bundle, key: String, value: AppModel) {
        bundle.putString(key, serializeAsValue(value))
    }

    override fun get(bundle: Bundle, key: String): AppModel? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): AppModel =
        json.decodeFromString(AppModel.serializer(), Uri.decode(value))

    override fun serializeAsValue(value: AppModel): String =
        Uri.encode(json.encodeToString(AppModel.serializer(), value))
}