package harmony.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage

private val miniMessage: MiniMessage = MiniMessage.miniMessage()

val String.asComponent: Component
    get() =
        miniMessage.deserialize(this).decoration(TextDecoration.ITALIC, false)
val Component.serialize: String
    get() =
        miniMessage.serialize(this)
