@file:JvmName("Lwjgl3Launcher")

package com.shooter.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.shooter.Game

/** Launches the desktop (LWJGL3) application.
 *
 * aspect ration: 16:9 (width X height)
 *
 * 2160p: 3840 x 2160
 * 1440p: 2560 x 1440
 * 1080p: 1920 x 1080
 * 720p: 1280 x 720
 * 480p: 854 x 480
 * 360p: 640 x 360
 * 240p: 426 x 240
 *
 * todo - TriggerSystem : name, dynamic, scope, ?(upd frequency), addTrigger(), delTrigger()
 * todo - AssetHolder : uuid(name), group, ?scope, disposeAllAssets()
 * todo - abstract layers:
 *  1 - static(immutable) scene
 *  2 - dynamic scene
 *  3 -
 * todo - InputSystem -> addTriggers
 * todo - yaml config (remove file object Config)
 * todo - оставить или убрать ComponentAPI? [TransformAPI] [TransformComp]
 * todo - нормальное оформление в коде UI
 * todo -
 * */
fun main() {
    Lwjgl3Application(Game(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("shooter")
        setWindowedMode(854, 480)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
