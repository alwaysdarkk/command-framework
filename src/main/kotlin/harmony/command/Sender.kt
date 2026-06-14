package harmony.command

import net.kyori.adventure.text.Component
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

/**
 * Represents a sender entity for commands, which can be either a player or the console.
 *
 * This class serves as a base for specific senders like `PlayerSender` and `ConsoleSender`.
 * Each sender has a message and display name associated with it, and can be used to check
 * whether a specific `CommandSender` is valid to perform a command.
 *
 * @property message The message that will be shown to the sender if they cannot execute the command.
 * @property display The display name of the sender, used for visual representation.
 */
open class Sender(val message: Component, val display: String) {

    companion object {
        /** Predefined instance for player-specific command senders. */
        val PLAYER get() = PlayerSender

        /** Predefined instance for console-specific command senders. */
        val CONSOLE get() = ConsoleSender

        /** Instance for both players and console senders, with a generic message. */
        val ALL = Sender(Component.empty(), "Players and Console")
    }

    /**
     * Checks if the given sender is allowed to execute a command.
     *
     * This method can be overridden by specific implementations to apply more detailed checks.
     * By default, this method allows any sender to pass.
     *
     * @param sender The sender executing the command.
     * @return `true` if the sender is allowed to execute the command, otherwise `false`.
     */
    open fun check(sender: CommandSender): Boolean {
        return true
    }

    /**
     * Creates a context that handles the execution of a command.
     *
     * This method is responsible for creating an `Context` performer which executes
     * the command based on the sender, the instructor, and any arguments passed.
     *
     * @param sender The sender executing the command.
     * @param instructor The instructor managing the command execution process.
     * @param args The arguments provided with the command.
     * @return An instance of `Context` responsible for handling the command.
     */
    open fun createContext(sender: CommandSender, instructor: Instructor, args: Array<out String>): Context {
        return DefaultContext(sender, instructor, args)
    }
}

/**
 * A specific sender implementation for players.
 *
 * This class only allows players to execute commands and will return an appropriate
 * message if the command is executed by a non-player entity.
 */
object PlayerSender : Sender("<red>Only players can run this command.".asComponent, "Players") {

    /**
     * Checks if the given sender is a player.
     *
     * @param sender The sender executing the command.
     * @return `true` if the sender is a player, otherwise `false`.
     */
    override fun check(sender: CommandSender): Boolean {
        return sender is Player
    }

    /**
     * Creates a context specific to player commands.
     *
     * @param sender The player executing the command.
     * @param instructor The instructor managing the command execution process.
     * @param args The arguments provided with the command.
     * @return A `PlayerPerformer` responsible for handling the command execution.
     */
    override fun createContext(sender: CommandSender, instructor: Instructor, args: Array<out String>): Context {
        return PlayerContext(sender, instructor, args)
    }
}

/**
 * A specific sender implementation for the console.
 *
 * This class only allows the console to execute commands and will return an appropriate
 * message if the command is executed by a non-console entity.
 */
object ConsoleSender : Sender("<red>Only console can run this command.".asComponent, "Console") {

    /**
     * Checks if the given sender is the console.
     *
     * @param sender The sender executing the command.
     * @return `true` if the sender is the console, otherwise `false`.
     */
    override fun check(sender: CommandSender): Boolean {
        return sender is ConsoleCommandSender
    }

    /**
     * Creates a context specific to console commands.
     *
     * @param sender The console executing the command.
     * @param instructor The instructor managing the command execution process.
     * @param args The arguments provided with the command.
     * @return A `ConsolePerformer` responsible for handling the command execution.
     */
    override fun createContext(sender: CommandSender, instructor: Instructor, args: Array<out String>): Context {
        return ConsoleContext(sender, instructor, args)
    }
}
