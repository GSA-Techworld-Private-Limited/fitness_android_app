package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class WorkShopResponses(
    @SerializedName("ongoing_workshops"  ) var ongoingWorkshops  : ArrayList<OngoingWorkshops> = arrayListOf(),
    @SerializedName("upcoming_workshops" ) var upcomingWorkshops : ArrayList<UpcomingWorkShops>           = arrayListOf()
)


data class OngoingWorkshops (

    @SerializedName("workshop_id"         ) var workshopId       : String? = null,
    @SerializedName("add_poster"          ) var addPoster        : String? = null,
    @SerializedName("workshop_name"       ) var workshopName     : String? = null,
    @SerializedName("workshop_cost"       ) var workshopCost     : String? = null,
    @SerializedName("description"         ) var description      : String? = null,
    @SerializedName("benefit"             ) var benefit          : String? = null,
    @SerializedName("no_of_workshop_days" ) var noOfWorkshopDays : Int?    = null,
    @SerializedName("workshop_status"     ) var workshopStatus   : String? = null,
    @SerializedName("created_at"          ) var createdAt        : String? = null,
    @SerializedName("updated_at"          ) var updatedAt        : String? = null

)

data class UpcomingWorkShops(
    @SerializedName("workshop_id"         ) var workshopId       : String? = null
)