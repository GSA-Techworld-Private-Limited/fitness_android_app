package com.fitness.app.responses

data class GroupedPlanDays(
    val dayName: String,
    val tasks: List<PlanDays>,
)
