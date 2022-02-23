package com.shooter.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.shooter.Game
import com.shooter.assets.TextureAssetDefinition
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.graphics.use

val PLAYER_CHARACTER = TextureAssetDefinition("game/player_01.png")
val LOGO = TextureAssetDefinition("logo.png")

class MainMenuScreen(game: Game) : AbstractScreen(game) {
    private val batch = SpriteBatch()

    private lateinit var player: Texture
    private lateinit var logo: Texture
    private var isLoaded = false

    override fun show() {
        game.assets.load(PLAYER_CHARACTER, LOGO) {
            player = game.assets.get(PLAYER_CHARACTER)
            logo = game.assets.get(LOGO)
            isLoaded = true
        }
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        batch.use {
            if (isLoaded) {
                it.draw(logo, 100f, 120f)
                it.draw(player, 150f, 140f)
            }
        }
    }

    override fun dispose() {
        batch.disposeSafely()
    }
}