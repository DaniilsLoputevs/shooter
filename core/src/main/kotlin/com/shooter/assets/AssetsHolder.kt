package com.shooter.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync

/**
 * Вся логика связанная с ассетами храниться тут.
 * todo - AssetHolder : uuid(name), group, ?scope, disposeAllAssets()
 */
class AssetsHolder : Disposable {
    private val assetStore: AssetStorage = run {
        KtxAsync.initiate()
        AssetStorage()
    }

    // todo - не нужно? O_o
    fun <T> get(assetDefinition: AssetDefinition<T>): T {
        return assetStore[assetDefinition.descriptor]
    }

    // todo - не нужно? O_o
    fun <T> load(assetDefinition: AssetDefinition<T>): Deferred<T> {
        return assetStore.loadAsync(assetDefinition.descriptor)
    }

    // todo - не нужно? O_o
    fun <T> load(assetDefinitions: List<AssetDefinition<T>>): List<Deferred<T>> {
        return assetDefinitions.map { assetStore.loadAsync(it.descriptor) }
    }

    // todo - не нужно? O_o
    fun <T> load(vararg assetDefinitions: AssetDefinition<T>): List<Deferred<T>> {
        return assetDefinitions.map { assetStore.loadAsync(it.descriptor) }
    }

    // todo - не нужно? O_o
    fun <T> isLoaded(assetDefinition: AssetDefinition<T>): Boolean {
        return assetStore.isLoaded(assetDefinition.descriptor)
    }

    fun <T> load(vararg assetDefinitions: AssetDefinition<T>, afterLoad: () -> Unit = {}) {
        KtxAsync.launch {
            assetDefinitions.map { assetStore.loadAsync(it.descriptor) }
                .awaitAll().apply { afterLoad.invoke() }
        }
    }

    fun <T> load(assetDefinitions: List<AssetDefinition<T>>, afterLoad: () -> Unit = {}) {
        KtxAsync.launch {
            assetDefinitions.map { assetStore.loadAsync(it.descriptor) }
                .awaitAll().apply { afterLoad.invoke() }
        }
    }


    override fun dispose() {
        assetStore.dispose()
    }

}

abstract class AssetDefinition<T>(val path: String) {
    var descriptor: AssetDescriptor<T> = descriptorInit()

    abstract fun descriptorInit(): AssetDescriptor<T>
    override fun toString(): String = descriptor.toString()
}

class TextureAssetDefinition(filePath: String) : AssetDefinition<Texture>(filePath) {
    override fun descriptorInit(): AssetDescriptor<Texture> = AssetDescriptor(super.path, Texture::class.java)
}
