package com.shooter.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.shooter.Game
import com.shooter.assets.CHAPTER
import com.shooter.assets.mainMenuAssetsLoad
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use

class MainMenuScreen(game: Game) : AbstractScreen(game) {
    private val image = Texture("logo.png".toInternalFile(), true).apply {
        setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }
    private val batch = SpriteBatch()

    private var player: Texture? = null

    override fun show() {
        // todo - fix
        game.assets.mainMenuAssetsLoad()
    //       .invokeOnCompletion {
//            player = game.assets.get(CHAPTER);
//        }
//        game.assets.load(CHAPTER).join()
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        if (game.assets.isLoaded(CHAPTER)) player = game.assets.get(CHAPTER)
        batch.use {
            it.draw(image, 100f, 160f)
//
            player?.apply {
                println("not null")
                it.draw(player, 100f, 160f)
            }
        }
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }
}