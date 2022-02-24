package com.shooter.screens

import com.shooter.Game
import com.shooter.levels.AbstractLevel
import com.shooter.logger.loggerApp

class LevelScreen(
    game: Game,
    val level: AbstractLevel
) : AbstractScreen(game) {
    override fun show() {
        logger.info { "show()" }
        super.show()
    }

    override fun render(delta: Float) = game.engine.update(delta)

    companion object {
        val logger = loggerApp<LevelScreen>()
    }
}