package com.shooter.ashley.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor
import java.util.*

class IdentifierComp : Component {
    var name: String = ""
    var tags: MutableList<String> = mutableListOf()
    var uuid: String = UUID.randomUUID().toString()

    companion object {
        val mapper = mapperFor<IdentifierComp>()
    }
}