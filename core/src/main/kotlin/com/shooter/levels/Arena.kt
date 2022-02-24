package com.shooter.levels

import com.badlogic.ashley.core.Engine
import com.shooter.Config
import com.shooter.ashley.components.GraphicComp
import com.shooter.ashley.components.IdentifierComp
import com.shooter.ashley.components.TransformComp
import com.shooter.assets.AssetsHolder
import com.shooter.assets.TextureAssetDefinition
import ktx.ashley.entity
import ktx.ashley.with

private val ARENA_BACKGROUND_ASSET = TextureAssetDefinition("loading_scree_stub.png")
private val PLAYER_CHARACTER = TextureAssetDefinition("game/player_01.png")

private val LEVEL_ASSETS = mapOf(
    Pair("background", ARENA_BACKGROUND_ASSET),
    Pair("player", PLAYER_CHARACTER),
)

class Arena : AbstractLevel() {

    override fun loadLevel(assetsHolder: AssetsHolder, engine: Engine) {
//        assetsHolder.load(LEVEL_ASSETS) {
        assetsHolder.load(ARENA_BACKGROUND_ASSET, PLAYER_CHARACTER) {
            setupBackground(assetsHolder, engine)
            setupPlayer(assetsHolder, engine)
            setupSystems(engine)
            isLoaded = true
        }
    }

    private fun setupBackground(assetsHolder: AssetsHolder, engine: Engine) {
        engine.run {
            entity {
                with<IdentifierComp> { name = "arena_background" }
                with<TransformComp> {
                    initThis(
                        width = Config.V_WORLD_WIDTH_UNITS, height = Config.V_WORLD_HEIGHT_UNITS, posZ = -11_111f
                    )
                }
                with<GraphicComp> { setSpriteRegion(assetsHolder[ARENA_BACKGROUND_ASSET]) }
            }
        }
    }

    private fun setupPlayer(assetsHolder: AssetsHolder, engine: Engine) {
        engine.run {
            entity {
                with<IdentifierComp> { name = "player" }
                with<TransformComp> {
                    initThis(width = 64f, height = 64f, posX = 20f, posY = 20f)
                }
                with<GraphicComp> { setSpriteRegion(assetsHolder[PLAYER_CHARACTER]) }
            }
        }
    }

    private fun setupSystems(engine: Engine) {

    }

}