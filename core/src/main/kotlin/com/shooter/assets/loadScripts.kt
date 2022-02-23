package com.shooter.assets

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ktx.async.KtxAsync

val CHAPTER = TextureAssetDefinition("game/player_01.png")

public fun AssetsHolder.mainMenuAssetsLoad() : Job {
    println("load $CHAPTER")

    return KtxAsync.launch {
        load(CHAPTER).join()
    }
}