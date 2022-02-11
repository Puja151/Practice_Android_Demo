package data

data class UsersList(
    val id : String,
    val name: String,
    val email: String,
    val address: UserAddress,
    val company: CompanyDetails
)

data class UserAddress(
    val city: String,
)

data class CompanyDetails(
    val name: String,
)

data class FetchError(
    val code:String
)

