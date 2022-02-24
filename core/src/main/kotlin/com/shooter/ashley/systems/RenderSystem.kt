package com.shooter.ashley.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.scenes.scene2d.Stage
import com.shooter.ashley.adapters.findComp
import com.shooter.ashley.components.GraphicComp
import com.shooter.ashley.components.TransformComp
import com.shooter.logger.loggerApp
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.graphics.use
import ktx.math.component1
import ktx.math.component2

/**
 * Component processor && Game Event Listener.
 * Rendering all [Entity] with components:
 * [GraphicComp]
 * [TransformComp]
 */
class RenderSystem(
    private val stage: Stage,
) : SortedIteratingSystem(
    allOf(GraphicComp::class, TransformComp::class).get(),
    compareBy { entity -> entity[TransformComp.mapper] }
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transformComp = entity.findComp(TransformComp.mapper)
        val graphicComp = entity.findComp(GraphicComp.mapper)

        if (graphicComp.sprite.texture == null) {
            logger.error { "Entity has no texture for rendering" }
            return
        }

        val (posX, posY) = transformComp.position
        val (sizeX, sizeY) = transformComp.size

        stage.batch.use(stage.camera) {
            graphicComp.sprite.run {
                // setBounds(...) == setPosition(...) && setSize(...)
                setBounds(posX, posY, sizeX, sizeY)
                draw(it)
            }
        }

    }


    /**
     * Called when this EntitySystem is added to an {@link Engine}.
     * @param engine The {@link Engine} this system was added to.
     */
    override fun addedToEngine(engine: Engine?) {
        super.addedToEngine(engine)
    }

    override fun removedFromEngine(engine: Engine?) {
        super.removedFromEngine(engine)
    }

    companion object {
        private val logger = loggerApp<RenderSystem>()
    }
}
