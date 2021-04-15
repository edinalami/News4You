package hu.bme.aut.news4you.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class User {
    @SerializedName("id")
    var id: Long = 0

    @SerializedName("username")
    var username: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val user = other as User
        return id == user.id &&
                username == user.username
    }

    override fun hashCode(): Int {
        return Objects.hash(id, username)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class User {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    username: ").append(toIndentedString(username)).append("\n")
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