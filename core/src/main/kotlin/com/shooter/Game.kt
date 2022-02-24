package com.shooter

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.shooter.Config.V_WORLD_HEIGHT_UNITS
import com.shooter.Config.V_WORLD_WIDTH_UNITS
import com.shooter.ashley.systems.DebugSystem
import com.shooter.ashley.systems.RenderSystem
import com.shooter.assets.AssetsHolder
import com.shooter.screens.AbstractScreen
import com.shooter.screens.MainMenuScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen


class Game : KtxGame<KtxScreen>() {
    val stage: Stage by lazy {
        Stage(FitViewport(V_WORLD_WIDTH_UNITS, V_WORLD_HEIGHT_UNITS))
            .apply { Gdx.input.inputProcessor = this }
    }
    val assets: AssetsHolder by lazy { AssetsHolder() }
    val engine: Engine by lazy { initEngine() }

    override fun create() {
        addScreen(MainMenuScreen(this))
        setScreen<MainMenuScreen>()
    }

    private fun initEngine(): Engine {
        return PooledEngine().apply {
            addSystem(DebugSystem())
//            addSystem(StartGameSystem().apply { setProcessing(false) })
//            addSystem(ScreenInputSystem(gameViewport).apply { setProcessing(false) })
            addSystem(RenderSystem(stage))
        }
    }

}

// todo - fix it
@Deprecated("Blyat! not working! fix this shortcut")
fun Game.addAndSetScreen(type: AbstractScreen) {
    addScreen(type)
    setScreen(type.javaClass)
}