package com.fitness.app.responses

data class GroupedPlanDays(
    val taskDate: String,
    val tasks: List<PlanDays>,
)
