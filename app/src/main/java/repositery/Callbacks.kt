package repositery

interface Callbacks {
    fun successData(userData : ArrayList<String>)
    fun failureCallback(errorString: String)
}