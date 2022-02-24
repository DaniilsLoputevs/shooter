package com.shooter.ui

import com.badlogic.gdx.scenes.scene2d.Stage

fun Stage.drawUI() = run {
    viewport.apply()
    act()
    draw()
}
