package com.verifyme.app.utils

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}

class DefaultPaginator<Key, Item> (
    private val initKey: Key,
    private inline val onLoadUpdate: (Boolean)-> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey: Key = initKey
    private var isRequesting = false

    override suspend fun loadNextItems() {
        if(isRequesting) return
        isRequesting = true
        onLoadUpdate(true)
        val result = onRequest(currentKey)
        isRequesting = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdate(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdate(false)
    }

    override fun reset() {

    }


}