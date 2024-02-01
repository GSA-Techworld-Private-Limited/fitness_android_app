package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName



data class UpcomingWorkshopResponse (

    @SerializedName("data" ) var data : ArrayList<UpcomingWorkshop> = arrayListOf()

)
data class UpcomingWorkshop(
    @SerializedName("workshop_id"         ) var workshopId       : String?        = null,
    @SerializedName("workshop_name"       ) var workshopName     : String?        = null,
    @SerializedName("workshop_cost"       ) var workshopCost     : String?        = null,
    @SerializedName("description"         ) var description      : String?        = null,
    @SerializedName("benefit"             ) var benefit          : String?        = null,
    @SerializedName("add_poster"          ) var addPoster        : String?        = null,
    @SerializedName("workshop_status"     ) var workshopStatus   : String?        = null,
    @SerializedName("created_at"          ) var createdAt        : String?        = null,
    @SerializedName("updated_at"          ) var updatedAt        : String?        = null,
    @SerializedName("no_of_workshop_days" ) var noOfWorkshopDays : String?        = null,
    @SerializedName("user"                ) var user             : ArrayList<Int> = arrayListOf()
)
