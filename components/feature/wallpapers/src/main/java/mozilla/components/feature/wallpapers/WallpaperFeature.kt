package mozilla.components.feature.wallpapers

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WallpaperFeature(
    val downloader: Downloader,
    // might have a drawables storage and a downloaded storage
    val storages: List<Storage>,
) {
    val wallpapers: List<Wallpaper> = storages.fold(listOf()) { combined, storage ->
        combined + storage.storedWallpapers
    }

    suspend fun download(wallpaper: Wallpaper) {

    }
}

interface Downloader {
    // should this be Flow? does that encapsulate well enough the process of fetching network?
    // maybe just suspend
    val availableWallpapers: Flow<List<Wallpaper>>
}

interface Storage {
    // This should be a Flow, but didn't want to deal with combining them in Feature yet
    val storedWallpapers: List<Wallpaper>
    interface Drawables {

    }
    interface Downloaded {

    }

    fun Wallpaper.loadBitmap(): Bitmap? {
        return null
    }
}

data class Wallpaper(
    val name: String,
    val downloaded: Boolean,
)