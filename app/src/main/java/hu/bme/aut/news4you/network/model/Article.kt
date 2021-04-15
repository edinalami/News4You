package hu.bme.aut.news4you.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Article {
    @SerializedName("section")
    var section: String = ""

    @SerializedName("subsection")
    var subsection: String = ""

    @SerializedName("title")
    var title: String = ""

    @SerializedName("abstract")
    var abstract: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("uri")
    var uri: String = ""

    @SerializedName("byline")
    var byline: String = ""

    @SerializedName("item_type")
    var itemType: String = ""

    @SerializedName("updated_date")
    var updatedDate: String = ""

    @SerializedName("created_date")
    var createdDate: String = ""

    @SerializedName("published_date")
    var publishedDate: String = ""

    @SerializedName("material_type_facet")
    var materialTypeFacet: String = ""

    @SerializedName("kicker")
    var kicker: String = ""

    @SerializedName("des_facet")
    var desFacet: List<String> = ArrayList()

    @SerializedName("org_facet")
    var orgFacet: List<String> = ArrayList()

    @SerializedName("per_facet")
    var perFacet: List<String> = ArrayList()

    @SerializedName("geo_facet")
    var geoFacet: List<String> = ArrayList()

    @SerializedName("multimedia")
    var multimedia: List<ArticleMultimedia> = ArrayList<ArticleMultimedia>()

    @SerializedName("short_url")
    var shortUrl: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val article = other as Article
        return section == article.section &&
                subsection == article.subsection &&
                title == article.title &&
                abstract == article.abstract &&
                url == article.url &&
                uri == article.uri &&
                byline == article.byline &&
                itemType == article.itemType &&
                updatedDate == article.updatedDate &&
                createdDate == article.createdDate &&
                publishedDate == article.publishedDate &&
                materialTypeFacet == article.materialTypeFacet &&
                kicker == article.kicker &&
                desFacet == article.desFacet &&
                orgFacet == article.orgFacet &&
                perFacet == article.perFacet &&
                geoFacet == article.geoFacet &&
                multimedia == article.multimedia &&
                shortUrl == article.shortUrl
    }

    override fun hashCode(): Int {
        return Objects.hash(
            section,
            subsection,
            title,
            abstract,
            url,
            uri,
            byline,
            itemType,
            updatedDate,
            createdDate,
            publishedDate,
            materialTypeFacet,
            kicker,
            desFacet,
            orgFacet,
            perFacet,
            geoFacet,
            multimedia,
            shortUrl
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Article {\n")
        sb.append("    section: ").append(toIndentedString(section)).append("\n")
        sb.append("    subsection: ").append(toIndentedString(subsection)).append("\n")
        sb.append("    title: ").append(toIndentedString(title)).append("\n")
        sb.append("    _abstract: ").append(toIndentedString(abstract)).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    uri: ").append(toIndentedString(uri)).append("\n")
        sb.append("    byline: ").append(toIndentedString(byline)).append("\n")
        sb.append("    itemType: ").append(toIndentedString(itemType)).append("\n")
        sb.append("    updatedDate: ").append(toIndentedString(updatedDate)).append("\n")
        sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n")
        sb.append("    publishedDate: ").append(toIndentedString(publishedDate)).append("\n")
        sb.append("    materialTypeFacet: ").append(toIndentedString(materialTypeFacet))
            .append("\n")
        sb.append("    kicker: ").append(toIndentedString(kicker)).append("\n")
        sb.append("    desFacet: ").append(toIndentedString(desFacet)).append("\n")
        sb.append("    orgFacet: ").append(toIndentedString(orgFacet)).append("\n")
        sb.append("    perFacet: ").append(toIndentedString(perFacet)).append("\n")
        sb.append("    geoFacet: ").append(toIndentedString(geoFacet)).append("\n")
        sb.append("    multimedia: ").append(toIndentedString(multimedia)).append("\n")
        sb.append("    shortUrl: ").append(toIndentedString(shortUrl)).append("\n")
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