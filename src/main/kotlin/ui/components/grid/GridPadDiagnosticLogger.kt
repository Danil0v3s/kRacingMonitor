package ui.components.grid

/**
 * Used to send diagnostic signals.
 * Default implementation is `null` and doing nothing, to handle need to set custom
 * [skippingItemListener].
 */
public object GridPadDiagnosticLogger {

    /**
     * Called when item has been skipped due to out the grid bounds.
     */
    public var skippingItemListener: ((message: String) -> Unit)? = null

    /**
     * Send skipped item signal
     *
     * @param message detailed message
     */
    internal fun onItemSkipped(message: () -> String) {
        skippingItemListener?.invoke(message())
    }
}