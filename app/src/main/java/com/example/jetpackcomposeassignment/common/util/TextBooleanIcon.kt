package com.example.jetpackcomposeassignment.common.utilfun getBooleanIcon(condition: Boolean): String {    return when {        condition -> "✓"        else -> "✘"    }}