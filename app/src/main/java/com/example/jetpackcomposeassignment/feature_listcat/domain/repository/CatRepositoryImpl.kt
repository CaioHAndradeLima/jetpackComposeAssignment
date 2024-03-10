package com.example.jetpackcomposeassignment.feature_listcat.domain.repositoryimport com.example.jetpackcomposeassignment.feature_listcat.data.repository_remote.CatApiimport com.example.jetpackcomposeassignment.feature_listcat.data.model.Catimport com.example.jetpackcomposeassignment.feature_listcat.data.repository_remote.CatRepositoryimport javax.inject.Injectclass CatRepositoryImpl @Inject constructor(    private val api: CatApi) : CatRepository {    /**        Save information while app is running    */    companion object CacheLocal {        lateinit var listCat: List<Cat>    }    override suspend fun getCats(): List<Cat> {        return api.getCats().apply {            listCat = this.map {                it.copy(                    tags = it.tags?.filter { tag -> return@filter tag.isNotEmpty() }                )            }        }    }    override suspend fun getCat(id: String): Cat {        //I do not find way to get cat by id from remote Api        return listCat.first { it.id == id }    }}