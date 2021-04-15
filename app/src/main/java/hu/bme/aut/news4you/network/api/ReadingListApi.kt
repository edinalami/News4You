package hu.bme.aut.news4you.network.api

import hu.bme.aut.news4you.network.model.Article
import hu.bme.aut.news4you.network.model.ReadingListResponse
import retrofit2.http.*

interface ReadingListApi {
    /**
     * Deletes the article from reading list
     *
     * @param articleUri URI of article that needs to be deleted
     * @return Call<Void>
    </Void> */
    @DELETE("readingList/deleteArticle/{articleUri}")
    suspend fun deleteFromReadingList(
        @Path("articleUri") articleUri: String
    )

    /**
     * Saves the article to reading list
     *
     * @param body Article object that needs to be added to the reading list
     * @return Call<Void>
    </Void> */
    @POST("readingList/saveArticle")
    suspend fun saveToReadingList(
        @Body body: Article
    )

    /**
     * Finds the news saved by the user
     *
     * @return Call<ReadingListResponse>
    </ReadingListResponse> */
    @GET("readingList/savedArticles")
    suspend fun getSavedArticles(): ReadingListResponse
}