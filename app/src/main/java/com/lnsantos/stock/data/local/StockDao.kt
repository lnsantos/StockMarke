package com.lnsantos.stock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCompany(company: List<CompanyListingEntity>)

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyTable()

    @Query(
        """
            SELECT * FROM companylistingentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == symbol
        """
    )
    suspend fun searchCompany(query: String): List<CompanyListingEntity>

}