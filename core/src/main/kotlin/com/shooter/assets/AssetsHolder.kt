package com.shooter.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import kotlinx.coroutines.Deferred
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync

/**
 * Вся логика связанная с ассетами храниться тут.
 * todo - AssetHolder : uuid(name), group, ?scope, disposeAllAssets()
 */
class AssetsHolder {
    private val assetStore: AssetStorage by lazy {
        KtxAsync.initiate()
        println("init coroutunes")
        AssetStorage()
    }

    // todo - заменить на ovveride operators?
    fun <T> get(assetDefinition: AssetDefinition<T>): T {
        return assetStore[assetDefinition.descriptor]
    }

    // todo - заменить на ovveride operators?
    fun <T> load(assetDefinition: AssetDefinition<T>): Deferred<T> {
        return assetStore.loadAsync(assetDefinition.descriptor)
    }

    // todo - заменить на ovveride operators?
    fun <T> loadAll(assetDefinitions: List<AssetDefinition<T>>): List<Deferred<T>> {
        return assetDefinitions.map { assetStore.loadAsync(it.descriptor) }
    }

    fun <T> isLoaded(assetDefinition: AssetDefinition<T>): Boolean {
        return assetStore.isLoaded(assetDefinition.descriptor)
    }

}

abstract class AssetDefinition<T>(val path: String) {
    var descriptor: AssetDescriptor<T> = descriptorInit()

    abstract fun descriptorInit(): AssetDescriptor<T>
}

class TextureAssetDefinition(filePath: String) : AssetDefinition<Texture>(filePath) {
    override fun toString(): String {
        return descriptor.toString()
    }

    override fun descriptorInit(): AssetDescriptor<Texture> = AssetDescriptor(super.path, Texture::class.java)
}
