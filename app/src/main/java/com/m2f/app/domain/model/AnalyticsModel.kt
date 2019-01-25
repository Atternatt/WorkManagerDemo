package com.m2f.app.domain.model

import java.io.Serializable

data class AnalyticsModel(val key: String, val timestamp: Long, val attributes: Map<String, String>) : Serializable

data class AnalyticsPerformance(
    val timestamp: Long,
    val totalMemory: Long,
    val availableMemory: Long,
    val lowMemory: Boolean
) : Serializable
