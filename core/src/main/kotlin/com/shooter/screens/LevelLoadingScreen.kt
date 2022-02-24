package com.shooter.screens

import com.badlogic.gdx.graphics.Texture
import com.shooter.Game
import com.shooter.assets.TextureAssetDefinition
import com.shooter.levels.AbstractLevel
import com.shooter.logger.loggerApp
import kotlinx.coroutines.launch
import ktx.app.clearScreen
import ktx.async.KtxAsync

val LEVEL_LOADING_SCREEN_BACKGROUND = TextureAssetDefinition("loading_scree_stub.png")

/**
 * todo - сделать UI что идёт загрузка уровня + "нажмите space, что бы продолжить".
 */
class LevelLoadingScreen(
    game: Game,
    private val level: AbstractLevel
) : AbstractScreen(game) {
    private lateinit var loadingLevelScreenTexture: Texture

    override fun show() {
        logger.info { "show()" }
        KtxAsync.launch { level.loadLevel(game.assets, game.engine) }
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        if (level.isLoaded) {
            game.addScreen(LevelScreen(game, level))
            game.setScreen<LevelScreen>()
        }
    }

    companion object {
        val logger = loggerApp<LevelLoadingScreen>()
    }
}