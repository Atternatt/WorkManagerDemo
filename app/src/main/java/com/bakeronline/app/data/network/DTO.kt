package com.bakeronline.app.data.network

import com.bakeronline.app.domain.BO


interface DTO {
    fun toBO(): BO
}