package com.shooter.levels

import com.badlogic.ashley.core.Engine
import com.shooter.assets.AssetsHolder

abstract class AbstractLevel {
    var isLoaded = false

    abstract fun loadLevel(assetsHolder: AssetsHolder, engine: Engine)
//    fun loadLevelAssets(assetsHolder: AssetsHolder) {}
//    fun initStaticGameScene(engine: Engine) {}
//    fun initDynamicGameScene(engine: Engine) {}
}