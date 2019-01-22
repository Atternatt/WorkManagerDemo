package com.m2f.app.data.network

import com.m2f.app.domain.BO


/**
 * Each Data transfer Object should implement this interface for identity purpose
 */
interface DTO {
    fun toBO(): BO
}