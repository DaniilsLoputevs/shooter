package com.shooter.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.shooter.Game
import com.shooter.levels.Arena
import com.shooter.logger.loggerApp
import com.shooter.ui.FONT
import com.shooter.ui.WHITE_64_STYLE
import com.shooter.ui.drawUI
import com.shooter.ui.initUIDefaultSkin
import ktx.actors.onClick
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.stack
import ktx.scene2d.table


class MainMenuScreen(game: Game) : AbstractScreen(game) {
    private lateinit var loadingLabel: Label
    private lateinit var touchToBeginLabel: Label
    private var isAssetsLoaded = false

    override fun show() {
        logger.info { "show()" }
        game.assets.load(FONT) {
            isAssetsLoaded = true
            initUIDefaultSkin(game.assets)
            showLoadingUI()
            loadingLabel.color.a = 0f
            touchToBeginLabel.color.a = 1f
            logger.info { "assets loaded!" }
        }
    }

    override fun render(delta: Float) {
        game.stage.drawUI()
    }


    private fun showLoadingUI() {
        game.stage.run {
            clear()
            actors {
                table {
                    stack {
                        loadingLabel = label("Loading...", WHITE_64_STYLE) {
                            setAlignment(Align.center)
                        }
                        touchToBeginLabel = label("Touch to start...", WHITE_64_STYLE) {
                            setAlignment(Align.center)
                            color.a = 0f
                            onClick {
                                if (isAssetsLoaded) {
                                    game.addScreen(LevelLoadingScreen(game, Arena()))
                                    game.setScreen<LevelLoadingScreen>()
                                }
                            }
                        }
                    }
                    setFillParent(true)
                    pack()
                }
            }
            isDebugAll = true
        }
    }

    companion object {
        val logger = loggerApp<MainMenuScreen>()
    }
}