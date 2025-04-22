package com.juhyeon.kurly.shared.local.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductBookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setProductBookmark(productBookmarkEntity: ProductBookmarkEntity)

    @Query("SELECT * FROM `bookmark`")
    fun getProductBookmarkList(): Flow<List<ProductBookmarkEntity>>

    @Query("DELETE FROM `bookmark` WHERE id = :id")
    suspend fun deleteProductBookmark(id: Int)
}