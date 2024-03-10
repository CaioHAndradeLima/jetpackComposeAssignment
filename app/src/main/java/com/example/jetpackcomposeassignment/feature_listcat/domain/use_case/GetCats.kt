package com.example.jetpackcomposeassignment.feature_listcat.domain.use_caseimport com.example.jetpackcomposeassignment.Rimport com.example.jetpackcomposeassignment.feature_listcat.data.repository_remote.CatRepositoryimport com.example.jetpackcomposeassignment.feature_listcat.domain.repository.OrderTypeimport com.example.jetpackcomposeassignment.common.RequestResourceimport com.example.jetpackcomposeassignment.common.UiTextimport com.example.jetpackcomposeassignment.common.util.toErrorMessageimport com.example.jetpackcomposeassignment.feature_listcat.data.model.Catimport kotlinx.coroutines.flow.Flowimport kotlinx.coroutines.flow.flowimport java.io.IOExceptionimport retrofit2.HttpExceptionclass GetCats(    private val repository: CatRepository) {    operator fun invoke(        order: OrderType = OrderType.Descending    ): Flow<RequestResource<List<Cat>>> = flow {        try {            emit(RequestResource.Loading())            val cats = repository.getCats().map { it.copy() }            emit(RequestResource.Success(cats))        } catch(e: HttpException) {            emit(RequestResource.Error(e.toErrorMessage()))        } catch(e: IOException) {            emit(RequestResource.Error(UiText.Resource(R.string.check_your_internet_connection)))        }    }}