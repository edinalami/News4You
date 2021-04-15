package hu.bme.aut.news4you.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class LatestNewsResponse {
    @SerializedName("copyright")
    var copyright: String = ""

    @SerializedName("last_updated")
    var lastUpdated: String = ""

    @SerializedName("section")
    var section: String = ""

    @SerializedName("results")
    var results: List<Article> = ArrayList()

    @SerializedName("num_results")
    var numResults: Int = 0

    @SerializedName("status")
    var status: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val newsResponse = other as LatestNewsResponse
        return copyright == newsResponse.copyright &&
                lastUpdated == newsResponse.lastUpdated &&
                section == newsResponse.section &&
                results == newsResponse.results &&
                numResults == newsResponse.numResults &&
                status == newsResponse.status
    }

    override fun hashCode(): Int {
        return Objects.hash(copyright, lastUpdated, section, results, numResults, status)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class NewsResponse {\n")
        sb.append("    copyright: ").append(toIndentedString(copyright)).append("\n")
        sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n")
        sb.append("    section: ").append(toIndentedString(section)).append("\n")
        sb.append("    results: ").append(toIndentedString(results)).append("\n")
        sb.append("    numResults: ").append(toIndentedString(numResults)).append("\n")
        sb.append("    status: ").append(toIndentedString(status)).append("\n")
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