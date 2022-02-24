package com.shooter.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync

/**
 * Вся логика связанная с ассетами храниться тут.
 * todo - AssetHolder : uuid(name), ?group, ?scope, disposeAllAssets()
 */
class AssetsHolder : Disposable {
    private val assetStore: AssetStorage = run {
        KtxAsync.initiate()
        AssetStorage()
    }

    operator fun <T : Disposable> get(assetDefinition: AssetDefinition<T>): T = assetStore[assetDefinition.descriptor]


    fun load(vararg assetDefinitions: AssetDefinition<*>, afterLoad: () -> Unit = {}) {
        KtxAsync.launch {
            assetDefinitions.map { assetStore.loadAsync(it.descriptor) }
                .awaitAll().apply { afterLoad.invoke() }
        }
    }

    // todo - не нужно? O_o
    fun load(assetDefinitions: List<AssetDefinition<*>>, afterLoad: () -> Unit = {}) {
        KtxAsync.launch {
            assetDefinitions.map { assetStore.loadAsync(it.descriptor) }
                .awaitAll().apply { afterLoad.invoke() }
        }
    }

    // todo - не нужно? O_o
    fun load(assetDefinitions: Map<String, AssetDefinition<*>>, afterLoad: () -> Unit = {}) {
        KtxAsync.launch {
            assetDefinitions.map { assetStore.loadAsync(it.value.descriptor) }
                .awaitAll().apply { afterLoad.invoke() }
        }
    }

    // todo - не нужно? O_o
    fun <T : Disposable> isLoaded(assetDefinition: AssetDefinition<T>): Boolean {
        return assetStore.isLoaded(assetDefinition.descriptor)
    }

    override fun dispose() = assetStore.dispose()
}

abstract class AssetDefinition<T : Disposable>(val path: String) {
    var descriptor: AssetDescriptor<T> = descriptorInit()

    abstract fun descriptorInit(): AssetDescriptor<T>
    override fun toString(): String = descriptor.toString()
}

class TextureAssetDefinition(filePath: String) : AssetDefinition<Texture>(filePath) {
    override fun descriptorInit(): AssetDescriptor<Texture> = AssetDescriptor(super.path, Texture::class.java)
}

class FontAssetDefinition(filePath: String) : AssetDefinition<BitmapFont>(filePath) {
    override fun descriptorInit(): AssetDescriptor<BitmapFont> =
        AssetDescriptor(
            this.path, BitmapFont::class.java,
//            BitmapFontLoader.BitmapFontParameter().apply {
//                atlasName = TextureAtlasAsset.UI.descriptor.fileName
//            }
        )
}
