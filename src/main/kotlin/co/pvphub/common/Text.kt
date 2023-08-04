package co.pvphub.common

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

private val serializer = LegacyComponentSerializer.builder()
    .character('&')
    .hexCharacter('#')
    .hexColors()
    .build()

private val oldSerializer = LegacyComponentSerializer.builder().build()

private val gsonSerializer = GsonComponentSerializer.builder().build()

fun Component.json() = gsonSerializer.serialize(this)

fun Component.legacy() = serializer.serialize(this)

fun String.jsonToComponent() = gsonSerializer.deserialize(this)

fun String.component() = serializer.deserialize(this)
val String.component: Component
    get() = component()

fun String.legacyToComponent() = oldSerializer.deserialize(this)

fun String.applyBrandingAndComponent() = applyBranding().component()
val String.formattedComponent: Component
    get() = applyBrandingAndComponent()

operator fun String.not() = formattedComponent

operator fun Component.plus(other: Component) = this.append(other)