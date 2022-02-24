package com.shooter.screens

import com.shooter.Game
import ktx.app.KtxScreen

abstract class AbstractScreen(
    val game: Game
) : KtxScreen {

    override fun resize(width: Int, height: Int) {
        game.stage.viewport.update(width, height, true)
    }
}