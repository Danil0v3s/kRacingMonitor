package repository

import com.diozero.ws281xj.PixelAnimations
import com.diozero.ws281xj.PixelColour
import com.diozero.ws281xj.rpiws281x.WS281x
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

object LedsController {

    private const val GPIO_PIN = 10
    private const val BRIGHTNESS = 5
    private const val NUM_LEDS = 18

    private val controller by lazy {
        try {
            WS281x(GPIO_PIN, BRIGHTNESS, NUM_LEDS)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private val revsChannel = Channel<Int>(Channel.BUFFERED)

    private var idleRPM: Float = -1f
    private var firstRPM: Float = -1f
    private var shiftRPM: Float = -1f
    private var lastRPM: Float = -1f
    private var blinkRPM: Float = -1f

    private val BLUE = PixelColour.createColourRGB(0, 0, 255)
    private val GREEN = PixelColour.createColourRGB(0, 255, 0)
    private val YELLOW = PixelColour.createColourRGB(255, 255, 0)
    private val RED = PixelColour.createColourRGB(255, 0, 0)

    private val blueRange = listOf(0, 1, 2)
    private val greenRange = listOf(3, 4, 5, 6)
    private val yellowRange = listOf(7, 8, 9, 10)
    private val redRange = listOf(11, 12, 13)

    init {
        observeRevs()
    }

    fun setIdle() {
        controller?.also { PixelAnimations.demo(it) }
    }

    fun setRevOptions(idleRPM: String, firstRPM: String, shiftRPM: String, lastRPM: String, blinkRPM: String) {
        this.idleRPM = idleRPM.toFloat()
        this.firstRPM = firstRPM.toFloat()
        this.shiftRPM = shiftRPM.toFloat()
        this.lastRPM = lastRPM.toFloat()
        this.blinkRPM = blinkRPM.toFloat()
    }

    fun updateRevs(current: Int) {
        revsChannel.trySend(current)
    }

    private fun observeRevs() = CoroutineScope(Dispatchers.IO).launch {
        revsChannel.receiveAsFlow().collect { current ->
            // not initialized yet
            if (firstRPM < 0) return@collect

            if (current >= blinkRPM) {
                for (i in 0 until NUM_LEDS - 1) {
                    controller?.setPixelColour(i, RED)
                }
            }

            val percentagePerLed = 100f / NUM_LEDS
            var remainingPercentage = map(current, idleRPM, lastRPM, 0f, 100f)

            controller?.apply {
                var ledIndex = 0
                while (remainingPercentage > 0f && ledIndex < NUM_LEDS - 1) {
                    val color = getColorForPixel(ledIndex)
                    setPixelColour(ledIndex, color)
                    ledIndex += 1
                    remainingPercentage -= percentagePerLed
                }

                for (i in ledIndex until NUM_LEDS - 1) {
                    setPixelColour(i, 0)
                }
                render()
            }
        }
    }

    private fun getColorForPixel(pixel: Int): Int {
        return when {
            blueRange.contains(pixel) -> BLUE
            greenRange.contains(pixel) -> GREEN
            yellowRange.contains(pixel) -> YELLOW
            redRange.contains(pixel) -> RED
            else -> 0
        }
    }

    private fun map(value: Int, sourceMin: Float, sourceMax: Float, targetMin: Float, targetMax: Float): Float {
        return (value - sourceMin) * (targetMax - targetMin) / (sourceMax - sourceMin) + targetMin
    }
}