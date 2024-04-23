/* RK35xx EPD Controller for Android devices
 *
 * Interface for rk35xx based devices, like the Xiaomi 7 Reader, Xiaomi Duokan Pro II, Xiaomi Ereader Pro II...
 *
 * Driver is doing a full-screen refresh using mode configured in System UI (EPD_PART_GC16 for Clear mode, EPD_DU for Balanced mode & Quick mode).
 *
 * Other known modes:
 *
 * val EPD_A2 = "12"
 * val EPD_A2_DITHER = "13"
 * val EPD_A2_ENTER = "16"
 * val EPD_AUTO_DU = "22"
 * val EPD_AUTO_DU4 = "23"
 * val EPD_DU4 = "15"
 * val EPD_DU = "14" // Balanced mode & Quick mode
 *
 * val EPD_FULL_GC16 = "2"
 * val EPD_FULL_GCC16 = "6"
 * val EPD_FULL_GL16 = "3"
 * val EPD_FULL_GLD16 = "5"
 * val EPD_FULL_GLR16 = "4"
 * val EPD_OVERLAY = "1"
 *
 * val EPD_PART_GCC16 = "11"
 * val EPD_PART_GL16 = "8"
 * val EPD_PART_GLD16 = "10"
 * val EPD_PART_GLR16 = "9"
 * val EPD_PART_GC16 = "7" // Clear mode
 *
 * val EPD_RESET = "17"
 * val EPD_AUTO = "0"
 * val EPD_NULL = "-1"
 *
 */
package org.koreader.launcher.device.epd.rockchip

import android.util.Log
import android.view.View
import java.lang.reflect.Modifier
import java.util.Locale

abstract class RK35xxEPDController {
    companion object {
        private const val TAG = "EPD"

        const val EPD_AUTO = 0

        fun requestEpdMode(view: View): Boolean {
            return try {
                val einkManagerClass = Class.forName("android.os.EinkManager")
                val einkManager: Any? = view.context.getSystemService("eink")

                val sendOneFullFrame = einkManagerClass.getDeclaredMethod("sendOneFullFrame")
                sendOneFullFrame.invoke(einkManager)

//                val setMode = einkManagerClass.getDeclaredMethod("setMode", String::class.java)
//                setMode.invoke(einkManager, "12")

                true
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                Log.e(TAG, e.stackTraceToString())
                false
            }
        }

    }
}
