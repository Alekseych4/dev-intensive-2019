package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    private val alphabet : Map<String, String> = mapOf("а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d",
        "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l",
        "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u",
        "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i",
        "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

    fun parseFullName(fullName : String?) : Pair<String?, String?>{
        if(fullName.isNullOrBlank()){
            return null to null
        }
        val parts : List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        //return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val parts : List<String> = payload.split(" ")
        val firstName = parts[0].chunked(1)
        val lastName = parts[1].chunked(1)
        var transFirstName : String = ""
        var transLastName : String = ""

        for (i in firstName){
            if (!alphabet[i].isNullOrBlank()){
                alphabet[i]?.let { transFirstName += it }
            } else if (!alphabet[i.toLowerCase()].isNullOrBlank()){
                alphabet[i.toLowerCase()]?.let { transFirstName += it.capitalize() }
            }else if (alphabet[i.toLowerCase()].isNullOrBlank()){
                transFirstName += i
            }
        }
        for (i in lastName){
            if (!alphabet[i].isNullOrBlank()){
                alphabet[i]?.let { transLastName += it }
            } else if (!alphabet[i.toLowerCase()].isNullOrBlank()){
                alphabet[i.toLowerCase()]?.let { transLastName += it.capitalize() }
            }else if (alphabet[i.toLowerCase()].isNullOrBlank()){
                transLastName += i
            }
        }

        return "$transFirstName$divider$transLastName"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        if(firstName.isNullOrBlank() && !lastName.isNullOrBlank()){
            return lastName.substring(0,1).toUpperCase()
        }else if(!firstName.isNullOrBlank() && lastName.isNullOrBlank()){
            return firstName.substring(0,1).toUpperCase()
        }else if(firstName.isNullOrBlank() && lastName.isNullOrBlank()){
            return null
        }
        return firstName?.substring(0,1)?.toUpperCase()+
                lastName?.substring(0,1)?.toUpperCase()

    }
}