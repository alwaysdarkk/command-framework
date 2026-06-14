package harmony.command

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * An object that serves as a coroutine scope with a SupervisorJob.
 *
 * This object allows for launching coroutines that can be supervised,
 * meaning that child coroutines can fail without cancelling the parent scope.
 */
object CommandScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob()
}
