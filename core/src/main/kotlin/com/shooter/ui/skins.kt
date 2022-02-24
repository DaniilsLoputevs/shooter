package com.shooter.ui

import com.shooter.assets.AssetsHolder
import com.shooter.assets.FontAssetDefinition
import ktx.scene2d.Scene2DSkin
import ktx.style.label
import ktx.style.skin

val FONT = FontAssetDefinition("fonts/Purisa_46_white.fnt")

const val WHITE_64_STYLE = "WHITE_64_STYLE"

fun initUIDefaultSkin(assetsHolder: AssetsHolder) {
    Scene2DSkin.defaultSkin = skin() {
        label(WHITE_64_STYLE) { font = assetsHolder[FONT] }
    }
}