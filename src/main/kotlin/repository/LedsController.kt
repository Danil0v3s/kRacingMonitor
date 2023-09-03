package repository

import com.diozero.ws281xj.PixelAnimations
import com.diozero.ws281xj.PixelColour
import com.diozero.ws281xj.rpiws281x.WS281x
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object LedsController {

    private const val GPIO_PIN = 10
    private const val BRIGHTNESS = 90
    private const val NUM_LEDS = 18

    private val controller by lazy {
        try {
            WS281x(GPIO_PIN, BRIGHTNESS, NUM_LEDS)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private var firstRPM: Int = 0
    private var shiftRPM: Int = 0
    private var lastRPM: Int = 0
    private var blinkRPM: Int = 0

    private val BLUE = PixelColour.createColourRGB(0, 0, 255)
    private val GREEN = PixelColour.createColourRGB(0, 255, 0)
    private val YELLOW = PixelColour.createColourRGB(255, 255, 0)
    private val RED = PixelColour.createColourRGB(255, 0, 0)

    fun setRevOptions(firstRPM: String, shiftRPM: String, lastRPM: String, blinkRPM: String) {
        this.firstRPM = firstRPM.toFloat().toInt()
        this.shiftRPM = shiftRPM.toFloat().toInt()
        this.lastRPM = lastRPM.toFloat().toInt()
        this.blinkRPM = blinkRPM.toFloat().toInt()
    }

    suspend fun updateRevs(current: Int) = CoroutineScope(Dispatchers.IO).launch {
        controller?.apply {
            when {
                current < firstRPM -> {
                    setPixelColour(0, BLUE)
                    setPixelColour(1, BLUE)
                    setPixelColour(2, BLUE)
                    setPixelColour(3, BLUE)
                    render()
                }
                current >= blinkRPM -> PixelAnimations.colourWipe(controller, RED, 10)
            }
        }
    }
}