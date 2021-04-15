package hu.bme.aut.news4you.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class ReadingListResponse {
    @SerializedName("results")
    var results: List<Article> = ArrayList()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val readingListResponse = other as ReadingListResponse
        return results == readingListResponse.results
    }

    override fun hashCode(): Int {
        return Objects.hash(results)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ReadingListResponse {\n")
        sb.append("    results: ").append(toIndentedString(results)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}