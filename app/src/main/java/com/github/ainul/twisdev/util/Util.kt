package com.github.ainul.twisdev.util

import java.text.DecimalFormat

object Util {

    /**
     * format Double data-type into a currency string
     * example: 20000.0 -> 2,000
     *
     * @param raw Boolean
     *  returns formatted string without currency-symbol like $, in this case "Rp."
     */
    @JvmStatic
    fun currencyFormatter(e: String, raw: Boolean = false): String {
        val formatted = DecimalFormat("###,###,###").format(e.toDouble())

        return if (raw) formatted else "Rp. $formatted"
    }
}