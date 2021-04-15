package hu.bme.aut.news4you.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class ArticleMultimedia {
    @SerializedName("copyright")
    var copyright: String = ""

    @SerializedName("subtype")
    var subtype: String = ""

    @SerializedName("format")
    var format: String = ""

    @SerializedName("width")
    var width: Int = 0

    @SerializedName("caption")
    var caption: String = ""

    @SerializedName("type")
    var type: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("height")
    var height: Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val articleMultimedia = other as ArticleMultimedia
        return copyright == articleMultimedia.copyright &&
                subtype == articleMultimedia.subtype &&
                format == articleMultimedia.format &&
                width == articleMultimedia.width &&
                caption == articleMultimedia.caption &&
                type == articleMultimedia.type &&
                url == articleMultimedia.url &&
                height == articleMultimedia.height
    }

    override fun hashCode(): Int {
        return Objects.hash(copyright, subtype, format, width, caption, type, url, height)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ArticleMultimedia {\n")
        sb.append("    copyright: ").append(toIndentedString(copyright)).append("\n")
        sb.append("    subtype: ").append(toIndentedString(subtype)).append("\n")
        sb.append("    format: ").append(toIndentedString(format)).append("\n")
        sb.append("    width: ").append(toIndentedString(width)).append("\n")
        sb.append("    caption: ").append(toIndentedString(caption)).append("\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    height: ").append(toIndentedString(height)).append("\n")
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