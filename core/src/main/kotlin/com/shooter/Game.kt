package com.shooter

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.shooter.Config.V_WORLD_HEIGHT_UNITS
import com.shooter.Config.V_WORLD_WIDTH_UNITS
import com.shooter.assets.AssetsHolder
import com.shooter.screens.MainMenuScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen


class Game : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(MainMenuScreen(this))
        setScreen<MainMenuScreen>()
    }

    val gameViewport: Viewport by lazy {
        FitViewport(V_WORLD_WIDTH_UNITS, V_WORLD_HEIGHT_UNITS)
    }
    val stage: Stage by lazy {
        val result = Stage(gameViewport)
        Gdx.input.inputProcessor = result
        result
    }

    //    val stage: Stage by lazy { initStage() }
    val assets: AssetsHolder by lazy { AssetsHolder() }
    val engine: Engine by lazy { initEngine() }

    private fun initEngine(): Engine {
        return PooledEngine().apply {
//            addSystem(DebugSystem())
//            addSystem(StartGameSystem().apply { setProcessing(false) })
//            addSystem(ScreenInputSystem(gameViewport).apply { setProcessing(false) })
//            addSystem(MainStackSystem().apply { setProcessing(false) })
//            addSystem(DragCardSystem().apply { setProcessing(false) })
//            addSystem(StackBindingSystem().apply { setProcessing(false) })
//            addSystem(ReturnCardsSystem().apply { setProcessing(false) })
//            addSystem(WinStackSystem().apply { setProcessing(false) })
//            addSystem(CardPositionSystem().apply { setProcessing(false) })
//            addSystem(TaskExecutorSystem().apply { setProcessing(false) })
//            addSystem(CalculateIsTouchableSystem().apply { setProcessing(false) })
//            addSystem(RenderSystem(stage, gameViewport))
        }
    }

}